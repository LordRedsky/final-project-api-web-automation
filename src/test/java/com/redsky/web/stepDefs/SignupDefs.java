package com.redsky.web.stepDefs;

import com.redsky.web.pages.SignupPage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignupDefs extends BaseTest {
    SignupPage signupPage;

    @When("user click signup menu")
    public void userClickSignupMenu() {
        signupPage = new SignupPage(driver);
        signupPage.goToSignupPage();
        signupPage.validatedOnSignupPage();
    }

    @And("user input username with {string}")
    public void userInputUsernameWith(String username) {
        signupPage.inputUsername(username);
    }

    @And("user input password with {string}")
    public void userInputPasswordWith(String password) {
        signupPage.inputPassword(password);
    }

    @And("user click signup button")
    public void userClickSignupButton() {
        signupPage.clickSignupButton();
    }

    @Then("user will get a message said {string}")
    public void userWillGetAMessageSaid(String message) {
        signupPage.validationResponseMessage(message);
    }


}
