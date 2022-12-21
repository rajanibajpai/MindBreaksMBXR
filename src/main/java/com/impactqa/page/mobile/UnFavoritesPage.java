package com.impactqa.page.mobile;

import com.impactqa.utilities.PageObjectRepoHelper;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

public class UnFavoritesPage extends BasePage{

    private static final String PageObjectRepoFileName = "UnFavoritesScreenPage.xml";

    public UnFavoritesPage(AppiumDriver driver, PageObjectRepoHelper.PLATFORM platform) {
        super(driver, PageObjectRepoFileName, platform);
    }

    @Step("Tab on favorites")
    public void tapOnFavorites()
    {
        appiumUtils.click("Favorites");
    }

    @Step("Tab on Lakeside Sunset heart icon to make it favorites")
    public void tapOnAudioCardFavoritesIcon()
    {
        appiumUtils.click("LakesideSunsetHeartIcon");
    }

    @Step("Is audio card displayed")
    public Boolean isAudioCardDisplayed()
    {
        return appiumUtils.isElementDisplayed("LakesideSunsetHeartIcon", 20);
    }

    @Step("Tab on Library")
    public void tapOnLibrary()
    {
        appiumUtils.click("Library");
    }
}
