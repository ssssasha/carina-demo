package com.qaprosoft.carina.demo.myapi.weatherapi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "${base_url}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/weatherby/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetWeatherByCityIdMethod extends AbstractApiMethodV2 {
    public GetWeatherByCityIdMethod(Integer id) {
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        addParameter("id", id.toString());
        addParameter("appid", "cb487e5b939c0e5e992c681075ed1595");
    }
}
