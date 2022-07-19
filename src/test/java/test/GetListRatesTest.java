package test;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import com.google.gson.Gson;

import base.BaseClassTest;
import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.NotiTest;

public class GetListRatesTest extends BaseClassTest {
	
	public BaseResponse getRatesRes(String index, String count, String id) {
		Response res = given().param("index", index).and().param("count", count).when().get("/rates/" + id);
		int statusCode = res.getStatusCode();
		if (statusCode == 404) return null;
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	@Test
    public void Test01() {
        BaseResponse rp = getRatesRes("1", "5", "1");
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
    public void Test02() {
        BaseResponse rp = getRatesRes("1", "5", "2");
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
    public void Test03() {
        BaseResponse rp = getRatesRes("1", "5", "3");
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
    public void Test04() {
        BaseResponse rp = getRatesRes("1", "5", "4");
        if (rp == null) {
			System.out.println("Error!\nFailed Test");
			System.out.println();
			return;
		}
        System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);

    }
}
