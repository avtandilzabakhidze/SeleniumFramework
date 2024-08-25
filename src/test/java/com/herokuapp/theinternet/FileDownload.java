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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;

public class FileDownload {
    WebDriver driver;
    WebDriverWait wait;

    private final String downloadDir = System.getProperty("user.home") + File.separator + "Downloads";

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
    public void fileDownload() throws InterruptedException, IOException {
        driver.findElement(By.linkText("File Download")).click();
        List<WebElement> downloadLink = driver.findElements(By.xpath("//div[@id ='content']//a"));
        for (WebElement link : downloadLink) {
            String fileName = link.getText();
            link.click();

            waitUntilFileIsDownloaded(fileName);

            File downloadedFile = new File(downloadDir + File.separator + fileName);
            Assert.assertTrue(downloadedFile.exists(), "File was not downloaded: " + fileName);

            Files.delete(downloadedFile.toPath());
        }

    }
    private void waitUntilFileIsDownloaded(String fileName) throws InterruptedException {
        File file = new File(downloadDir + File.separator + fileName);
        int attempts = 0;
        while (!file.exists() && attempts < 20) {
            Thread.sleep(500);
            attempts++;
        }
    }
}
