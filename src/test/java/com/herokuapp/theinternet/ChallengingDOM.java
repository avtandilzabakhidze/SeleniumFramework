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

import java.time.Duration;
import java.util.List;

public class ChallengingDOM {
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
    public void testFirstButtonChangeResult() {
        driver.findElement(By.linkText("Challenging DOM")).click();
        WebElement result = driver.findElement(By.id("canvas"));
        List<WebElement> buttons = driver.findElements(By.xpath("//div[contains(@class,large-2)]//a[contains(@class,'button')]"));
        buttons.getFirst().click();
        WebElement lastResult = driver.findElement(By.id("canvas"));
        Assert.assertNotEquals(result, lastResult,"\n result did not change \n");
    }

    @Test
    public void testSecondButtonChangeResult() {
        driver.findElement(By.linkText("Challenging DOM")).click();
        WebElement result = driver.findElement(By.id("canvas"));
        List<WebElement> buttons = driver.findElements(By.xpath("//div[contains(@class,large-2)]//a[contains(@class,'button')]"));
        buttons.get(1).click();
        WebElement lastResult = driver.findElement(By.id("canvas"));
        Assert.assertNotEquals(result, lastResult,"\n result did not change \n");
    }

    @Test
    public void testThirdButtonChangeResult() {
        driver.findElement(By.linkText("Challenging DOM")).click();
        WebElement result = driver.findElement(By.id("canvas"));
        List<WebElement> buttons = driver.findElements(By.xpath("//div[contains(@class,large-2)]//a[contains(@class,'button')]"));
        buttons.get(2).click();
        WebElement lastResult = driver.findElement(By.id("canvas"));
        Assert.assertNotEquals(result, lastResult,"\n result did not change \n");
    }
}
