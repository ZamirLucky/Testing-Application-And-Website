package Selenium;

import java.io.File;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Robert
 * @see http://learnselenium.techcanvass.com/selenium-tutorials/
 * @see https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html
 */
public class Drivers {
	private static final String baseDir = "drivers"+File.separatorChar;
	public static final String FIREFOX = baseDir+"geckodriver.exe";
	public static final String CHROME  = baseDir+"chromedriver.exe";
	public static final String EDGE  = baseDir+"msedgedriver.exe";
	
	public static WebDriver startFirefox() {
		System.setProperty("webdriver.gecko.driver", Drivers.FIREFOX);
		FirefoxOptions options = new FirefoxOptions();
		options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); //location where Firefox is installed
		return new FirefoxDriver(options);
	}
	
	public static WebDriver startChrome() {
		System.setProperty("webdriver.chrome.driver", Drivers.CHROME);
		return new ChromeDriver();
	}

	public static WebDriver startChromeHeadlessNoLogs() {
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		System.setProperty("webdriver.chrome.driver", Drivers.CHROME);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		return new ChromeDriver(options);
	}

	public static WebDriver startEdge() {
		System.setProperty("webdriver.edge.driver", Drivers.EDGE);
		return new EdgeDriver();
	}
	
	public static void waitToClick(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement elementFound = wait.until(ExpectedConditions.elementToBeClickable(element));
		elementFound.click();
	}
	
	public static void waitToClick(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		element.click();
	}
	
	public static void scrollIntoView(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element); 
	}
}
