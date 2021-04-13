package com.lucky.qa.APIs;


import io.restassured.response.Response;

public class ImplementAPIsMethods extends APIsRequestBuilder {

    public int getCategoryID(String category)  {
        Response res = affiliateCategoryGetCategories(ServiceNames.affiliateCategory_GetCategories.getServiceName(),
                "pageSize", 10, "pageIndex", 0);
        String value = getParamInJsonArray(res.jsonPath(), category, "Data");
        return getIntValueFromArray(value, ", AffiliateCategoryName=", "AffiliateCategoryId=");
    }


    public int getAffiliateMerchantsByCategory(String category)  {
        Response response = getAffiliateMerchantsByCategory( ServiceNames.affiliateCategory_GetAffiliateMerchantsByCategory.getServiceName(),
                "categoryId", getCategoryID(category), "pageSize", 20, "pageIndex", 0);
        return validateValueInResponse(response, "Data.TotalCount");
    }

    public int getCountOfAffiliateMerchants(String keyword) {
        Response response = getAffiliateMerchants(ServiceNames.Merchant_POSTMerchantGetAffiliateMerchants.serviceName,  keyword);
        return validateValueInResponse(response,"Data.TotalCount");
    }

}
