package UnitTestingCoverage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.text.ParseException;
import java.util.List;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TOTPGeneratorTest {
	
	static TOTPGenerator totpGenerator = new TOTPGenerator(); // instantiate class
	static String orginalSecretKey;									// store secret key
	static List<String> totps;
	static String DEFAULT_SECRETKEY = "MCASTSTAMCASTSTA ";
    
   
	/**
	 * This method is executed once before all tests.
	 * initializes some setup required for the tests.
	 *  reads a secret key from a file
	 *  
	 * @throws Exception
	 */
    
	@BeforeAll
	static void setUpBeforeAll() throws Exception {
		// Read secret key from file
		//// secret key is null or invalid? set default key:
		orginalSecretKey = totpGenerator.readSecretKey("src\\main\resources/secretKey.txt");

		 totps = totpGenerator.generateTOTPs(orginalSecretKey, "2023-12-25 07:00:00");
	}
	

	@Test
	void testSpecificTimesWithDeafultSecretKey() throws ParseException {
		totps = totpGenerator.generateTOTPs(DEFAULT_SECRETKEY, "2023-12-25 07:00:00");
		assertEquals("400136" , totps.get(1));
		
		totps = totpGenerator.generateTOTPs(DEFAULT_SECRETKEY, "2024-01-25 10:00:00");
		assertEquals("390900" ,totps.get(1));
		
		totps = totpGenerator.generateTOTPs(DEFAULT_SECRETKEY, "2024-02-25 13:00:00");
		assertEquals("764104" , totps.get(1));
	}
	
	@Test
	void testMissedTime() throws ParseException {
		// Define the action that should throw an exception
	    assertThrows(ParseException.class, () -> {
	        // Call the method that is expected to throw the exception
	        totpGenerator.generateTOTPs(orginalSecretKey, "2022-12-25");
	    });	
	}
	
	@Test
	void testIncorrectMonth() throws ParseException {
		// Define the action that should throw an exception
	    assertThrows(ParseException.class, () -> {
	        // Call the method that is expected to throw the exception
	        totpGenerator.generateTOTPs(orginalSecretKey, "2024-}}-25 13:00:00");
	    });	
	}
	


}
