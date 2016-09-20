package baseclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class BaseClass {
	
	static WebDriver driver;
	
	public static WebDriver browserSetup(String browserType) {
		if (browserType.contentEquals("firefox")) {
			driver = new FirefoxDriver();			
		} else if (browserType.contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			driver = new ChromeDriver(options);
		} else if (browserType.contentEquals("ie")) {
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (!browserType.contains("firefox")||!browserType.contains("chrome")||!browserType.contains("ie")) {
			Assert.fail("Test failed. Please enter a valid browser option - chrome,firefox or ie.");
		}

		driver.manage().window().maximize();
		return driver;
	}
	
	public static void browserTeardown()
	{
		if (driver!=null) {
			driver.close();
			driver.quit();
		}
	}
	
	public boolean enterText(WebElement myElement, String textToEnter) throws InterruptedException {
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
		Thread.sleep(500);
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
	
	public static boolean typeRobotKeyCode(String inputString) throws InterruptedException, AWTException {
		Robot robot = new Robot();
		char[] charArr = inputString.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			if (Character.toString(charArr[i]).equalsIgnoreCase(":")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_SEMICOLON);
			}else if (Character.toString(charArr[i]).equalsIgnoreCase("_")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_MINUS);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_MINUS);
			}else if (Character.toString(charArr[i]).equalsIgnoreCase("E")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_E);
			}else if (Character.toString(charArr[i]).equalsIgnoreCase("B")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_B);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_B);
			}
			else {
				int charCode = (int)charArr[i];
				int keyCode = KeyEvent.getExtendedKeyCodeForChar(charCode);
//				System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
//				System.out.println(charArr[i]);
//				System.out.println(charCode);
//				System.out.println(keyCode);
//				System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
				robot.keyPress(keyCode);
				robot.keyRelease(keyCode);
			}
			
			Thread.sleep(100);
		}
		
		
		return true;
	}
	
	public static boolean pressRobotTabKey(int numberofTimes) throws InterruptedException, AWTException {
		Robot robot = new Robot();
		for (int i = 0; i < numberofTimes; i++) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(500);
		}
		
		return true;
	}
	
	public static boolean pressRobotEnterKey(int numberofTimes) throws InterruptedException, AWTException {
		Robot robot = new Robot();
		Thread.sleep(1000);
		for (int i = 0; i < numberofTimes; i++) {
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(500);
		}
		
		return true;
	}
	
	public static boolean pressRobotCtrlSaveKey() throws AWTException, InterruptedException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		return true;
	}
	
	public static String getCurrentTimeStampInWinFormat() {
		String timeStamp = null;
		java.util.Date date= new java.util.Date();
		timeStamp = new Timestamp(date.getTime()).toString();
		timeStamp = timeStamp.replaceAll(" ", "-");
		timeStamp = timeStamp.replaceAll(":", "-");
		timeStamp = timeStamp.replaceAll("\\.", "-");
		return timeStamp;
	}
	
	public boolean downloadAndSavePdfFile(String filePath, String fileName) throws AWTException, InterruptedException {
		  Thread.sleep(10000);
		  pressRobotCtrlSaveKey();
		  pressRobotTabKey(5);
		  pressRobotEnterKey(1);
		  typeRobotKeyCode(filePath);
		  pressRobotEnterKey(1);
		  pressRobotTabKey(6);
		  typeRobotKeyCode(fileName);
		  pressRobotEnterKey(1);
		
		return true;
	}
	
	public boolean waitAndClick(WebElement ele, int timeout, WebDriver driver, String elementName){
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
			element = wait.until(ExpectedConditions.elementToBeClickable(ele));
			click(element, elementName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean waitForElementToBeDisplayed(WebElement ele, int timeout, WebDriver driver){
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean waitForElementToBeEnabled(WebElement ele, int timeout, WebDriver driver){
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean waitForElementToBeNOTDisplayed(WebElement ele, int timeout, WebDriver driver){
		try {
			List<WebElement> elements = Arrays.asList(ele);
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			Boolean isNotDisplayed = wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
			return isNotDisplayed;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public void waitForPageLoad(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30); wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
	}
}
