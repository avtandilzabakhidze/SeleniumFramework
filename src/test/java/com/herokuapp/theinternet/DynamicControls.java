package com.herokuapp.theinternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.function.Function;

public class DynamicControls {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void removeElement() {
        driver.findElement(By.linkText("Dynamic Controls")).click();
        driver.findElement(By.xpath("//form[@id ='checkbox-example']//button")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30L))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement progress = driver.findElement(By.id("loading"));
                WebElement messageDone = driver.findElement(By.id("message"));
                String getMessage = messageDone.getText();

                if (getMessage.equals("It's gone!")) {
                    System.out.println("Progress Is Complete!");
                    return progress;
                }
                else {
                    System.out.println(progress.getText());
                    return null;
                }
            }
        });

        WebElement messageDone = driver.findElement(By.id("message"));
        Assert.assertEquals(messageDone.getText(), "It's gone!", "\n The message is not as expected \n");
        Assert.assertTrue(driver.findElements(By.id("checkbox")).isEmpty(), "\n The checkbox is not removed \n");
    }

    @Test(priority = 2)
    public void addElement() {
        driver.findElement(By.xpath("//form[@id ='checkbox-example']//button")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30L))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement progress = driver.findElement(By.id("loading"));
                WebElement messageDone = driver.findElement(By.id("message"));
                String getMessage = messageDone.getText();

                if (getMessage.equals("It's back!")) {
                    System.out.println("Progress Is Complete!");
                    return progress;
                }
                else {
                    System.out.println(progress.getText());
                    return null;
                }
            }
        });

        WebElement messageDone = driver.findElement(By.id("message"));
        Assert.assertEquals(messageDone.getText(), "It's back!", "\n The message is not as expected \n");
    }
}
