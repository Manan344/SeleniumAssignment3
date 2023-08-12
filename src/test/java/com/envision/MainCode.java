package com.envision;

//import javafx.scene.web.WebErrorEvent;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import java.util.concurrent.TimeUnit;

public class MainCode extends BaseCode{

    @BeforeTest
    public void startPoint(){
        openBrowser("chrome");
        openURL("https://opensource-demo.orangehrmlive.com/");
    }

    @Test
     public void searchDirectoryScenario() throws InterruptedException {

        pauseFor(2);

        WebElement uName = driver.findElement(By.name("username"));     //Enter username
        uName.sendKeys("Admin");

        WebElement pwd = driver.findElement(By.name("password"));       //Enter password
        pwd.sendKeys("admin123");

        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        pauseFor(2);

        WebElement loginUser = driver.findElement(By.className("oxd-userdropdown-name"));
//        System.out.println(loginUser.getText());

        Assert.assertTrue(loginUser.isDisplayed(),"Login username doesnt match");

        WebElement directoryTab = driver.findElement(By.xpath("//span[text()='Directory']"));
        directoryTab.click();
        pauseFor(2);

        String directoryURL = driver.getCurrentUrl();
        System.out.println(directoryURL);

        Assert.assertEquals(directoryURL, "https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory");

        WebElement jobTitle = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[1]"));
        jobTitle.click();                   //Select Job Title
        pauseFor(2);

        Actions a = new Actions(driver);
        a.moveToElement(jobTitle).sendKeys("Sales").click().build().perform();

        pauseFor(2);


        WebElement location = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[2]"));
        location.click();                       //Select Location
        pauseFor(2);

        a.moveToElement(location).sendKeys("ca").sendKeys(Keys.ENTER).build().perform();
        pauseFor(2);

        //click on search
        WebElement searchButton = driver.findElement(By.xpath("//button[text()=' Search ']"));
        searchButton.click();
        pauseFor(5);

        WebElement displayResult = driver.findElement(By.xpath("//p[text()='Anthony  Nolan ']"));

        Assert.assertTrue(displayResult.isDisplayed(),"Missing records");
        pauseFor(2);

        driver.findElement(By.className("oxd-userdropdown-name")).click();
        pauseFor(2);

        //Click on Logout
        driver.findElement(By.xpath("//a[@href = '/web/index.php/auth/logout']")).click();


    }

    @Test
    public void personalDetailsValidationScenario() throws InterruptedException {

        pauseFor(2);

        WebElement uName = driver.findElement(By.name("username"));
        uName.sendKeys("Admin");

        WebElement pwd = driver.findElement(By.name("password"));
        pwd.sendKeys("admin123");

        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        pauseFor(2);

        WebElement loginUser = driver.findElement(By.className("oxd-userdropdown-name"));
        Assert.assertTrue(loginUser.isDisplayed(),"Login username doesnt match");

        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        pauseFor(2);

//        System.out.println(driver.getCurrentUrl());  //remove

        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,"https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7");

        //Verify personal details on the "Personal Details Section" by:
        WebElement fname = driver.findElement(By.name("firstName"));
        WebElement lname = driver.findElement(By.name("lastName"));

        String verifyFirstName = fname.getAttribute("value");
        String verifyLastName = lname.getAttribute("value");

//        System.out.println(verifyFirstName);
//        System.out.println(verifyLastName);
//        System.out.println(verifyLastName);

        Assert.assertTrue(verifyFirstName.equals("Paul"), "First name incorrect");
        Assert.assertTrue(lname.isDisplayed(),"Last Name incorrect");

        pauseFor(2);

        driver.findElement(By.className("oxd-userdropdown-name")).click();

        pauseFor(2);

        driver.findElement(By.xpath("//a[@href = '/web/index.php/auth/logout']")).click();

    }

    @AfterTest
    public void endPoint(){
        quitBrowser();
    }
}
