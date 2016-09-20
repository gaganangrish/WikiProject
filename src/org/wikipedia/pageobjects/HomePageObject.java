package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import baseclass.BaseClass;

public class HomePageObject extends BaseClass{
	public WebDriver driver;
	
	
	@FindBy(how = How.XPATH, using = "//li[@id='coll-create_a_book']/a")
	public WebElement createABookLink;

	@FindBy(how = How.XPATH, using = "//input[@type='search']")
	public WebElement inputSearchTextBox;
	
	@FindBy(how = How.XPATH, using = "//a[@id='coll-add_article']")
	public WebElement addBookLink;
	
	@FindBy(how = How.XPATH, using = "//li[@id='coll-download-as-rdf2latex']/a")
	public WebElement pdfDownloadLink;

	@FindBy(how = How.XPATH, using = "//a[@title='Click to edit/download/order your book']")
	public WebElement showBookLink;
	
	
	
	
	
	public boolean waitandClickShowBookLink(WebDriver driver) throws InterruptedException {
		int count = 0;
		boolean isReady = false;
		do {
			Thread.sleep(500);
			count++;
			if (showBookLink.getText().contains("Show book (2 pages)")) {
				isReady = true;
			}
		} while (!isReady&& count <180);
		if (isReady) {
			click(showBookLink, "Show book (2 pages) link");
		} else {
			Assert.fail("Show book link not ready even after waiting for 3 mins");
		}
		return true;
	}
}
