package in.reqres;

import base.BaseTest;
import serialization.in.reqres.api.login.*;
import serialization.in.reqres.api.unknown.*;
import serialization.in.reqres.api.users.*;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import specification.Specification;

import java.util.*;
import java.io.File;
import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static custom.properties.in.reqres.TestData.*;

public class Tests extends BaseTest {

    @Feature("GET-request")
    @Description(value = "Проверка, что полученные по запросу определенной страницы каталога пользователей названия файлов" +
            "аватаров  уникальны")
    @Test(groups = "in.reqres", description = "Проверка, что полученные по запросу определенной страницы каталога " +
            "пользователей названия файлов аватаров уникальны",
            dataProvider = "pageNum",
            dataProviderClass = helpers.DataProviders.class)
    public void shouldReturnUserListFromPageNumberWithUniqAvatarFileNames(String request, Integer page) {
        Set<String> avatarNamesSet = new HashSet<>();
        Pattern pattern = Pattern.compile("[^/]+\\" + propsFileFormat.avatarFileFormat() + "$");

        UsersPage usersPage = given()
                .when()
                .get(request + page)
                .then()
                .body(propsField.fieldData(), not(empty()))
                .extract().body().as(UsersPage.class);
        for (User user : usersPage.getData()) {
            Matcher matcher = pattern.matcher(user.getAvatar());
            Assert.assertTrue(matcher.find(), "Не найден файл аватара");
            Assert.assertTrue(
                    avatarNamesSet.add(matcher.group()),
                    "Имя файла аватара " + matcher.group() + " уже используется.");
        }
    }

    @Feature("POST-request")
    @Description("Проверяет успешность авторизации по наличию токена")
    @Test(groups = "in.reqres", description = "Проверяет успешность авторизации по наличию токена",
            dataProvider = "jsonLoginPass",
            dataProviderClass = helpers.DataProviders.class)
    public void shouldBeSuccessfulLogin(String request, File json) {
        Success success = given()
                .body(json)
                .when()
                .post(request)
                .then()
                .extract().body().as(Success.class);

        Assert.assertFalse(success.getToken().isEmpty(),
                "Отсутствует токен");
    }

    @Feature("POST-request")
    @Description("Проверяет неудачу авторизации по наличию сообщения об ошибке")
    @Test(groups = "in.reqres", description = "Проверяет неудачу авторизации по наличию сообщения об ошибке",
            dataProvider = "jsonLogin",
            dataProviderClass = helpers.DataProviders.class)
    public void shouldBeUnsuccessfulLogin(String request, File json) {
        Specification.initSpec(Specification.responseSpec(400));

        Failure failure = given()
                .body(json)
                .when()
                .post(request)
                .then()
                .extract().body().as(Failure.class);

        Specification.initSpec(Specification.responseSpec());

        Assert.assertFalse(failure.getError().isEmpty(),
                "Отсутствует сообщение об ошибке");

    }

    @Feature("GET-request")
    @Description("Проверка, что данные, полученные при обращении к неизвестному каталогу сервера, отсортированы по годам")
    @Test(groups = "in.reqres",
            description = "Проверка, что данные, полученные при обращении к неизвестному каталогу сервера, отсортированы по годам")
    public void shouldReturnSortedByYearUnknownList() {
        DataContainsYearPage dataContainsYearPage = given()
                .when()
                .get(propsRequest.apiUnknownRequest())
                .then()
                .body(propsField.fieldData(), not(empty()))
                .extract().body().as(DataContainsYearPage.class);
        Integer previousYear = Year.MIN_VALUE;
        for (DataContainsYear dataContainsYear : dataContainsYearPage.getData()) {
            Assert.assertTrue(previousYear <= dataContainsYear.getYear(),
                    "Год " + dataContainsYear.getYear() + " меньше, чем " + previousYear + ". Данные не отсортированы.");
            previousYear = dataContainsYear.getYear();
        }
    }
}
