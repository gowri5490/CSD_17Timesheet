package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {


		protected WebDriver driver;
		
		//Use this method to get the current driver value
		public BasePage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);//this class for page object design patterns
		}
		
	}

