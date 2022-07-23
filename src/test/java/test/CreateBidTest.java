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

public class CreateBidTest extends BaseClassTest {
	BaseResponse createBidRes(String email, String password, String price, String idAuction) {
        if (email.equals("") || password.equals("")) {
        	BaseResponse rp = new BaseResponse();
			rp.code = "1004";
			rp.message = "Chưa đăng nhập";
			return rp;
        } else {
        	LoginResponse login = LoginTest.getResultLogin(email, password);
        	Response res = given().header("Authorization", "Bearer " + login.data.getAccess_token()).and().param("price", price).when().post("/bids/create/" + idAuction);
    		Gson g = new Gson();
    		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
    		return rp;
        }	      
	}
	
	@Test
	 // not login
	public void Test01() {
		String price = RandomStringUtils.randomNumeric(10);
		BaseResponse rp = createBidRes("", "", price, "1");
		
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
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
	 // price = empty
	public void Test02() {
		String price = "";
		BaseResponse rp = createBidRes("tien123456@gmail.com", "123456", price, "1");
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
      assert(rp.message != null && !"".equals(rp.message));
       
		NotiTest.notiTest("1001", rp.code, "price: 7000", rp.message);
			
		AssertTest.assertTest("1001", rp.code, "price: 7000", rp.message);
	}
	
	@Test
	 // price < max_bid hiện tại
	public void Test03() {
		String price = "123456";
		BaseResponse rp = createBidRes("tien123456@gmail.com", "123456", price, "1");
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
     assert(rp.message != null && !"".equals(rp.message));
      
		NotiTest.notiTest("1001", rp.code, "price: 7014", rp.message);
			
		AssertTest.assertTest("1001", rp.code, "price: 7014", rp.message);
	}
	
	@Test
	 // price > max_bid hiện tại, nhưng auction có id = 1 đã kết thúc
	public void Test04() {
		String price = "223456456789";
		BaseResponse rp = createBidRes("tien123456@gmail.com", "123456", price, "1");
		
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!");
			System.out.println("Failed Test!");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
    assert(rp.message != null && !"".equals(rp.message));
     
		NotiTest.notiTest("1008", rp.code, "Không thể trả giá", rp.message);
			
		AssertTest.assertTest("1008", rp.code, "Không thể trả giá", rp.message);
	}
	
	
	@Test
	// tạo bid thành công
	public void Test05() {		
	    String price = RandomStringUtils.randomNumeric(25);
		BaseResponse rp = createBidRes("tien123456@gmail.com", "123456", price, "3");
		
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
			
		AssertTest.assertTest("1000", rp.code, "OK", rp.message);
	}
}
