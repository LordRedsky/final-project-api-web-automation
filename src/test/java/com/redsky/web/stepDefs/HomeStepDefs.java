package com.redsky.web.stepDefs;

import com.redsky.web.pages.HomePage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeStepDefs extends BaseTest {

    HomePage homepage;

    @Then("all menu and button displayed properly")
    public void allMenuAndButtonDisplayedProperly() throws InterruptedException {
        Thread.sleep(1000);
        homepage = new HomePage(driver);
        homepage.validateHomepage();
    }

//    @When("user click {string} button")
//    public void userClickButton(String category) throws InterruptedException {
//        Thread.sleep(1000);
//        homepage = new HomePage(driver);
//    }
//
//    @Then("only {string} products will be displayed")
//    public void onlyProductsWillBeDisplayed(String category) {
//
//    }


    @And("user will be directed back to homepage")
    public void userWillBeDirectedBackToHomepage() throws InterruptedException {
        homepage = new HomePage(driver);
//        homepage.goToHomepage();
//        Thread.sleep(1500);
        homepage.validateHomepage();
    }
}
