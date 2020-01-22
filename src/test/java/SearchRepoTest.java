import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners({ExtentITestListenerClassAdapter.class})
public class SearchRepoTest {

    private String uri = "https://api.github.com/search/repositories";

    @Test
    public void queryRepoForJava() {
        ValidatableResponse response = given()
                .queryParam("q", "language:java")
                .queryParam("sort", "stars")
                .queryParam("order", "asc")
                .when()
                .get(uri)
                .then();
        response.extract().response().prettyPrint();
    }

    @Test
    public void filterByCreationDate() {
        ValidatableResponse response = given()
                .param("q", "created:2020-01-22")
                .queryParam("sort", "updated")
                .queryParam("order", "asc")
                .when()
                .get(uri)
                .then();
        response.extract().response().prettyPrint();
    }

    @Test
    public void queryRepoForJavaAndCSharp() {
        ValidatableResponse response = given()
                .queryParam("q", "language:java")
                .queryParam("q", "c#")
                .queryParam("sort", "forks")
                .queryParam("order", "desc")
                .when()
                .get(uri)
                .then();
        response.extract().response().prettyPrint();
    }

    @Test
    public void filterRepoByUser() {
        ValidatableResponse response = given()
                .queryParam("q", "user:soorajs95")
                .queryParam("sort", "updated")
                .queryParam("order", "asc")
                .when()
                .get(uri)
                .then();
        response.extract().response().prettyPrint();
    }

    @Test
    public void mostStarredRepo() {
        ValidatableResponse response = given()
                .queryParam("q", "language:Java")
                .queryParam("sort", "stars")
                .queryParam("order", "desc")
                .when()
                .get(uri)
                .then();
        response.extract().response().prettyPrint();
    }
}
