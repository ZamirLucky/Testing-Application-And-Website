# Testing-Application-And-Website
This project aims to practice software test automation by generating TOTP and testing it.
and also launched the Amazon.de website and tested it. 
So you will gain here the skills of unit tests, integration tests, and acceptance tests. 

# Code Design
TOTPGenerator Class:
This class contains methods for generating TOTPs, validating secret keys, and reading secret keys from a file. 
generateTOTPs(String secretKey, String date): Generates TOTPs for the current, previous, and next time steps 
passed by secret key and date as parameter. The one for loop has this method chatGPT.has implements and I 
understood and used. 
isSecretKeyValid(String secretKey): Validates the format of the secret key. 
readSecretKey(String path): Reads the secret key from a file. 

Used Google Authenticator Library:
The code imports classes from the com.warrenstrange.googleauth package, which is part of the Google 
Authenticator library. This library enables me to generate TOTPs. I used widely The Google Authenticator 
library. Here is the link I got the Google Auth dependencies 
https://mvnrepository.com/artifact/com.warrenstrange/googleauth/1.4.0

# Unit Testing Overview:
TOTPGeneratorTest Class:
testSpecificTimesWithDeafultSecretKey():
Tests the TOTP generation for specific times using the default secret key and verifies whether the TOTPs match 
the expected values.
testMissedTime():
Tests the behavior when a missing time and only date is entered and checks whether the method throws a 
ParseException.
testWrongMonth():
Tests the behavior when an invalid month is provided and verifies whether the method throws a 
ParseException.

# Integration Testing Overview:
testSuccessfulRead():
Tests the successful loading of the secret key and checks whether the correct secret key is loaded from 
the specified file path.
testIOException(), testEmptyFile():
Tests the behavior when an incorrect path or empty file is provided and verifies whether the methods
returns the default secret key.
testFileFormats():
Tests various file formats for the secret key and checks whether the method returns the default secret 
key for invalid secret key formats.

# Acceptance Testing Overview:
We are conducting acceptance testing on various functionalities of the Amazon.de website. 
The tests encompass searching with specific parameters and sorting results in multiple ways, including by price, customer reviews, and condition (new/used items). Additionally, we are verifying the language change feature and the login process for existing users.

# Technologies Applied:
Java 

JUnit

Google Authenticator

Selenium

# Screen Captured
Unit test coverage
![image](https://github.com/Abdirizak264/Testing-Application-And-Website/assets/115321486/17bae749-cce1-4d09-9c2c-9bcc9010c363)

![image](https://github.com/Abdirizak264/Testing-Application-And-Website/assets/115321486/1e36d613-849b-4e82-b276-3bc6907dc393)




