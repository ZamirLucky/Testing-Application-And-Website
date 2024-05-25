package Main;

import java.text.ParseException;
import java.util.List;
 
import UnitTestingCoverage.TOTPGenerator;

public class MainOutput {
	
	static TOTPGenerator totpGenerator = new TOTPGenerator();
	
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		// Load secret key (you can replace this with your method to load from file, database, etc.)
		String orginalSecretKey = totpGenerator.readSecretKey("C:\\Users\\student/secretKeyFiles.txt");
	
        // Generate TOTPs
        List<String> totps = totpGenerator.generateTOTPs(orginalSecretKey, "2024-28-25 13:00:00");
        
        // Display the current one, the previous, and the next one
        System.out.println("Current TOTP: " + totps.get(1));
        System.out.println("Previous TOTP: " + totps.get(0));
        System.out.println("Next TOTP: " + totps.get(2));
	}

}
