package org.qa.opencart.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.opencart.constants.Constants;
import org.qa.opencart.utils.ElementUtil;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    private By emaild = By.id("input-email");
    private By password = By.id("input-password");
    private By LoginBtn = By.xpath("//input[@value='Login']");
    private By forgotPWDLink = By.linkText("Forgotten Password");
    private By registerLink = By.linkText("Register");
    private By LogoutSuccessMsg = By.cssSelector("div#common-success h1");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    @Step("Getting Login Page Title for Open Cart Application")
    public String getLoginPageTitle() {
        return eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
    }

    @Step("Getting Login Page URL for Open Cart Application")
    public String getLoginPageURrl() {
        return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL, Constants.DEFAULT_TIME_OUT);
    }

    @Step("Peforming User Login with username: {0} & password: {1} for Open Cart Application")
    public AccountsPage doLogin(String username, String passwd) {
        System.out.println("Usename : " + username + " & Password : " + passwd);
        //driver.findElement(emaild).sendKeys(username);
        eleUtil.waitForElementVisible(emaild, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(username);
        //driver.findElement(password).sendKeys(passwd);
        eleUtil.waitForElementVisible(password, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(passwd);
        eleUtil.doClick(LoginBtn);
        return new AccountsPage(driver);
    }

    @Step("Checking Forgot Link displayed on Login Page for Open Cart Application")
    public boolean isForgotPwdLinkExist() {
        //return driver.findElement(forgotPWDLink).isDisplayed();
        return eleUtil.doIsDisplayed(forgotPWDLink);
    }

    @Step("Checking Register Link displayed on Login Page for Open Cart Application")
    public boolean isregisterLinkExist() {
        //return driver.findElement(registerLink).isDisplayed();
        return eleUtil.doIsDisplayed(registerLink);
    }

    @Step("Checking Logout message displayed after Logout for Open Cart Application")
    public String getLogoutSucessMsg() {
        return eleUtil.waitForElementVisible(LogoutSuccessMsg, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
    }
}
