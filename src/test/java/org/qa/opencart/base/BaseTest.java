package org.qa.opencart.base;

import org.openqa.selenium.WebDriver;
import org.qa.opencart.factory.DriverFactory;
import org.qa.opencart.pages.AccountsPage;
import org.qa.opencart.pages.LoginPage;
import org.qa.opencart.pages.ProductInfoPage;
import org.qa.opencart.pages.SearchResultsPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    public DriverFactory df;
    public Properties prop;
    public WebDriver driver;
    protected LoginPage loginPage;
    protected AccountsPage acctsPage;
    protected SearchResultsPage searchResPage;
    protected ProductInfoPage productInfoPage;

    @BeforeTest
    public void setUp(){
       df= new DriverFactory();
       prop = df.init_prop();
       driver = df.init_driver(prop);
       loginPage = new LoginPage(driver);
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
