package com.qaprosoft.carina.demo;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.qaprosoft.carina.demo.myapi.*;
import com.qaprosoft.carina.demo.myapi.weatherapi.GetWeatherByCityIdMethod;
import com.qaprosoft.carina.demo.myapi.weatherapi.GetWeatherByCityNameMethod;
import com.qaprosoft.carina.demo.myapi.weatherapi.GetWeatherMethod;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class MyAPITest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testGetAlbums() {
        GetAlbumMethod getAlbumMethod = new GetAlbumMethod();
        getAlbumMethod.callAPIExpectSuccess();
        getAlbumMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getAlbumMethod.validateResponseAgainstSchema("api/albums/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    public void testCreateAlbum(){
        PostAlbumMethod postAlbumMethod = new PostAlbumMethod();
        postAlbumMethod.callAPIExpectSuccess();
        postAlbumMethod.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    public void testUpdate(){
        PutAlbumMethod putAlbumMethod = new PutAlbumMethod();
        putAlbumMethod.callAPIExpectSuccess();
        putAlbumMethod.validateResponse();
    }


    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    public void testDeleteAlbums() {
        DeleteAlbumMethod deleteAlbumMethod = new DeleteAlbumMethod();
        deleteAlbumMethod.callAPIExpectSuccess();
        deleteAlbumMethod.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    public void testGetPhotosByAlbumId(){
        GetListPhotosMethod getListPhotosMethod = new GetListPhotosMethod(1);
        getListPhotosMethod.callAPIExpectSuccess();
        getListPhotosMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getListPhotosMethod.validateResponseAgainstSchema("api/albums/_getList/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    public void testGetAlbumsByUserId(){
        GetAlbumsByUserMethod getAlbumsByUserMethod = new GetAlbumsByUserMethod(1);
        getAlbumsByUserMethod.callAPIExpectSuccess();
        getAlbumsByUserMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getAlbumsByUserMethod.validateResponseAgainstSchema("api/albums/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    public void testGetCommentsByPostsId(){
        GetCommentsByPostsMethod getCommentsByPostsMethod = new GetCommentsByPostsMethod(2);
        getCommentsByPostsMethod.callAPIExpectSuccess();
        getCommentsByPostsMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getCommentsByPostsMethod.validateResponseAgainstSchema("api/posts/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testGetWeather() {
        GetWeatherMethod getWeatherMethod = new GetWeatherMethod();
        getWeatherMethod.callAPIExpectSuccess();
        getWeatherMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherMethod.validateResponseAgainstSchema("api/weather/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testGetWeatherByCityName() {
        GetWeatherByCityNameMethod getWeatherByCityNameMethod = new GetWeatherByCityNameMethod("London");
        getWeatherByCityNameMethod.callAPIExpectSuccess();
        getWeatherByCityNameMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherByCityNameMethod.validateResponseAgainstSchema("api/weatherby/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testGetWeatherByCityId() {
        GetWeatherByCityIdMethod getWeatherByCityIdMethod = new GetWeatherByCityIdMethod(2643743);
        getWeatherByCityIdMethod.callAPIExpectSuccess();
        getWeatherByCityIdMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherByCityIdMethod.validateResponseAgainstSchema("api/weatherby/_get/rs.schema");
    }

}
