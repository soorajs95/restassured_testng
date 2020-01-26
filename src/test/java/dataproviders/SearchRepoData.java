package dataproviders;

import org.testng.annotations.DataProvider;

public class SearchRepoData {

    @DataProvider(name = "searchByLanguage")
    public Object[][] searchByLanguageData() {
        return new String[][]{
                {"language:java", "stars", "asc", "language", "java"},
                {"language:ruby", "forks", "desc", "language", "ruby"}
        };
    }

    @DataProvider(name = "searchByDate")
    public Object[][] searchByCreationDate() {
        return new String[][]{
                {"created:2020-01-24", "forks", "asc", "created_at", "2020-01-24"},
        };
    }

    @DataProvider(name = "searchByUser")
    public Object[][] searchByUserData() {
        return new String[][]{
                {"user:soorajs95", "updated", "asc", "owner.login", "soorajs95"},
        };
    }

    @DataProvider(name = "searchByLicense")
    public Object[][] searchByLicenseData() {
        return new String[][]{
                {"license:apache-2.0", "stars", "desc", "license.key", "apache-2.0"},
        };
    }

    @DataProvider(name = "searchByOrg")
    public Object[][] searchByOrgData() {
        return new String[][]{
                {"org:github", "forks", "desc", "owner.login", "github"},
        };
    }

}
