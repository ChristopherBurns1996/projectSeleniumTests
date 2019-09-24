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

    @Test
    public void navHometoPMTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/HomePage.html");
        Thread.sleep(1000);

        WebElement pmButton = driver.findElementById("pmButton");
        pmButton.click();
        Thread.sleep(1000);

        WebElement title = driver.findElementById("pageTitle");
        assertEquals("Publisher Menu", title.getText());
    }

    @Test
    public void navGMtoAGTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/GameMenu.html");
        Thread.sleep(1000);

        WebElement agButton = driver.findElementById("agButton");
        agButton.click();
        Thread.sleep(1000);

        WebElement title = driver.findElementById("pageTitle");
        assertEquals("Add Game to Library", title.getText());
    }

    @Test
    public void navAGtoGMTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/AddGame.html");
        Thread.sleep(1000);

        WebElement gmButton = driver.findElementById("gmButton");
        gmButton.click();
        Thread.sleep(1000);

        WebElement title = driver.findElementById("pageTitle");
        assertEquals("Games Menu", title.getText());
    }

    @Test
    public void navGMtoDGTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/GameMenu.html");
        Thread.sleep(1000);

        WebElement dgButton = driver.findElementById("dgButton");
        dgButton.click();
        Thread.sleep(1000);

        WebElement title = driver.findElementById("pageTitle");
        assertEquals("Displaying All Games", title.getText());
    }

    @Test
    public void navDGtoGMTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/DisplayGames.html");
        Thread.sleep(1000);

        WebElement gmButton = driver.findElementById("gmButton");
        gmButton.click();
        Thread.sleep(1000);

        WebElement title = driver.findElementById("pageTitle");
        assertEquals("Games Menu", title.getText());
    }

    @Test
    public void navDGtoUGTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/DisplayGames.html");
        Thread.sleep(1000);

        WebElement ugButton = driver.findElementByXPath("//*[@id=\"gameTable\"]/tr/td[6]/button");
        ugButton.click();
        Thread.sleep(1000);

        WebElement title = driver.findElementById("pageTitle");
        assertEquals("Update Game Information", title.getText());
    }

}
