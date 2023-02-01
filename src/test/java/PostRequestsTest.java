import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Card;
import org.example.Profile;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostRequestsTest {

    private final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Mzk4NWNlNjgwODNjMzAwNDIzM2Q4M2MiLCJpYXQiOjE2NzUxODk5NDMsImV4cCI6MTY3NTc5NDc0M30.5RuME5yVysEOUQzu7ewQaYQIS-rymnsjxWnkd3-y2NQ";
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }
    @Test
    public void createNewPlaceAndCheckResponse(){
        //File json = new File("src/test/resources/newCard.json");
        Card card = new Card("Интересное место", "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(TOKEN)
                        .and()
                        .body(card)
                        .when()
                        .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }

    @Test
    public void updateProfileAndCheckStatusCode(){
        //File json = new File("src/test/resources/patchAboutMe.json");
        Profile profile = new Profile("Василий Васильев", "Самый крутой исследователь");

        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(TOKEN)
                        .and()
                        .body(profile)
                        .when()
                        .patch("/api/users/me");
        response.then().assertThat().body("data.name", equalTo("Василий Васильев"))
                .and()
                .statusCode(200);
    }
}
