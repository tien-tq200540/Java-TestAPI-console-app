package test;

import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.LoginResponse;
import models.NotiTest;

import static io.restassured.RestAssured.*;
import org.junit.Test;

import com.google.gson.Gson;
import base.BaseClassTest;
import constant.ConstCharacters;

public class GetInforTest extends BaseClassTest {
	public BaseResponse getInfoRes(String email, String password) {
		LoginResponse login = LoginTest.getResultLogin(email, password);
		
		if (login.data == null) return null;
		
		Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).when().get("/info");
		int statusCode = res.getStatusCode();
		if (statusCode == 404) return null;
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	    }
	
	
	@Test
	public void Test01() {
		BaseResponse rp = getInfoRes("tien123456@gmail.com", "123456");
		
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
		
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);
		
	}
	
	@Test
	public void Test02() {
		BaseResponse rp = getInfoRes("tien1234567@gmail.com", ConstCharacters.CHAR_255);
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
		
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);
		
	}
	
	@Test
	// when access_token = "" (empty) => not login
	public void Test03() {
		Response res = given().header("Authorization", "Bearer " + "").when().get("/info");
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1004", rp.code, "Chưa đăng nhập", rp.message);
		
		AssertTest.assertTest("1004", rp.code, "Chưa đăng nhập", rp.message);
		
	}
	
	@Test
	// access_token = fail data => access_token doesn't exist => not login
	public void Test04() {
		Response res = given().header("Authorization", "Bearer " + "5353").when().get("/info");
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
		
		NotiTest.notiTest("1004", rp.code, "Chưa đăng nhập", rp.message);
		
		AssertTest.assertTest("1004", rp.code, "Chưa đăng nhập", rp.message);
		
	}
	
	@Test
	// access_token = null => access_token doesn't exist => not login
	public void Test05() {
		Response res = given().header("Authorization", "Bearer ").when().get("/info");
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		
		System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
		
		NotiTest.notiTest("1004", rp.code, "Chưa đăng nhập", rp.message);
		
		AssertTest.assertTest("1004", rp.code, "Chưa đăng nhập", rp.message);	
	}
	
}
