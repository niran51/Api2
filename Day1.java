package Day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class day1 {

    @Test
    public void getUsers() {
        RestAssured.baseURI = "https://reqres.in/api";
        Response response =
            RestAssured
            .given()
            .when()
            .get("/users/2");

        response.then().statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", equalTo("janet.weaver@reqres.in"))
            .body("data.first_name", equalTo("Janet"))
            .body("data.last_name", equalTo("Weaver"))
            .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
            .log().all();
    }

    @Test
    public void testCreateUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        String requestBody = "{\"name\": \"John Doe\", \"job\": \"Software Engineer\"}";

        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/users");

        response.then().statusCode(201)
            .body("name", equalTo("John Doe"))
            .body("job", equalTo("Software Engineer"));
    }

    @Test
    public void updateUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        String requestBody = "{\"name\": \"Payal Devkota\", \"job\": \"Software Engineer\"}";

        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .put("/users/2");

        response.then().statusCode(200)
            .body("name", equalTo("Payal Devkota"))
            .body("job", equalTo("Software Engineer"));
    }

    @Test
    public void deleteUsers() {
        RestAssured.baseURI = "https://reqres.in/api";

        Response response = RestAssured
            .given()
            .when()
            .delete("/users/2");

        response.then().statusCode(204);
    }
}