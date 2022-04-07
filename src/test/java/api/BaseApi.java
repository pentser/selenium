
package api;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseApi {
    protected final String URL = "";


    protected ExtractableResponse<Response> sendPostAssertCodeExtractResponse(String body, String url, int code){
        return given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(code)
                .extract();
    }
}

