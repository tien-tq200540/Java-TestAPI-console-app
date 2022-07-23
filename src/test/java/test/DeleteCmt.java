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

public class DeleteCmt extends BaseClassTest {
	BaseResponse delCmtRes(String email, String password, String idCmt) {
		Response res;
		if (email.equals("") && password.equals("")) {
			System.out.println("Chưa đăng nhập");		
			res = given().header("Authorization", "Bearer " + "").when().post("/comments/delete/" + idCmt);
			if(res.getStatusCode() == 302) {
				BaseResponse rp = new BaseResponse();
				rp.code = "1004";
				rp.message = "Chưa đăng nhập";
				return rp;
			}
		} else {
			LoginResponse login = LoginTest.getResultLogin(email, password);
			res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).when().post("/comments/delete/" + idCmt);
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
	
	/* data: email: tien20212@gmail.com
	 *       pass: 123456
	 *       idCmt fail to del: 1042
	*/
	@Test
	public void Test01(){
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = delCmtRes("", "","1042");
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
	// 1006: xóa không thành công
	public void Test02(){
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = delCmtRes("tien20212@gmail.com", "123456","1042");
		if (rp == null) {
			System.out.println("Error!\nFailed Test");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1006", rp.code, "削除できません。", rp.message);
        
        AssertTest.assertTest("1006", rp.code, "削除できません。", rp.message);
	}	
	    
	@Test
	public void Test03(){
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		CreateCmtTest crt = new CreateCmtTest();
		String context3 = RandomStringUtils.randomAlphanumeric(10);
		crt.getCreateCmtRes("tien20212@gmail.com", "123456", "1", context3);
		
		// lần xóa sau tăng 1097 lên 1 = 1098, 1099, .... : điều chỉnh thủ công
		BaseResponse rp = delCmtRes("tien20212@gmail.com", "123456", "1097");
		
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
