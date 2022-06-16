package com.autodns.gateway;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class Tests extends BaseTest {

    @Feature("GET-request")
    @Description("Проверка количества тегов в xml-файле")
    @Test(groups = "com.autodns.gateway", description = "Проверка количества тегов в xml-файле",
            dataProvider = "tagsNum",
            dataProviderClass = helpers.DataProviders.class)
    public void shouldContainsSomeNumOfTags(String request, Integer tagsNum) {
        String xmlAsString = given()
                .when()
                .get(request)
                .then()
                .extract().asString();
        Matcher matchers = Pattern.compile("<[^?/<\\s]+>").matcher(xmlAsString);
        Integer counter = 0;
        while (matchers.find()) {
            counter++;
        }
        Assert.assertEquals(counter, tagsNum,
                "Неверное число тегов. Ожидалось " + tagsNum + ", найдено " + counter);
    }
}
