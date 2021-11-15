import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;


public class ApiTestsStepdefinitions {
    String testApi = "https://reqres.in/api";

    @Given("existing Server application https:\\/\\/reqres.in\\/api")
    public void existingServerApplicationHttpsReqresInApi() {
        given().when().get(testApi).then().assertThat().statusCode(404);
    }

    @Then("on GET request to \\/users it returns expected users list")
    public void onGETRequestToUsersItReturnsExpectedUsersList() {
        String testEndpoint = testApi + "/users";
        given().when().get(testEndpoint).then().
                assertThat().
                body("page", greaterThan(0)).
                body("per_page", greaterThan(0)).
                body("data.size()", greaterThan(0)).
                body("data.id", everyItem((notNullValue()))).
                body("data.email", everyItem((notNullValue()))).
                body("data.first_name", everyItem((notNullValue()))).
                body("data.last_name", everyItem((notNullValue()))).
                body("data.avatar", everyItem((notNullValue())));
    }

    @Then("on GET request to \\/users\\/{int} it returns expected welcome message")
    public void onGETRequestToUsersItReturnsExpectedWelcomeMessage(int arg0) {
        String testEndpoint = testApi + "/users/" + arg0;

        given().
                queryParam("id", arg0).
                when().
                get(testEndpoint).
                then().
                statusCode(200).
                body("data.id", equalTo(2)).
                body("data.email", equalTo("janet.weaver@reqres.in")).
                body("data.first_name", equalTo("Janet")).
                body("data.last_name", equalTo("Weaver")).
                body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
    }

    @Then("on GET request to \\/users\\/{int} it returns {int} status code")
    public void onGETRequestToUsersItReturnsStatusCode(int arg0, int arg1) {
        String testEndpoint = testApi + "/users/" + arg0;

        given().
                queryParam("id", arg0).
                when().
                get(testEndpoint).
                then().
                statusCode(arg1);
    }


    @Then("POST new registration email <email> and password <password> to \\/register endpoint returns expected status <status> and response <response>;")
    public void postNewRegistrationEmailEmailAndPasswordPasswordToRegisterEndpointReturnsExpectedStatusStatusAndResponseResponse(String email, String password, int status, String response) {
        String testEndpoint = testApi + "/register/";
        given().when().get(testEndpoint).then().log().body();
    }
}