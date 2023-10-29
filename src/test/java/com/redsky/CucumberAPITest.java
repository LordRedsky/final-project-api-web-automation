package com.redsky;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.redsky.api"},
        features = {"src/test/features/api"},
        plugin = {
                "pretty",
                "html:reports/test-report-api.html",
                "json:reports/test-report-api.json"
        }
)
public class CucumberAPITest {}
