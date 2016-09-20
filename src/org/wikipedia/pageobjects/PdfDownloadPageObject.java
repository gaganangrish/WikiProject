package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class PdfDownloadPageObject {
	
	static WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//h1")
	public WebElement pageTitle;

	@FindBy(how = How.XPATH, using = "//strong//a[@class='external text']")
	public WebElement pdfDownloadLink;
	
	
	
	public boolean waitForRenderingToBeFinished(WebDriver driver) throws InterruptedException {
		String pageTitleH1 = pageTitle.getText();
		int count = 0;
		boolean isComplete = false;

		do {
			Thread.sleep(500);
			count++;
			if (pageTitleH1.equalsIgnoreCase("Rendering finished")) {
				isComplete = true;
			}
		} while (!isComplete && count < 180);
		if (!isComplete) {
			Assert.fail("PDF not generating even after waiting for 3 mins. Page URL: "+driver.getCurrentUrl());
		}
		
		return true;
	}

}
