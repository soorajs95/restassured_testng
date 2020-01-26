package utilities;

public enum SortByParameter {
    stars("stargazers_count"),
    forks("forks_count"),
    updated("updated_at");

    public String sortByParam;

    SortByParameter(String sortByParam) {
        this.sortByParam = sortByParam;
    }

    public String getParameterFieldName() {
        return sortByParam;
    }

}

