package testng;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.SearchRepoUtils;

@Listeners({ExtentITestListenerClassAdapter.class})
public class SearchRepoTest extends SearchRepoUtils {

    private String uri = "https://api.github.com/search/repositories";

    @Test
    public void searchByLanguageAndVerifyResponse() {
        Response response = getResponse("language:java", "stars", "desc");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(verifyResultPath(response, "language", "java"));
    }

    @Test
    public void searchByCreationDateAndVerifyResponse() {
        Response response = getResponse("created:2020-01-24", "updated", "asc");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(verifyResultPath(response, "created_at", "2020-01-24"));
    }

    @Test
    public void searchByUserAndVerifyResponse() {
        Response response = getResponse("user:soorajs95", "updated", "asc");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(verifyResultPath(response, "owner.login", "soorajs95"));
    }

    @Test
    public void searchByLicenseAndVerifyResponse() {
        Response response = getResponse("license:apache-2.0", "stars", "desc");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(verifyResultPath(response, "license.key", "apache-2.0"));
    }

    @Test
    public void searchByOrgNameAndVerifyResponse() {
        Response response = getResponse("org:github", "forks", "desc");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(verifyResultPath(response, "owner.login", "github"));
        Assert.assertTrue(verifyResultPath(response, "owner.type", "organization"));
    }

}
