package com.lucky.qa.APIs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class APIsRequestBuilder extends APIsActions {

    public String getParamInJsonArray(JsonPath path, String ValueTovalidate, String list) {
        List<HashMap<String, Object>> data = path.getList(list);
        for (HashMap<String, Object> singleObject : data) {
            if (singleObject.containsValue(ValueTovalidate)) {
                return singleObject.toString();
            }
        }
        throw new NoSuchElementException(String.format("Can't find param "));
    }


    public int getIntValueFromArray(String arrayToSplit, String firstIndex, String secondIndex) {
        String[] parts = arrayToSplit.split(firstIndex);
        String[] part = parts[0].split(secondIndex);
        return Integer.parseInt(part[1]);
    }


    public Response affiliateCategoryGetCategories(String baseUri, String servcieName,
                                                   String key, Integer value, String key2, Integer value2) {
        return sendRequest(APIsActions.RequestType.GET, servcieName, prepareRequestSpecs(baseUri,
                ContentType.JSON, setQueryParams(key, value, key2, value2)));

    }

    public Response getAffiliateMerchantsByCategory(String baseUri, String servcieName,
                                                    String key, Integer value, String key2, Integer value2, String key3, Integer value3) {

        return sendRequest(APIsActions.RequestType.GET, servcieName, prepareRequestSpecs(baseUri,
                ContentType.JSON, setQueryParams(key, value, key2, value2, key3, value3)));

    }


}
