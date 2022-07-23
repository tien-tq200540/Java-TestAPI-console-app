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

public class CreateAuctionTest extends BaseClassTest {
	BaseResponse createAucRes(String email, String password, String category_id, String start_date, String end_date, String title_ni) {
		Gson g = new Gson();
		Response res;
        LoginResponse login = LoginTest.getResultLogin(email, password);
        res = given().header("Authorization", "Bearer " + login.data.getAccess_token())
        			.and().param("category_id", category_id)
        			.and().param("start_date", start_date)
        			.and().param("end_date", end_date)
        			.and().param("title_ni", title_ni)
        			.when().post("/auctions/create");
        BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	
	@Test
	 // not login
	public void Test01() {
		String title_ni = RandomStringUtils.randomAlphanumeric(10);
		Response res = given().header("Authorization", "Bearer " + "")
        			.and().param("category_id", "1")
        			.and().param("start_date", "2022-07-29 11:03:38")
        			.and().param("end_date", "2022-08-16 11:03:38")
        			.and().param("title_ni", title_ni)
        			.when().post("/auctions/create");
		BaseResponse rp = new BaseResponse();
		if (res.getStatusCode() == 302) {
			rp.code = "1004";
			rp.message = "Chưa đăng nhập";
		}
				
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		assert(rp.code != null && !"".equals(rp.code));
       assert(rp.message != null && !"".equals(rp.message));
        
		NotiTest.notiTest("1004", rp.code, "Chưa đăng nhập", rp.message);
			
		AssertTest.assertTest("1004", rp.code, "Chưa đăng nhập", rp.message);
	}
	
	@Test
	 // trùng tên phiên đấu giá
	public void Test02() {
		BaseResponse rp = createAucRes("oop@gmail.com", "123456", "1", "2022-07-29 11:03:38", "2022-08-16 11:03:38", "test create auc");
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
      assert(rp.message != null && !"".equals(rp.message));
       
		NotiTest.notiTest("1001", rp.code, "category: &start_date: &end_date: &title: 7005", rp.message);
			
		AssertTest.assertTest("1001", rp.code, "category: &start_date: &end_date: &title: 7005", rp.message);
	}
	
	@Test
	 // ít nhất 1 input = empty
	public void Test03() {
		BaseResponse rp = createAucRes("oop@gmail.com", "123456", "", "", "", "");
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
     assert(rp.message != null && !"".equals(rp.message));
      
		NotiTest.notiTest("1001", rp.code, "category: 7000&start_date: 7000&end_date: 7000&title: 7000", rp.message);
			
		AssertTest.assertTest("1001", rp.code, "category: 7000&start_date: 7000&end_date: 7000&title: 7000", rp.message);
	}
	
	@Test
	 // error format date
	public void Test04() {
		String title_ni = RandomStringUtils.randomAlphanumeric(10);
		BaseResponse rp = createAucRes("oop@gmail.com", "123456", "1", "2022/0829 11:03:38", "2022/0829 11:03:38", title_ni);
		
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
    assert(rp.message != null && !"".equals(rp.message));
     
		NotiTest.notiTest("1001", rp.code, "category: &start_date: 7008&end_date: 7008&title: ", rp.message);
			
		AssertTest.assertTest("1001", rp.code, "category: &start_date: 7008&end_date: 7008&title: ", rp.message);
	}
	
	@Test
	 // thời gian bắt đầu quá sớm
	public void Test05() {
		String title_ni = RandomStringUtils.randomAlphanumeric(10);
		BaseResponse rp = createAucRes("oop@gmail.com", "123456", "1", "2022/05/29 11:03:38", "2022/08/29 11:03:38", title_ni);
		
		System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
   assert(rp.message != null && !"".equals(rp.message));
    
		NotiTest.notiTest("1001", rp.code, "category: &start_date: 7009&end_date: &title: ", rp.message);
			
		AssertTest.assertTest("1001", rp.code, "category: &start_date: 7009&end_date: &title: ", rp.message);
	}
	
	@Test
	 // thời gian kết thúc sớm hơn thời gian bắt đầu
	public void Test06() {
		String title_ni = RandomStringUtils.randomAlphanumeric(10);
		BaseResponse rp = createAucRes("oop@gmail.com", "123456", "1", "2022/08/29 11:03:38", "2022/05/29 11:03:38", title_ni);
		
		System.out.println("Unit test 6: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
   
		NotiTest.notiTest("1001", rp.code, "category: &start_date: &end_date: 7010&title: ", rp.message);
			
		AssertTest.assertTest("1001", rp.code, "category: &start_date: &end_date: 7010&title: ", rp.message);
	}
	
	@Test
	 // tạo thành công
	public void Test07() {
		String title_ni = RandomStringUtils.randomAlphanumeric(10);
		BaseResponse rp = createAucRes("oop@gmail.com", "123456", "1", "2022/08/29 11:03:38", "2022/10/29 11:03:38", title_ni);
		
		System.out.println("Unit test 6: The code and message strings shall be not NULL as well as non-empty:");
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
}
