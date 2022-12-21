package com.impactqa.test.mobile;

import com.impactqa.base.BaseTestMobile;
import com.impactqa.listeners.TestAllureListener;
import com.impactqa.page.mobile.*;
import com.impactqa.utilities.ExcelUtil;
import com.impactqa.utilities.FrameworkConfig;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("Test Android")
@Feature("Test Android Execution")
@Listeners({TestAllureListener.class})
public class TC013_MB_Ui_Icon_Rail_Functionality_test extends BaseTestMobile {

    @BeforeClass
    @Parameters({"dataID"})
    @Description("Read test data with testID {0}")
    public void getTestData(String dataID) {
        ExcelUtil excel = new ExcelUtil();
        excel.setWorkbook(FrameworkConfig.getStringConfigProperty("TestDataFileLocation"),
                FrameworkConfig.getStringConfigProperty("TestDataSheetName_mobile"));

        testDataMap = excel.getRowDataMatchingDataId(dataID);
        if (testDataMap.size() < 1)
            Assert.fail("dataID '" + dataID + "' is valid the excel sheet. please check the test data sheet");
    }

    @Test(priority = 1, description = "Launch APP")
    @Story("Launch APP")
    @Description("Verify APP Launch")
    public void verifyBeginScreen() {
        BeginScreenPage beginScreenPage = new BeginScreenPage(driver, platform);
        if(beginScreenPage.isBeginScreenDisplayed()){
            Allure.step("Pass");
            beginScreenPage.tapOnBegin();
        }else{
            Allure.step("Fail");
            Assert.fail("Fail");

        }
    }

    @Test(priority = 2, description = "Register Screen", dependsOnMethods = "verifyBeginScreen")
    @Story("Register Screen")
    @Description("Verify Register Screen")
    public void verifyRegisterScreen() {
        BeginScreenPage beginScreenPage = new BeginScreenPage(driver, platform);
        if(beginScreenPage.isRegisterScreenDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");

        }
    }

    @Test(priority = 3, description = "Login", dependsOnMethods = "verifyBeginScreen")
    @Story("Login")
    @Description("Verify Login")
    public void login() {
        LoginPage loginPage = new LoginPage(driver, platform);
        loginPage.tapOnAlreadyMemberForLogin();
        if(loginPage.isLoginScreenDisplayed()){
            Allure.step("Pass");
            loginPage.enterEmail(testDataMap.get("Email"));
            loginPage.enterPassword(testDataMap.get("Password"));
            loginPage.tapOnLogin();
            if(FrameworkConfig.getStringConfigProperty("ApplicationEnvironment").equals("Dev")){
                loginPage.tapOnMindBreaksDev();
            }
            loginPage.tapOnSelect();
            loginPage.isExperiencesDisplay();
        }else{
            Allure.step("Fail");

        }
    }

    @Test(priority = 4, description = "verify Library page open with Experiences", dependsOnMethods = "login")
    @Story("verify Library page with Experiences and having menu")
    @Description("verify Library page open with Experiences")
    public void verifyLibraryWithExperiencesPage() {
        UiIconRailFunctionality uiIconRailFunctionality = new UiIconRailFunctionality(driver, platform);
        uiIconRailFunctionality.tapOnLibrary();
        if(uiIconRailFunctionality.isLibraryExperiencesDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");
        }
    }

    @Test(priority = 5, description = "verify Library page open with Collections", dependsOnMethods = "verifyLibraryWithExperiencesPage")
    @Story("verify Library page with Collections and having menu")
    @Description("verify Library page open with Collections")
    public void verifyLibraryWithCollectionsPage() {
        UiIconRailFunctionality uiIconRailFunctionality = new UiIconRailFunctionality(driver, platform);
        if(uiIconRailFunctionality.isLibraryCollectionsDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");
        }
    }

    @Test(priority = 6, description = "verify Library page open with Filter", dependsOnMethods = "verifyLibraryWithCollectionsPage")
    @Story("verify Library page with Filter and having options")
    @Description("verify Library page open with Filter")
    public void verifyLibraryWithFilterPage() {
        UiIconRailFunctionality uiIconRailFunctionality = new UiIconRailFunctionality(driver, platform);
        if(uiIconRailFunctionality.isLibraryFilterDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");
        }
    }

    @Test(priority = 7, description = "verify Favorites icon and respective Audio Tracks", dependsOnMethods = "verifyLibraryWithFilterPage")
    @Story("verify Favorites icon and respective Audio Tracks")
    @Description("verify Favorites icon and respective Audio Tracks")
    public void verifyFavoritesIconWithTracks(){
        UiIconRailFunctionality uiIconRailFunctionality = new UiIconRailFunctionality(driver, platform);
        FavoritesPage favoritesPage = new FavoritesPage(driver, platform);
        uiIconRailFunctionality.back();
        favoritesPage.tapOnFavorites();
        if(uiIconRailFunctionality.verifyFavoritesPage()){
            uiIconRailFunctionality.nameOfFavoritesTracksAndTotalCount();
            Allure.step("Pass");
        }else{
            Allure.step("Fail");
        }
    }

    @Test(priority = 8, description = "verify you page stats tabs with respective information", dependsOnMethods = "verifyFavoritesIconWithTracks")
    @Story("verify you page with stats tab")
    @Description("verify you page stats tabs with respective information")
    public void verifyYouStats(){
        UiIconRailFunctionality uiIconRailFunctionality = new UiIconRailFunctionality(driver, platform);
        uiIconRailFunctionality.tapOnYou();
        if(uiIconRailFunctionality.isYouStatsDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");
        }
    }

    @Test(priority = 9, description = "verify you page Achievements tabs with respective information", dependsOnMethods = "verifyFavoritesIconWithTracks")
    @Story("verify you page with Achievements tab")
    @Description("verify you page Achievements tabs with respective information")
    public void verifyYouAchievements(){
        UiIconRailFunctionality uiIconRailFunctionality = new UiIconRailFunctionality(driver, platform);
        if(uiIconRailFunctionality.isYouAchievementsDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");
        }
    }

    @Test(priority = 10, description = "Logout", dependsOnMethods = "verifyYouAchievements")
    @Story("Logout")
    @Description("Verify Logout")
    public void logout() {
        LoginPage loginPage = new LoginPage(driver, platform);
        BeginScreenPage beginScreenPage = new BeginScreenPage(driver, platform);
        loginPage.tapOnMore();
        if(FrameworkConfig.getStringConfigProperty("ApplicationEnvironment").equals("Prod")){
            loginPage.getVersion();
        }
        loginPage.tapOnLogout();
        if(beginScreenPage.isBeginScreenDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");
            Assert.fail("Fail");

        }
    }

}
