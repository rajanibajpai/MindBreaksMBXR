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
public class TC007_MB_Experiences_Meditate_test extends BaseTestMobile {

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

    @Test(priority = 4, description = "verify Experiences Audio Card", dependsOnMethods = "login")
    @Story("verify Experiences Meditate audio Card ")
    @Description("verify Experiences Audio Card")
    public void verifyExperiencesMeditateAudioCards() throws InterruptedException {
        ExperiencesEscapePage experiencesEscapePage = new ExperiencesEscapePage(driver, platform);
        ExperiencesMeditatePage experiencesMeditatePage = new ExperiencesMeditatePage(driver, platform);
        experiencesEscapePage.tapOnExperiences();
        experiencesMeditatePage.tapOnMeditate();
        experiencesMeditatePage.selectRandomTracksFromMeditateAndPlay();
        if(experiencesEscapePage.isMusicPlayerDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");
        }

    }

    @Test(priority = 5, description = "Logout", dependsOnMethods = "verifyExperiencesMeditateAudioCards")
    @Story("Logout")
    @Description("Verify Logout")
    public void logout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, platform);
        BeginScreenPage beginScreenPage = new BeginScreenPage(driver, platform);
        ExperiencesEscapePage experiencesEscapePage = new ExperiencesEscapePage(driver, platform);
        experiencesEscapePage.tapOnMinimizePlayer();
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
