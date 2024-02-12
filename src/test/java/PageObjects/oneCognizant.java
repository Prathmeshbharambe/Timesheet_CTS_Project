package PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class oneCognizant extends basePage {

	public oneCognizant(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(css="li.searchTopBar")
	WebElement searchIconEdge;
	
	@FindBy(xpath="//*[@id=\"oneC_searchAutoComplete\"]")
	WebElement searchBarChrome;
	
	@FindBy(xpath="//*[@id='oneCSearchTop']")
	WebElement searchBarEdge;
	
	@FindBy(xpath="//*[@id=\"newSearchQALST\"]/div[1]/div/div[2]")
	WebElement timeSheetChrome;
	
	@FindBy(xpath="//*[@id='newSearchQALST']/div[1]")
	WebElement timeSheetIcon1;
	
	@FindBy(xpath="//*[@id='newSearchQALST']/div[1]/div")
	WebElement timeSheetIcon2;
	
//	public void clickSearchIcon()
//	{
//		searchIconEdge.click();
//	}
	
	
	public void inputSearchBar(String br)
	{
		System.out.println(br);
		
		if(br.equalsIgnoreCase("chrome")) 
		{
			searchBarChrome.sendKeys("timesheet");
			timeSheetChrome.click();
		}
		else if(br.equalsIgnoreCase("Edge"))
		{
			searchIconEdge.click();
			searchBarEdge.sendKeys("timesheet");
			try
			{
				timeSheetIcon1.click();
			}
			catch(Exception e)
			{
				timeSheetIcon2.click();
			}
			
		}
	}
	
//	public void timeSheetIcon()
//	{
//		
//	}
	
	public void windowHandelsTimesheet(WebDriver driver) throws InterruptedException
	{
		Set <String> Window = driver.getWindowHandles();
	    List <String> Window1 = new ArrayList<String>(Window);
	    System.out.println("Timesheet's Window Handle - " + Window1.get(2));
		driver.switchTo().window(Window1.get(2));
	}
}
