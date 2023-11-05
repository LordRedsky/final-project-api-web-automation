package com.redsky.web.stepDefs;

import com.redsky.web.pages.CartPage;
import com.redsky.web.pages.CheckoutPage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CheckoutStepDefs extends BaseTest {
    //    @And("user add {string} product to cart")
//    public void userAddProductToCart(String productQuantity) {
//    }
    CheckoutPage checkoutPage;
//    CartPage cartPage;

    @And("user click place order button")
    public void userClickPlaceOrderButton() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickPlaceOrderBtn();
    }

    @And("user will see place order form")
    public void userWillSeePlaceOrderForm() {
        checkoutPage.validateOnCheckoutForm();
        checkoutPage.validatedTotalPrice();
    }

    @And("user fill the form with:")
    public void userFillTheFormWith(Map<String, String> formData) {
        String name = formData.get("Name");
        String country = formData.get("Country");
        String city = formData.get("City");
        String cc = formData.get("Credit Card");
        String month = formData.get("Month");
        String year = formData.get("Year");

        checkoutPage.fillTheCheckoutForm(name, country, city, cc, month, year);

    }

    @Then("user click purchase button")
    public void userClickPurchaseButton() {
        checkoutPage.clickPurchaseBtn();
    }

    @And("user will see a pop up message said {string}")
    public void userWillSeeAPopUpMessageSaid(String response_message) {
        checkoutPage.verifyTransaction(response_message);
    }

    @Then("user click close button on form page")
    public void userClickCloseButtonOnFormPage() {
        checkoutPage.clickCloseBtn();
    }

    @And("user fill the form with {string}")
    public void userFillTheFormWith(String fillIndicator) {
        if (fillIndicator.equals("all_fields_blank")) {
            checkoutPage.fillTheCheckoutForm(
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            );
        } else if (fillIndicator.equals("name_fields_blank")) {
            checkoutPage.fillTheCheckoutForm(
                    "",
                    "Indonesia",
                    "Manado",
                    "232425",
                    "Oktober",
                    "2023");
        } else if (fillIndicator.equals("cc_fields_blank")) {
            checkoutPage.fillTheCheckoutForm(
                    "Redsky",
                    "Indonesia",
                    "Manado",
                    "",
                    "Oktober",
                    "2023");
        }
    }
}
