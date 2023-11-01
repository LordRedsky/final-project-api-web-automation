package com.redsky.web.stepDefs;

import com.redsky.web.pages.Homepage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeStepDefs extends BaseTest {

    Homepage homepage;

    @When("user click {string} button")
    public void userClickButton(String category) throws InterruptedException {
        Thread.sleep(1000);
        homepage = new Homepage(driver);

    }

    @Then("only {string} products will be displayed")
    public void onlyProductsWillBeDisplayed(String category) {
    }
}
