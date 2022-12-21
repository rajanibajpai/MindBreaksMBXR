package com.impactqa.page.mobile;

import com.impactqa.utilities.CustomMap;
import com.impactqa.utilities.PageObjectRepoHelper;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.xmlbeans.impl.xb.xsdschema.All;

import java.util.List;

public class ExperiencesEscapePage extends BasePage{

    private static final String PageObjectRepoFileName = "ExperiencesScreenPage.xml";

    public ExperiencesEscapePage(AppiumDriver driver, PageObjectRepoHelper.PLATFORM platform) {
        super(driver, PageObjectRepoFileName, platform);
    }

    @Step("Tap on the Experiences")
    public void tapOnExperiences()
    {
        appiumUtils.isElementDisplayed("Experiences", 20);
        appiumUtils.click("Experiences");
    }

    @Step("Play any random track form Escape tracks list")
    public void selectRandomTracksFromEscapeAndPlay() throws InterruptedException {
        List<String> allTracks = appiumUtils.getTextOfListElements("allTracks");
        for (int i = 0; i < allTracks.size(); i++) {
            Allure.step("Escape tracks list : " + allTracks);
            int index = (int)(Math.random() * allTracks.size());
            Allure.step("Random track is : " + allTracks.get(index));
            if (!appiumUtils.isElementDisplayed("tracks", 30, "tracks", CustomMap.of("$$tracks$$", allTracks.get(index)))) {
                appiumUtils.scrollDown(40, 50);
            }
            appiumUtils.click("tracks", "tracks", CustomMap.of("$$tracks$$", allTracks.get(index)));
            Thread.sleep(3000);
            break;
        }
    }

    @Step("Is Rate this track displayed")
    public boolean isRateThisTrackDisplayed() {
        return appiumUtils.isElementDisplayed("RateTrack",10);
    }

    @Step("Tap four stars")
    public  void tapOnFourStars(){
        appiumUtils.click("RateAsFour");
    }

    @Step("Is music player display")
    public boolean isMusicPlayerDisplayed(){
        return appiumUtils.isElementDisplayed("minimizePlayer",30);
    }

    @Step("Tap minimize button")
    public  void tapOnMinimizePlayer() throws InterruptedException {
        Thread.sleep(2000);
        appiumUtils.click("minimizePlayer");
    }

    @Step("Tap on Show details")
    public  void tapOnShowDetails(){appiumUtils.click("ShowDetails");}

    @Step("Tap on 25 minute duration")
    public  void tapOnMint(){appiumUtils.click("selectDuration");}

    @Step("Tab on Done")
    public  void tapOnDone(){appiumUtils.click("Done");}

}
