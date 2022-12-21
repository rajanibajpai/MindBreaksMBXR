package com.impactqa.page.mobile;

import com.impactqa.base.BaseTestMobile;
import com.impactqa.utilities.PageObjectRepoHelper;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static java.util.Map.*;

public class CollectionsPage extends BasePage{

    private static final String PageObjectRepoFileName = "CollectionsScreenPage.xml";

    public CollectionsPage(AppiumDriver driver, PageObjectRepoHelper.PLATFORM platform) {
        super(driver, PageObjectRepoFileName, platform);
    }

    @Step("Tab on CollectionsPage")
    public void tapOnCollections()
    {
        appiumUtils.isElementDisplayed("collections", 20);
        appiumUtils.click("collections");
    }

    @Step("Get the actualCount of audio cards in each Collections menu and match with number audio cards in each Collections menu. ")
    public void verifyEachMenuCards() throws InterruptedException {
        List<String> allCardMenu = appiumUtils.getTextOfListElements("allCardMenu");
        for(int i=0; i < allCardMenu.size(); i++){
            System.out.println( allCardMenu.get(i) );
            Integer expectCount = Integer.parseInt(allCardMenu.get(i).replaceAll("[^0-9]", ""));
            appiumUtils.click("cardMenu","cardMenu", of("$$cardMenu$$", allCardMenu.get(i)));
            Integer actualCount = countOfAudioCards();
            Thread.sleep(1000);
            if (BaseTestMobile.platform== PageObjectRepoHelper.PLATFORM.IOS){
                appiumUtils.clickAtCoordinates(12,64,"back");
            }else{
                appiumUtils.pressBackButton();
            }
            appiumUtils.scrollDown(30,50);
            if(expectCount == actualCount){
                Allure.step("The number of tracks listed on the "+allCardMenu.get(i)+" menu tab "+expectCount+" matches with the number of tracks listed on the sub-menu for that collection which is " +actualCount);
            }else {
                Allure.step("The number of tracks listed on the "+allCardMenu.get(i)+" menu tab "+expectCount+" does not matched with the number of tracks listed on the sub-menu for that collection which is " +actualCount);
            }
        }

    }

    @Step("Get the number of all the respective audio cards")
    public int countOfAudioCards(){
        appiumUtils.isElementDisplayed("allAudioCards",30);
        appiumUtils.scrollDown(50,50);
        Allure.step("Total number of audio card in Power Naps sub-menu is : " + appiumUtils.getTextOfListElements("allAudioCards").size());
        return appiumUtils.getTextOfListElements("allAudioCards").size();
    }

}
