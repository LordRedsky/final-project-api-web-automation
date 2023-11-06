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

public class CheckoutPage {
    WebDriver driver;

    //FROM CART PAGE
    By totalPriceFromCartPage = By.id("totalp");
    By placeOrderBtn = By.xpath("//button[contains(text(), 'Place Order')]");
    //FROM CHECKOUT FORM
    By totalFromCheckoutForm = By.id("totalm");
    By checkoutTitle = By.xpath("//*[@id=\"orderModalLabel\"]");
    By totalFromCheckoutPage = By.id("totalm");
    By nameTextBox = By.xpath("//*[@id=\"name\"]");
    By countryTextBox = By.xpath("//*[@id=\"country\"]");
    By cityTextBox = By.xpath("//*[@id=\"city\"]");
    By creditCardTextBox = By.xpath("//*[@id=\"card\"]");
    By monthTextBox = By.xpath("//*[@id=\"month\"]");
    By yearTextBox = By.xpath("//*[@id=\"year\"]");

    By purchaseBtn = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]");
    By closeBtn = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]");
    By okeBtn = By.xpath("//button[contains(text(), 'OK')]");
    By successNotification = By.xpath("/html/body/div[10]/h2");
//    "//*[@id="orderModal"]/" "/html/body/div[3]/div/div/div[3]/button[1]"

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickPlaceOrderBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement placeOrderBtnElement = wait.until(
                ExpectedConditions.elementToBeClickable(placeOrderBtn)
        );

        placeOrderBtnElement.click();
    }

    public void validateOnCheckoutForm() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement titleElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(checkoutTitle)
        );
        Thread.sleep(2_000);

        assertTrue(titleElement.isDisplayed());
        assertEquals("Place order", titleElement.getText());

    }

    public void validatedTotalPrice() {
        Preferences prefs = Preferences.userNodeForPackage(SignupPage.class);
        String priceFromCheckoutForm = driver.findElement(totalFromCheckoutPage).getText();

        String priceFromCartPage = prefs.get("cartPrice", null);

        assertTrue(priceFromCartPage.contains(priceFromCheckoutForm));
    }

    public void fillTheCheckoutForm(String name, String country, String city, String cc, String month, String year) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(nameTextBox));
        WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(countryTextBox));
        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(cityTextBox));
        WebElement ccField = wait.until(ExpectedConditions.visibilityOfElementLocated(creditCardTextBox));
        WebElement monthField = wait.until(ExpectedConditions.visibilityOfElementLocated(monthTextBox));
        WebElement yearField = wait.until(ExpectedConditions.visibilityOfElementLocated(yearTextBox));

        nameField.sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        ccField.sendKeys(cc);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
    }
    public void clickPurchaseBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement purchaseBtnElement = wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseBtn));

        purchaseBtnElement.click();
    }

    public void verifyTransaction(String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification));

        assertTrue(notification.isDisplayed());
        assertEquals(message, notification.getText());

        driver.findElement(okeBtn).click();
    }

    public void clickCloseBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement closeBtnElement = wait.until(ExpectedConditions.elementToBeClickable(closeBtn));

        closeBtnElement.click();
    }
}
