package test;

import io.restassured.response.Response;
import models.AssertTest;
import models.LoginResponse;
import models.NotiTest;

import org.junit.Test;

import com.google.gson.Gson;

import base.BaseClassTest;
import constant.ConstCharacters;

import static io.restassured.RestAssured.*;

public class LoginTest extends BaseClassTest {
	
	public static LoginResponse getResultLogin(String email, String password) {
		Response res = given().param("email", email).
        		and().param("password", password)
        		.when().post("/login");
		
		Gson g = new Gson();
        LoginResponse rp = g.fromJson(res.asString(), LoginResponse.class);
        rp.data.user.setPassword(password);
		return rp;
	}

	/* True Request Data:
	email: tien123456@gmail.com, pass: 123456
	email: tien1234567@gmail.com, pass: ConstantCharacters.CHAR_255
	*/
	
	@Test
	// email: true, pass: true
    public void Test01() {
        LoginResponse rp = getResultLogin("tien123456@gmail.com", "123456");
        
        System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
    }
	
	@Test
	// email: true, pass: fail
    public void Test02() {
        LoginResponse rp = getResultLogin("tien123456@gmail.com", "12345");
        
        System.out.println("Unit test 2: The code and message strings shall be not NULL as well as non-empty:");       
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1002", rp.code, "メールとパスワードは違いました", rp.message);
        
        AssertTest.assertTest("1002", rp.code, "メールとパスワードは違いました", rp.message);
    }
	
	@Test
	// email: fail, pass: true
    public void Test03() {
        LoginResponse rp = getResultLogin("tien12345@gmail.com", "123456");
        
        System.out.println("Unit test 3: The code and message strings shall be not NULL as well as non-empty:");       
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1002", rp.code, "メールとパスワードは違いました", rp.message);
        
        AssertTest.assertTest("1002", rp.code, "メールとパスワードは違いました", rp.message);
    }
	
	@Test
	// email = "", pass = random (email = random, pass = "" is same)
    public void Test04() {
        LoginResponse rp = getResultLogin("", "123456");
        
        System.out.println("Unit test 4: The code and message strings shall be not NULL as well as non-empty:");       
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1001", rp.code, "email: 7000 &password: ", rp.message);
        
        AssertTest.assertTest("1001", rp.code, "email: 7000 &password: ", rp.message);
    }
	
	@Test
	// email = fail format, pass = random
    public void Test05() {
        LoginResponse rp = getResultLogin("tien123456gmail.com", "123456");
        
        System.out.println("Unit test 5: The code and message strings shall be not NULL as well as non-empty:");       
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1001", rp.code, "email: 7002 &password: ", rp.message);
        
        AssertTest.assertTest("1001", rp.code, "email: 7002 &password: ", rp.message);
    }
	
	@Test
	// email = fail format, pass = ""
    public void Test06() {
        LoginResponse rp = getResultLogin("tien123456gmail.com", "");
        
        System.out.println("Unit test 6: The code and message strings shall be not NULL as well as non-empty:");       
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1001", rp.code, "email: 7002 &password: 7000", rp.message);
        
        AssertTest.assertTest("1001", rp.code, "email: 7002 &password: 7000", rp.message);
    }
	
	@Test
	// email = true, pass = 255 char
    public void Test07() {
        LoginResponse rp = getResultLogin("tien1234567@gmail.com", ConstCharacters.CHAR_255);
        
        System.out.println("Unit test 7: The code and message strings shall be not NULL as well as non-empty:");       
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1000", rp.code, "OK", rp.message);
        
        AssertTest.assertTest("1000", rp.code, "OK", rp.message);
    }
	
	@Test
	// email = true, pass = more than 255 char (example: 256 char)
    public void Test08() {
        LoginResponse rp = getResultLogin("tien1234567@gmail.com", ConstCharacters.CHAR_256);
        
        System.out.println("Unit test 8: The code and message strings shall be not NULL as well as non-empty:");       
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1001", rp.code, "email:  &password: 7001", rp.message);
        
        AssertTest.assertTest("1001", rp.code, "email:  &password: 7001", rp.message);
    }
	
	@Test
	// email = "", pass = ""
    public void Test09() {
        LoginResponse rp = getResultLogin("", "");
        
        System.out.println("Unit test 9: The code and message strings shall be not NULL as well as non-empty:");       
        assert(rp.code != null && !"".equals(rp.code));
        assert(rp.message != null && !"".equals(rp.message));
        
        NotiTest.notiTest("1001", rp.code, "email: 7000 &password: 7000", rp.message);
        
        AssertTest.assertTest("1001", rp.code, "email: 7000 &password: 7000", rp.message);
    }
}