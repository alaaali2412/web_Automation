package com.lucky.qa.APIs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ServiceNames {


    affiliateCategory_GetCategories("/AffiliateCategory/get-categories"),
    affiliateCategory_GetAffiliateMerchantsByCategory("AffiliateCategory/get-affiliate-merchants-by-category"),
    Merchant_POSTMerchantGetAffiliateMerchants("/merchant/get-affiliate-merchants");

    protected String serviceName;


    ServiceNames(String serviceName) {
        this.serviceName = serviceName;
    }

    protected String getServiceName() {
        return serviceName;
    }


}

