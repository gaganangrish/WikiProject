package tests;

import org.testng.annotations.Test;

import baseclass.BaseClass;
import pageobjects.BookCreaterPageObject;
import pageobjects.HomePageObject;
import pageobjects.ManageBookPageObject;
import pageobjects.PdfDownloadPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class BookCreaterTests extends BaseClass{
	static WebDriver driver;
	HomePageObject homeObject;
	BookCreaterPageObject bookCreaterObject;
	PdfDownloadPageObject pdfDownloadObject;
	ManageBookPageObject manageBookObject;
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
	  click(homeObject.addBookLink, "Add book link");
	  enterText(homeObject.inputSearchTextBox, "Jscript");
	  pressRobotEnterKey(1);
	  click(homeObject.addBookLink, "Add book link");
	  homeObject.waitandClickShowBookLink(driver);
	  enterText(manageBookObject.titleTextField, "Book Creator from Wikipedia");
	  enterText(manageBookObject.subTitleTextField, "Using Automation");
	  click(manageBookObject.downloadButton, "Manage pdf page download button");
	  pdfDownloadObject.waitForRenderingToBeFinished(driver);
	  click(pdfDownloadObject.pdfDownloadLink, "PDF download link");
	  downloadAndSavePdfFile(userDir, "ExportedBook_"+getCurrentTimeStampInWinFormat());
	  boolean isPresent = checkFilePresent(userDir+"ExportedBook_"+getCurrentTimeStampInWinFormat()+".pdf");
	  Assert.assertTrue(isPresent, "File is not present. Test failed. Expected file name: "+userDir+"ExportedBook_"+getCurrentTimeStampInWinFormat()+".pdf");
	  
	}


	@AfterClass
	public void afterClass() {
		browserTeardown();
	}

}
