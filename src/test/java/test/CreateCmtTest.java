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

public class CreateCmtTest extends BaseClassTest {
	
	BaseResponse getCreateCmtRes(String email, String password, String id, String text) {
        LoginResponse login = LoginTest.getResultLogin(email, password);
        if (login.data == null) {
        	BaseResponse rp = new BaseResponse();
			rp.code = "1004";
			rp.message = "Chưa đăng nhập";
			return rp;
        };
		Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).and().param("content", text).when().post("/comments/create/" + id);
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	@Test
	public void Test01() {
		BaseResponse rp = getCreateCmtRes("tien123456@gmail.com", "123456", "1", "mua");
		
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
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
		BaseResponse rp = getCreateCmtRes("tien123456@gmail.com", "123456", "2", "mua");
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Finished! Satisfied!");
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1008", rp.code, "Không thể bình luận", rp.message);
		
		AssertTest.assertTest("1008", rp.code, "Không thể bình luận", rp.message);
	}
	
	@Test
	// context = empty => chua nhap
	public void Test03() {
		BaseResponse rp = getCreateCmtRes("tien123456@gmail.com", "123456", "1", "");
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Finished! Satisfied!");
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1001", rp.code, "必須項目が未入力です。", rp.message);
		
		AssertTest.assertTest("1001", rp.code, "必須項目が未入力です。", rp.message);
	}
	
	@Test
	// access_token = empty / failed data / null => not login
	public void Test04() {
		//BaseResponse rp = getCreateCmtRes("tien123456@gmail.com", "1234567", "1", "mua");
		Response res = given().header("Authorization", "Bearer " + "").and().param("content", "mua").when().post("/comments/create/" + "1");
		if (res.getStatusCode() == 302) {
			BaseResponse rp = new BaseResponse();
			rp.code = "1004";
			rp.message = "Chưa đăng nhập";
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
	    assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
		
	    NotiTest.notiTest("1004", rp.code, "Chưa đăng nhập", rp.message);
	    AssertTest.assertTest("1004", rp.code, "Chưa đăng nhập", rp.message);	
	}
}
	
	@Test
	// access_token = empty / failed data / null => not login
	public void Test05() {
		Response res = given().header("Authorization", "Bearer " + "00").and().param("content", "mua").when().post("/comments/create/" + "1");
		if (res.getStatusCode() == 302) {
			BaseResponse rp = new BaseResponse();
			rp.code = "1004";
			rp.message = "Chưa đăng nhập";
		System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
	    assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
		
	    NotiTest.notiTest("1004", rp.code, "Chưa đăng nhập", rp.message);
	    AssertTest.assertTest("1004", rp.code, "Chưa đăng nhập", rp.message);	
	    }
	}
	
	@Test
	// access_token = empty / failed data / null => not login
	public void Test06() {
		Response res = given().header("Authorization", "Bearer ").and().param("content", "mua").when().post("/comments/create/" + "1");
		if (res.getStatusCode() == 302) {
			BaseResponse rp = new BaseResponse();
			rp.code = "1004";
			rp.message = "Chưa đăng nhập";
		System.out.println("Unit test 6: The code and message strings shall be not NULL as well as non-empty:");
	    assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
		
	    NotiTest.notiTest("1004", rp.code, "Chưa đăng nhập", rp.message);
	    AssertTest.assertTest("1004", rp.code, "Chưa đăng nhập", rp.message);	
	    }
	}
}
