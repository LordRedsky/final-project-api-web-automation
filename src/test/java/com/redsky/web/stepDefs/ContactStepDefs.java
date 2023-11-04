package com.redsky.web.stepDefs;

import com.redsky.web.pages.ContactPage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactStepDefs extends BaseTest {

    ContactPage contactPage;
    @When("user click contact menu")
    public void userClickContactMenu() {
        contactPage = new ContactPage(driver);
        contactPage.goToContactPage();
        contactPage.validatedOnContactPage();
    }

    @And("user input contact email with {string}")
    public void userInputContactEmailWith(String email) {
        contactPage.inputEmail(email);
    }

    @And("user input contact name with {string}")
    public void userInputContactNameWith(String name) {
        contactPage.inputName(name);
    }

    @And("user input message with {string}")
    public void userInputMessageWith(String message) {
        contactPage.inputMessage(message);
    }

    @And("user click send message button")
    public void userClickSendMessageButton() {
        contactPage.clickSendMessage();
    }

    @And("user click close button on contact page")
    public void userClickCloseButtonOnContactPage() {
        contactPage = new ContactPage(driver);
        contactPage.clickCloseBtn();
    }
}
