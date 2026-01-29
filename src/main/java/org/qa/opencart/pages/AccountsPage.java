package org.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.qa.opencart.constants.Constants;
import org.qa.opencart.utils.ElementUtil;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    private By LogoutLink = By.partialLinkText("Logout");
    private By logo = By.xpath("//div[@id='logo']");
    private By SectionsHeaders = By.cssSelector("div#content h2");
    private By search = By.name("search");
    private By searchBtn = By.cssSelector("div#search button");


    public AccountsPage(WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    public  String getAccountsPageTitle(){
        return eleUtil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
    }
    public  String getAccountsPageURL(){
        return eleUtil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL, Constants.DEFAULT_TIME_OUT);
    }

    public List<String> getAccountsPageSectionsList(){
        List<WebElement> secList =  eleUtil.getElements(SectionsHeaders);
        List<String> secValList = new ArrayList<String>();
        for(WebElement e : secList){
            String text = e.getText();
            secValList.add(text);
        }
        return secValList;
    }

    public boolean isLogoutLinkExists(){
        return eleUtil.waitForElementVisible(LogoutLink,Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
    }

    public LoginPage clickOnLogout(){
        if(isLogoutLinkExists()){
            eleUtil.doClick(LogoutLink);
        }
        return new LoginPage(driver);
    }

    public boolean isSearchFieldExists(){
        return eleUtil.waitForElementVisible(search,Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
    }

    public SearchResultsPage doSearch(String searchKey){
        if(isSearchFieldExists()){
            eleUtil.doSendKeys(search,searchKey);
            eleUtil.doClick(searchBtn);
           return new SearchResultsPage(driver);
        }
        return null;
    }
}
