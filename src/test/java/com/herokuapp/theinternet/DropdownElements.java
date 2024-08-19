package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DropdownElements {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testOption1() {
        driver.findElement(By.linkText("Dropdown")).click();
        driver.findElement(By.id("dropdown")).click();
        driver.findElement(By.xpath("//select[@id = 'dropdown']//option[@value =\"1\"]")).click();
        boolean dropdown = driver.findElement(By.id("dropdown")).isDisplayed();

        Assert.assertTrue(dropdown, "\n Dropdown is not displayed \n");
    }

    @Test
    public void testOption2() {
        driver.findElement(By.linkText("Dropdown")).click();
        driver.findElement(By.id("dropdown")).click();
        driver.findElement(By.xpath("//select[@id = 'dropdown']//option[@value =\"2\"]")).click();
        boolean dropdown = driver.findElement(By.id("dropdown")).isDisplayed();

        Assert.assertTrue(dropdown, "\n Dropdown is not displayed \n");
    }
}
