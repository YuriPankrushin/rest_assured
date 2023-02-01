// импортируем RestAssured
import io.restassured.RestAssured;
// импортируем Before
import io.restassured.response.Response;
import org.example.Data;
import org.example.User;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
// импортируем Test
import org.junit.Test;
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetRequestsTest {

    private final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Mzk4NWNlNjgwODNjMzAwNDIzM2Q4M2MiLCJpYXQiOjE2NzUxODk5NDMsImV4cCI6MTY3NTc5NDc0M30.5RuME5yVysEOUQzu7ewQaYQIS-rymnsjxWnkd3-y2NQ";
    // аннотация Before показывает, что метод будет выполняться перед каждым тестовым методом
    @Before
    public void setUp() {
        // повторяющуюся для разных ручек часть URL лучше записать в переменную в методе Before
        // если в классе будет несколько тестов, указывать её придётся только один раз
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    // создаём метод автотеста
    @Test
    public void getMyInfoStatusCode() {
        // метод given() помогает сформировать запрос
        given()
                // указываем протокол и данные авторизации
                .auth().oauth2(TOKEN)
                // отправляем GET-запрос с помощью метода get, недостающую часть URL (ручку) передаём в него в качестве параметра
                .get("/api/users/me")
                // проверяем, что статус-код ответа равен 200
                .then().statusCode(200);
    }

    @Test
    public void checkUserName() {
        given()
                .auth().oauth2(TOKEN)
                .get("/api/users/me")
                .then().assertThat().body("data.name", equalTo("Юрий"));
    }

    @Test
    public void checkUserNameDeserialize() {
        User user = given()
                .auth().oauth2(TOKEN)
                .get("/api/users/me")
                .body().as(User.class);
        MatcherAssert.assertThat(user, notNullValue());
    }

    @Test
    public void checkCardsStatusCode() {
        // проверяем статус-код ответа на запрос «Получение всех карточек»
        given()
                .auth().oauth2(TOKEN)
                .get("/api/cards")
                .then().statusCode(200);
    }


    @Test
    public void checkUserActivityAndPrintResponseBody() {
        // отправляет запрос и сохраняет ответ в переменную response, экзмепляр класса Response
		Response response = given().auth().oauth2(TOKEN).get("/api/users/me");
        // проверяет, что в теле ответа ключу about соответствует нужное занятие
        response.then().assertThat().body("data.about", equalTo("Самый крутой исследователь"));
        // выводит тело ответа на экран
        System.out.println(response.body().asString());
    }

}