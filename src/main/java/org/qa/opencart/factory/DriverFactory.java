package org.qa.opencart.factory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.qa.opencart.exceptions.FrameworkExceptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    WebDriver driver;
    Properties prop;
    OptionsManager optionsManager;
    public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

    /*
       Method to initialize WD on basis on browserName & returns driver
     */
    public WebDriver init_driver(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        optionsManager = new OptionsManager(prop);
        System.out.println("BrowserName : " + browserName);
        if (browserName.equalsIgnoreCase("chrome")) {
            //driver = new ChromeDriver(optionsManager.getChromeOptions());
            tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
            tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        } else if (browserName.equalsIgnoreCase("safari")) {
            //driver = new SafariDriver();
            tldriver.set(new SafariDriver());

        } else {
            System.out.println("Please pass the right browser : " + browserName);
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        // driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        getDriver().get(prop.getProperty("url").trim());
        return getDriver();
    }


    public Properties init_prop() {
        prop = new Properties();
        FileInputStream fis = null;

        String envName = System.getProperty("env");
        System.out.println("Running tests on environment : " + envName);

        if (envName == null) {
            System.out.println("No env is given...hence running tests on QA env");
            try {
                fis = new FileInputStream("./src/test/resources/config/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                switch (envName.toLowerCase()) {
                    case "qa":
                        System.out.println("Running tests on QA Env");
                        fis = new FileInputStream("./src/test/resources/config/qa_config.properties");
                        break;
                    case "stage":
                        System.out.println("Running tests on Stage Env");
                        fis = new FileInputStream("./src/test/resources/config/stage_config.properties");
                        break;
                    default:
                        System.out.println("Please pass the Right Env");
                        throw new FrameworkExceptions( "No environment with specified environment name " + envName + " exist");
                        //break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return prop;

    }

    public static WebDriver getDriver() {
        return tldriver.get();
    }

    public String getScreenshot() {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = "./" + "screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
