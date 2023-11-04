package com.redsky.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactPage {
    WebDriver driver;
    By contactMenu = By.xpath("/html/body/nav/div[1]/ul/li[2]/a");
    By contactTitle = By.id("exampleModalLabel");
    By emailTextBox = By.cssSelector("[id=\"recipient-email\"]");
    By nameTextBox = By.id("recipient-name");
    By messageTextBox = By.id("message-text");
    By closeButton = By.xpath("//*[@id=\"exampleModal\"]/div/div/div[3]/button[1]");
    By sendMessageButton = By.xpath("//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]");
    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToContactPage() {
        driver.findElement(contactMenu).click();
    }

    public void validatedOnContactPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(contactTitle));

        assertTrue(titleElement.isDisplayed());
        assertEquals("New message", titleElement.getText());
    }

    public void inputEmail(String email) {
        driver.findElement(emailTextBox).sendKeys(email);
    }

    public void inputName(String name) {
        driver.findElement(nameTextBox).sendKeys(name);
    }

    public void inputMessage(String message) {
        driver.findElement(messageTextBox).sendKeys(message);
    }

    public void clickSendMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement sendMessageElement = wait.until(ExpectedConditions.elementToBeClickable(sendMessageButton));

        sendMessageElement.click();
    }

    public void clickCloseBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement closeBtnElement = wait.until(ExpectedConditions.elementToBeClickable(closeButton));

        closeBtnElement.click();
    }



}
