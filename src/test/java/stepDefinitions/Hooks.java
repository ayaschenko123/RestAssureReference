package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeTest() throws IOException {
        //should run only when placeID is null

        StepDefinition stepDefinition = new StepDefinition();

        if(StepDefinition.place_id == null) {
            stepDefinition.add_place_payload_test_with("Test1", "Test2", "Test3");
            stepDefinition.user_calls_with_post_http_request("addPaceAPI", "POST");
            stepDefinition.verify_place_id_created_maps_to_using("Test1", "getPlaceAPI");
        }
    }
}
