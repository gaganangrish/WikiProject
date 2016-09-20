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
	
	public boolean typeRobotKeyCode(String inputString) throws InterruptedException, AWTException {
		Robot robot = new Robot();
		char[] charArr = inputString.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			pressChar(charArr[i]);
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
		  Thread.sleep(5000);
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
	
	public void pressChar(char ch) throws AWTException {
		Robot robot = new Robot();
		switch (ch) {
		case '-':robot.keyPress(KeyEvent.VK_MINUS);robot.keyRelease(KeyEvent.VK_MINUS);break;
		case '.':robot.keyPress(KeyEvent.VK_PERIOD);robot.keyRelease(KeyEvent.VK_PERIOD);break;
		case '0':robot.keyPress(KeyEvent.VK_0);robot.keyRelease(KeyEvent.VK_0);break;	
		case '1':robot.keyPress(KeyEvent.VK_1);robot.keyRelease(KeyEvent.VK_1);break;	
		case '2':robot.keyPress(KeyEvent.VK_2);robot.keyRelease(KeyEvent.VK_2);break;	
		case '3':robot.keyPress(KeyEvent.VK_3);robot.keyRelease(KeyEvent.VK_3);break;	
		case '4':robot.keyPress(KeyEvent.VK_4);robot.keyRelease(KeyEvent.VK_4);break;	
		case '5':robot.keyPress(KeyEvent.VK_5);robot.keyRelease(KeyEvent.VK_5);break;	
		case '6':robot.keyPress(KeyEvent.VK_6);robot.keyRelease(KeyEvent.VK_6);break;	
		case '7':robot.keyPress(KeyEvent.VK_7);robot.keyRelease(KeyEvent.VK_7);break;	
		case '8':robot.keyPress(KeyEvent.VK_8);robot.keyRelease(KeyEvent.VK_8);break;	
		case '9':robot.keyPress(KeyEvent.VK_9);robot.keyRelease(KeyEvent.VK_9);break;	
		case ':':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_SEMICOLON);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_SEMICOLON);break;		
		case 'A':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_A);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_A);break;	
		case 'B':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_B);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_B);break;	
		case 'C':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_C);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_C);break;	
		case 'D':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_D);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_D);break;	
		case 'E':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_E);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_E);break;	
		case 'F':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_F);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_F);break;	
		case 'G':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_G);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_G);break;	
		case 'H':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_H);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_H);break;	
		case 'I':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_I);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_I);break;	
		case 'J':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_J);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_J);break;	
		case 'K':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_K);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_K);break;	
		case 'L':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_L);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_L);break;	
		case 'M':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_M);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_M);break;	
		case 'N':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_N);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_N);break;	
		case 'O':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_O);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_O);break;	
		case 'P':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_P);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_P);break;	
		case 'Q':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_Q);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_Q);break;	
		case 'R':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_R);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_R);break;	
		case 'S':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_S);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_S);break;	
		case 'T':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_T);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_T);break;	
		case 'U':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_U);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_U);break;	
		case 'V':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_V);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_V);break;	
		case 'W':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_W);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_W);break;	
		case 'X':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_X);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_X);break;	
		case 'Y':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_Y);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_Y);break;	
		case 'Z':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_Z);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_Z);break;	
		case '\\':robot.keyPress(KeyEvent.VK_BACK_SLASH);robot.keyRelease(KeyEvent.VK_BACK_SLASH);break;	
		case '_':robot.keyPress(KeyEvent.VK_SHIFT);robot.keyPress(KeyEvent.VK_MINUS);robot.keyRelease(KeyEvent.VK_SHIFT);robot.keyRelease(KeyEvent.VK_MINUS);break;	
		case 'a':robot.keyPress(KeyEvent.VK_A);robot.keyRelease(KeyEvent.VK_A);break;	
		case 'b':robot.keyPress(KeyEvent.VK_B);robot.keyRelease(KeyEvent.VK_B);break;
		case 'c':robot.keyPress(KeyEvent.VK_C);robot.keyRelease(KeyEvent.VK_C);break;
		case 'd':robot.keyPress(KeyEvent.VK_D);robot.keyRelease(KeyEvent.VK_D);break;
		case 'e':robot.keyPress(KeyEvent.VK_E);robot.keyRelease(KeyEvent.VK_E);break;
		case 'f':robot.keyPress(KeyEvent.VK_F);robot.keyRelease(KeyEvent.VK_F);break;
		case 'g':robot.keyPress(KeyEvent.VK_G);robot.keyRelease(KeyEvent.VK_G);break;
		case 'h':robot.keyPress(KeyEvent.VK_H);robot.keyRelease(KeyEvent.VK_H);break;
		case 'i':robot.keyPress(KeyEvent.VK_I);robot.keyRelease(KeyEvent.VK_I);break;
		case 'j':robot.keyPress(KeyEvent.VK_J);robot.keyRelease(KeyEvent.VK_J);break;
		case 'k':robot.keyPress(KeyEvent.VK_K);robot.keyRelease(KeyEvent.VK_K);break;
		case 'l':robot.keyPress(KeyEvent.VK_L);robot.keyRelease(KeyEvent.VK_L);break;
		case 'm':robot.keyPress(KeyEvent.VK_M);robot.keyRelease(KeyEvent.VK_M);break;
		case 'n':robot.keyPress(KeyEvent.VK_N);robot.keyRelease(KeyEvent.VK_N);break;
		case 'o':robot.keyPress(KeyEvent.VK_O);robot.keyRelease(KeyEvent.VK_O);break;
		case 'p':robot.keyPress(KeyEvent.VK_P);robot.keyRelease(KeyEvent.VK_P);break;
		case 'q':robot.keyPress(KeyEvent.VK_Q);robot.keyRelease(KeyEvent.VK_Q);break;
		case 'r':robot.keyPress(KeyEvent.VK_R);robot.keyRelease(KeyEvent.VK_R);break;
		case 's':robot.keyPress(KeyEvent.VK_S);robot.keyRelease(KeyEvent.VK_S);break;
		case 't':robot.keyPress(KeyEvent.VK_T);robot.keyRelease(KeyEvent.VK_T);break;
		case 'u':robot.keyPress(KeyEvent.VK_U);robot.keyRelease(KeyEvent.VK_U);break;
		case 'v':robot.keyPress(KeyEvent.VK_V);robot.keyRelease(KeyEvent.VK_V);break;
		case 'w':robot.keyPress(KeyEvent.VK_W);robot.keyRelease(KeyEvent.VK_W);break;
		case 'x':robot.keyPress(KeyEvent.VK_X);robot.keyRelease(KeyEvent.VK_X);break;
		case 'y':robot.keyPress(KeyEvent.VK_Y);robot.keyRelease(KeyEvent.VK_Y);break;
		case 'z':robot.keyPress(KeyEvent.VK_Z);robot.keyRelease(KeyEvent.VK_Z);break;

		default:
			break;
		}
	}
}
