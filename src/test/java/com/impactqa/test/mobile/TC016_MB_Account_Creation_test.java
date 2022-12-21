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
public class TC016_MB_Account_Creation_test extends BaseTestMobile {

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
        beginScreenPage.tapOnBegin();
        if(beginScreenPage.isRegisterScreenDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");

        }
    }
    @Test(priority = 2, description = "Account Creation Screen", dependsOnMethods = "verifyRegisterScreen")
    @Story("Account Creation Screen")
    @Description("Verify Account Creation Screen")
    public void registerUser() {
        AccountCreation accountCreation = new AccountCreation(driver, platform);
        accountCreation.enterEmail();
        accountCreation.reEnterEmail();
        accountCreation.tapOnRegister();
        if(accountCreation.isEnterYourCodeDisplayed()){
            Allure.step("Pass");
        }else{
            Allure.step("Fail");

        }
    }

}
