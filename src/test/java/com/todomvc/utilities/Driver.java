package com.todomvc.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {

    //we will create a driver object only if we don't already have one
    // we are using encapsulation

    private Driver(){}

    private static WebDriver driver;

    public static WebDriver get(){   // return type method and will return driver object

        if(driver == null){
            String browser = ConfigurationReader.get("browser");
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "chrome-ssl":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setAcceptInsecureCerts(true);
                    driver = new ChromeDriver(options);
                    break;
                case "firefox-ssl":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions optionsF = new FirefoxOptions();
                    optionsF.setAcceptInsecureCerts(true);
                    driver = new FirefoxDriver(optionsF);
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
            }

        }

        return driver;
    }

    public static void closeDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
/*
Bahadir: The null check will return true only the first time get() method is called in order to instantiate
 the private static field and on subsequent calls the same instance will be returned.
 */

}