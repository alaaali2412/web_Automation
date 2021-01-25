package com.lucky.qa.APIs;

import io.restassured.response.Response;

public class ImplementMethods extends APIsRequestBuilder {
    ServiceNames serviceName = new ServiceNames();

    public int getCategoryID(String category) {

        Response res = affiliateCategoryGetCategories(serviceName.baseURI(), serviceName.affiliateCategoryGetCategories(),
                "pageSize", 10, "pageIndex",
                0);
        String value = getParamInJsonArray(res.jsonPath(), category, "Data");
        return getIntValueFromArray(value, ", AffiliateCategoryName=", "AffiliateCategoryId=");
    }

    public int getAffiliateMerchantsByCategory(String category) {

        Response response = getAffiliateMerchantsByCategory(serviceName.baseURI(), serviceName.affiliateCategoryGetAffiliateMerchantsByCategory(),
                "categoryId", getCategoryID(category), "pageSize", 20, "pageIndex", 0);

        return validateValueInResponse(response, "Data.TotalCount");
    }

}
