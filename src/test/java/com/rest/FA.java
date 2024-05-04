package com.rest;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FA {
    SessionFilter filter;

    @BeforeClass
    public void beforeClass(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://localhost:8443").
                setRelaxedHTTPSValidation().build();
    }

    @Test
    public void form_authentication_using_csrf_token() {
        SessionFilter filter = new SessionFilter();

        given().
//                csrf("/login"). // -> Alternative mechanism to fetch CSRF
                auth().form("dan", "dan123", new FormAuthConfig("/signin", "txtUsername", "txtPassword")
                                .withAdditionalField("_csrf") // -> use this
//                                .withAutoDetectionOfCsrf() -> Deprecated
                ).
                filter(filter).
                log().all().
        when().
                get("/login").
        then().
                log().all().
                assertThat().
                statusCode(200);

        given().
                sessionId(filter.getSessionId()).
                log().all().
        when().
                get("/profile/index").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body("html.body.div.p", equalTo("This is User Profile\\Index. Only authenticated people can see this"));
    }
}
