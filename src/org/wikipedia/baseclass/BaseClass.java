package baseclass;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

public abstract class BaseClass {
	
	static WebDriver driver;
	
	public static WebDriver browserSetup(String browserType) {
		if (browserType.contentEquals("firefox")) {
			driver = new FirefoxDriver();			
		} else if (browserType.contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserType.contentEquals("ie")) {
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (!browserType.contains("firefox")||!browserType.contains("chrome")||!browserType.contains("ie")) {
			Assert.fail("Test failed. Please enter a valid browser option - chrome,firefox or ie.");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void browserTeardown()
	{
		if (driver!=null) {
			driver.close();
			driver.quit();
		}
	}
	
	public boolean enterText(WebElement myElement, String textToEnter) {
		boolean isDisplayed = false;
		try {
			isDisplayed = myElement.isDisplayed();
		} catch (Exception e) {}
		if (!isDisplayed) {
			Assert.fail("Element not present on the page for entering text: "+textToEnter);
		}else {
			myElement.clear();
			myElement.sendKeys(textToEnter);	
		}
		return true;
	}
	
	public boolean click(WebElement myElement, String webElementName) {
		try {
			myElement.click();
			return true;
		} catch (Exception e) {
			System.out.println("Element in error is: "+webElementName);
			return false;
		}
	}
	
	public boolean checkFilePresent(String filePath) {
		boolean isFound = false;
		try {
			File f = new File(filePath);
			if (f.exists()) {
				isFound = true;
			}else {
				isFound=false;
			}
		} catch(Exception e){}
		return isFound;
	}
}
