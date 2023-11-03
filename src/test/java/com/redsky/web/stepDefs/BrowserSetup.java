package com.redsky.web.stepDefs;

import com.redsky.web.pages.HomePage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.Given;

public class BrowserSetup extends BaseTest {

    HomePage homepage;

    @Given("user is on homepage")
    public void userIsOnHomepage() {
        homepage = new HomePage(driver);
        homepage.goToHomepage();
        homepage.validateHomepage();
    }
}
