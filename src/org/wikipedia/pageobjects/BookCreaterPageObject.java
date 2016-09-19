package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import baseclass.BaseClass;

public class BookCreaterPageObject extends BaseClass{

	static WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//button[@name='confirm']")
	public WebElement startBookCreatorButton;
}
