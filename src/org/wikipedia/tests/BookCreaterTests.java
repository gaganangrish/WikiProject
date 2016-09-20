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

public class BookCreaterTests extends BaseClass {
	static WebDriver driver;
	HomePageObject homeObject;
	BookCreaterPageObject bookCreaterObject;
	PdfDownloadPageObject pdfDownloadObject;
	ManageBookPageObject manageBookObject;
	String userDir = System.getProperty("user.dir");

	@BeforeClass
	@Parameters({ "browserName", "url" })
	public void beforeClass(String browserName, String url) {
		driver = browserSetup(browserName);
		driver.get(url);
		homeObject = PageFactory.initElements(driver, HomePageObject.class);
		bookCreaterObject = PageFactory.initElements(driver, BookCreaterPageObject.class);
		pdfDownloadObject = PageFactory.initElements(driver, PdfDownloadPageObject.class);
		manageBookObject = PageFactory.initElements(driver, ManageBookPageObject.class);
		
		
		
	}

	@Test
	public void bookCreaterTest() throws InterruptedException, AWTException {
		waitAndClick(homeObject.createABookLink, 5, driver, "Home page create a book link");
		waitAndClick(bookCreaterObject.startBookCreatorButton, 5, driver, "Start Book Creater button");
		
		enterText(homeObject.inputSearchTextBox, "Selenium");
		pressRobotEnterKey(1);
		waitAndClick(homeObject.addBookLink, 5, driver, "Add book link");
		
		enterText(homeObject.inputSearchTextBox, "Jscript");
		pressRobotEnterKey(1);
		waitAndClick(homeObject.addBookLink, 5, driver, "Add book link");
		
		homeObject.waitandClickShowBookLink(driver);
		enterText(manageBookObject.titleTextField, "Book Creator from Wikipedia");
		enterText(manageBookObject.subTitleTextField, "Using Automation");
		waitAndClick(manageBookObject.downloadButton, 5, driver, "Manage pdf page download button");
		
		pdfDownloadObject.waitForRenderingToBeFinished(driver);
		waitAndClick(pdfDownloadObject.pdfDownloadLink, 5, driver, "PDF download link");
		String timeStamp = getCurrentTimeStampInWinFormat();
		downloadAndSavePdfFile(userDir, "ExportedBook_" + timeStamp);
		boolean isPresent = checkFilePresent(userDir +"\\"+ "ExportedBook_" + timeStamp + ".pdf");
		Assert.assertTrue(isPresent, "File is not present. Test failed. Expected file name: " + userDir+"\\"
				+ "ExportedBook_" + timeStamp + ".pdf");

	}

	@AfterClass
	public void afterClass() {
		browserTeardown();
	}

}
