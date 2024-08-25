package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;

public class StatusCodes {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testStatusCode200() {
        driver.findElement(By.linkText("Status Codes")).click();
        driver.findElement(By.linkText("200")).click();
        boolean displayed = driver.findElement(By.xpath("//p[contains(text(),'200')]")).isDisplayed();
        Assert.assertTrue(displayed, "\n Status Codes did not display \n");
    }

    @Test
    public void testStatusCode301() {
        driver.findElement(By.linkText("Status Codes")).click();
        driver.findElement(By.linkText("301")).click();
        boolean displayed = driver.findElement(By.xpath("//p[contains(text(),'301')]")).isDisplayed();
        Assert.assertTrue(displayed, "\n Status Codes did not display \n");
    }

    @Test
    public void testStatusCode404() {
        driver.findElement(By.linkText("Status Codes")).click();
        driver.findElement(By.linkText("404")).click();
        boolean displayed = driver.findElement(By.xpath("//p[contains(text(),'404')]")).isDisplayed();
        Assert.assertTrue(displayed, "\n Status Codes did not display \n");
    }

    @Test
    public void testStatusCode500() {
        driver.findElement(By.linkText("Status Codes")).click();
        driver.findElement(By.linkText("500")).click();
        boolean displayed = driver.findElement(By.xpath("//p[contains(text(),'500')]")).isDisplayed();
        Assert.assertTrue(displayed, "\n Status Codes did not display \n");
    }
}
