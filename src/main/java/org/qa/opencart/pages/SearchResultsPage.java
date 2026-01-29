package org.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.opencart.constants.Constants;
import org.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

     private WebDriver driver;
     private ElementUtil eleutil;

     private By searchResultsList = By.cssSelector("div.product-layout.product-grid");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        eleutil = new ElementUtil(this.driver);

    }

    public int getSearchResultsCount(){
        return eleutil.waitForElementsVisible(searchResultsList, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
    }

    public ProductInfoPage selectProduct(String productName){
        By productNameLink = By.linkText(productName);
        eleutil.doClick(productNameLink);
        return new ProductInfoPage(driver);

    }
}
