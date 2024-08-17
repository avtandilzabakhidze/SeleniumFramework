package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class AddRemoveElements {
    WebDriver driver;

    @BeforeTest
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
    public void addOne() {
        driver.findElement(By.linkText("Add/Remove Elements")).click();
        WebElement element = driver.findElement(By.xpath("//button[text() ='Add Element']"));
        element.click();

        List<WebElement> elements = driver.findElements(By.xpath("//div[@id = 'content']//button[@class=\"added-manually\"]"));
        Assert.assertEquals(elements.size(), 1, "\n There did not add one element \n");
    }

    @Test
    public void addFive() {
        driver.findElement(By.linkText("Add/Remove Elements")).click();

        for (int i = 0; i < 5; i++) {
            WebElement element = driver.findElement(By.xpath("//button[text() ='Add Element']"));
            element.click();
        }

        List<WebElement> elements = driver.findElements(By.xpath("//div[@id = 'content']//button[@class=\"added-manually\"]"));
        Assert.assertEquals(elements.size(), 5, "\n There did not add five element \n");
    }

    @Test
    public void removeElement() {
        driver.findElement(By.linkText("Add/Remove Elements")).click();

        for (int i = 0; i < 6; i++) {
            WebElement element = driver.findElement(By.xpath("//button[text() ='Add Element']"));
            element.click();
        }

        List<WebElement> addedElements = driver.findElements(By.xpath("//div[@id = 'content']//button[@class=\"added-manually\"]"));
        Assert.assertEquals(addedElements.size(), 6, "\n Initial number of elements is not as expected \n");

        addedElements.getLast().click();

        List<WebElement> removedElements = driver.findElements(By.xpath("//div[@id = 'content']//button[@class=\"added-manually\"]"));
        Assert.assertEquals(removedElements.size(), 5, "\n Last Element did not remove \n");

    }


}
