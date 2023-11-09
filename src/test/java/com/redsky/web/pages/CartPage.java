package com.redsky.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
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
        Preferences prefs = Preferences.userNodeForPackage(SignupPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle));
        WebElement totalPriceFromCart = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));

//        System.out.println("from cart " + totalPriceFromCart.getText());
        String priceFromCart = "Total: " + totalPriceFromCart.getText();
        prefs.put("cartPrice", priceFromCart);

        assertTrue(titleElement.isDisplayed());
        assertEquals("Products", titleElement.getText());
    }

    public void addProductsToCart(List<String> products) throws InterruptedException {
//        System.out.println(products);
        for (String product : products) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            Thread.sleep(3_500);
            WebElement productElement = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            productNameOnHomePage(product)
                    )
            );
            productElement.click();

            Thread.sleep(3_500);
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

    public void verifyTotalPrice() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> priceElements = driver.findElements(priceCard);
        int totalPrices = 0;

//        Thread.sleep(1_500);
        for (WebElement priceElement : priceElements) {
            WebElement price = wait.until(ExpectedConditions.visibilityOf(priceElement));
            String nominalPriceString = price.getText();
            int nominalPriceInteger = Integer.parseInt(nominalPriceString);
//            Thread.sleep(1_500);
            totalPrices += nominalPriceInteger;
        }

        WebElement totalElements = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));
//        Thread.sleep(1_500);
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        for (String product : products) {
            Thread.sleep(3_500);
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            productNameOnCartPage(product)
                    )
            );
            Thread.sleep(3_500);
            WebElement productRow = driver.findElement(
                    productNameOnCartPage(product)
            );
            Thread.sleep(3_500);
            WebElement deleteElement = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            productRow.findElement(deleteButton)
                    )
            );

//            Thread.sleep(1_500);

            for (int i = 1; i <= totalClicks; i++) {
                Thread.sleep(2_500);
                deleteElement.click();
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        for (String product : products) {
            Thread.sleep(4_000);
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            productNameOnCartPage(product)
                    )
            );

            Thread.sleep(4_000);
            int totalQuantity = driver.findElements(
                    productNameOnCartPage(product)
            ).size();

            Thread.sleep(1_500);
            assertEquals(totalProducts, totalQuantity);
        }
    }

    public void addAllProductToCart() throws InterruptedException {
        Preferences prefs = Preferences.userNodeForPackage(SignupPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<String> allProductNames = new ArrayList<>();
        int totalPages = 2;

        for (int i = 1; i < totalPages; i++) {
            Thread.sleep(3_500);
            List<WebElement> productElements = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(productCard)
            );
            Thread.sleep(3_500);
//            System.out.println(productElements.size());
            for (int j = 0; j < productElements.size(); j++) {
                Thread.sleep(3_500);
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

                Thread.sleep(3_500);
                addToCartElement.click();


                wait.until(ExpectedConditions.alertIsPresent()); //.accept();
                Thread.sleep(3_500);
                driver.switchTo().alert().accept();

                driver.navigate().back();
                driver.navigate().back();

                prefs.put("listProductNames", String.join(",", allProductNames));
            }


            WebElement nextElement = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            nextElement.click();
            Thread.sleep(3_500);
        }
    }

    public void validatedAllProductOnCart() throws InterruptedException {
        Preferences prefs = Preferences.userNodeForPackage(SignupPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String[] expectedProductName = prefs.get("listProductNames", null).split(",");
        int count = 0;

        Thread.sleep(3_500);
        List<WebElement> productNameElement = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]")
                )
        );
        int length = productNameElement.size();

        Thread.sleep(3_500);
        for (WebElement productName : productNameElement) {
            String nameProductOnCart = productName.getText();
//            System.out.println("dari element: "+nameProductOnCart);

            for (int i = 0; i < length; i++) {
                String name = Arrays.stream(expectedProductName).toArray()[i].toString();
//                System.out.println("dari prefs: "+ name);
                if (name.contains(nameProductOnCart)) {
                    count++;
                }
            }
        }
//        System.out.println("count: " + count);
//        System.out.println("length: " + length);
        assertEquals(length, count);
    }

}
