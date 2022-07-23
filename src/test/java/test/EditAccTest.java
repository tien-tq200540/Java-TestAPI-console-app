package test;

import static io.restassured.RestAssured.given;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.google.gson.Gson;

import base.BaseClassTest;
import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.LoginResponse;
import models.NotiTest;

public class EditAccTest extends BaseClassTest {
	BaseResponse editAccRes(String email, String password, String new_email, String new_name, String new_phone) {
        if (email.equals("") || password.equals("")) {
        	BaseResponse rp = new BaseResponse();
			rp.code = "1004";
			rp.message = "Chưa đăng nhập";
			return rp;
        } else {
        	LoginResponse login = LoginTest.getResultLogin(email, password);
        	Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).and().param("name", new_name).and().param("phone", new_phone).and().param("email", new_email).when().post("/edit");
    		Gson g = new Gson();
    		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
    		return rp;
        }	      
	}
	
	/*
	 * data: email: oop@gmail.com
	 *       pass: 123456
	 */
	 @Test
	 // edit name & phone
 	public void Test01() {
 		String name = RandomStringUtils.randomAlphanumeric(10);
 		String phone = RandomStringUtils.randomNumeric(10);
 		BaseResponse rp = editAccRes("oop@gmail.com", "123456", "oop@gmail.com", name, phone);
 		
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
 			
 		AssertTest.assertTest("1000", rp.code, "OK", rp.message);
 	}
	 
	 @Test
	 // if not login
 	public void Test02() {
 		String name = RandomStringUtils.randomAlphanumeric(10);
 		String phone = RandomStringUtils.randomNumeric(10);
 		BaseResponse rp = editAccRes("", "", "oop@gmail.com", name, phone);
 		
 		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
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
	 // if name / phone = empty
 	public void Test03() {
 		String name = "";
 		String phone = RandomStringUtils.randomNumeric(10);
 		BaseResponse rp = editAccRes("oop@gmail.com", "123456", "oop@gmail.com", name, phone);
 		
 		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
 		if (rp == null) {
 			System.out.println("Error!");
 			System.out.println("Failed Test!");
 			System.out.println();
 			return;
 		}
 		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
         
 		NotiTest.notiTest("1001", rp.code, "name: 7000&phone: &address: &email:  &avatar: ", rp.message);
 			
 		AssertTest.assertTest("1001", rp.code, "name: 7000&phone: &address: &email:  &avatar: ", rp.message);
 	}
	 
	 @Test
	 // if duplicate email
 	public void Test04() {
 		String name = RandomStringUtils.randomAlphanumeric(10);
 		String phone = RandomStringUtils.randomNumeric(10);
 		BaseResponse rp = editAccRes("oop@gmail.com", "123456", "oop2@gmail.com", name, phone);
 		
 		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
 		if (rp == null) {
 			System.out.println("Error!");
 			System.out.println("Failed Test!");
 			System.out.println();
 			return;
 		}
 		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
         
 		NotiTest.notiTest("1001", rp.code, "name: &phone: &address: &email: 7004 &avatar: ", rp.message);
 			
 		AssertTest.assertTest("1001", rp.code, "name: &phone: &address: &email: 7004 &avatar: ", rp.message);
 	}
	 
	 @Test
	 // success change email
 	public void Test05() {
		String email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
 		String name = RandomStringUtils.randomAlphanumeric(10);
 		String phone = RandomStringUtils.randomNumeric(10);
 		BaseResponse rp = editAccRes("oop@gmail.com", "123456", email, name, phone);
 		
 		System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
 		if (rp == null) {
 			System.out.println("Error!");
 			System.out.println("Failed Test!");
 			System.out.println();
 			return;
 		}
 		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
         
 		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
 		// change to old email for future test
 		LoginResponse lg = LoginTest.getResultLogin(email, "123456");
    	given().header("Authorization", "Bearer " + lg.data.getAccess_token()).and().param("name", name).and().param("phone", phone).and().param("email", "oop@gmail.com").when().post("/edit");
 		
    	AssertTest.assertTest("1000", rp.code, "OK", rp.message);
 	}
	 
	 @Test
	 // if phone was not a number
 	public void Test06() {
		String email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
 		String name = RandomStringUtils.randomAlphanumeric(10);
 		String phone = RandomStringUtils.randomNumeric(10);
 		BaseResponse rp = editAccRes("oop@gmail.com", "123456", email, name, phone);
 		
 		System.out.println("Unit test 6: The code and message strings shall be not NULL as well as non-empty:");
 		if (rp == null) {
 			System.out.println("Error!");
 			System.out.println("Failed Test!");
 			System.out.println();
 			return;
 		}
 		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
         
 		NotiTest.notiTest("1000", rp.code, "OK", rp.message);
 		// change to old email for future test
 		LoginResponse lg = LoginTest.getResultLogin(email, "123456");
    	given().header("Authorization", "Bearer " + lg.data.getAccess_token()).and().param("name", name).and().param("phone", phone).and().param("email", "oop@gmail.com").when().post("/edit");
 		
    	AssertTest.assertTest("1000", rp.code, "OK", rp.message);
 	}
}
