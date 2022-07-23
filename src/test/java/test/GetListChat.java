package test;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import com.google.gson.Gson;

import base.BaseClassTest;
import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.LoginResponse;
import models.NotiTest;

public class GetListChat extends BaseClassTest {
	BaseResponse getListChatRes(String email, String password) {
        if (email.equals("") || password.equals("")) {
        	Response res = given().header("Authorization", "Bearer " + "").when().get("/chat");
        	Gson g = new Gson();
    		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
			return rp;
        } else {
        	LoginResponse login = LoginTest.getResultLogin(email, password);
        	Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).when().get("/chat");
    		Gson g = new Gson();
    		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
    		return rp;
        }	
	}
	
	@Test
	public void Test01() {
		BaseResponse rp = getListChatRes("tien20212@gmail.com", "123456");
		
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
		
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);
	}
	
	@Test
	public void Test02() {
		BaseResponse rp = getListChatRes("", "");
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1004", rp.code, "Chưa đăng nhập", rp.message);
		
		AssertTest.assertTest("1004", rp.code, "Chưa đăng nhập", rp.message);
	}
}
