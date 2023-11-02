package com.redsky.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Homepage {
    WebDriver driver;

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    By productStoreTitle = By.id("nava");
    // NavigationBar Menu
    By homeMenu = By.xpath("/html/body/nav/div[1]/ul/li[1]/a");
    By contactMenu = By.xpath("/html/body/nav/div[1]/ul/li[2]/a");
    By aboutUsMenu = By.xpath("/html/body/nav/div[1]/ul/li[3]/a");
    By cartMenu = By.xpath("//*[@id=\"cartur\"]");
    By loginMenu = By.xpath("//*[@id=\"login2\"]");
    By signupMenu = By.xpath("//*[@id=\"signin2\"]");

    // Category Menu
    By phonesMenu = By.cssSelector("a.list-group-item:nth-child(2)");
    By laptopsMenu = By.cssSelector("a.list-group-item:nth-child(3)");
    By monitorsMenu = By.cssSelector("a.list-group-item:nth-child(4)");
    public void goToHomepage() {
        driver.get("https://www.demoblaze.com/");
    }

    public void validateHomepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productStoreTitle));
        WebElement homeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(homeMenu));
        WebElement contactElement = wait.until(ExpectedConditions.visibilityOfElementLocated(contactMenu));
        WebElement aboutUsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(aboutUsMenu));
        WebElement cartElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartMenu));
        WebElement loginElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginMenu));
        WebElement signupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(signupMenu));
        WebElement phonesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(phonesMenu));
        WebElement laptopsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(laptopsMenu));
        WebElement monitorsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(monitorsMenu));

        assertEquals("PRODUCT STORE", titleElement.getText());
        assertTrue(titleElement.isDisplayed());
        assertTrue(homeElement.isDisplayed());
        assertTrue(contactElement.isDisplayed());
        assertTrue(aboutUsElement.isDisplayed());
        assertTrue(cartElement.isDisplayed());
        assertTrue(loginElement.isDisplayed());
        assertTrue(signupElement.isDisplayed());
        assertTrue(phonesElement.isDisplayed());
        assertTrue(laptopsElement.isDisplayed());
        assertTrue(monitorsElement.isDisplayed());
    }

//    public void setupCategory(String category) {
//
//    }

}
