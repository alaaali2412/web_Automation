package com.lucky.qa.APIs;


import com.lucky.qa.APIs_payloads.Merchant_getAffiliateMerchants;
import com.lucky.qa.utilities.Helper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ImplementAPIsMethods extends APIsActions {
    Helper helper = new Helper();

    public String languageValue(String language) {
        String value = null;
        switch (language) {
            case "English":
                return value = "1";
            case "Arabic_Egypt":
                return value = "2";
            case "French":
                return value = "3";
            case "Arabic_Morocco":
                return value = "4";
        }
        return value;
    }

    public String setBaseUri(String language) {
        helper.setPropertiesFileName("APIsLinks.properties");
        String baseUri = null;
        if (language.equals("Arabic_Egypt") || language.equals("English")) {
            return baseUri = helper.getValuesFromPropertiesFile("Egypt.Couponz.Lucky.Api.Staging");
        } else if (language.equals("Arabic_Morocco") || language.equals("French")) {
            return baseUri = helper.getValuesFromPropertiesFile("Morocco.Couponz.Lucky.Api.Staging");
        }
        return baseUri;
    }

    public Response affiliateCategoryGetCategories(String LanguageValue, String baseUri) {
        helper.setPropertiesFileName("APIsLinks.properties");
        return prepareGetAPIsResponse(baseUri, RequestType.GET,
                ServiceNames.affiliateCategory_GetCategories.getServiceName(), ContentType.JSON,
                setQueryParams("pageSize", 10, "pageIndex", 0), setSessionHeaders("Language", LanguageValue));
    }

    public int getCategoryID(String category, String LanguageValue, String baseUri) {
        Response res = affiliateCategoryGetCategories(LanguageValue, baseUri);
        String value = getParamInJsonArray(res.jsonPath(), category, "Data");
        return getIntValueFromArray(value, ", AffiliateCategoryName=", "AffiliateCategoryId=");
    }

    public int getAffiliateMerchantsByCategory(String category, String LanguageValue, String baseUri) {
        helper.setPropertiesFileName("APIsLinks.properties");
        Response response = prepareGetAPIsResponse(baseUri, RequestType.GET, ServiceNames.affiliateCategory_GetAffiliateMerchantsByCategory.getServiceName(),
                ContentType.JSON, setQueryParams("categoryId", getCategoryID(category, LanguageValue, baseUri), "pageSize", 20, "pageIndex", 0), setSessionHeaders("Language", LanguageValue));
        return validateValueInResponse(response, "Data.TotalCount");
    }

    public int getCountOfAffiliateMerchants(String keyword, String LanguageValue, String baseUri) {
        helper.setPropertiesFileName("APIsLinks.properties");
        Merchant_getAffiliateMerchants getAffiliateMerchants = new Merchant_getAffiliateMerchants();
        getAffiliateMerchants.setSearchKey(keyword);
        Response response = preparePostAPIsResponse(baseUri, RequestType.POST,
                ServiceNames.Merchant_POSTMerchantGetAffiliateMerchants.serviceName, ContentType.JSON, getAffiliateMerchants,
                setSessionHeaders("Language", LanguageValue));
        return validateValueInResponse(response, "Data.TotalCount");
    }
}
