package com.lucky.qa.APIs;


import com.lucky.qa.APIs_payloads.Merchant_getAffiliateMerchants;
import com.lucky.qa.utilities.Helper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class APIsRequestBuilder extends APIsActions {

    Helper helper = new Helper();
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


    public Response affiliateCategoryGetCategories(String serviceName,
                                                   String key, Integer value, String key2, Integer value2) {
        helper.setPropertiesFileName("APIsLinks.properties");
        return sendRequest(RequestType.GET, serviceName,
                prepareRequestSpecs(helper.getValuesFromPropertiesFile("Couponz.Lucky.Api.Staging"),
                        ContentType.JSON, setQueryParams(key, value, key2, value2)));

    }

    public Response getAffiliateMerchantsByCategory(String serviceName,
                                                    String key, Integer value, String key2, Integer value2, String key3, Integer value3) {
        helper.setPropertiesFileName("APIsLinks.properties");
        return sendRequest(RequestType.GET, serviceName,
                prepareRequestSpecs(helper.getValuesFromPropertiesFile("Couponz.Lucky.Api.Staging"),
                        ContentType.JSON, setQueryParams(key, value, key2, value2, key3, value3)));

    }

    public Response getAffiliateMerchants(String serviceName, String keyword) {
        Merchant_getAffiliateMerchants getAffiliateMerchants = new Merchant_getAffiliateMerchants();
        getAffiliateMerchants.setSearchKey(keyword);
        helper.setPropertiesFileName("APIsLinks.properties");
        return sendRequest(RequestType.POST, serviceName,
                prepareRequestSpecs(helper.getValuesFromPropertiesFile("Couponz.Lucky.Api.Staging"), ContentType.JSON, getAffiliateMerchants));
    }
}
