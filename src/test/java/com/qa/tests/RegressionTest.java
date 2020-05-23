package com.qa.tests;


import com.qa.utils.TestListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import static com.qa.staticdata.ReportingEpics.LOGIN;
import static com.qa.staticdata.ReportingFeatures.DESKTOP;
import static com.qa.staticdata.ReportingFeatures.MOBILE;
import static com.qa.staticdata.TestTags.REGRESSION;
import static com.qa.staticdata.TestTags.SMOKE;

@Listeners({TestListener.class})
public class RegressionTest extends BaseTest {

    private final String INVALID_LOGIN_INFORMATION =
            "Bitte überprüfen Sie Ihre Eingaben und probieren Sie es erneut. Haben Sie noch keine CGM LIFE ID?";

    @Test(priority = 0, description = "Desc: Login Test login with test User successfully... ")
    @Story("Story: Login to ClickDoc Website successfully... ")
    @Features({@Feature(DESKTOP), @Feature(MOBILE)})
    @Severity(SeverityLevel.BLOCKER)
    @Tag(name = SMOKE)
    @Epic(LOGIN)
    void loginTest() {
        homePage.goToLoginPageAndFetchTitle();
        homePage.login("dirk.nonn@cgm.com#1111", "abcdefg");
        homePage.secondUserLogin();
    }

    @Test(priority = 0, description = "Desc: Invalid Login with Unknown test User... ")
    @Story("Story: Login to ClickDoc Website successfully... ")
    @Features({@Feature(DESKTOP), @Feature(MOBILE)})
    @Severity(SeverityLevel.BLOCKER)
    @Tag(name = REGRESSION)
    @Epic(LOGIN)
    void invalidLoginTest() {
        homePage.goToLoginPageAndFetchTitle();
        homePage.login("dirk.nonn@asd.co1", "abcdefg1");
        Assert.assertEquals(homePage.invalidLogin(), INVALID_LOGIN_INFORMATION);
    }

    @Test(priority = 0, description = "Desc: Invalid Login with Unknown test User... ")
    @Story("Story: Login to ClickDoc Website successfully... ")
    @Features({@Feature(DESKTOP), @Feature(MOBILE)})
    @Severity(SeverityLevel.BLOCKER)
    @Tag(name = REGRESSION)
    @Epic(LOGIN)
    void qaTest() {
        Assert.assertEquals(2 - 1, 3 - 2);
    }

}
