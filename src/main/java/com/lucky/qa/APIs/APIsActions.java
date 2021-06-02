package com.lucky.qa.APIs;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static io.restassured.RestAssured.given;

public class APIsActions {

    protected Map<String, Integer> queryParams;
    protected Map<String, String> sessionHeaders;

    public Map<String, String> setSessionHeaders(String key, String value) {
        this.sessionHeaders = new HashMap<>();
        sessionHeaders.put(key, value);
        return sessionHeaders;
    }

    public Map<String, Integer> setQueryParams(String key, int value, String key1, int value1) {
        queryParams = new HashMap<>();
        queryParams.put(key, value);
        queryParams.put(key1, value1);
        return queryParams;
    }

    public Map<String, Integer> setQueryParams(String key, int value,
                                               String key1, int value1, String key2, int Value2) {
        Map<String, Integer> queryParam = new HashMap<>();
        queryParam.put(key, value);
        queryParam.put(key1, value1);
        queryParam.put(key2, Value2);
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

    public RequestSpecBuilder initializeBuilder(String baseUri, ContentType contentType,
                                                Map<String, String> sessionHeaders) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(baseUri).setContentType(contentType).addHeaders(sessionHeaders);
        return builder;
    }

    public RequestSpecification prepareRequestSpecs(String baseUri, ContentType contentType, Map<String, ?> queryParams,
                                                    Map<String, String> sessionHeaders) {
        RequestSpecBuilder builder = initializeBuilder(baseUri, contentType, sessionHeaders);
        builder.addQueryParams(queryParams);
        return builder.build();
    }

    public RequestSpecification prepareRequestSpecs(String baseUri, ContentType contentType,
                                                    Map<String, String> sessionHeaders, Object body) {
        RequestSpecBuilder builder = initializeBuilder(baseUri, contentType, sessionHeaders);
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

    public String getParamInJsonArray(JsonPath path, String ValueToValidate, String list) {
        List<HashMap<String, Object>> data = path.getList(list);
        for (HashMap<String, Object> singleObject : data) {
            if (singleObject.containsValue(ValueToValidate)) {
                return singleObject.toString();
            }
        }
        throw new NoSuchElementException("Can't find param ");
    }

    public int getIntValueFromArray(String arrayToSplit, String firstIndex, String secondIndex) {
        String[] parts = arrayToSplit.split(firstIndex);
        String[] part = parts[0].split(secondIndex);
        return Integer.parseInt(part[1]);
    }

    public Response prepareGetAPIsResponse(String baseURI, RequestType requestType, String serviceName, ContentType contentType,
                                           Map<String, Integer> queryParams, Map<String, String> sessionHeaders) {
        return sendRequest(requestType, serviceName,
                prepareRequestSpecs(baseURI, contentType, queryParams, sessionHeaders));
    }

    public Response preparePostAPIsResponse(String baseURI, RequestType requestType, String serviceName, ContentType contentType,
                                            Object body, Map<String, String> sessionHeaders) {
        return sendRequest(requestType, serviceName,
                prepareRequestSpecs(baseURI, contentType, sessionHeaders, body));
    }

}
