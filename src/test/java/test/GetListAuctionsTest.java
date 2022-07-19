package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.NotiTest;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import static io.restassured.RestAssured.*;

public class GetListAuctionsTest  {
	@Before
    public void init() {
        RestAssured.baseURI = "https://auctions-app-2.herokuapp.com";
        RestAssured.basePath = "/api/auctions";
    }

	public BaseResponse getListAuc(String index, String count, String idStatus) {
		Response res = given().param("index", index).and().param("count", count).when().get("/" + idStatus);
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
    @Test
    public void Test01() {
        BaseResponse rp = getListAuc("1", "10", "0");
        System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);

    }
    
    @Test
    public void Test02() {
        BaseResponse rp = getListAuc("1", "10", "1");
        System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);

    }
    
    @Test
    public void Test03() {
        BaseResponse rp = getListAuc("1", "10", "2");
        System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);

    }
    
    @Test
    public void Test04() {
        BaseResponse rp = getListAuc("1", "10", "3");
        System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);

    }
    
    @Test
    public void Test05() {
        BaseResponse rp = getListAuc("1", "5", "4"); // chi co max 5 Auc / page 1 khi status = 4
        System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);

    }
    
    @Test
    public void Test06() {
        BaseResponse rp = getListAuc("1", "5", "5");
        System.out.println("Unit test 6: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);

    }
    
    @Test
    public void Test07() {
        BaseResponse rp = getListAuc("1", "6", "6");
        System.out.println("Unit test 7: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);

    }
}
