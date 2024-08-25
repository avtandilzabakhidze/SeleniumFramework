package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class JavaScriptAlerts {
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
    public void JSAlert() {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.xpath("//button[text() ='Click for JS Alert']")).click();

        driver.switchTo().alert().accept();
        String result = driver.findElement(By.id("result")).getText();
        String expectedValue = "You successfully clicked an alert";
        Assert.assertEquals(result,expectedValue,"\n Alert was not clicked \n");
    }

    @Test
    public void JSConfirm() {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.xpath("//button[text() ='Click for JS Confirm']")).click();

        driver.switchTo().alert().accept();
        String result = driver.findElement(By.id("result")).getText();
        String expectedValue = "You clicked: Ok";
        Assert.assertEquals(result,expectedValue,"\n Alert was not clicked \n");
    }

    @Test
    public void JSPromt() {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.xpath("//button[text() ='Click for JS Prompt']")).click();

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("hello there !");
        alert.accept();
        String result = driver.findElement(By.id("result")).getText();
        String expectedValue = "You entered: hello there !";
        Assert.assertEquals(result,expectedValue,"\n Alert was not clicked \n");
    }
}
