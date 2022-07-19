package models;

public class NotiTest {
	public static void notiTest (String codeExpected, String codeActual, String mesExpected, String mesActual) {
		System.out.println("Expected: ");
        System.out.println(codeExpected);
        System.out.println(mesExpected);
        
        System.out.println("Actual: ");
        System.out.println(codeActual);
        System.out.println(mesActual);
		
		if (codeActual.equals(codeExpected) && mesActual.equals(mesExpected)) 
        	System.out.println("Finished! Satisfied!");
        else System.out.println("Failed Test");
        System.out.println();
	}
}
