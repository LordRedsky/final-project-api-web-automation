package com.redsky.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class stepDefs {

    private RequestSpecification request;
    private Response response;
    private JSONObject requestBody;


    @Given("all request header is properly setup")
    public void allRequestHeaderIsProperlySetup() {
        String access_token = "653d0b19a38ceded3354529f";
        request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("app-id", access_token);
    }

    @When("user send a {string} request to the {string} endpoint")
    public void userSendARequestToTheEndpoint(String method, String endpoint_name) {
        switch (method) {
            case "GET":
                if (endpoint_name.equals("user")){
                    response = request.when().get(endpoint.user_url);
                }
                else if (endpoint_name.equals(" user")) {
                    response = request.when().get(endpoint.baseURL + " user");
                }
                else if (endpoint_name.equals("tag")) {
                    response = request.when().get(endpoint.tag_url);
                }
                else if (endpoint_name.equals(" tag")) {
                    response = request.when().get(endpoint.baseURL + " tag");
                }
                break;

            case "POST":
                response = request.when().post(endpoint.user_create_url);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }
    }

    @Then("status code should be {int}")
    public void statusCodeShouldBe(int status_code) {
        int actual_status_code = response.getStatusCode();

        assertEquals(status_code, actual_status_code);
    }

    @And("the response should be match with {string}")
    public void theResponseShouldBeMatchWith(String JSONSchema) {
        String pathJSON = "src/test/resources/api/JSONSchemas/" + JSONSchema;
        File JSONFile = new File(pathJSON);

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JSONFile));
    }

    @When("user send a {string} request with specific id {string}")
    public void userSendARequestWithSpecificId(String method, String specific_id) {
        switch (method) {
            case "GET":
                response = request.when().get(endpoint.user_specific_url + specific_id);
                break;
            case "PUT":
                break;
            case "DELETE":
                break;
        }
    }

    @And("the response should be contain:")
    public void theResponseShouldBeContain(Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            System.out.println(entry.getKey());
        }
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String actual = response.path(key).toString();

            assertThat(actual, equalTo(value));
        }
    }

    @When("user set the request parameters to:")
    public void userSetTheRequestParametersTo(Map<String, String> params) {
        String limit = params.get("limit");
        String page = params.get("page");

        request.params("limit", limit, "page", page);
    }
}
