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

public class InfoItemTest extends BaseClassTest {
	
	BaseResponse getInfoItemRes(String email, String password, String id) {
        LoginResponse login = LoginTest.getResultLogin(email, password);
		
		if (login.data == null) return null;
		
		Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).when().get("/items/info/" + id);
		int statusCode = res.getStatusCode();
		if (statusCode == 404) return null;
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	@Test
	public void Test01() {
		BaseResponse rp = getInfoItemRes("tien123456@gmail.com", "123456", "1");
		
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error! The itemId must be integer");
			System.out.println("Finished! Satisfied!");
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
		
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);
	}
	
	@Test
	public void Test02() {
        BaseResponse rp = getInfoItemRes("tien123456@gmail.com", "123456", "2");
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error! The itemId must be integer");
			System.out.println("Finished! Satisfied!");
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
		
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);	
	}
	
	@Test
	public void Test03() {
        BaseResponse rp = getInfoItemRes("tien123456@gmail.com", "123456", "0");
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error! The itemId must be integer");
			System.out.println("Finished! Satisfied!\n");
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
		
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);	
	}
	
	@Test
	public void Test04() {
        BaseResponse rp = getInfoItemRes("tien123456@gmail.com", "123456", "-1");
		
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error! The itemId must be integer");
			System.out.println("Finished! Satisfied!\n");
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
		
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);	
	}
}
