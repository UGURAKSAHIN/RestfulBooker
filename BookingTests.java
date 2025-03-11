package RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BookingTests {

    static String URL = "https://restful-booker.herokuapp.com";
    static int bookingID;


    @Test
    public void getBookingTest(){

        Response response = given()
                .when()
                .get(URL + "/booking")
                .then()
                .statusCode(200)
                .body("firstname", notNullValue())
                .extract()
                .response();
        response.prettyPrint();

        String firstname = response.jsonPath().getString("firstname");
        Assertions.assertNotNull(firstname);
    }

    @Test
    public void testCreateBooking() {

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2025-03-11");
        bookingDates.put("checkout", "2025-03-12");

        JSONObject payload = new JSONObject();
        payload.put("firstname", "Jim");
        payload.put("lastname", "Brown");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .when()
                .post(URL + "/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("Jim"))
                .body("booking.lastname", equalTo("Brown"))
                .extract()
                .response();

        bookingID = response.jsonPath().getInt("bookingid");
        System.out.println("Created Booking ID: " + bookingID);
    }
    @Test
    public void testUpdateBooking() {

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2025-04-11");
        bookingDates.put("checkout", "2025-06-12");

        JSONObject payload = new JSONObject();
        payload.put("firstname", "Jerry");
        payload.put("lastname", "Brick");
        payload.put("totalprice", 119);
        payload.put("depositpaid", false);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "snack");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .when()
                .post(URL + "/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("Jerry"))
                .body("booking.lastname", equalTo("Brick"))
                .extract()
                .response();
        response.prettyPrint();
    }
}
