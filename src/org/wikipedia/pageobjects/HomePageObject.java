package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import baseclass.BaseClass;

public class HomePageObject extends BaseClass{
	public WebDriver driver;
	
	
	@FindBy(how = How.XPATH, using = "//li[@id='coll-create_a_book']/a")
	public WebElement createABookLink;
	
	
	
	public static boolean userAction1() {
		return true;
	}
}
