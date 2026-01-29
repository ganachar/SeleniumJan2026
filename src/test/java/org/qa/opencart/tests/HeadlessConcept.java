package org.qa.opencart.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessConcept {

    public static  WebDriver driver;
    public static void main(String args[]){
        WebDriverManager.chromiumdriver().setup();

        ChromeOptions opts = new ChromeOptions();
        //opts.addArguments("--headless");
        //opts.addArguments("--incognito");

        driver = new ChromeDriver(opts);
        driver.get("https://google.com");
        String tiltle = driver.getTitle();
        System.out.println(tiltle);
        driver.quit();


    }
}
