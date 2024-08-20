package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class EntryAd {
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

    @Test(priority = 1)
    public void closeAd() {
        driver.findElement(By.linkText("Entry Ad")).click();
        WebElement closeAd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class ='modal-footer']//p")));
        closeAd.click();
        Boolean adDiv = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"modal\"]")));
        Assert.assertFalse(adDiv , "\n Ad is still displayed \n");
    }

    @Test(priority = 2)
    public void closeAdClickButton() {
        driver.findElement(By.linkText("Entry Ad")).click();
        WebElement closeAd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class ='modal-footer']//p")));
        closeAd.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"modal\"]")));
        WebElement clickHere = driver.findElement(By.linkText("click here"));
        clickHere.click();
    }
}
