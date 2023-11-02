package com.redsky.web.stepDefs;

import com.redsky.web.pages.Homepage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeStepDefs extends BaseTest {

    Homepage homepage;

    @Then("all menu and button displayed properly")
    public void allMenuAndButtonDisplayedProperly() throws InterruptedException {
        Thread.sleep(1000);
        homepage = new Homepage(driver);
        homepage.validateHomepage();
    }

    @When("user click {string} button")
    public void userClickButton(String category) throws InterruptedException {
        Thread.sleep(1000);
        homepage = new Homepage(driver);
    }

    @Then("only {string} products will be displayed")
    public void onlyProductsWillBeDisplayed(String category) {

    }


}
