package IntegrationTestingRefactorCode;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RefactoredFileReader {
	
	// Method to validate the format of the secret key
    public static boolean isSecretKeyValid(String secretKey) {
		// TODO Auto-generated method stub
    	//return secretKey.matches("[A-Z2-7]+");
    	//secretKey = secretKey.trim();
    	System.out.println("Secret key: " + secretKey);
    	boolean isValid = secretKey.matches("[A-Z1-9]{16}");
    	System.out.println("Is valid: " + isValid);
    	return isValid;
	}
     
 // Method to read the secretKey
	public static String GetSecretKEY(String path) {
		
		try {
			File file = new File(path);
			return readSecretKey(new Scanner(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()+"\nThe secretKey is MCASTSTAMCASTSTA");
			return "MCASTSTAMCASTSTA";
		}
	}
	public static String readSecretKey(Scanner scanner) {
		String secretKey = null;
		String temp;
		if (scanner.hasNextLine()) {
	         	temp = scanner.nextLine();
	         if (isSecretKeyValid(temp)){
	        	 secretKey = temp;
	         }else {
	        	 secretKey = "MCASTSTAMCASTSTA";
	         }
	     }else {
	    	 secretKey = "MCASTSTAMCASTSTA";	 
	     }    
		scanner.close();
	    return secretKey;
	}

}
