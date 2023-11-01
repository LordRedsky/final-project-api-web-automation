package com.redsky.web.stepDefs;

import com.redsky.web.pages.Homepage;
import com.redsky.web.utility.BaseTest;
import io.cucumber.java.en.Given;

public class BrowserSetup extends BaseTest {

    Homepage homepage;

    @Given("user is on homepage")
    public void userIsOnHomepage() {
        homepage = new Homepage(driver);
        homepage.goToHomepage();
    }
}
