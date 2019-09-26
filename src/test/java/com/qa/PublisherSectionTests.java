package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class PublisherSectionTests {
    ChromeDriver driver;

    //sets up the google chrome driver
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Documents\\projectSeleniumTests\\src\\test\\java\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    //closes the driver
    @After
    public void tearDown(){
        driver.close();
    }

    //Tests the front ends ability to take in information, send it to the back end, and then display it to the user
    @Test
    public void crudAddandReadPublisherTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/AddPublisher.html");
        Thread.sleep(1000);

        //enters data into the add game page form and clicks the submit button
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

        //checks the last row of the table to see if the name of the record added has appeared
        WebElement lastRow =  driver.findElementByXPath("//*[@id=\"publisherTable\"]/tr[last()]/td[2]");
        assertEquals("Bungie", lastRow.getText());
    }

    //Tests the front ends ability to take in new information about a record in the database, send it to the back end, and then display the updated information to the user
    @Test
    public void crudUpdatePublisherTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/DisplayPublishers.html");
        Thread.sleep(1000);

        WebElement updateButton =  driver.findElementByXPath("//*[@id=\"publisherTable\"]/tr[1]/td[5]/button");
        updateButton.click();

        //enters data into the update game page form and clicks the submit button
        WebElement pn = driver.findElementById("publisherName");
        pn.sendKeys("Bethesda");
        WebElement ceo = driver.findElementById("currentCEO");
        ceo.sendKeys("Todd Howard");
        WebElement ye = driver.findElementById("yearEstablished");
        ye.sendKeys("2007");
        WebElement submitbutton = driver.findElementById("upSubmit");
        submitbutton.click();
        Thread.sleep(1000);

        //checks the updated row of the table to see if the name of the record changed has been successfully updated
        WebElement firstRow =  driver.findElementByXPath("//*[@id=\"publisherTable\"]/tr[1]/td[2]");
        assertEquals("Bethesda", firstRow.getText());
    }

    //Tests the front ends ability to send a request to delete a record, and then display that this record has been deleted to the user
    @Test
    public void crudDeletePublisherTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/DisplayPublishers.html");
        Thread.sleep(1000);

        //checks the name in the second row of the table and clicks the delete button on the first row
        String nextValue = (driver.findElementByXPath("//*[@id=\"publisherTable\"]/tr[2]/td[2]")).getText();
        WebElement deleteButton =  driver.findElementByXPath("//*[@id=\"publisherTable\"]/tr[1]/td[6]/button");
        deleteButton.click();

        Thread.sleep(1000);

        driver.switchTo().alert().accept();

        Thread.sleep(1000);

        //checks that the information on the second row is now on the first row as the original first row has been deleted
        WebElement firstRow =  driver.findElementByXPath("//*[@id=\"publisherTable\"]/tr[1]/td[2]");
        assertEquals(nextValue, firstRow.getText());
    }
}
