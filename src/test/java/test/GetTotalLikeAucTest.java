package test;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import com.google.gson.Gson;

import base.BaseClassTest;
import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.NotiTest;

public class GetTotalLikeAucTest extends BaseClassTest {
	
	public BaseResponse getTotalLikeRes(String id) {
		Response res = given().when().get("/totalLikes/" + id);
		int statusCode = res.getStatusCode();
		if (statusCode == 404) return null;
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	// if id = 4, 5,..., 8 & 10, 11, 13, 14 return 404 Not Found?
	
	@Test
	public void Test01(){
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = getTotalLikeRes("1");
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
		BaseResponse rp = getTotalLikeRes("2");
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
		BaseResponse rp = getTotalLikeRes("3");
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
		BaseResponse rp = getTotalLikeRes("4");
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
	public void Test05(){
		System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = getTotalLikeRes("5");
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
