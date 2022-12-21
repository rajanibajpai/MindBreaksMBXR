package com.impactqa.page.mobile;

import com.impactqa.utilities.PageObjectRepoHelper;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

public class FavoritesPage extends BasePage{

    private static final String PageObjectRepoFileName = "FavoritesScreenPage.xml";

    public FavoritesPage(AppiumDriver driver, PageObjectRepoHelper.PLATFORM platform) {
        super(driver, PageObjectRepoFileName, platform);
    }

    @Step("Select the Escape form list of Experiences")
    public void tapOnEscape()
    {
        appiumUtils.isElementDisplayed("Escape", 20);
        appiumUtils.click("Escape");
    }

    @Step("Is Lakeside Sunset audio card display")
    public boolean isAudioCardDisplayed()
    {
        if(appiumUtils.isElementDisplayed("LakesideSunset", 20)){
            return true;
        }else {
            appiumUtils.scrollDown(40, 50);
            return appiumUtils.isElementDisplayed("LakesideSunset", 20);
        }
    }

    @Step("Tab on Lakeside Sunset heart icon to make it favorites")
    public void tapOnAudioCardFavoritesIcon()
    {
        appiumUtils.click("LakesideSunsetHeartIcon");
        appiumUtils.sleepForMiliseconds(2000);
    }

    @Step("Tab on favorites")
    public void tapOnFavorites()
    {
        appiumUtils.isElementDisplayed("Favorites", 15);
        appiumUtils.click("Favorites");
    }
}
