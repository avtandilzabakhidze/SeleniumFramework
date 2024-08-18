package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DisappearingElements {
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
    public void testHomeButton() {
        driver.findElement(By.linkText("Disappearing Elements")).click();
        driver.findElement(By.linkText("Home")).click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://the-internet.herokuapp.com/","Home page not displayed");
    }

    @Test
    public void testContactUsButton() {
        driver.findElement(By.linkText("Disappearing Elements")).click();
        driver.findElement(By.linkText("Contact Us")).click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://the-internet.herokuapp.com/contact-us/","Contact Us page not displayed");
    }

    @Test
    public void testPortfolioButton() {
        driver.findElement(By.linkText("Disappearing Elements")).click();
        driver.findElement(By.linkText("Portfolio")).click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://the-internet.herokuapp.com/portfolio/","Portfolio page not displayed");
    }

    @Test
    public void testGalleryButton() {
        driver.findElement(By.linkText("Disappearing Elements")).click();
        driver.findElement(By.linkText("Gallery")).click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://the-internet.herokuapp.com/gallery/","Gallery page not displayed");
    }
}
