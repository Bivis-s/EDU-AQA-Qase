package api.adapters;

import com.google.gson.Gson;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import static io.restassured.RestAssured.given;

@Log4j2
public class BaseApiAdapter {
    /**
     * Create gson object for convert objects to json
     */
    protected Gson gson = new Gson();

    /**
     * @param headers    request headers
     * @param requestUrl url of the get request
     * @return Response object
     */
    protected Response get(Headers headers, String requestUrl) {
        log.debug("Send get request to URL: " + requestUrl);
        return given()
                .headers(headers)
                .when()
                .get(requestUrl)
                .then().log().all()
                .extract().response();
    }

    /**
     * @param headers    request headers
     * @param requestUrl url of the delete request
     * @return Response object
     */
    protected Response delete(Headers headers, String requestUrl) {
        log.debug("Send delete request to URL: " + requestUrl);
        return given()
                .headers(headers)
                .when()
                .delete(requestUrl)
                .then().log().all()
                .extract().response();
    }

    /**
     * @param headers     request headers
     * @param requestUrl  url of the post request
     * @param requestBody json String of request body
     * @return Response object
     */
    protected Response post(Headers headers, String requestUrl, String requestBody) {
        log.debug("Send post request with headers " + headers.toString() + " body '" + requestBody + "' to URL: " + requestUrl);
        return given()
                .headers(headers)
                .body(requestBody)
                .when()
                .post(requestUrl)
                .then().log().all()
                .extract().response();
    }
}
