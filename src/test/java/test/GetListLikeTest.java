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

public class GetListLikeTest extends BaseClassTest {
	public BaseResponse getListLikeRes(String email, String password, String index, String count, String id) {
		if (email.equals("") || password.equals("")) return null;
		LoginResponse login = LoginTest.getResultLogin(email, password);
		Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).and().param("index", index).and().param("count", count).when().get("/likes/" + id);
		if (res.getStatusCode() == 404) return null;
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	@Test
	 // not login
	public void Test01() {
		BaseResponse rp = getListLikeRes("", "", "1", "5", "3");
		
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse ();
			rp.code = "Unknown";
			rp.message = "Unknown";
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1004", rp.code, "Chưa đăng nhập", rp.message);
			
		AssertTest.assertTest("1004", rp.code, "Chưa đăng nhập", rp.message);
	}
	
	@Test
	 // login
	public void Test02() {
		BaseResponse rp = getListLikeRes("tien123456@gmail.com", "123456", "1", "5", "3");
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse ();
			rp.code = "Unknown";
			rp.message = "Unknown";
		}
		assert(rp.code != null && !"".equals(rp.code));
       assert(rp.message != null && !"".equals(rp.message));
       
		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
			
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);
	}
}
