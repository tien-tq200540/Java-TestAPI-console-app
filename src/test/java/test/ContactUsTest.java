package test;

import static io.restassured.RestAssured.given;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.google.gson.Gson;

import base.BaseClassTest;
import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.NotiTest;

public class ContactUsTest extends BaseClassTest {
	BaseResponse contactUsRes(String name, String phone, String email, String content, String report_type) {
        	Response res = given().param("name", name).and().param("phone", phone).and().param("email", email).and().param("content", content).and().param("report_type", report_type).when().post("/contactUs");
    		Gson g = new Gson();
    		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
    		return rp;	
	}
	
	@Test
	 // all random input = true
	public void Test01() {
		String name = RandomStringUtils.randomAlphabetic(10);
		String phone = RandomStringUtils.randomNumeric(10);
		String email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
		String content = RandomStringUtils.randomAlphanumeric(30);
		String input = "123";
		String report_type = RandomStringUtils.random(1, input);
		
		BaseResponse rp = contactUsRes(name, phone, email, content, report_type);
		
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
	 // at least 1 input = empty
	public void Test02() {
		String name = "";
		String phone = RandomStringUtils.randomNumeric(10);
		String email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
		String content = RandomStringUtils.randomAlphanumeric(30);
		String input = "123";
		String report_type = RandomStringUtils.random(1, input);
		
		BaseResponse rp = contactUsRes(name, phone, email, content, report_type);
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
      assert(rp.message != null && !"".equals(rp.message));
       
		NotiTest.notiTest("1001", rp.code, "name: 7000&phone: &email: &content: &report_type: ", rp.message);
			
		AssertTest.assertTest("1001", rp.code, "name: 7000&phone: &email: &content: &report_type: ", rp.message);
	}
	
	@Test
	 // failed email format
	public void Test03() {
		String name = RandomStringUtils.randomAlphabetic(10);
		String phone = RandomStringUtils.randomNumeric(10);
		String email = RandomStringUtils.randomAlphanumeric(10);
		String content = RandomStringUtils.randomAlphanumeric(30);
		String input = "123";
		String report_type = RandomStringUtils.random(1, input);
		
		BaseResponse rp = contactUsRes(name, phone, email, content, report_type);
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
     assert(rp.message != null && !"".equals(rp.message));
      
		NotiTest.notiTest("1001", rp.code, "name: &phone: &email: 7002&content: &report_type: ", rp.message);
			
		AssertTest.assertTest("1001", rp.code, "name: &phone: &email: 7002&content: &report_type: ", rp.message);
	}
	
	@Test
	 // failed report_type
	public void Test04() {
		String name = RandomStringUtils.randomAlphabetic(10);
		String phone = RandomStringUtils.randomNumeric(10);
		String email = RandomStringUtils.randomAlphanumeric(10);
		String content = RandomStringUtils.randomAlphanumeric(30);
		String report_type = "4";
		
		BaseResponse rp = contactUsRes(name, phone, email, content, report_type);
		
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
    assert(rp.message != null && !"".equals(rp.message));
     
		NotiTest.notiTest("1001", rp.code, "name: &phone: &email: &content: &report_type: The selected report type is invalid.", rp.message);
			
		AssertTest.assertTest("1001", rp.code, "name: &phone: &email: &content: &report_type: The selected report type is invalid.", rp.message);
	}
}
