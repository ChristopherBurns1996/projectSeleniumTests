package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class PublisherSectionTests {
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
    public void crudAddandReadPublisherTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/AddPublisher.html");
        Thread.sleep(1000);

        WebElement pn = driver.findElementById("publisherName");
        pn.sendKeys("Bungie");
        WebElement ceo = driver.findElementById("currentCEO");
        ceo.sendKeys("Pete Parsons");
        WebElement ye = driver.findElementById("yearEstablished");
        ye.sendKeys("2001");
        WebElement submitbutton = driver.findElementById("apSubmit");
        submitbutton.click();
        Thread.sleep(1000);

        driver.get("http://34.89.59.112/DisplayPublishers.html");

        Thread.sleep(1000);

        WebElement lastRow =  driver.findElementByXPath("//*[@id=\"publisherTable\"]/tr[last()]/td[2]");
        assertEquals("Bungie", lastRow.getText());
    }

    @Test
    public void crudUpdatePublisherTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/DisplayPublishers.html");
        Thread.sleep(1000);

        WebElement updateButton =  driver.findElementByXPath("//*[@id=\"publisherTable\"]/tr[1]/td[5]/button");
        updateButton.click();

        WebElement pn = driver.findElementById("publisherName");
        pn.sendKeys("Bethesda");
        WebElement ceo = driver.findElementById("currentCEO");
        ceo.sendKeys("Todd Howard");
        WebElement ye = driver.findElementById("yearEstablished");
        ye.sendKeys("2007");
        WebElement submitbutton = driver.findElementById("upSubmit");
        submitbutton.click();
        Thread.sleep(1000);

        WebElement firstRow =  driver.findElementByXPath("//*[@id=\"publisherTable\"]/tr[1]/td[2]");
        assertEquals("Bethesda", firstRow.getText());
    }
}
