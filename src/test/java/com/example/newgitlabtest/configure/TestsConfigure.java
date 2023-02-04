package com.example.newgitlabtest.configure;

import com.example.newgitlabtest.apitests.PetApi;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class TestsConfigure {
    public static final int OK = 200;

    public static final PetApi feignSettings() {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .errorDecoder(new CustomFeignErrorDecoder())
                .target(PetApi.class, "https://petstore.swagger.io/v2");
    }

    public static final RequestSpecification assuredSpecification() {
        return RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }
}
