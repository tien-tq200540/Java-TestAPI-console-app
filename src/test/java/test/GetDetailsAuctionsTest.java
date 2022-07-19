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

public class GetDetailsAuctionsTest {
	@Before
    public void init() {
        RestAssured.baseURI = "https://auctions-app-2.herokuapp.com";
        RestAssured.basePath = "/api/auctions/detail";
    }
	
	public BaseResponse getResDetailAuc(int id) {
		if (id <= 3) {
			String idString = "1";
			switch (id) {
				case 1: idString = "1";
				        break;
				case 2: idString = "2";
		                break;
				case 3: idString = "3";
		                break;
			}
			Response res = given().when().get("/" + idString);
			Gson g = new Gson();
			BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
			return rp;
		} else {
			return null;
			//BaseResponse rp = new BaseResponse();
			//rp.code = "1001";
			//rp.message = "7007";
			//return rp;
		}
	}
	
	@Test
	public void Test01() {
        BaseResponse rp = getResDetailAuc(1);
		
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
        BaseResponse rp = getResDetailAuc(2);
		
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
        BaseResponse rp = getResDetailAuc(3);
		
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
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
		BaseResponse rp = getResDetailAuc(4);
		if (rp == null) {
			System.out.println("Error!\nFailed Test");
			System.out.println();
			return;
		}
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1001", rp.code, "7007", rp.message);
        
        AssertTest.assertTest("1001", rp.code, "7007", rp.message);
        
	}
}
