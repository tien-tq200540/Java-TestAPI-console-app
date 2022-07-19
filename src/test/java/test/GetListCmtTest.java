package test;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.NotiTest;

public class GetListCmtTest {
	@Before
    public void init() {
        RestAssured.baseURI = "https://auctions-app-2.herokuapp.com";
        RestAssured.basePath = "/api/comments";
    }
	
	public BaseResponse getResCmt(String index, String count, String id) {
		Response res = given().param("index", index).and().param("count", count).when().get("/" + id);
		int statusCode = res.getStatusCode();
		if (statusCode == 500) return null;
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	@Test
	public void Test01(){
		BaseResponse rp = getResCmt("1", "10", "1");
		
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
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
		BaseResponse rp = getResCmt("1", "10", "2");
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
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
		BaseResponse rp = getResCmt("1", "10", "3");
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
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
	// index = count = empty => ko xđ đc cmt => code 1001, index: 7006 & count: 7006
	public void Test04(){
		BaseResponse rp = getResCmt("", "", "1");
		
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
		if (rp == null) {
			System.out.println("Error!\nFailed Test");
			System.out.println();
			return;
		}
		assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1001", rp.code, "index: 7006 &count: 7006", rp.message);
        
        AssertTest.assertTest("1001", rp.code, "index: 7006 &count: 7006", rp.message);
	}	
	
	@Test
	// index = count = 1 char != num char => ko xđ đc cmt => code 1001, index: 7006 & count: 7006
	// khi send request index = count = 1 char != num char => error server ? 
	public void Test05(){
		System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
		
		BaseResponse rp = getResCmt("a", "a", "1");
		if (rp == null) {
			System.out.println("Expected:");
			System.out.println("1001");
			System.out.println("index: 7006 &count: 7006");
			System.out.println("Actual:");
			System.out.println("Code: null");
			System.out.println("Message: null");
			System.out.println("Error!\nFailed Test");
			System.out.println();
			return;
		}
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1001", rp.code, "index: 7006 &count: 7006", rp.message);
        
        AssertTest.assertTest("1001", rp.code, "index: 7006 &count: 7006", rp.message);
	}	
}
