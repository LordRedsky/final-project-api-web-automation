package com.redsky.web.stepDefs;

import com.redsky.web.pages.LoginPage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefs extends BaseTest {
    LoginPage loginPage;

    @When("user click login menu")
    public void userClickLoginMenu() {
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.validatedOnLoginPage();
    }

    @And("user login with username with {string}")
    public void userLoginWithUsernameWith(String username) {
        loginPage.inputUsername(username);
    }

    @And("user login with password with {string}")
    public void userLoginWithPasswordWith(String password) {
        loginPage.inputPassword(password);
    }

    @And("user click login button")
    public void userClickLoginButton() {
        loginPage.clickLoginButton();
    }

    @And("user will see an element Welcome {string}")
    public void userWillSeeAnElementWelcome(String username) {
        loginPage.validationAlreadyLoggedIn(username);
    }

}
