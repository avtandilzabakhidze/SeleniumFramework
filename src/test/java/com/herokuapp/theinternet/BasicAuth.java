package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BasicAuth {
    WebDriver driver;
    private final String username = "admin";
    private final String password = "admin";

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testBasicAuth() {
        driver.findElement(By.linkText("Basic Auth")).click();
        String authUrl = "https://" + username + ":" + password + "@" + driver.getCurrentUrl().replace("https://", "");
        driver.get(authUrl);
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Congratulations! You must have the proper credentials."),
                "\n Authentication failed \n");

    }
}
