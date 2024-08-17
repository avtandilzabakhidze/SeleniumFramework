package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class Checkboxes {
    WebDriver driver;

    @BeforeMethod
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
    public void SelectFirstCheckbox() {
        driver.findElement(By.linkText("Checkboxes")).click();
        List<WebElement> checkboxes = driver.findElements(By.xpath("//form[@id ='checkboxes']//input"));
        Assert.assertFalse(checkboxes.getFirst().isSelected(), "\n First Checkboxes is selected \n");
        List<WebElement> elements = driver.findElements(By.xpath("//form[@id ='checkboxes']//input"));
        elements.getFirst().click();
        Assert.assertTrue(elements.getFirst().isSelected(), "\n First Checkbox did not select \n");
    }

    @Test
    public void SelectLastCheckbox() {
        driver.findElement(By.linkText("Checkboxes")).click();
        List<WebElement> checkboxes = driver.findElements(By.xpath("//form[@id ='checkboxes']//input"));
        Assert.assertFalse(checkboxes.getFirst().isSelected(), "\n Last Checkbox did not select \n");

        List<WebElement> elements = driver.findElements(By.xpath("//form[@id ='checkboxes']//input"));
        elements.getLast().click();
        Assert.assertTrue(elements.getFirst().isSelected(), "\n Last Checkbox did not select \n");
    }

    @Test
    public void SelectAllCheckCheckbox() {
        driver.findElement(By.linkText("Checkboxes")).click();
        List<WebElement> checkboxes = driver.findElements(By.xpath("//form[@id ='checkboxes']//input"));

        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected(), "\n Checkbox is not selected: " + checkbox + "\n");
        }
    }

    @Test
    public void UnselectAllCheckCheckbox() {
        driver.findElement(By.linkText("Checkboxes")).click();
        List<WebElement> checkboxes = driver.findElements(By.xpath("//form[@id ='checkboxes']//input"));

        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
            checkbox.click();
        }
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected(), "\n Checkbox is selected: " + checkbox + "\n");
        }
    }
}
