package com.redsky.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.prefs.Preferences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPage {
    WebDriver driver;
    By loginMenu = By.id("login2");
    By loginTitle = By.id("logInModalLabel");
    By usernameTextBox = By.xpath("//*[@id=\"loginusername\"]");
    By passwordTextBox = By.xpath("//*[@id=\"loginpassword\"]");
    By loginBtn = By.cssSelector("#logInModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(2)");
    By welcomeElement = By.xpath("//*[@id=\"nameofuser\"]");

    Preferences prefs = Preferences.userNodeForPackage(SignupPage.class);


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToLoginPage() {
        driver.findElement(loginMenu).click();
    }

    public void validatedOnLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
        assertTrue(titleElement.isDisplayed());
        assertEquals("Log in", titleElement.getText());
    }

    public void inputUsername(String username) {
        switch (username) {
            case "existing_user":
                String existing_username = prefs.get("new_user", null);
                driver.findElement(usernameTextBox).sendKeys(existing_username);
                break;

            case "existing_user_with_space":
                String username_with_space = " " + prefs.get("new_user", null);
                driver.findElement(usernameTextBox).sendKeys(username_with_space);
                break;

            default:
                driver.findElement(usernameTextBox).sendKeys(username);
                break;
        }
    }

    public void inputPassword(String password) {
        if (password.equals("existing_password")) {
            String existing_password = prefs.get("new_password", null);
            driver.findElement(passwordTextBox).sendKeys(existing_password);
        } else {
            driver.findElement(passwordTextBox).sendKeys(password);
        }
    }

    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement loginElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
        loginElement.click();
    }

    public void validationAlreadyLoggedIn(String username) {
        String existing_username = prefs.get("new_user", null);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement welcomeUserElement = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeElement));

        assertTrue(welcomeUserElement.isDisplayed());

        if(username.equals("existing_user")) {
            assertEquals("Welcome " + existing_username, welcomeUserElement.getText());
        } else {
            System.out.println("red");
        }

    }
}
