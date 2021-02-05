package com.lucky.qa.APIs;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.*;

import static io.restassured.RestAssured.*;

public class APIsActions {

    protected HashMap<String, Integer> queryParam;


    public Map<String, Integer> setQueryParams(String key, int value,
                                               String key1, int value1) {
        queryParam = new HashMap<>();
        return queryParam;
    }


    public Map<String, Integer> setQueryParams(String key, int value,
                                               String key1, int value1, String key2, int Value2) {
        Map<String, Integer> queryParam = new HashMap<>();
        queryParam.put(key, value);
        queryParam.put(key1, value1);
        return queryParam;
    }

    public Response sendRequest(RequestType requestType, String request, RequestSpecification specs) {
        switch (requestType) {
            case POST:
                return given().spec(specs).when().post(request).andReturn();
            case PUT:
                return given().spec(specs).when().put(request).andReturn();
            case GET:
                return given().spec(specs).when().get(request).andReturn();
            case DELETE:
                return given().spec(specs).when().delete(request).andReturn();
            default:
                break;
        }
        return null;
    }

    public RequestSpecBuilder initializeBuilder(String baseUri, ContentType contentType) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(baseUri).setContentType(contentType);

        return builder;
    }

    public RequestSpecification prepareRequestSpecs(String baseUri, ContentType contentType, Map<String,
            Integer> queryParams) {
        RequestSpecBuilder builder = initializeBuilder(baseUri, ContentType.JSON);
        builder.addQueryParams(queryParams);
        return builder.build();
    }
    public RequestSpecification prepareRequestSpecs(String baseUri, ContentType contentType, Object body) {
        RequestSpecBuilder builder = initializeBuilder(baseUri, ContentType.JSON);
        builder.setBody(body);
        return builder.build();
    }
    public ResponseSpecBuilder responseSpecBuilder() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        return responseSpecBuilder;
    }


    public int validateValueInResponse(Response response, String ValueToValidate) {
        JsonPath path = response.jsonPath();
        return path.get(ValueToValidate);

    }

    public enum RequestType {
        POST, GET, DELETE, PUT
    }
}
