package com.redsky.web.pages;

import com.redsky.web.stepDefs.SignupDefs;
import com.redsky.web.utility.RandomAlphabet;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.prefs.Preferences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignupPage {
    WebDriver driver;
    Preferences prefs = Preferences.userNodeForPackage(SignupPage.class);
    By signupMenu = By.cssSelector("#signin2");
    By signUpTitle = By.xpath("//*[@id=\"signInModalLabel\"]");
    By usernameTextBox = By.cssSelector("[id=\"sign-username\"]");
    By passwordTextBox = By.cssSelector("[id=\"sign-password\"]");
    By closeButton = By.xpath("/html/body/div[2]/div/div/div[3]/button[1]");
//    ""
    By signUpButton = By.xpath("//button[contains(text(), 'Sign up')]");
    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToSignupPage() {
        driver.findElement(signupMenu).click();
    }
    public void validatedOnSignupPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpTitle));
        assertTrue(titleElement.isDisplayed());
        assertEquals("Sign up",titleElement.getText());
    }

    public void inputUsername(String username){

        switch(username) {
            case "generate_new_username":
                String random_username = RandomAlphabet.generate(8);
                driver.findElement(usernameTextBox).sendKeys(random_username);
                prefs.put("new_user", random_username);
                break;

            case "existing_user":
                String existing_user = prefs.get("new_user", null);
                driver.findElement(usernameTextBox).sendKeys(existing_user);
                break;

            case "random_user":
                String random_user = RandomAlphabet.generate(8);
                driver.findElement(usernameTextBox).sendKeys(random_user);
                break;

            case "random_character":
                System.out.println("random character");
                break;

            default:
                driver.findElement(usernameTextBox).sendKeys("");
                break;
        }
    }

    public void inputPassword(String password) {

        switch (password) {
            case "generate_new_password":
                String random_password = RandomAlphabet.generate(10);
                driver.findElement(passwordTextBox).sendKeys(random_password);
                prefs.put("new_password", password);
                break;
            case "existing_password":
                String existing_pass = prefs.get("new_password", null);
                driver.findElement(passwordTextBox).sendKeys(existing_pass);
                break;

            default:
                driver.findElement(passwordTextBox).sendKeys("");
                break;
        }
    }

    public  void clickSignupButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement signupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpButton));
        signupElement.click();
    }

    public void clickCloseButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement closeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(closeButton));
        closeElement.click();
    }

    public void validationResponseMessage(String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();

        assertEquals(message, alert.getText());
        alert.accept();
    }
}
