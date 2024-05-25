package UnitTestingCoverage;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import com.warrenstrange.googleauth.GoogleAuthenticator;



public class TOTPGenerator {
	final static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final int TIME_STEP = 30; // 30 seconds
	
	public static List<String> generateTOTPs(String secretKey, String date) throws ParseException {
		// Initialize Google Authenticator
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		
		// Generate TOTP for current, previous, and next time steps
        List<String> totps = new ArrayList<>();
		Date dateObj = formatter.parse(date);
		long currentTimeInMillis = dateObj.getTime();
		for (int i = -1; i <= 1; i++) {
			long timeInMillis = currentTimeInMillis + (i  * TIME_STEP);
			int totp = gAuth.getTotpPassword(secretKey, timeInMillis);
			totps.add(String.format("%06d", totp));
		}

		return totps;
	}
	
	// Method to validate the format of the secret key
    public static boolean isSecretKeyValid(String secretKey) {
		// TODO Auto-generated method stub
    	System.out.println("Secret key: " + secretKey);
    	boolean isValid = secretKey.matches("[A-Z1-9]{16}");
    	System.out.println("Is valid: " + isValid);
    	return isValid;
	}
    
 // Method to read the secretKey
    public static String readSecretKey(String path){
       	String secretKey = null;
       	String temp;
        	try {
                      
                File file = new File(path);
                Scanner scanner = new Scanner(file);
                if (scanner.hasNext()) {
    	            temp = scanner.nextLine();	 
    	            if (isSecretKeyValid(temp)) {
    	            	secretKey = temp;
    	            }
    	            
                }else {
               	 secretKey = "MCASTSTAMCASTSTA"; 	 
                }    
                scanner.close();
    	         return secretKey;
                            
            } catch (IOException  e) {
           	 secretKey = "MCASTSTAMCASTSTA";
           	 return secretKey;
            }
        }

}
