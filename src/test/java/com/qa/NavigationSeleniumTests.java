package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class NavigationSeleniumTests {
    ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Documents\\projectSeleniumTests\\src\\test\\java\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void navHometoGMTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/HomePage.html");
        Thread.sleep(1000);

        WebElement gmButton = driver.findElementById("gmButton");
        gmButton.click();
        Thread.sleep(1000);

        WebElement title = driver.findElementById("pageTitle");
        assertEquals("Games Menu", title.getText());
    }

}
