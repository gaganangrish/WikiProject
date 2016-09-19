package debug;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import baseclass.BaseClass;

public class SaveFile extends BaseClass {

	public static void main(String[] args) throws AWTException, InterruptedException {
		
//		char a = 'a';
//		int charCode = (int)a;
//		int keyCode = KeyEvent.getExtendedKeyCodeForChar(charCode);
//		System.out.println(keyCode);
		FirefoxProfile fxProfile = new FirefoxProfile();
		fxProfile.setPreference("browser.download.folderList", 2);
		fxProfile.setPreference("browser.download.manager.showWhenStarting", false);
		fxProfile.setPreference("browser.download.dir", "c:\\mydownloads");
		fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		WebDriver driver = new ChromeDriver(options);
		
//		System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
//		WebDriver driver = new InternetExplorerDriver();

//		WebDriver driver = new FirefoxDriver();

		driver.get(
				"https://en.wikipedia.org/w/index.php?title=Special:Book&bookcmd=download&collection_id=f137825801f4da25e03d24ef66b6d4e64de3f8ca&writer=rdf2latex&return_to=Main+Page");
		

//		new Actions(driver).sendKeys(Keys.chord(Keys.CONTROL, "s")).perform();
		
//		String selectAll = Keys.chord(Keys.CONTROL, "s");
//		driver.findElement(By.xpath("//body")).sendKeys(selectAll);
//		driver.findElement(By.xpath("//body")).click();
//		driver.findElement(By.xpath("//body")).sendKeys(Keys.CONTROL+"a");
//		driver.findElement(By.xpath("//body")).click();
//		driver.findElement(By.xpath("//body")).sendKeys(Keys.CONTROL+"s");
		System.out.println(System.getProperty("user.dir"));

		Robot robot = new Robot();
		
//		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
//		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_CONTROL);


		
		Thread.sleep(5000);		
		Thread.sleep(2000);
//
//		robot.keyPress(KeyEvent.VK_1);
//		robot.keyRelease(KeyEvent.VK_1);
//		
		pressRobotTabKey(5);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		String userDir = System.getProperty("user.dir");
		String filename = userDir+"file1";
		typeRobotKeyCode(filename);
//		

//		Thread.sleep(500);
//		robot.keyPress(KeyEvent.VK_C);
//		robot.keyRelease(KeyEvent.VK_C);
//		Thread.sleep(500);
//		robot.keyPress(KeyEvent.VK_SHIFT);
//		robot.keyPress(KeyEvent.VK_SEMICOLON);
//		robot.keyRelease(KeyEvent.VK_SHIFT);
//		robot.keyRelease(KeyEvent.VK_SEMICOLON);
//
//		Thread.sleep(500);
//		robot.keyPress(KeyEvent.VK_BACK_SLASH);
//		robot.keyRelease(KeyEvent.VK_BACK_SLASH);
//		Thread.sleep(500);
//		robot.keyPress(KeyEvent.VK_ENTER);
//		robot.keyRelease(KeyEvent.VK_ENTER);

		
		
		
	}

}
