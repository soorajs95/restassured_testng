package utilities;

import io.restassured.response.Response;
import org.testng.Assert;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class SearchRepoUtils {

    private String uri = "https://api.github.com/search/repositories";

    public Response getResponse(String query, String sort, String order) {
        Response response = given()
                .queryParam("q", query)
                .queryParam("sort", sort)
                .queryParam("order", order)
                .when()
                .get(uri);
        Assert.assertEquals(HttpURLConnection.HTTP_OK, response.getStatusCode(), "Status code not matching");
        return response;
    }

    public void verifyResultPath(Response response, String resultPath, String value) {
        List<Object> fieldValues = response.path("items." + resultPath);
        Assert.assertTrue(fieldValues.stream().allMatch(s -> s.toString().toLowerCase().contains(value)), "Field value not matching in result");
    }

    public void verifySortingOrderInResults(Response response, String sortByParam, String orderBy) {
        if (sortByParam.equalsIgnoreCase("updated"))
            verifySortByUpdated(response, sortByParam, orderBy);
        else if (sortByParam.equalsIgnoreCase("stars") || sortByParam.equalsIgnoreCase("forks"))
            verifySortingByStarsOrForks(response, sortByParam, orderBy);
    }

    public void verifySortByUpdated(Response response, String sortByParam, String orderBy) {
        List<Object> fieldValues = response.path("items." + SortByParameter.valueOf(sortByParam).getParameterFieldName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        try {
            if (orderBy.equals("asc"))
                for (int i = 1; i < fieldValues.size(); i++) {
                    Assert.assertTrue(dateFormat.parse(fieldValues.get(i).toString()).compareTo(dateFormat.parse(fieldValues.get(i - 1).toString())) >= 0, "Results not sorted by " + sortByParam + "in " + orderBy);
                }
            else if (orderBy.equals("desc"))
                for (int i = 1; i < fieldValues.size(); i++) {
                    Assert.assertTrue(dateFormat.parse(fieldValues.get(i).toString()).compareTo(dateFormat.parse(fieldValues.get(i - 1).toString())) <= 0, "Results not sorted by " + sortByParam + "in " + orderBy);
                }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void verifySortingByStarsOrForks(Response response, String sortByParam, String orderBy) {
        List<Object> fieldValues = response.path("items." + SortByParameter.valueOf(sortByParam).getParameterFieldName());
        List<Integer> intFieldValues = fieldValues.stream().map(s -> Integer.parseInt(s.toString())).collect(Collectors.toList());
        List<Integer> intFieldValuesBefore = intFieldValues;
        if (orderBy.equals("asc"))
            Collections.sort(intFieldValues);
        else if (orderBy.equals("desc"))
            intFieldValues.sort(Collections.reverseOrder());
        Assert.assertEquals(intFieldValues, intFieldValuesBefore, "Results not sorted by " + sortByParam + "in " + orderBy);
    }
}
