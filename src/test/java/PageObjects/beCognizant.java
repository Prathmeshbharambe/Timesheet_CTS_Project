package PageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v119.profiler.model.Profile;
import org.openqa.selenium.support.FindBy;

import com.google.common.collect.Table.Cell;

import Utilitis.ExcelUtilis;

public class beCognizant extends basePage {
	
	WebDriver driver;
	
	public beCognizant(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
	WebElement profile;
	
	@FindBy(id="mectrl_currentAccount_primary")
	WebElement name;
	
	@FindBy(id="mectrl_currentAccount_secondary")
	WebElement emailId;
	
	@FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
	WebElement profileXpathClose;
	
	@FindBy(id="mectrl_headerPicture")
	WebElement profileIdClose;
	
	@FindBy(xpath="//div[@title='OneCognizant']")
	WebElement oneCognizant;
	
	
	public void clickProfile()
	{
		profile.click();
	}
	
	public void getProfile() throws IOException
	{
		String Name = name.getText();
		String username = "Bharambe, Prathmesh (Cognizant)";
		String Email = emailId.getText();
		String email_id ="2303703@cognizant.com";
		System.out.println("--------------- Personal Info ---------------");
		System.out.println("Name     : "+Name+"\nMail Id  : "+Email);
		System.out.println("---------------------------------------------");
		System.out.println("");
		assertEquals(true, Name.equals(username));
		assertEquals(true, Email.equals(email_id));
		ExcelUtilis.write("Profile Info", 0, 0,"UserName");
		ExcelUtilis.write("Profile Info", 0, 1, "Email");
		ExcelUtilis.write("Profile Info", 1, 0, Name);
		ExcelUtilis.write("Profile Info", 1, 1, Email);
	}
	
	public void closeProfile()
	{
		try
		{
			profileXpathClose.click();
		}
		catch(Exception e)
		{
			profileIdClose.click();
		}
		
	}
	
	public  @FindBy(xpath = "//*[@id='5d7d4eec-cbe0-4c55-ae2e-f38d926d82a0']") 
	WebElement aroundOneCElement;
//	public @FindBy (xpath = "//div[@title ='Join us for the Delivery Summit 2024: Learn more']")
//	WebElement appandtool;
	public void openOneCognizant(WebDriver driver) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("arguments[0].scrollIntoView(true)",aroundOneCElement);
		 Thread.sleep(3000);
		oneCognizant.click();
		
	}
	
	public void windowHandlesOneCog(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(10000);
		Set <String> Window = driver.getWindowHandles();
	    List <String> Window1 = new ArrayList<String>(Window); 
	    System.out.println("One Cognizant's Window Handle - "+ Window1.get(1));
	    driver.switchTo().window(Window1.get(1));
	}

}
