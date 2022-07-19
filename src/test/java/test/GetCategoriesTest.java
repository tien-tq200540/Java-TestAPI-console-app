package test;

import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.NotiTest;

import org.junit.Test;

import com.google.gson.Gson;

import base.BaseClassTest;

import static io.restassured.RestAssured.*;

public class GetCategoriesTest extends BaseClassTest {
	
    @Test
    public void Test01() {
        Response res = given().when().get("/categories");

        Gson g = new Gson();
        BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
        
        System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
    }
}
