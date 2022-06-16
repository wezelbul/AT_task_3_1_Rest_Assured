package helpers;

import org.testng.annotations.DataProvider;

import java.io.File;

import static custom.properties.com.autodns.gateway.TestData.propsData;
import static custom.properties.in.reqres.TestData.propsRequest;

public class DataProviders {

    @DataProvider(name = "pageNum")
    public Object[][] pageNum() {
        return new Object[][]{{propsRequest.apiUsersPageRequest(), 2}};
    }

    @DataProvider(name = "jsonLoginPass")
    public Object[][] jsonLoginPass() {
        return new Object[][]{{propsRequest.apiLoginRequest(),
                new File("jsons/in/reqres/api/login/LoginPass.json")}};
    }

    @DataProvider(name = "jsonLogin")
    public Object[][] jsonLogin() {
        return new Object[][]{{propsRequest.apiLoginRequest(),
                new File("jsons/in/reqres/api/login/Login.json")}};
    }

    @DataProvider(name = "tagsNum")
    public Object[][] tagsNum() {
        return new Object[][]{{"", propsData.countTags()}};
    }


}
