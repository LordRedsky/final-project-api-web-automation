package com.redsky.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartPage {
    WebDriver driver;

    //FROM HOMEPAGE
    By productCard = By.cssSelector("[class=\"col-lg-4 col-md-6 mb-4\"]");
    By productName = By.xpath(".//div/div/h4/a");
    By nextButton = By.id("next2");

    //FROM CART PAGE
    By cartMenu = By.xpath("//*[@id=\"cartur\"]");
    By cartTitle = By.xpath("//*[@id=\"page-wrapper\"]/div/div[1]/h2");
    By addToCartButton = By.cssSelector("a.btn.btn-success.btn-lg");
    By totalPrice = By.id("totalp");
    By priceCard = By.xpath("//*[@id=\"tbodyid\"]/tr/td[3]");
    By deleteButton = By.xpath("//td[4]/a");



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
            productElement.click();

            WebElement addToCartElement = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            addToCartButton
                    )
            );
            addToCartElement.click();

            wait.until(ExpectedConditions.alertIsPresent()).accept();
            driver.navigate().back();
            driver.navigate().back();
        }
    }

    public void validateProductOnCart(List<String> products) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        for (String expectedProduct : products) {
            WebElement productExpectedOnCart = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            productNameOnCartPage(expectedProduct)
                    )
            );

            String actualProductOnCart = productExpectedOnCart.getText();

            System.out.println(actualProductOnCart);
            System.out.println(expectedProduct);
            assertEquals(expectedProduct, actualProductOnCart);
        }
    }

    public void verifyTotalPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> priceElements = driver.findElements(priceCard);
        int totalPrices = 0;

        for (WebElement priceElement : priceElements) {
            WebElement price = wait.until(ExpectedConditions.visibilityOf(priceElement));
            String nominalPriceString = price.getText();
            int nominalPriceInteger = Integer.parseInt(nominalPriceString);

            totalPrices += nominalPriceInteger;
        }

        WebElement totalElements = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));

        assertEquals(String.valueOf(totalPrices), totalElements.getText());
    }

    public void removeItemFromCart(List<String> products) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        for (String product : products) {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            productNameOnCartPage(product)
                    )
            );
            WebElement productRow = driver.findElement(
                    productNameOnCartPage(product)
            );
            WebElement deleteElement = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            productRow.findElement(deleteButton)
                    )
            );

            deleteElement.click();
        }
    }

    public void removeItemQuantity(int totalClicks, List<String> products) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        for (String product : products) {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            productNameOnCartPage(product)
                    )
            );
            WebElement productRow = driver.findElement(
                    productNameOnCartPage(product)
            );
            WebElement deleteElement = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            productRow.findElement(deleteButton)
                    )
            );

            for (int i = 1; i <= totalClicks; i++) {
                deleteElement.click();
                Thread.sleep(1000);
            }
        }
    }

    public void addQuantityProduct(int totalClick, List<String> products) {
        for (String product : products) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement productElement = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            productNameOnHomePage(product)
                    )
            );
            productElement.click();

            WebElement addToCartElement = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            for (int i = 0; i < totalClick; i++) {
                addToCartElement.click();
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            }

            driver.navigate().back();
            driver.navigate().back();
        }
    }

    public void validateQuantityProduct(int totalProducts, List<String> products) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        for (String product : products) {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            productNameOnCartPage(product)
                    )
            );

            int totalQuantity = driver.findElements(
                    productNameOnCartPage(product)
            ).size();

            assertEquals(totalProducts, totalQuantity);
        }
    }

    public void addAllProductToCart() throws InterruptedException {
        Preferences prefs = Preferences.userNodeForPackage(SignupPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<String> allProductNames = new ArrayList<>();
        int totalPages = 2;

        for (int i = 1; i < totalPages ; i++) {
            Thread.sleep(1000);
            List<WebElement> productElements = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(productCard)
            );

//            System.out.println(productElements.size());
            for (int j = 0; j < productElements.size(); j++) {
                Thread.sleep(1000);
                String index_products = String.valueOf(j + 1);
                WebElement productElement = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                driver.findElement(By.xpath(
                                        "(.//div/div/h4/a)[" + index_products + "]")
                                )
                        )
                );

               String productNameString = productElement.getText();
               allProductNames.add(productNameString);
               productElement.click();

               WebElement addToCartElement = wait.until(
                 ExpectedConditions.elementToBeClickable(addToCartButton)
               );

                Thread.sleep(500);
               addToCartElement.click();


               wait.until(ExpectedConditions.alertIsPresent()); //.accept();
                Thread.sleep(500);
                driver.switchTo().alert().accept();

                driver.navigate().back();
                driver.navigate().back();

                prefs.put("listProductNames", String.join(",", allProductNames));
            }


            WebElement nextElement = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            nextElement.click();
            Thread.sleep(500);
        }

//        System.out.println(allProductNames);
//        System.out.println(prefs.get("listProductNames", null));
    }

    public void validatdAllProductOnCart() {
        Preferences prefs = Preferences.userNodeForPackage(SignupPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String[] expectedProductName = prefs.get("listProductNames", null).split(",");

        List<String> actualNameProductOnCart = new ArrayList<>();
        List<WebElement> productNameElement = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]")
                )
        );

//        System.out.println(expectedProductName.length);
        for (WebElement productName : productNameElement) {
            String nameProductOnCart = productName.getText();
//            System.out.println(nameProductOnCart);
        }
    }

}
