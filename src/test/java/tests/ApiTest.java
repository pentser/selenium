package tests;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiTest {

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 3000));

 @Test
    public void Test1() {



     given().
             config(config).
             header("Content-Type","application/json").
             get("http://dummy.restapiexample.com/api/v1/employees").
             then().statusCode(200).
             body("data[0].employee_name",equalTo("Tiger Nixon"));

 }



 @Test
 public void test2() {


   given().config(config).
           get("http://dummy.restapiexample.com/api/v1/employee/1").
           then().statusCode(200).
           body("message",equalTo("Successfully! Record has been fetched."));
 }


@Test
public void test3() {
    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 1000));



    JSONObject request=new JSONObject();
    request.put("name","eli");
    request.put("salary","25000");
    request.put("age","44");


   given().
           config(config).
           body(request.toJSONString()).
           when().
           post("http://dummy.restapiexample.com/api/v1/create").
           then().statusCode(200);
}
}
