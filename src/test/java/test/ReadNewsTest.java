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


public class ReadNewsTest extends BaseClassTest {
	BaseResponse readNewsRes(String email, String password, String idNews) {
		Response res;
		if (email.equals("") && password.equals("")) {
			System.out.println("Chua dang nhap");
			System.out.println("Chua doc tin tuc");
			res = given().when().get("/news/read/" + idNews);
		} else {
			System.out.println("Da doc tin tuc");
			LoginResponse login = LoginTest.getResultLogin(email, password);
			res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).when().get("/news/read/" + idNews);
		}
		BaseResponse rp = new BaseResponse();
		if (res.getStatusCode() == 404) {
			rp.code = "Unknown";
			rp.message = "Unknown";
		} else {
			Gson g = new Gson();
			rp = g.fromJson(res.asString(), BaseResponse.class);
		}
		return rp;
	}
	
	// idNews tồn tại: 1, 2, 4
	@Test
	public void Test01(){
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = readNewsRes("", "","1");
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
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = readNewsRes("", "","2");
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
	public void Test03(){
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = readNewsRes("", "","4");
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
	public void Test04(){	
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = readNewsRes("", "","3"); // idNews doesn't exist
		if (rp == null) {
			System.out.println("Error!\nFailed Test");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("9993", rp.code, "ID không hợp lệ", rp.message);
        
        AssertTest.assertTest("9993", rp.code, "ID không hợp lệ", rp.message);
	}	
	
	@Test
	public void Test05(){	
		System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = readNewsRes("tien20212@gmail.com", "123456","1");
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
}
