package Selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(OrderAnnotation.class)
class Amazon_DeTest {

	private static WebDriver driver; 
	private static WebDriverWait wait; 
	 
	@BeforeAll
	public static void setUp() {
		driver = Drivers.startChrome();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.get("https://www.amazon.de/");
		driver.manage().window().maximize();
		//ACCEPT COOKIES        
		List<WebElement> acceptCookies = driver.findElements(By.id("sp-cc-accept"));        
		if (!acceptCookies.isEmpty())            
			acceptCookies.get(0).click();        
		else            
			System.out.println("Cookies banner not found");
		
	}
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
	
	
	// Get Search Bar 
	public void searchInput(String element) {	
		WebElement searchBar = driver.findElement(By.name("field-keywords"));
	
		searchBar.clear();
		sleep(2000); // Wait for 2 seconds
		searchBar.sendKeys(element, Keys.ENTER);		
	}
	// get time 
	
	private void sleep(long milliseconds) {
	    try {
	        Thread.sleep(milliseconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Given that i am on https://www.amazon.de/
	 * When i go to the Search bar
	 * And I search for a Skipping rope
	 * And filtered by the price hIGHT TO LOW 
	 * Then shows me the price from high to low for all elements in the page
	 ***/
	
	@Test
	@Order(1)
	public void sortByPriceHighToLowTest() {
		searchInput("skipping rope");
		// sorting by price desc
		WebElement sortDropdown =	wait.until(elementToBeClickable(By.cssSelector("select#s-result-sort-select")));
		Select sorter = new Select(sortDropdown);
		sorter.selectByVisibleText("Price: High to low");
		
		//Wait for Items to load
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.s-main-slot")));
		
		assertNotNull(driver.findElement(By.cssSelector("div.s-main-slot")));
		sleep(2000); // Wait for 2 seconds
	}
	/**
	 * Given that i am on https://www.amazon.de/
	 * When i go to the Search bar
	 * And I search for a LEDs
	 * And filtered by the price Low to Hight
	 * Then shows me the price from cheap to expensive for all elements in the page
	 ***/
	@Test
	@Order(2)
	public void sortByPriceLowtoHighTest() {
		searchInput("LEDs");
		// sorting by price Asc
		WebElement sortDropdown =	wait.until(elementToBeClickable(By.cssSelector("select#s-result-sort-select")));
		Select sorter = new Select(sortDropdown);
		sorter.selectByVisibleText("Price: Low to high");
					
		//Wait for Items to load
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.s-main-slot")));	
		assertNotNull(driver.findElement(By.cssSelector("div.s-main-slot")));
		sleep(2000); // Wait for 2 seconds
	}
	/**
	 * Given that i am on https://www.amazon.de/
	 * When i go to the Search bar
	 * And I search for a dumbbells 
	 * And filtered by Newest arrivals
	 * Then shows me the news elements for all elements in the page
	 ***/
	@Test
	@Order(3)
	public void sortByNewestArrivalsTest() {
		searchInput("dumbbells");
		// sorting by newest arrival items
		WebElement sortDropdown =	wait.until(elementToBeClickable(By.cssSelector("select#s-result-sort-select")));
		Select sorter = new Select(sortDropdown);
		sorter.selectByVisibleText("Newest arrivals");
					
		//Wait for Items to load
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.s-main-slot")));
		assertNotNull(driver.findElement(By.cssSelector("div.s-main-slot")));
		sleep(2000); // Wait for 2 seconds
	}
	/**
	 * Given that i am on https://www.amazon.de/
	 * When i go to the Search bar
	 * And I search for a programming books 
	 * And filtered by Avg. Customer review
	 * Then shows me the most reviews books first
	 ***/
	@Test
	@Order(4)
	public void sortByCustomerReviewTest() {
		searchInput("programming books");
		// sorting by customer review
		WebElement sortDropdown =	wait.until(elementToBeClickable(By.cssSelector("select#s-result-sort-select")));
		Select sorter = new Select(sortDropdown);
		sorter.selectByVisibleText("Avg. Customer review");
					
		//Wait for Items to load
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.s-main-slot")));
		sleep(2000); // Wait for 2 seconds
	}
	/**
	 * Given that i am on https://www.amazon.de/
	 * When i go to the Search bar
	 * And I search for a perfume
	 * And filtered by Featured 
	 * Then display the featured perfumes first
	 ***/
	@Test
	@Order(5)
	public void sortByFeaturedTest() {
		searchInput("perfume");
		// sorting by featured
		WebElement sortDropdown =	wait.until(elementToBeClickable(By.cssSelector("select#s-result-sort-select")));
		Select sorter = new Select(sortDropdown);
		sorter.selectByVisibleText("Featured");
					
		//Wait for Items to load
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.s-main-slot")));
		assertNotNull(driver.findElement(By.cssSelector("div.s-main-slot")));
		sleep(2000); // Wait for 2 seconds
	}
	/**
	 * Given that i am on https://www.amazon.de/
	 * When i go to the Search bar
	 * And I search home gym equipment
	 * And clicked used text link
	 * Then display the secondhand items 
	 ***/
	@Test
	@Order(6)
	public void displayUsedItemsTest() {
		searchInput("home gym equipment");
		// sorting by price Asc
		//WebElement maxPrice =	wait.until(elementToBeClickable(By.xpath("//li[@id='p_n_condition-type/776950031']")));
		WebElement used =	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Used")));
		// Click to focus on the element
	    used.click();
		
	    
					
		//Wait for page to load
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.s-main-slot")));
		assertNotNull(driver.findElement(By.cssSelector("div.s-main-slot")));
		sleep(2000); // Wait for 2 seconds
	}
	/**
	 * Given that i am on https://www.amazon.de/
	 * when clicked new text link
	 * Then display the new items
	 ***/
	@Test
	@Order(7)
	public void displayNewItemsTest() {
		WebElement newElement =	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("New")));
		// Click to focus on the element
		newElement.click();
					
		//Wait for page to load
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.s-main-slot")));
		assertNotNull(driver.findElement(By.cssSelector("div.s-main-slot")));
		sleep(2000); // Wait for 2 seconds
	}
	/**
	 * Given that i am on https://www.amazon.de/
	 * When i go to the Search bar
	 * And I search home Shilajit
	 * And clicked add to basket button
	 * Then adds one item to the Shopping Basket
	 ***/
	@Test
	@Order(8)
	public void addOneItemToBasketTest() {
		searchInput("Shilajit");
		WebElement Item =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-3-announce']")));
		// Click to focus on the element
		Item.click();
		sleep(3000);
		
		//check the value of the cart
		WebElement cart =	driver.findElement(By.id("nav-cart-count"));
		assertEquals("1",cart.getText());
		sleep(2000); // Wait for 2 seconds
	}
	/**
	 * Given that i am on https://www.amazon.de/
	 * when hovering on the langauges icon
	 * And clicked Türkçe - TR text link
	 * Then changed to the language to Turkish
	 ***/
	@Test
	@Order(10)
	public void changeLanguageToTurkishTest() {
		WebElement languages =	wait.until(ExpectedConditions.elementToBeClickable(By.id("icp-nav-flyout")));
		
		// Hover over the languages dropdown
	    Actions actions = new Actions(driver);
	    actions.moveToElement(languages).perform();

	    // Wait for the options to appear
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("icp-nav-flyout")));
	    
