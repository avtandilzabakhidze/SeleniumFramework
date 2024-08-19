package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDrop {
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
    public void dragAndDropFirstInSecond() {
        driver.findElement(By.linkText("Drag and Drop")).click();
        WebElement element1 = driver.findElement(By.id("column-a"));
        WebElement element2 = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(element1, element2).perform();
        WebElement element3 = driver.findElement(By.id("column-a"));
        Assert.assertEquals(element3.getText(), "B","\n Drag and Drop is not working \n");
    }

    @Test
    public void dragAndDropSecondInFirst() {
        driver.findElement(By.linkText("Drag and Drop")).click();
        WebElement element1 = driver.findElement(By.id("column-a"));
        WebElement element2 = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(element2, element1).perform();
        WebElement element3 = driver.findElement(By.id("column-b"));
        Assert.assertEquals(element3.getText(), "A","\n Drag and Drop is not working \n");
    }
}
