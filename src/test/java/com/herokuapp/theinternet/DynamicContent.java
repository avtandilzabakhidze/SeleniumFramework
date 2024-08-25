package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
import java.util.List;

public class DynamicContent {
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
    public void changeText() {
        driver.findElement(By.linkText("Dynamic Content")).click();
        List<WebElement> untilClick = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@id='content']//div[contains(@class, 'large-2')]/following-sibling::div[contains(@class, 'large-10')]")
        ));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("click here")));
        element.click();

        List<WebElement> afterClick = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@id='content']//div[contains(@class, 'large-2')]/following-sibling::div[contains(@class, 'large-10')]")
        ));
        Assert.assertEquals(untilClick.size(), afterClick.size(), "\n Number of clicks not equal \n");

        for (int i = 0; i < untilClick.size() ; i++) {
            String until = untilClick.get(i).getText();
            System.out.println(until);
            String after = afterClick.get(i).getText();
            System.out.println(after);
            Assert.assertNotEquals(until, after, "\n Text did not change \n");
        }
    }
}
