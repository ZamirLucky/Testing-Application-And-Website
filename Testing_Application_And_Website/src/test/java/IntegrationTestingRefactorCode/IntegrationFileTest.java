package IntegrationTestingRefactorCode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class IntegrationFileTest {
	static RefactoredFileReader totp = new RefactoredFileReader(); // instantiate class
	static String secretKey;

	@Test 
	void testSuccessfulRead() {
			assertEquals("ZAM27LUCKY6HAS24", totp.GetSecretKEY("src/test/java/secretKeyFile.txt"),
					"Secretkey Successful laoded");
	}
	@Test
    void testIOException() {
        assertEquals("MCASTSTAMCASTSTA", totp.GetSecretKEY("src/test/java/nonFxistFile.txt"), 
        		"IO exceeption test");
    }
	@Test 
	void testEmptyFile() {
		secretKey = "";
		assertEquals("MCASTSTAMCASTSTA",totp.readSecretKey(new Scanner(secretKey)),
					"Empty file test");
	}
	@Test
    void testFileFormats() {
        assertEquals("MCASTSTAMCASTSTA", totp.readSecretKey(new Scanner("ZAM27LUCKY6HAS24WGYH")), 
        		"longer than 16 characters test");
        assertEquals("MCASTSTAMCASTSTA", totp.readSecretKey(new Scanner("ZAM27LUCK")), 
        		"shorter than 16 characters test");
        assertEquals("MCASTSTAMCASTSTA", totp.readSecretKey(new Scanner("ZAM27Lfsdgfhssa##'>/,215")), 
        		"wrong characters test");
	}

}
