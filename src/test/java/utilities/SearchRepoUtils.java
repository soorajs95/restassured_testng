package utilities;

import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchRepoUtils {

    private String uri = "https://api.github.com/search/repositories";

    public Response getResponse(String query, String sort, String order) {
        return given()
                .queryParam("q", query)
                .queryParam("sort", sort)
                .queryParam("order", order)
                .when()
                .get(uri);
    }

    public boolean verifyResultPath(Response response, String resultPath, String value) {
        List<Object> resultValues = response.path("items." + resultPath);
        boolean status = true;
        for (Object actVal : resultValues) {
            if (!actVal.toString().toLowerCase().contains(value.toLowerCase()))
                status = false;
            break;
        }
        return status;
    }

}