	    // Select Turkish
	    WebElement turkish = driver.findElement(By.linkText("Türkçe - TR"));
	    turkish.click(); 
		
	    // Check if EN modified to TR
	    sleep(3000); 
	    WebElement selectedLanguage = driver.findElement(By.className("icp-nav-link-inner"));    
	    assertEquals("TR", selectedLanguage.getText());
	    sleep(2000); // Wait for 2 seconds
	}
	/**
	 * Given that i am on https://www.amazon.de/
	 * when hovering on the sing in icon
	 * And clicked sing in button
	 * And entering correct username 
	 * And clicked continue button
	 * And entering correct password 
	 * And clicked continue button
	 * Then let me in to loged in 
	 ***/
	@Test
	@Order(9)
	public void loginTest() {
		WebElement login =	wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-accountList")));
		
		// Hover over the languages dropdown
	    Actions actions = new Actions(driver);
	    actions.moveToElement(login).perform();

	    // Wait for the options to appear
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-accountList")));
	    
	    // Select Turkish
	    WebElement btnSignin = driver.findElement(By.linkText("Sign in"));
	    btnSignin.click(); 
			    
	    // Enter username
	    WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
	    email.clear();
	    email.sendKeys("+35699725544");
	    
	    WebElement btnContinue = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
	    btnContinue.click();
	    
	    // enetr pasword
	    WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
	    password.clear();
	    password.sendKeys("123456");
	    	    
	    WebElement btnSignInSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.id("signInSubmit")));
	    btnSignInSubmit.click();
	    
	    // Check if account button shows username
	    sleep(2000);
	    WebElement logedInMessage = driver.findElement(By.id("nav-link-accountList-nav-line-1"));    
	    assertEquals("Hello, Abdirizak", logedInMessage.getText());
	    sleep(2000); // Wait for 2 seconds
	}
		
}
