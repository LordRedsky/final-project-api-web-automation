package com.redsky.api.stepDef;

import com.redsky.api.helper.Endpoint;
import com.redsky.api.helper.Payload;
import com.redsky.api.utilities.GetJSONSchemaFile;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class StepDefs {

    private RequestSpecification request;
    private Response response;
    JSONObject bodyReq;
    Preferences prefs = Preferences.userNodeForPackage(StepDefs.class);

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
                if (endpoint_name.equals("user")) {
                    response = request.when().get(Endpoint.user_url);
                } else if (endpoint_name.equals(" user")) {
                    response = request.when().get(Endpoint.baseURL + " user");
                } else if (endpoint_name.equals("tag")) {
                    response = request.when().get(Endpoint.tag_url);
                } else if (endpoint_name.equals(" tag")) {
                    response = request.when().get(Endpoint.baseURL + " tag");
                }
                break;

            case "POST":
                if (endpoint_name.equals("create")) {
                    response = request.when().post(Endpoint.user_create_url);
                }
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
        File JSONFile = GetJSONSchemaFile.getJSONSchema(JSONSchema);

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JSONFile));
    }

    @When("user send a {string} request with specific id {string}")
    public void userSendARequestWithSpecificId(String method, String specific_id) {
        switch (method) {
            case "GET":
                response = request.when().get(Endpoint.user_specific_url + specific_id);
                break;
            case "PUT":
                String updatedID = prefs.get(specific_id, null);
                response = request.when().put(Endpoint.user_specific_url + updatedID);
                break;
            case "DELETE":
                break;
        }
    }

    @And("the response should be contain:")
    public void theResponseShouldBeContain(Map<String, String> values) {
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

    @And("the response body {string} will be named as {string} and will be used for next test")
    public void theResponseBodyWillBeNamedAsAndWillBeUsedForNextTest(String id, String categoryID) throws BackingStoreException {
        if (categoryID.equals("reusableID_01")) {
            String id_user = response.jsonPath().getString(id);
            System.out.println(id_user);
            prefs.put("ID_required", id_user);
        } else if (categoryID.equals("reusableID_02")) {
            String id_user = response.jsonPath().getString(id);
            prefs.put("ID_full", id_user);
        }
    }


    @When("user send a {string} request with id {string}")
    public void userSendARequestWithId(String method, String deletedID) throws BackingStoreException {
        String ID_01 = prefs.get("ID_required", null);
        String ID_02 = prefs.get("ID_full", null);

        switch (method) {
            case "PUT":
                break;

            case "DELETE":
                if (deletedID.equals("ID_required")) {
                    response = request.when().delete(Endpoint.user_specific_url + ID_01);
                    System.out.println(ID_01);
                    prefs.remove(deletedID);
                } else if (deletedID.equals("ID_full")) {
                    System.out.println(ID_02);
                    response = request.when().delete(Endpoint.user_specific_url + ID_02);
                    prefs.remove(deletedID);
                }
                break;
        }
    }

    @And("user prepare {string} body for {string} method with:")
    public void userPrepareBodyForMethodWith(String fields, String method, Map<String, String> body) {

        switch (method) {
            case "POST", "PUT":
                if (fields.equals("required fields")) {
                    bodyReq = Payload.getRequiredFieldBody(body);
                    request.body(bodyReq.toString());
                } else if (fields.equals("full fields")) {
                    bodyReq = Payload.getFullFieldBody(body);
                    request.body(bodyReq.toString());
                }
                break;
        }
    }

    @And("the response body should contain:")
    public void theResponseBodyShouldContain(Map<String, String> expectedValues) {
        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            String actual = response.path(key).toString();
            assertThat(actual, equalTo(value));
        }
    }

    @And("the response body should contain {string}, with message {string} and {string}")
    public void theResponseBodyShouldContainWithMessageAnd(String error, String errorMessage_1, String errorMessage_2) {
    response.then().assertThat()
            .body("error", Matchers.equalTo(error))
            .body("data.firstName", Matchers.equalTo(errorMessage_1))
            .body("data.lastName", Matchers.equalTo(errorMessage_2));
    }
}
