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

import java.io.IOException;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class BrokenImages {
    WebDriver driver;
    CloseableHttpClient httpClient;
    HttpGet httpGet;
    HttpResponse response;

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
    public void brokenImages() throws IOException {
        driver.findElement(By.linkText("Broken Images")).click();
        List<WebElement> images = driver.findElements(By.xpath("//div[@class = 'example']//img"));

        for (WebElement img : images) {
            String imgUrl = img.getAttribute("src");
            boolean imageBroken = isImageBroken(imgUrl);
            Assert.assertFalse(imageBroken, "\n Broken image found: " + imgUrl + "\n");
        }
    }

    private boolean isImageBroken(String imageUrl) throws IOException {
        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(imageUrl);
        response = httpClient.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();
        httpClient.close();

        return statusCode != 200;
    }
}
