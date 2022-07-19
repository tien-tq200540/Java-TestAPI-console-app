package test;

import io.restassured.response.Response;
import models.AssertTest;
import models.BaseResponse;
import models.NotiTest;

import static io.restassured.RestAssured.*;
import org.junit.Test;

import com.google.gson.Gson;
import base.BaseClassTest;


public class GetSearchTest extends BaseClassTest {
	
	public final static String[] type = { 
			"1", 
			"1", 
			"2", 
			"2", 
			"2",
			"3",
			"4",
			"5",
			"",
			""
	};
	

    public final static String[] key = { 
    		"10000", 
    		"9999099", 
    		"13", 
    		"40",
    		"21:42",
    		"2022",
    		"channel",
    		"channel",
    		"channel",
    		""
	};
    
	public static final String[] codeExpected = { 
			"1000", 
			"9998", 
			"1000", 
			"9998", 
			"1000",
			"1000",
			"1000",
			"9998",
			"9998",
			"9998"
	};
	public static final String[] messageExpected = { 
			"OK", 
			"Khong tim thay", 
			"OK",
			"Khong tim thay",
			"OK",
			"OK",
			"OK",
			"Khong tim thay",
			"Khong tim thay",
			"検索できません"
	};
	
	
	public BaseResponse getResSearch(String type, String key) {
		Response res = given().param("type", type).and().param("key", key).when().get("/search");
		Gson g = new Gson();
		BaseResponse rp = g.fromJson(res.asString(), BaseResponse.class);
		return rp;
	}
	
	@Test
	public void Test01(){
		BaseResponse rp = getResSearch(type[0], key[0]);
		
		System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest(codeExpected[0], rp.code, messageExpected[0], rp.message);
        
        AssertTest.assertTest(codeExpected[0], rp.code, messageExpected[0], rp.message);
	}	
	
	@Test
	public void Test02(){
        BaseResponse rp = getResSearch(type[1], key[1]);
		
		System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest(codeExpected[1], rp.code, messageExpected[1], rp.message);
        
        AssertTest.assertTest(codeExpected[1], rp.code, messageExpected[1], rp.message);
	}
	
	@Test
	public void Test03(){
        BaseResponse rp = getResSearch(type[2], key[2]);
		
		System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest(codeExpected[2], rp.code, messageExpected[2], rp.message);
        
        AssertTest.assertTest(codeExpected[2], rp.code, messageExpected[2], rp.message);
	}	
	
	@Test
	public void Test04(){
        BaseResponse rp = getResSearch(type[3], key[3]);
		
		System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest(codeExpected[3], rp.code, messageExpected[3], rp.message);
        
        AssertTest.assertTest(codeExpected[3], rp.code, messageExpected[3], rp.message);
	}
	
	@Test
	public void Test05(){
        BaseResponse rp = getResSearch(type[4], key[4]);
		
		System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest(codeExpected[4], rp.code, messageExpected[4], rp.message);
        
        AssertTest.assertTest(codeExpected[4], rp.code, messageExpected[4], rp.message);
	}	
	
	@Test
	public void Test06(){
        BaseResponse rp = getResSearch(type[5], key[5]);
		
		System.out.println("Unit test 6: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest(codeExpected[5], rp.code, messageExpected[5], rp.message);
        
        AssertTest.assertTest(codeExpected[5], rp.code, messageExpected[5], rp.message);
	}	
	
	@Test
	public void Test07(){
        BaseResponse rp = getResSearch(type[6], key[6]);
		
		System.out.println("Unit test 7: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest(codeExpected[6], rp.code, messageExpected[6], rp.message);
        
        AssertTest.assertTest(codeExpected[6], rp.code, messageExpected[6], rp.message);
	}	
	
	@Test
	public void Test08(){
        BaseResponse rp = getResSearch(type[7], key[7]);
		
		System.out.println("Unit test 8: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest(codeExpected[7], rp.code, messageExpected[7], rp.message);
        
        AssertTest.assertTest(codeExpected[7], rp.code, messageExpected[7], rp.message);
	}	
	
	@Test
	public void Test09(){
        BaseResponse rp = getResSearch(type[8], key[8]);
		
		System.out.println("Unit test 9: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest(codeExpected[8], rp.code, messageExpected[8], rp.message);
        
        AssertTest.assertTest(codeExpected[8], rp.code, messageExpected[8], rp.message);
	}	
	
	@Test
	public void Test10(){
        BaseResponse rp = getResSearch(type[9], key[9]);
		
		System.out.println("Unit test 10: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest(codeExpected[9], rp.code, messageExpected[9], rp.message);
        
        AssertTest.assertTest(codeExpected[9], rp.code, messageExpected[9], rp.message);
	}	
}

