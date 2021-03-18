package utils.readers;

import property_objects.ApiUrlsProperties;

public class ApiUrlsPropertyReader extends PropertyReader {
    private static final String API_URLS_PROPERTIES_FILE_PATH = "src/test/resources/properties/api_urls.properties";

    public ApiUrlsPropertyReader() {
        super(API_URLS_PROPERTIES_FILE_PATH);
    }

    private String getCreateNewProjectUrl() {
        return getMap().get("create_new_project_url");
    }

    private String getCreateNewSuiteUrl() {
        return getMap().get("create_new_suite_url");
    }

    public ApiUrlsProperties getApiUrlProperties() {
        ApiUrlsProperties apiUrlsProperties = new ApiUrlsProperties();
        apiUrlsProperties.setCreateNewProjectUrl(getCreateNewProjectUrl());
        apiUrlsProperties.setCreateNewSuiteUrl(getCreateNewSuiteUrl());
        return apiUrlsProperties;
    }
}
