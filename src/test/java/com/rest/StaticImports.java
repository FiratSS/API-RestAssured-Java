package com.rest;

import org.testng.annotations.Test;
import util.ConfigLoader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StaticImports {

    @Test
    public void simple_test_case(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigLoader.getProperty("api.key")).
        when().
                get("/workspaces").
        then().
                statusCode(200).
                body("name", is(equalTo("Omprakash")),
                        "email", is(equalTo("askomdch@gmail.com")));
    }
}
