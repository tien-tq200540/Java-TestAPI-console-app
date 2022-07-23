package test;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;

import base.BaseClassTest;
import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.LoginResponse;
import models.NotiTest;

public class ChangePassTest extends BaseClassTest {
	BaseResponse getChangePassRes(String email, String password, String new_pass, String re_pass) {
		// old_pass = password hiện tại
        if (email.equals("") || password.equals("")) {
        	BaseResponse rp = new BaseResponse();
			rp.code = "1004";
			rp.message = "Chưa đăng nhập";
			return rp;
        } else {
        	LoginResponse login = LoginTest.getResultLogin(email, password);
        	Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).and().param("old_pass", login.data.user.getPassword()).and().param("new_pass", new_pass).and().param("re_pass", re_pass).when().post("/changepass");
    		Gson g = new Gson();
    		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
    		return rp;
        }	
	}
	
	/* data: email: tien20212@gmail.com
	         old_pass: 123456
	*/
	@Test
	public void Test01() {
		String old_pass = "123456";
		String new_pass = RandomStringUtils.randomAlphanumeric(10);
		String re_pass = new_pass;
		BaseResponse rp = getChangePassRes("tien20212@gmail.com", old_pass, new_pass, re_pass);
		
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
		
		// Đổi lại pass như cũ cho lần chạy sau
		BaseResponse rp2 = getChangePassRes("tien20212@gmail.com", new_pass, old_pass, old_pass);
		
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);
	}
	
	@Test
	// if new_pass != re_pass
	public void Test02() {
		String old_pass = "123456";
		String new_pass = RandomStringUtils.randomAlphanumeric(10);
		String re_pass = RandomStringUtils.randomAlphanumeric(10);
		BaseResponse rp = getChangePassRes("tien20212@gmail.com", old_pass, new_pass, re_pass);
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1001", rp.code, "old_pass: &new_pass: &re_pass: 7003", rp.message);
		
		AssertTest.assertTest("1001", rp.code, "old_pass: &new_pass: &re_pass: 7003", rp.message);
	}
	
	@Test
	// if old_pass = fail data
	public void Test03() {
		String old_pass = "abcd123";
		String new_pass = RandomStringUtils.randomAlphanumeric(10);
		String re_pass = new_pass;
		LoginResponse login = LoginTest.getResultLogin("tien20212@gmail.com", "123456");
		Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).and().param("old_pass", old_pass).and().param("new_pass", new_pass).and().param("re_pass", re_pass).when().post("/changepass");
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1001", rp.code, "old_pass: 7003&new_pass: &re_pass: ", rp.message);
		
		AssertTest.assertTest("1001", rp.code, "old_pass: 7003&new_pass: &re_pass: ", rp.message);
	}
	
	@Test
	// if access_token = empty / null => not login
	public void Test04() {
		String new_pass = RandomStringUtils.randomAlphanumeric(10);
		String re_pass = new_pass;
		BaseResponse rp = getChangePassRes("", "", new_pass, re_pass);
		
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
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
	
	@Test
	// if old_pass = empty
	public void Test05() {
		String old_pass = "";
		String new_pass = RandomStringUtils.randomAlphanumeric(10);
		String re_pass = new_pass;
		LoginResponse login = LoginTest.getResultLogin("tien20212@gmail.com", "123456");
		Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).and().param("old_pass", old_pass).and().param("new_pass", new_pass).and().param("re_pass", re_pass).when().post("/changepass");
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		
		System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1001", rp.code, "old_pass: 7000&new_pass: &re_pass: ", rp.message);
		
		AssertTest.assertTest("1001", rp.code, "old_pass: 7000&new_pass: &re_pass: ", rp.message);
	}
}
