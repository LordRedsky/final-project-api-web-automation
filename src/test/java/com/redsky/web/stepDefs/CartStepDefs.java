package com.redsky.web.stepDefs;

import com.redsky.web.pages.CartPage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Collections;
import java.util.List;

public class CartStepDefs extends BaseTest {

    CartPage cartPage;

    @When("user click add to cart button of these products:")
    public void userClickAddToCartButtonOfTheseProducts(List<String> products) throws InterruptedException {
        cartPage = new CartPage(driver);
        cartPage.addProductsToCart(products);
    }

    @And("user go to cart page")
    public void userGoToCartPage() {
        cartPage.goToCartPage();
        cartPage.validatedOnCartPage();
    }

    @Then("the cart should contain products:")
    public void theCartShouldContainProducts(List<String> products) {
        cartPage.validateProductOnCart(products);
    }

    @And("the total price is correct")
    public void theTotalPriceIsCorrect() throws InterruptedException {
        cartPage.verifyTotalPrice();
    }

    @And("user click delete button for these products:")
    public void userClickDeleteButtonForTheseProducts(List<String> products) {
        cartPage.removeItemFromCart(products);
    }

    @And("user click delete button {int} times for these products:")
    public void userClickDeleteButtonTimesForTheseProducts(int clicks, List<String> products) throws InterruptedException {
        cartPage.removeItemQuantity(clicks, products);
        Thread.sleep(1_500);
    }

    @When("user click add to cart button {int} times for these products:")
    public void userClickAddToCartButtonTimesForTheseProducts(int totalClicks, List<String> products) {
        cartPage = new CartPage(driver);
        cartPage.addQuantityProduct(totalClicks, products);
    }

    @Then("total cart item for these product should be {int}:")
    public void totalCartItemForTheseProductShouldBe(int totalProducts, List<String> products) throws InterruptedException {
        cartPage.validateQuantityProduct(totalProducts, products);
        Thread.sleep(3_000);
    }


    @When("user click add to cart for all products on cart")
    public void userClickAddToCartForAllProductsOnCart() throws InterruptedException {
        cartPage = new CartPage(driver);
        Thread.sleep(1_500);
        cartPage.addAllProductToCart();
        Thread.sleep(500);
    }

    @Then("the cart should contain all products")
    public void theCartShouldContainAllProducts() throws InterruptedException {
        cartPage.validatedAllProductOnCart();
    }

    @And("user add {string} product to cart")
    public void userAddProductToCart(String addedProduct) throws InterruptedException {
        cartPage = new CartPage(driver);
        if (addedProduct.equals("one")) {
            List<String> product = Collections.singletonList("Samsung galaxy s6");
            Thread.sleep(3_500);
            cartPage.addProductsToCart(product);
        }
    }

    @And("user will be directed back to cart page")
    public void userWillBeDirectedBackToCartPage() {
        cartPage = new CartPage(driver);
        cartPage.validatedOnCartPage();
    }

}
