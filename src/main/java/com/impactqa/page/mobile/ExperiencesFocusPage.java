package com.impactqa.page.mobile;

import com.impactqa.utilities.CustomMap;
import com.impactqa.utilities.PageObjectRepoHelper;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.util.List;

public class ExperiencesFocusPage extends BasePage{

    private static final String PageObjectRepoFileName = "ExperiencesScreenPage.xml";

    public ExperiencesFocusPage(AppiumDriver driver, PageObjectRepoHelper.PLATFORM platform) {
        super(driver, PageObjectRepoFileName, platform);
    }

    @Step("Select the Focus form list of Experiences")
    public void tapOnFocus()
    {
        if(!appiumUtils.isElementDisplayed("Focus", 20)){
            appiumUtils.scrollDown(35, 50);
    }
        appiumUtils.click("Focus");
    }

    @Step("Play any random track form Focus tracks list")
    public void selectRandomTracksFromFocusAndPlay() throws InterruptedException {
        List<String> allTracks = appiumUtils.getTextOfListElements("allTracks");
        for (int i = 0; i < allTracks.size(); i++) {
            Allure.step("Focus tracks list : " + allTracks);
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

}
