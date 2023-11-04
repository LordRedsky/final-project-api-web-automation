package com.redsky.web.stepDefs;

import com.redsky.web.pages.AboutusPage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AboutusStepDefs extends BaseTest {

    AboutusPage aboutusPage;
    @When("user click About us menu")
    public void userClickAboutUsMenu() {
        aboutusPage = new AboutusPage(driver);
        aboutusPage.goToAboutUsPage();
        aboutusPage.validateOnAboutUsPage();
    }

    @Then("user should see information about the company")
    public void userShouldSeeInformationAboutTheCompany() {
        aboutusPage.validationPupUpVideoAboutUs();
    }

    @And("user click close button")
    public void userClickCloseButton() {
        aboutusPage = new AboutusPage(driver);
        aboutusPage.clickCloseButton();
    }
}
