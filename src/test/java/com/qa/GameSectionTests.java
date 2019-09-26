package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class GameSectionTests {
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
    public void crudAddandReadGameTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/AddGame.html");
        Thread.sleep(1000);

        //enters data into the add game page form and clicks the submit button
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

        //checks the last row of the table to see if the name of the record added has appeared
        WebElement lastRow =  driver.findElementByXPath("//*[@id=\"gameTable\"]/tr[last()]/td[2]");
        assertEquals("Runescape", lastRow.getText());
    }

    //Tests the front ends ability to take in new information about a record in the database, send it to the back end, and then display the updated information to the user
    @Test
    public void crudUpdateGameTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/DisplayGames.html");
        Thread.sleep(1000);

        WebElement updateButton =  driver.findElementByXPath("//*[@id=\"gameTable\"]/tr[1]/td[6]/button");
        updateButton.click();

        //enters data into the update game page form and clicks the submit button
        WebElement gn = driver.findElementById("gameName");
        gn.sendKeys("Call of Duty: Modern Warfare");
        WebElement gpub = driver.findElementById("publisher");
        gpub.sendKeys("Activision");
        WebElement gplat = driver.findElementById("platform");
        gplat.sendKeys("Multi-Platform");
        WebElement gyor = driver.findElementById("yearOfRelease");
        gyor.sendKeys("2007");
        WebElement submitbutton = driver.findElementById("ugSubmit");
        submitbutton.click();
        Thread.sleep(1000);

        //checks the updated row of the table to see if the name of the record changed has been successfully updated
        WebElement firstRow =  driver.findElementByXPath("//*[@id=\"gameTable\"]/tr[1]/td[2]");
        assertEquals("Call of Duty: Modern Warfare", firstRow.getText());
    }

    //Tests the front ends ability to send a request to delete a record, and then display that this record has been deleted to the user
    @Test
    public void crudDeleteGameTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://34.89.59.112/DisplayGames.html");
        Thread.sleep(1000);

        //checks the name in the second row of the table and clicks the delete button on the first row
        String nextValue = (driver.findElementByXPath("//*[@id=\"gameTable\"]/tr[2]/td[2]")).getText();
        WebElement deleteButton =  driver.findElementByXPath("//*[@id=\"gameTable\"]/tr[1]/td[7]/button");
        deleteButton.click();

        Thread.sleep(1000);

        driver.switchTo().alert().accept();

        Thread.sleep(1000);

        //checks that the information on the second row is now on the first row as the original first row has been deleted
        WebElement firstRow =  driver.findElementByXPath("//*[@id=\"gameTable\"]/tr[1]/td[2]");
        assertEquals(nextValue, firstRow.getText());
    }
}
