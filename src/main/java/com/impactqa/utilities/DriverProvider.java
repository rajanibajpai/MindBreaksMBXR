package com.impactqa.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 *
 * @version 1.0
 * @description This class creates Appium Driver session
 * @since 2021-03-20
 */

public class DriverProvider {

    /**
     * This method create AppiumDriver for Local and Cloud devices
     * <p>
     * Takes value of <b>RunTestInMobileCloud=(true/false) from config.properties </b>
     * <p>
     * if RunTestInMobileCloud=true
     * <i> it return browser session for Cloud devices</i>
     * <p>
     * if RunTestInMobileCloud=false
     * <i> it return browser session for local devices</i>
     *
     * @param platform       - Type of platform (IOS/Android/Web)
     * @param sessionDetails - Session/Capabilities Details
     */
    @Step("Mobile Session")
    public static AppiumDriver createNewMobileSession(PageObjectRepoHelper.PLATFORM platform, Map<String, String> sessionDetails) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        for (String key : sessionDetails.keySet()) {
            if (sessionDetails.get(key) != null && !sessionDetails.get(key).trim().isEmpty()) {

                if (key.equals("appFileName")) {
                    String appFileName = sessionDetails.get(key);
                    String appPath = null;
                    if (appFileName.contains("/") || appFileName.contains("\\") || appFileName.equals("settings") || appFileName.equals("com.android.settings") || appFileName.startsWith("PRIVATE:"))
                        appPath = appFileName;
                    else
                        appPath = new File(FrameworkConfig.getStringConfigProperty("MobileApppath") + "/" + appFileName).getAbsolutePath();
                    desiredCapabilities.setCapability("app", appPath);

                } else
                    desiredCapabilities.setCapability(key, sessionDetails.get(key).trim());
            }
        }
        URL remoteUrl = null;

        if (FrameworkConfig.getStringConfigProperty("RunTestInMobileCloud").equals("true")) {
            remoteUrl = new URL("https://" + FrameworkConfig.getStringConfigProperty("remoteCloudMobileDriverHubHost") + "/wd/hub");
            desiredCapabilities.setCapability("securityToken", FrameworkConfig.getStringConfigProperty("MobileCloudSecurityKey"));
        } else {
            remoteUrl = new URL("http://" + FrameworkConfig.getStringConfigProperty("remoteMobileDriverHubHost") + "/wd/hub");
        }
        AppiumDriver driver = null;
        if (platform == PageObjectRepoHelper.PLATFORM.ANDROID) {
            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        } else if (platform == PageObjectRepoHelper.PLATFORM.IOS) {
            driver = new IOSDriver(remoteUrl, desiredCapabilities);
        }
        Allure.step("Capabilities: " + driver.getCapabilities());
        return driver;
    }

}
