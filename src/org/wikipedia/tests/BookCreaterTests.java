package tests;

import org.testng.annotations.Test;

import baseclass.BaseClass;
import pageobjects.BookCreaterPageObject;
import pageobjects.HomePageObject;
import pageobjects.PdfDownloadPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

public class BookCreaterTests extends BaseClass{
	static WebDriver driver;
	HomePageObject homeObject;
	BookCreaterPageObject bookCreaterObject;
	PdfDownloadPageObject pdfDownloadObject;
	String userDir = System.getProperty("user.dir");

	@BeforeClass
	@Parameters({"browserName","url"})
	public void beforeClass(String browserName, String url) {
		driver = browserSetup(browserName);
		driver.get(url);
		homeObject = PageFactory.initElements(driver, HomePageObject.class);
		bookCreaterObject = PageFactory.initElements(driver, BookCreaterPageObject.class);
		pdfDownloadObject = PageFactory.initElements(driver, PdfDownloadPageObject.class);
			
	}
	
	
	@Test
	public void bookCreaterTest() throws InterruptedException, AWTException {
	  click(homeObject.createABookLink, "Home page create a book link");
	  click(bookCreaterObject.startBookCreatorButton, "Start Book Creater button");
	  enterText(homeObject.inputSearchTextBox, "Selenium");
	  pressRobotEnterKey(1);
	  
	}


	@AfterClass
	public void afterClass() {
		browserTeardown();
	}

}
