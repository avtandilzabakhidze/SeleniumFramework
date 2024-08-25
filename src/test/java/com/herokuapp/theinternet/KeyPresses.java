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

public class KeyPresses {
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
    public void keyPressLetter() {
        driver.findElement(By.linkText("Key Presses")).click();
        driver.findElement(By.id("target")).sendKeys("Ass");
        String result = driver.findElement(By.id("result")).getText();
        String substring = result.substring(13);
        String character = "S";
        Assert.assertEquals(substring,character,"\n this key editor did not work with Letter\n");
    }

    @Test
    public void keyPressNumber() {
        driver.findElement(By.linkText("Key Presses")).click();
        driver.findElement(By.id("target")).sendKeys("Ass1");
        String result = driver.findElement(By.id("result")).getText();
        String substring = result.substring(13);
        String character = "1";
        Assert.assertEquals(substring,character,"\n this key editor did not work with Number\n");
    }

    @Test
    public void keyPressSlash() {
        driver.findElement(By.linkText("Key Presses")).click();
        driver.findElement(By.id("target")).sendKeys("/");
        String result = driver.findElement(By.id("result")).getText();
        String substring = result.substring(13);
        String character = "SLASH";
        Assert.assertEquals(substring,character,"\n this key editor did not work with slash\n");
    }

    @Test
    public void keyPressComma() {
        driver.findElement(By.linkText("Key Presses")).click();
        driver.findElement(By.id("target")).sendKeys(",");
        String result = driver.findElement(By.id("result")).getText();
        String substring = result.substring(13);
        String character = "COMMA";
        Assert.assertEquals(substring,character,"\n this key editor did not work with comma\n");
    }
}
