package com.impactqa.page.mobile;

import com.impactqa.utilities.PageObjectRepoHelper;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.util.List;

public class ExperiencesSubMenuPage extends BasePage{

    private static final String PageObjectRepoFileName = "ExperiencesSubMenuPage.xml";

    public ExperiencesSubMenuPage(AppiumDriver driver, PageObjectRepoHelper.PLATFORM platform) {
        super(driver, PageObjectRepoFileName, platform);
    }

    @Step("is you Experiences sub-Menu Escape Displayed")
    public boolean isExperiencesSubMenuEscapeDisplayed()
    {
        return appiumUtils.isElementDisplayed("escape", 30);
    }

    @Step("is you Experiences sub-Menu Meditate Displayed")
    public boolean isExperiencesSubMenuMeditateDisplayed()
    {
        appiumUtils.click("meditate");
        return appiumUtils.isElementDisplayed("meditate", 30);
    }

    @Step("is you Experiences sub-Menu Energize Displayed")
    public boolean isExperiencesSubMenuEnergizeDisplayed()
    {
        appiumUtils.click("energize");
        return appiumUtils.isElementDisplayed("energize", 30);
    }

    @Step("is you Experiences sub-Menu Rest Displayed")
    public boolean isExperiencesSubMenuRestDisplayed()
    {
        appiumUtils.swipeRightToLeft("scrollMenu");
        appiumUtils.click("rest");
        return appiumUtils.isElementDisplayed("rest", 30);
    }

    @Step("is you Experiences sub-Menu Focus Displayed")
    public boolean isExperiencesSubMenuFocusDisplayed()
    {
//        appiumUtils.swipeRightToLeft("scrollMenu");
        appiumUtils.click("focus");
        return appiumUtils.isElementDisplayed("focus", 30);
    }

    @Step("is you Experiences sub-Menu Inspire Displayed")
    public boolean isExperiencesSubMenuInspireDisplayed()
    {
        appiumUtils.swipeRightToLeft("scrollMenu");
        appiumUtils.click("inspire");
        return appiumUtils.isElementDisplayed("inspire", 30);
    }


}
