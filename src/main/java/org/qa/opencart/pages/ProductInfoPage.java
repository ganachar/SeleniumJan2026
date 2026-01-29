package org.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.opencart.constants.Constants;
import org.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

    private WebDriver driver;
    private ElementUtil eleutil;

    private By productHeader = By.cssSelector("div#content h1");

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleutil= new ElementUtil(this.driver);
    }

    public String getProductHeaderName(){
        return eleutil.waitForElementVisible(productHeader, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
    }

}
