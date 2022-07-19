package models;

public class AssertTest {
	public static void assertTest(String codeExpected, String codeActual, String mesExpected, String mesActual) {
		assert(codeActual.equals(codeExpected));
        assert(mesActual.equals(mesExpected));
	}
}
