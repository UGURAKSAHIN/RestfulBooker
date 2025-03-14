package restAssured;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Authentication {

    private String token;

    @Test
    public void testAuth(){

        String Url = "https://restful-booker.herokuapp.com/auth";

        JSONObject reqBody = new JSONObject();
        reqBody.put("username", "admin");
        reqBody.put("password", "password123");
        System.out.println(reqBody);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post(Url);
        response.prettyPrint();

        JsonPath actualBody = response.jsonPath();

        response.then().assertThat().contentType(ContentType.JSON).statusCode(200);

        token = response.jsonPath().getString("token");
        System.out.println(token);
    }
    public String getToken(){
        return token;
    }
}
