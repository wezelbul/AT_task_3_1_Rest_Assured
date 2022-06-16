package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;

import static specification.Specification.*;

public class BaseTest {

    @BeforeGroups("in.reqres")
    public static void specForInReqres() {
        initSpec(requestSpec("https://reqres.in/", "application/json"),
                responseSpec());
    }

    @BeforeGroups("com.autodns.gateway")
    public static void specForComAutodnsGateway() {
        initSpec(requestSpec("https://gateway.autodns.com/", "application/xml"),
                responseSpec());
    }

    @AfterSuite
    public static void clearAllSpec() {
        clearSpec();
    }

}
