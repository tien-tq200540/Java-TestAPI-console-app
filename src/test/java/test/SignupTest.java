package test;

import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.LoginResponse;
import models.NotiTest;

import org.junit.Test;

import com.google.gson.Gson;

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
	public void Test01() {
		//BaseResponse rp = getResSignup();
		
	}
}
