package base;

import org.junit.Before;

import io.restassured.RestAssured;

public class BaseClassTest {
	@Before
    public void init() {
        RestAssured.baseURI = "https://auctions-app-2.herokuapp.com";
        RestAssured.basePath = "/api";
    }
}
