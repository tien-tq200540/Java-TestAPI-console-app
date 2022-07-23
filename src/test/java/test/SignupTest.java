package test;

import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.NotiTest;

import org.junit.Test;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;

import base.BaseClassTest;

import static io.restassured.RestAssured.*;

public class SignupTest extends BaseClassTest {
	
	public static BaseResponse getResSignup(String email, String password, String re_pass, String name, String phone) {
		Response res = given().param("email", email).
        		and().param("password", password).
        		and().param("re_pass", re_pass).
        		and().param("name", name).
        		and().param("phone", phone)
        		.when().post("/signup");
		
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	@Test
	// all input = true random
	public void Test01() {
		String email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
		String password = RandomStringUtils.randomAlphanumeric(10);
		String re_pass = password;
		String name = RandomStringUtils.randomAlphabetic(10);
		String phone = RandomStringUtils.randomNumeric(10);
		BaseResponse rp = getResSignup(email, password, re_pass, name, phone);
		
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
		
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);
	}
	
	@Test
	// at least 1 input = null
	public void Test02() {
		String email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
		String password = "";
		String re_pass = password;
		String name = RandomStringUtils.randomAlphabetic(10);
		String phone = RandomStringUtils.randomNumeric(10);
		BaseResponse rp = getResSignup(email, password, re_pass, name, phone);
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1001", rp.code, "name: &phone: &address: &email: &password: 7000&re_pass:  &avatar: ", rp.message);
		
		AssertTest.assertTest("1001", rp.code, "name: &phone: &address: &email: &password: 7000&re_pass:  &avatar: ", rp.message);
	}
	
	@Test
	// phone = String of char
	public void Test03() {
		String email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
		String password = RandomStringUtils.randomAlphanumeric(10);
		String re_pass = password;
		String name = RandomStringUtils.randomAlphabetic(10);
		String phone = RandomStringUtils.randomAlphabetic(10);
		BaseResponse rp = getResSignup(email, password, re_pass, name, phone);
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1001", rp.code, "name: &phone: The phone format is invalid.&address: &email: &password: &re_pass:  &avatar: ", rp.message);
		
		AssertTest.assertTest("1001", rp.code, "name: &phone: The phone format is invalid.&address: &email: &password: &re_pass:  &avatar: ", rp.message);
	}
	
	@Test
	// Wrong email format
	public void Test04() {
		String email = RandomStringUtils.randomAlphanumeric(10) + "gmail.com";
		String password = RandomStringUtils.randomAlphanumeric(10);
		String re_pass = password;
		String name = RandomStringUtils.randomAlphabetic(10);
		String phone = RandomStringUtils.randomNumeric(10);
		BaseResponse rp = getResSignup(email, password, re_pass, name, phone);
		
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1001", rp.code, "name: &phone: &address: &email: 7002&password: &re_pass:  &avatar: ", rp.message);
		
		AssertTest.assertTest("1001", rp.code, "name: &phone: &address: &email: 7002&password: &re_pass:  &avatar: ", rp.message);
	}
}
