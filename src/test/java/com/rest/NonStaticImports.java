package com.rest;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import util.ConfigLoader;

public class NonStaticImports {

    @Test
    public void simple_test_case(){
        RestAssured.given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigLoader.getProperty("api.key")).
        when().
                get("/workspaces").
        then().
                statusCode(200).
                body("name", Matchers.is(Matchers.equalTo("Omprakash")),
                        "email", Matchers.is(Matchers.equalTo("askomdch@gmail.com")));
    }
}
