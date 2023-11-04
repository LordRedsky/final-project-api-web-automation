package com.redsky.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartPage {
    WebDriver driver;
    //    ""
    By cartMenu = By.xpath("//*[@id=\"cartur\"]");
    By cartTitle = By.xpath("//*[@id=\"page-wrapper\"]/div/div[1]/h2");
    By addToCartButton = By.cssSelector("a.btn.btn-success.btn-lg");
    By totalPrice = By.id("totalp");
    By priceCard = By.xpath("//*[@id=\"tbodyid\"]/tr/td[3]");
    By deleteButton = By.xpath("//td[4]/a");


    //FROM HOMEPAGE
    By productCard = By.cssSelector("[class=\"col-lg-4 col-md-6 mb-4\"]"); // "div.col-md-6:nth-child(1)"
    By productName = By.xpath(".//div/div/h4/a");
    By nextButton = By.id("next2");

    private By productNameOnHomePage(String name) {
        return By.xpath("//a[contains(text(), '" + name + "')]");
    }

    private By productNameOnCartPage(String name) {
        return By.xpath("//td[contains(text(), '" + name + "')]");
    }

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement cartElement = wait.until(ExpectedConditions.elementToBeClickable(cartMenu));

        cartElement.click();
    }

    public void validatedOnCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle));

        assertTrue(titleElement.isDisplayed());
        assertEquals("Products", titleElement.getText());
    }

    public void addProductsToCart(List<String> products) {
        for (String product : products) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement productElement = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            productNameOnHomePage(product)
                    )
            );
            WebElement addToCartElement = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            addToCartButton
                    )
            );

            productElement.click();
            addToCartElement.click();

            wait.until(ExpectedConditions.alertIsPresent()).accept();
            driver.navigate().back();
        }
    }

}
