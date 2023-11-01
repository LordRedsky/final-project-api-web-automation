package com.redsky.web.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.redsky.api"},
        features = {"src/test/resources/web/features"},
        plugin = {
                "pretty",
                "html:reports/test-report-api.html",
                "json:reports/test-report-api.json"
        },
        monochrome = true
)
public class CucumberWEBTest {}
