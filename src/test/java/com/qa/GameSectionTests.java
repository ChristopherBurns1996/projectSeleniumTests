package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class GameSectionTests {
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
    public void crudAddGameTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/AddGame.html");
        Thread.sleep(1000);

        WebElement gn = driver.findElementById("gameName");
        gn.sendKeys("Runescape");
        WebElement gpub = driver.findElementById("publisher");
        gpub.sendKeys("Jagex");
        WebElement gplat = driver.findElementById("platform");
        gplat.sendKeys("PC");
        WebElement gyor = driver.findElementById("yearOfRelease");
        gyor.sendKeys("1999");
        WebElement submitbutton = driver.findElementById("agSubmit");
        submitbutton.click();
        Thread.sleep(1000);

        driver.get("http://34.89.59.112/DisplayGames.html");

        Thread.sleep(1000);

        WebElement lastRow =  driver.findElementByXPath("//*[@id=\"gameTable\"]/tr[last()]/td[2]");
        assertEquals("Runescape", lastRow.getText());
    }
}
