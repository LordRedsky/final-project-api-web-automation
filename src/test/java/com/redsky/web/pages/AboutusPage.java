package com.redsky.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AboutusPage {
    WebDriver driver;
    By aboutUsMenu = By.xpath("/html/body/nav/div[1]/ul/li[3]/a");
    By aboutTitle = By.id("videoModalLabel");
    By closeButton = By.cssSelector("#videoModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(1)");
    By aboutInformation = By.xpath("//*[@id=\"example-video_html5_api\"]");
    By playBtnInformationVideo = By.xpath("/html/body/div[4]/div/div/div[2]/form/div/div/button/span[1]");
    public AboutusPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToAboutUsPage() {
        driver.findElement(aboutUsMenu).click();
    }

    public void validateOnAboutUsPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement aboutTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(aboutTitle));

        assertTrue(aboutTitleElement.isDisplayed());
        assertEquals("About us", aboutTitleElement.getText());
    }

    public void validationPupUpVideoAboutUs() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement videoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(aboutInformation));
//        WebElement videoPlay = wait.until(ExpectedConditions.elementToBeClickable(playBtnInformationVideo));

        assertTrue(videoElement.isDisplayed());
//        videoPlay.click();

    }

    public void clickCloseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement closeBtnElement = wait.until(ExpectedConditions.elementToBeClickable(closeButton));

        closeBtnElement.click();
    }
}

