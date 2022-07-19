package test;

import io.restassured.response.Response;
import models.LoginResponse;
import models.NotiTest;
import models.AssertTest;
import models.BaseResponse;

import org.junit.Test;

import com.google.gson.Gson;

import base.BaseClassTest;
import constant.ConstCharacters;

import static io.restassured.RestAssured.*;

public class LogoutTest extends BaseClassTest {
	public BaseResponse getLogoutRes(String email, String password) {
		LoginResponse login = LoginTest.getResultLogin(email, password);
		
		if (login.data == null) return null;
		Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).when().post("/logout");
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	//must login first
	
	@Test
    public void Test01() {
        BaseResponse rp = getLogoutRes("tien123456@gmail.com", "123456");
        
        System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
    }
	
	@Test
    public void Test02() {
        BaseResponse rp = getLogoutRes("tien1234567@gmail.com", ConstCharacters.CHAR_255);
        
        System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
    }
}
