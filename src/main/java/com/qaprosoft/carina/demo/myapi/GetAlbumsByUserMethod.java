package com.qaprosoft.carina.demo.myapi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "${base_url}/albums", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/albums/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetAlbumsByUserMethod extends AbstractApiMethodV2 {
    public GetAlbumsByUserMethod(Integer userId) {
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        addParameter("userId", userId.toString());
    }
}
