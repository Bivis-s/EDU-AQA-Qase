package api.adapters;

import api.objects.CreateNewProjectRequest;
import api.objects.CreateNewSuiteRequest;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import property_objects.AccountProperties;
import property_objects.ApiUrlsProperties;

@Log4j2
public class QaseApiAdapter extends BaseApiAdapter {
    private final AccountProperties accountProperties;
    private final ApiUrlsProperties apiUrlsProperties;

    public QaseApiAdapter(AccountProperties accountProperties, ApiUrlsProperties apiUrlsProperties) {
        this.accountProperties = accountProperties;
        this.apiUrlsProperties = apiUrlsProperties;
    }

    private String getToken() {
        return accountProperties.getApiToken();
    }

    private Headers getHeaders() {
        return new Headers(
                new Header("Token", getToken()),
                new Header("Content-type", "application/json")
        );
    }

    private void checkResponseStatus (Response response) {
        String responseStatus = response.then().extract().path("status").toString();
        if (responseStatus.equals("true")) {
            log.info("Response status: " + responseStatus);
        } else if (responseStatus.equals("false")) {
            log.error("Response status: " + responseStatus + ", error fields: " + response.then().extract().path("errorFields"));
        }
    }

    public QaseApiAdapter createNewProject(CreateNewProjectRequest request) {
        log.info("Create new project via api, '" + request.toString() + "'");
        Response response = post(getHeaders(), apiUrlsProperties.getCreateNewProjectUrl(), gson.toJson(request));
        checkResponseStatus(response);
        return this;
    }

    public QaseApiAdapter createNewSuite(String projectName, CreateNewSuiteRequest request) {
        log.info("Create new suite '" + request.toString() + "' in project '" + projectName + "' via api");
        Response response = post(getHeaders(), String.format(apiUrlsProperties.getCreateNewSuiteUrl(), projectName), gson.toJson(request));
        checkResponseStatus(response);
        return this;
    }
}
