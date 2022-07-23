package test;

import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.NotiTest;

import org.junit.Test;

import com.google.gson.Gson;

import base.BaseClassTest;

import static io.restassured.RestAssured.*;

public class GetDetailsAuctionsTest extends BaseClassTest {
	public BaseResponse getResDetailAuc(String id) {
			Response res = given().when().get("/auctions/detail/" + id);
			if (res.getStatusCode() == 404) return null;
			Gson g = new Gson();
			BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
			return rp;
	}
	
	@Test
	public void Test01() {
        BaseResponse rp = getResDetailAuc("1");
		
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
	}
	
	@Test
	public void Test02() {
        BaseResponse rp = getResDetailAuc("2");
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
	}
	
	@Test
	public void Test03() {
        BaseResponse rp = getResDetailAuc("3");
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
	}
	
	@Test
	public void Test04() {
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = getResDetailAuc("4");
		if (rp == null) {
			rp = new BaseResponse();
			rp.code = "Unknown";
			rp.message = "Unknown";
		}
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("9993", rp.code, "ID không hợp lệ", rp.message);
        
        AssertTest.assertTest("9993", rp.code, "ID không hợp lệ", rp.message);
        
	}
}
