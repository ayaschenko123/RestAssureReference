package stepDefinitions;

import POJO.AddPlace;
import POJO.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utilities {
    RequestSpecification res;
    ResponseSpecification responseSpec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;




    @Given("Add Place Payload test with {string} {string} {string}")
    public void add_place_payload_test_with(String name, String language, String address) throws IOException {

//        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if (method.equalsIgnoreCase("POST")){
        response = res.when().post(resourceAPI.getResource())
                .then().spec(responseSpec).extract().response();}
        else if (method.equalsIgnoreCase("GET")){
            response = res.when().get(resourceAPI.getResource())
                    .then().spec(responseSpec).extract().response();}
        }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        assertEquals(getJsonPath(response, key), value);
    }


    @Then("verify place_ID created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        //prepare request spec
        //get API call
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_post_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);

    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

}
