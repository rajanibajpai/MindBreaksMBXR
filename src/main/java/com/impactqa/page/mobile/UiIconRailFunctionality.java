package com.impactqa.page.mobile;

import com.impactqa.utilities.PageObjectRepoHelper;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.util.List;

public class UiIconRailFunctionality extends BasePage{

    private static final String PageObjectRepoFileName = "UiIconRailFunctionalityPage.xml";

    public UiIconRailFunctionality(AppiumDriver driver, PageObjectRepoHelper.PLATFORM platform) {
        super(driver, PageObjectRepoFileName, platform);
    }

    @Step("Tab on favorites")
    public void tapOnFavorites()
    {
        appiumUtils.isElementDisplayed("Favorites", 15);
        appiumUtils.click("Favorites");
    }

    @Step("Verify favorites page open successfully")
    public boolean verifyFavoritesPage(){
        return appiumUtils.isElementDisplayed("FavoritesPage",20);
    }

    @Step("Verify featured page open successfully")
    public boolean verifyFeaturedPage(){
        return appiumUtils.isElementDisplayed("FeaturedPage",20);
    }

    @Step("Tab on featured")
    public void tapOnFeatured()
    {
        appiumUtils.isElementDisplayed("Featured", 15);
        appiumUtils.click("Featured");
    }

    @Step("Name of favorites tracks and total count")
    public void nameOfFavoritesTracksAndTotalCount()
    {
        int numberOfFavoritesTracks = appiumUtils.getNumberOfElements("AllFavoriteTracks");
        List<String> favoritesTracks = appiumUtils.getTextOfListElements("Tracks");
        Allure.step("Total number of favorites tracks " + numberOfFavoritesTracks + " and there names are " + favoritesTracks);
    }

    @Step("Verify recently played track")
    public void verifyRecentlyPlayedTrack() throws InterruptedException {
        String firstReleaseTrack = appiumUtils.getAttribute("firstNewRelease", "name");
        System.out.println("firstReleaseTrack : " + firstReleaseTrack);
        appiumUtils.click("firstNewRelease");
        Thread.sleep(2000);
        appiumUtils.click("minimizePlayer");
        appiumUtils.click("Favorites");
        appiumUtils.click("Featured");
        String recentlyPlayedTrack = appiumUtils.getAttribute("firstRecentlyPlayed", "name");
        System.out.println("recentlyPlayedTrack : " + recentlyPlayedTrack);
        if(firstReleaseTrack.equals(recentlyPlayedTrack)){
            Allure.step("Recently play track is " + firstReleaseTrack + " and recently played track was " + recentlyPlayedTrack );
        }else {
            Allure.step("Recently play track " + firstReleaseTrack + " was not matched with " + recentlyPlayedTrack );
        }
    }

    @Step("Close the minimise music player")
    public void closePlayer(){
        appiumUtils.isElementDisplayed("player", 15);
        appiumUtils.dragAndDrop("player", "Featured");
    }

    @Step("Tab on Library")
    public void tapOnLibrary()
    {
        appiumUtils.isElementDisplayed("Library", 15);
        appiumUtils.click("Library");
    }

    @Step("is Library Experiences Displayed")
    public boolean isLibraryExperiencesDisplayed()
    {
        return appiumUtils.isElementDisplayed("LibraryExperiences", 15);
    }

    @Step("is Library Collections Displayed")
    public boolean isLibraryCollectionsDisplayed()
    {
        appiumUtils.click("LibraryCollections");
        return appiumUtils.isElementDisplayed("LibraryCollections", 15);
    }

    @Step("is Library Filter Displayed")
    public boolean isLibraryFilterDisplayed()
    {
        appiumUtils.click("LibraryFilter");
        return appiumUtils.isElementDisplayed("LibraryFilter", 15);
    }

    @Step("is you stats Displayed")
    public boolean isYouStatsDisplayed()
    {
        appiumUtils.click("Stats");
        return appiumUtils.isElementDisplayed("Stats", 15);
    }

    @Step("is you Achievements Displayed")
    public boolean isYouAchievementsDisplayed()
    {
        appiumUtils.click("Achievements");
        return appiumUtils.isElementDisplayed("Achievements", 15);
    }

    @Step("Tab on You")
    public void tapOnYou()
    {
        appiumUtils.isElementDisplayed("You", 15);
        appiumUtils.click("You");
    }

    @Step("Navigate back")
    public void back(){
        appiumUtils.clickAtCoordinates(12,64,"back");
    }
}
