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

    By productStoreTitle = By.cssSelector("[id=\"nava\"]");
    By homeMenu = By.xpath("/html/body/nav/div[1]/ul/li[1]/a");

    public void goToHomepage() {
        driver.get("https://www.demoblaze.com/");
    }

    public void validateHomepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(productStoreTitle));
        assertTrue(title.isDisplayed());
        assertEquals("PRODUCT STORE", title.getText());
    }

//    public void setupCategory(String category) {
//
//    }

}
