package com.lucky.qa.APIs;


import com.lucky.qa.APIs_payloads.Merchant_getAffiliateMerchants;
import com.lucky.qa.utilities.Helper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ImplementAPIsMethods extends APIsActions {
    Helper helper = new Helper();

    public Response affiliateCategoryGetCategories() {
        helper.setPropertiesFileName("APIsLinks.properties");
        return prepareGetAPIsResponse(helper.getValuesFromPropertiesFile("Couponz.Lucky.Api.Staging"), RequestType.GET,
                ServiceNames.affiliateCategory_GetCategories.getServiceName(), ContentType.JSON,
                setQueryParams("pageSize", 10, "pageIndex", 0), setSessionHeaders("Language", "2"));
    }

    public int getCategoryID(String category) {
        Response res = affiliateCategoryGetCategories();
        String value = getParamInJsonArray(res.jsonPath(), category, "Data");
        return getIntValueFromArray(value, ", AffiliateCategoryName=", "AffiliateCategoryId=");
    }

    public int getAffiliateMerchantsByCategory(String category) {
        helper.setPropertiesFileName("APIsLinks.properties");
        Response response = prepareGetAPIsResponse(helper.getValuesFromPropertiesFile("Couponz.Lucky.Api.Staging"), RequestType.GET, ServiceNames.affiliateCategory_GetAffiliateMerchantsByCategory.getServiceName(),
                ContentType.JSON, setQueryParams("categoryId", getCategoryID(category), "pageSize", 20, "pageIndex", 0), setSessionHeaders("Language", "2"));
        return validateValueInResponse(response, "Data.TotalCount");
    }

    public int getCountOfAffiliateMerchants(String keyword) {
        helper.setPropertiesFileName("APIsLinks.properties");
        Merchant_getAffiliateMerchants getAffiliateMerchants = new Merchant_getAffiliateMerchants();
        getAffiliateMerchants.setSearchKey(keyword);
        Response response = preparePostAPIsResponse(helper.getValuesFromPropertiesFile("Couponz.Lucky.Api.Staging"), RequestType.POST,
                ServiceNames.Merchant_POSTMerchantGetAffiliateMerchants.serviceName, ContentType.JSON, getAffiliateMerchants,
                setSessionHeaders("Language", "2"));
        //getAffiliateMerchants(ServiceNames.Merchant_POSTMerchantGetAffiliateMerchants.serviceName,  keyword, setSessionHeaders("Language", "2"));
        return validateValueInResponse(response, "Data.TotalCount");
    }
}
