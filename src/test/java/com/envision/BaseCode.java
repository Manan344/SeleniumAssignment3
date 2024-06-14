package com.envision;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseCode {

    WebDriver driver;
    ChromeOptions co;       //

    public void openBrowser(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\manan\\Automation Tools\\Drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
            co = new ChromeOptions();
//            co.addArguments("-headless=new");
            driver = new ChromeDriver(co);      //remove co

        }

        else if(browserName.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver" ,"C:\\Users\\manan\\IdeaProjects\\SeleniumAssignment3\\src\\test\\resources\\msedgedriver.exe");
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
    }

    public void openURL(String URL){
        driver.navigate().to(URL);
    }

    public void pauseFor(int i) throws InterruptedException {
        Thread.sleep(i*1000);
    }

    public void quitBrowser(){
        driver.quit();
        driver.quit();
    }

}
