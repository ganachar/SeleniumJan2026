package org.qa.opencart.tests;

import io.qameta.allure.*;
import org.qa.opencart.base.BaseTest;
import org.qa.opencart.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Epic - 100 : Epic for login Page of Open Cart application")
@Story("Login Page - 101 : Design Login Page with various features")
public class LoginPageTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginPageTest.class);

    @Description("Login Page Title Test..")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 1)
    public void loginPageTitleTest() {
        String actualTitle = loginPage.getLoginPageTitle();
        System.out.println("Actual Page title for LoginPage : " + actualTitle);
        Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
    }

    @Description("Login Page URL Test...")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void loginPageURLTest() {
        String actualURL = loginPage.getLoginPageURrl();
        System.out.println("Actual Page URL for LoginPage : " + actualURL);
        Assert.assertTrue(actualURL.contains(Constants.LOGIN_PAGE_URL));
    }

    @Description("Fogot password Link Test...")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void forgetPWDLinkTest() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @Description("Register Link Test...")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void registerLinkTest() {
        Assert.assertTrue(loginPage.isregisterLinkExist());
    }

    @Description("User Login Test ...")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 5)
    public void loginTest() {
        Assert.assertTrue(loginPage
                .doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim())
                .isLogoutLinkExists());

    }
}
