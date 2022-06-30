package com.qaprosoft.carina.demo.myapi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "${base_url}/comments", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/posts/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetCommentsByPostsMethod extends AbstractApiMethodV2 {
    public GetCommentsByPostsMethod(Integer postId) {
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        addParameter("postId", postId.toString());
    }
}
