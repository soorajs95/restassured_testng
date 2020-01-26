package testng;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dataproviders.SearchRepoData;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.SearchRepoUtils;

@Listeners({ExtentITestListenerClassAdapter.class})
public class SearchRepoTest extends SearchRepoUtils {

    @Test(testName = "Search repositories by language and verify response", dataProvider = "searchByLanguage", dataProviderClass = SearchRepoData.class)
    public void searchByLanguageAndVerifyResponse(String query, String sortBy, String orderBy, String field, String language) {
        Response response = getResponse(query, sortBy, orderBy);
        verifyResultPath(response, field, language);
        verifySortingOrderInResults(response, sortBy, orderBy);
    }

    @Test(testName = "Search repositories by date and verify response", dataProvider = "searchByDate", dataProviderClass = SearchRepoData.class)
    public void searchByCreationDateAndVerifyResponse(String query, String sortBy, String orderBy, String field, String language) {
        Response response = getResponse(query, sortBy, orderBy);
        verifyResultPath(response, field, language);
        verifySortingOrderInResults(response, sortBy, orderBy);
    }

    @Test(testName = "Search repositories by user and verify response", dataProvider = "searchByUser", dataProviderClass = SearchRepoData.class)
    public void searchByUserAndVerifyResponse(String query, String sortBy, String orderBy, String field, String language) {
        Response response = getResponse(query, sortBy, orderBy);
        verifyResultPath(response, field, language);
        verifySortingOrderInResults(response, sortBy, orderBy);
    }

    @Test(testName = "Search repositories by license and verify response", dataProvider = "searchByLicense", dataProviderClass = SearchRepoData.class)
    public void searchByLicenseAndVerifyResponse(String query, String sortBy, String orderBy, String field, String language) {
        Response response = getResponse(query, sortBy, orderBy);
        verifyResultPath(response, field, language);
        verifySortingOrderInResults(response, sortBy, orderBy);
    }

    @Test(testName = "Search repositories by organisation name and verify response", dataProvider = "searchByOrg", dataProviderClass = SearchRepoData.class)
    public void searchByOrgNameAndVerifyResponse(String query, String sortBy, String orderBy, String field, String language) {
        Response response = getResponse(query, sortBy, orderBy);
        verifyResultPath(response, field, language);
        verifySortingOrderInResults(response, sortBy, orderBy);
//        Response response = getResponse("org:github", "forks", "desc");
//        verifyResultPath(response, "owner.login", "github");
//        verifyResultPath(response, "owner.type", "organization");
//        verifySortingOrderInResults(response, "forks", "desc");
    }

}
