package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ManageBookPageObject {

	static WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//input[@id='titleInput']")
	public WebElement titleTextField;

	@FindBy(how = How.XPATH, using = "//input[@id='subtitleInput']")
	public WebElement subTitleTextField;

	@FindBy(how = How.XPATH, using = "//input[@id='downloadButton']")
	public WebElement downloadButton;
	
	
}
