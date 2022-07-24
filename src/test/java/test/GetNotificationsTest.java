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

public class GetNotificationsTest extends BaseClassTest {
	BaseResponse getNotiRes(String email, String password, String index, String count) {
		Response res;
        if (email.equals("") && password.equals("")) {
        	res = given().header("Authorization", "Bearer " + "").and().param("index", index).and().param("count", count).when().get("/notifications");
        } else {
        	LoginResponse login = LoginTest.getResultLogin(email, password);
        	res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).and().param("index", index).and().param("count", count).when().get("/notifications");
        }
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	// account test: email: tien20212@gmail.com || pass: 123456
	
		@Test
		public void Test01(){
			BaseResponse rp = getNotiRes("tien20212@gmail.com", "123456", "1", "1");
			
			System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
			if (rp == null) {
				System.out.println("Error!\nFailed Test");
				System.out.println();
				return;
			}
			assert(rp.code != null && !"".equals(rp.code));
	        assert(rp.message != null && !"".equals(rp.message));
	        
	        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
	        
	        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
		}	
		
		@Test
		public void Test02(){
			BaseResponse rp = getNotiRes("tien20212@gmail.com", "123456", "1", "5");
			
			System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
			if (rp == null) {
				System.out.println("Error!\nFailed Test");
				System.out.println();
				return;
			}
			assert(rp.code != null && !"".equals(rp.code));
	        assert(rp.message != null && !"".equals(rp.message));
	        
	        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
	        
	        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
		}	
		
		@Test
		// index = || count = || index = count = "" <empty> => get all noti => OK
		public void Test03(){
			BaseResponse rp = getNotiRes("tien20212@gmail.com", "123456", "", "5");
			
			System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
			if (rp == null) {
				System.out.println("Error!\nFailed Test");
				System.out.println();
				return;
			}
			assert(rp.code != null && !"".equals(rp.code));
	        assert(rp.message != null && !"".equals(rp.message));
	        
	        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
	        
	        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
		}	
		
		@Test
		// access_token = empty / null => not login
		public void Test04(){
			Response res = given().header("Authorization", "Bearer " + "").and().param("index", "1").and().param("count", "5").when().get("/notifications");
			Gson g = new Gson();
			BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
			
			System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
			if (rp == null) {
				System.out.println("Error!\nFailed Test");
				System.out.println();
				return;
			}
			assert(rp.code != null && !"".equals(rp.code));
	        assert(rp.message != null && !"".equals(rp.message));
	        
	        NotiTest.notiTest("1004", rp.code, "Chưa đăng nhập", rp.message);
	        
	        AssertTest.assertTest("1004", rp.code, "Chưa đăng nhập", rp.message);
		}	
		
		@Test
		// index / count / index && count = char => code: 1001, mess: 7006
		public void Test05(){
			LoginResponse login = LoginTest.getResultLogin("tien20212@gmail.com", "123456");
			Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).and().param("index", "a").and().param("count", "1").when().get("/notifications");
			BaseResponse rp = new BaseResponse();
			if (res.getStatusCode() == 500) {
				rp.code = "Unknown";
				rp.message = "Unknowm";
			} else {
				Gson g = new Gson();
				rp = g.fromJson(res.asString(), BaseResponse.class);
			}	
			System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
			if (rp == null) {
				System.out.println("Error!\nFailed Test");
				System.out.println();
				return;
			}
			assert(rp.code != null && !"".equals(rp.code));
	        assert(rp.message != null && !"".equals(rp.message));
	        
	        NotiTest.notiTest("1001", rp.code, "index: 7006 &count: ", rp.message);
	        
	        AssertTest.assertTest("1001", rp.code, "index: 7006 &count: ", rp.message);
		}	
}
