package com.impactqa.test.mobile;

import com.impactqa.base.BaseTestMobile;
import com.impactqa.listeners.TestAllureListener;
import com.impactqa.page.mobile.BeginScreenPage;
import com.impactqa.page.mobile.FavoritesPage;
import com.impactqa.page.mobile.LoginPage;
import com.impactqa.page.mobile.UnFavoritesPage;
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
public class TC004_MB_UnFavorites_test extends BaseTestMobile {

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


    @Test(priority = 4, description = " Un-favorites the audio card", dependsOnMethods = "login")
    @Story("Verify Un-Favorites")
    @Description("verify favorites the audio card")
    public void verifyUnFavoriteAudioCard() {
        UnFavoritesPage unFavoritesPage = new UnFavoritesPage(driver, platform);
        unFavoritesPage.tapOnFavorites();
        unFavoritesPage.tapOnAudioCardFavoritesIcon();
        unFavoritesPage.tapOnLibrary();
        unFavoritesPage.tapOnFavorites();
        if(!unFavoritesPage.isAudioCardDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");
        }
    }

    @Test(priority = 5, description = "Logout", dependsOnMethods = "verifyUnFavoriteAudioCard")
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
