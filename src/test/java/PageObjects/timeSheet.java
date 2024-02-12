package PageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import Utilitis.ExcelUtilis;

public class timeSheet extends basePage{
	
	public timeSheet(WebDriver driver) 
	{
		super(driver);
	}
	String websiteHeader = "Timesheets", firstWeekDate, dateConcat, startDatestr, endDatestr;
	String[] resultDate; 
	
	@FindBy(xpath="//*[@id='PT_PAGETITLElbl']")
	WebElement pageTitle;
	
	@FindBy(xpath="//*[@id='win0groupletPTNUI_LAND_REC_GROUPLET$0']")
	WebElement timesheet2;
	
	@FindBy(xpath="//a[contains(@id,'CTS_TS_LAND_PER_DESCR30$') and contains(@class,'ps-link')]")
	List<WebElement> timesheetDates;
	
	@FindBy(xpath="//span[contains(@id,'CTS_TS_LAND_PER_CTS_TS_STATUS_LAND$')]")
	List<WebElement> timesheetDatesStatus;
	
	@FindBy(xpath="//*[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH']")
	WebElement timesheetSearchBy;
	
	@FindBy(xpath="//*[@id='CTS_TS_LAND_WRK_DATE$prompt']")
	WebElement timesheetCalendar;
	
	@FindBy(xpath="//a[@id='curdate']")
	WebElement timesheetCurrentDate;
	
	@FindBy(xpath="//a[@id='CTS_TS_LAND_WRK_SEARCH']")
	WebElement timesheetSearch;
	
	@FindBy(xpath="//*[@id='CTS_TS_LAND_PER_DESCR30$0']")
	WebElement currentWeek;
	
	@FindBy(xpath="//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']")
	WebElement timesheetStatus;
	
	@FindBy(xpath="//*[@id='msgcontainer']")
	WebElement errorMsg;
	
	@FindBy(xpath="//*[@id='#ICOK']")
	WebElement okButton;
	
	public void headerValidation()
	{
		System.out.println("");
		assertEquals(true,pageTitle.getText().equalsIgnoreCase(websiteHeader),"Website header is not verified.\\n Opening Timesheet......");
//		if (pageTitle.getText().equalsIgnoreCase(websiteHeader))
//		{
//			System.out.println("Website header for TimeSheet is verified.\nOpening Timesheet.....");
//		}
//		else
//		{
//			System.out.println("Website header is not verified.\n Opening Timesheet......");
//			timesheet2.click();
//		}
//		System.out.println("");
	}
	
	public void threeWeeksTimesheet() throws IOException, ParseException 
	{  boolean verified = true;
		System.out.println(" ");
		System.out.println("--------- Dates of Last Three Weeks ---------");
		for (int date =0;date<3;date++)
		{ 	
			firstWeekDate =timesheetDates.get(0).getText();
			dateConcat = timesheetDates.get(date).getText();
			System.out.println(dateConcat);
			
			String [] resultDate  = firstWeekDate.split(" To ");
			
			SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
			Date  startDate = fmt.parse(resultDate[0]);
			Date endDate = fmt.parse(resultDate[1]);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			if (cal.get(Calendar.DAY_OF_WEEK)!= Calendar.SATURDAY) {
				verified= false;
			}
			cal.setTime(endDate);
			if(cal.get(Calendar.DAY_OF_WEEK)!= Calendar.FRIDAY) {
				verified = false;
			}
			ExcelUtilis.write("Timesheet",date,0, dateConcat );
			ExcelUtilis.write("Timesheet", date, 1, "Verified");
		}
		System.out.println("---------------------------------------------");
		System.out.println(" ");
	}
	
	public void currentWeek() throws InterruptedException, IOException
	{
		Select dropdown = new Select(timesheetSearchBy);
		dropdown.selectByVisibleText("Date");
		
		Thread.sleep(3000);
		timesheetCalendar.click();
		timesheetCurrentDate.click();
		timesheetSearch.click();
		
		System.out.println(" ");
		System.out.println("------------- The Current Week --------------");
		System.out.println(currentWeek.getText());
		System.out.println("---------------------------------------------");
		System.out.println(" ");
		ExcelUtilis.write("dropdowns", 0, 0, "status-Date");
		ExcelUtilis.write("dropdowns", 1, 0, currentWeek.getText());
	}
	
	public void dateValidationTimesheet() 
	{
		startDatestr = resultDate[0];
		endDatestr = resultDate[1];
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
		try 
		{
			Date startDate=dateFormat.parse(startDatestr);
			Date endDate=dateFormat.parse(endDatestr);
			
			System.out.println(" ");
			System.out.println("--------- Date Validation for Current Week ---------");
			if(isSaturday(startDate)&& isFriday(endDate)) 
			{
				System.out.println("Timesheet is valid : "+startDatestr+"is Saturday and\n                     "+endDatestr+"is Friday");
			}
			else 
			{
				System.out.println("The Week given in the Timesheet is invalid");
			}
			System.out.println("---------------------------------------------");
			System.out.println(" ");
		}
		catch(ParseException e) 
		{
			e.printStackTrace();
		}
	}
 
	static boolean isSaturday(Date date) 
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY;
	}
	
	static boolean isFriday(Date date) 
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY;
	}
	
	
	
	public void tsStatusApproved() throws InterruptedException, IOException
	{
		Select dropdown = new Select(timesheetSearchBy);
		dropdown.selectByVisibleText("Status");
		Thread.sleep(10000);
		Select dropdownStatus = new Select(timesheetStatus);
		dropdownStatus.selectByVisibleText("Approved");
		timesheetSearch.click();
		
		Thread.sleep(10000);
		
		System.out.println(" ");
		System.out.println("------------- The Approved Week --------------");
		for(int a=0;a<timesheetDates.size();a++)
		{
			System.out.println(timesheetDates.get(a).getText()+" - '"+timesheetDatesStatus.get(a).getText()+"'");
			ExcelUtilis.write("dropdowns", 0, 2, "status-date");
			ExcelUtilis.write("dropdowns", a, 2, timesheetDates.get(a).getText());
			ExcelUtilis.write("dropdowns", 0, 3, "Status - Aprroved");
			ExcelUtilis.write("dropdowns", a, 3, timesheetDatesStatus.get(a).getText());
		}
		System.out.println("----------------------------------------------");
		System.out.println(" ");
	}
	
	public void tsStatusOverdue() throws InterruptedException, IOException
	{
		Select dropdownStatus = new Select(timesheetStatus);
		dropdownStatus.selectByVisibleText("Overdue");
		timesheetSearch.click();
		
		Thread.sleep(10000);
		if(errorMsg.isDisplayed())
		{
			System.out.println(" ");
			System.out.println("------------- The Overdue Week --------------");
			System.out.println("No results found. ");
			System.out.println("---------------------------------------------");
			System.out.println(" ");
			okButton.click();
			ExcelUtilis.write("dropdowns", 0, 5, "status-date");
			ExcelUtilis.write("dropdowns", 1, 5, "No result found");
			ExcelUtilis.write("dropdowns", 0, 6, "Status - Overdue");
			ExcelUtilis.write("dropdowns", 1, 6, "Null");
		}
		
		else
		{
			System.out.println(" ");
			System.out.println("------------- The Overdue Week --------------");
			for(int a =0;a<timesheetDates.size();a++)
			{
				System.out.println(timesheetDates.get(a).getText()+" - '"+timesheetDates.get(a).getText()+"'");
				ExcelUtilis.write("dropdowns", 0, 5, "status-date");
				ExcelUtilis.write("dropdowns", a, 5, timesheetDates.get(a).getText());
				ExcelUtilis.write("dropdowns", 0, 6, "Status - overdue");
				ExcelUtilis.write("dropdowns", a, 6, timesheetDatesStatus.get(a).getText());
			}
			System.out.println("---------------------------------------------");
			System.out.println(" ");
		}
	}
	
	public void tsStatusPartiallyApproved() throws InterruptedException, IOException
	{
		Select dropdownStatus = new Select(timesheetStatus);
		dropdownStatus.selectByVisibleText("Partially Approved");
		timesheetSearch.click();
		
		Thread.sleep(10000);
		if(errorMsg.isDisplayed())
		{
			System.out.println(" ");
			System.out.println("------------- The Partially Approved Week --------------");
			System.out.println("No results found. ");
			System.out.println("---------------------------------------------------------");
			System.out.println(" ");
			okButton.click();
			ExcelUtilis.write("dropdowns", 0, 8, "status-date");
			ExcelUtilis.write("dropdowns", 1, 8, "No result found");
			ExcelUtilis.write("dropdowns", 0, 9, "Status - Partially Aprroved");
			ExcelUtilis.write("dropdowns", 1, 9, "Null");
		}
		
		else
		{
			System.out.println(" ");
			System.out.println("------------- The Partially Approved Week --------------");
			for(int a =0;a<timesheetDates.size();a++)
			{
				System.out.println(timesheetDates.get(a).getText()+" - '"+timesheetDates.get(a).getText()+"'");
				ExcelUtilis.write("dropdowns", 0, 8, "status-date");
				ExcelUtilis.write("dropdowns", a, 8, timesheetDates.get(a).getText());
				ExcelUtilis.write("dropdowns", 0, 9, "Status - Partially Approved");
				ExcelUtilis.write("dropdowns", a, 9, timesheetDatesStatus.get(a).getText());
			}
			System.out.println("---------------------------------------------------------");
			System.out.println(" ");
		}
	}
	
	public void tsStatusPending() throws InterruptedException, IOException
	{
		Select dropdownStatus = new Select(timesheetStatus);
		dropdownStatus.selectByVisibleText("Pending");
		timesheetSearch.click();
		
		Thread.sleep(10000);
		
		System.out.println(" ");
		System.out.println("------------- The Pending Week --------------");
		for(int a =0;a<timesheetDates.size();a++)
		{
			System.out.println(timesheetDates.get(a).getText()+" - '"+timesheetDatesStatus.get(a).getText()+"'");
			ExcelUtilis.write("dropdowns", 0, 11, "status-date");
			ExcelUtilis.write("dropdowns", a, 11, timesheetDates.get(a).getText());
			ExcelUtilis.write("dropdowns", 0, 12, "Status - Pending");
			ExcelUtilis.write("dropdowns", a, 12, timesheetDatesStatus.get(a).getText());
		}
		System.out.println("----------------------------------------------");
		System.out.println(" ");
	}
	
	public void tsStatusSaved() throws InterruptedException, IOException
	{
		Select dropdownStatus = new Select(timesheetStatus);
		dropdownStatus.selectByVisibleText("Saved");
		timesheetSearch.click();
		
		Thread.sleep(10000);
		if(errorMsg.isDisplayed())
		{
			System.out.println(" ");
			System.out.println("------------- The Saved Week --------------");
			System.out.println("No results found. ");
			System.out.println("-------------------------------------------");
			System.out.println(" ");
			okButton.click();
			ExcelUtilis.write("dropdowns", 0, 14, "status-date");
			ExcelUtilis.write("dropdowns", 1, 14, "No result found");
			ExcelUtilis.write("dropdowns", 0, 15, "Status - Saved");
			ExcelUtilis.write("dropdowns", 1, 15, "Null");
		}
		
		else
		{
			System.out.println(" ");
			System.out.println("------------- The Saved Week --------------");
			for(int a =0;a<timesheetDates.size();a++)
			{
				System.out.println(timesheetDates.get(a).getText()+" - '"+timesheetDatesStatus.get(a).getText()+"'");
				ExcelUtilis.write("dropdowns", 0, 14, "status-date");
				ExcelUtilis.write("dropdowns", a, 14, timesheetDates.get(a).getText());
				ExcelUtilis.write("dropdowns", 0, 15, "Status - Saved");
				ExcelUtilis.write("dropdowns", a, 15, timesheetDatesStatus.get(a).getText());
			}
			System.out.println("--------------------------------------------");
			System.out.println(" ");
		}	
	}
	
	
	public void tsStatusSentBackforRevision() throws InterruptedException, IOException
	{
		Select dropdownStatus = new Select(timesheetStatus);
		dropdownStatus.selectByVisibleText("Sent Back for Revision");
		timesheetSearch.click();
		
		Thread.sleep(10000);
		if(errorMsg.isDisplayed())
		{
			System.out.println(" ");
			System.out.println("------------- The Sent Back for Revision Week --------------");
			System.out.println("No results found. ");
			System.out.println("------------------------------------------------------------");
			System.out.println(" ");
			okButton.click();
			ExcelUtilis.write("dropdowns", 0, 17, "status-date");
			ExcelUtilis.write("dropdowns", 1, 17, "No result found");
			ExcelUtilis.write("dropdowns", 0, 18, "Status - Sent Back ");
			ExcelUtilis.write("dropdowns", 1, 18, "Null");
		}
		
		else
		{
			System.out.println(" ");
			System.out.println("------------- The Sent Back for Revision Week --------------");
			for(int a =0;a<timesheetDates.size();a++)
			{
				System.out.println(timesheetDates.get(a).getText()+" - '"+timesheetDates.get(a).getText()+"'");
				ExcelUtilis.write("dropdowns", 0, 17, "status-date");
				ExcelUtilis.write("dropdowns", a, 17, timesheetDates.get(a).getText());
				ExcelUtilis.write("dropdowns", 0, 18, "Status - Sent Back");
				ExcelUtilis.write("dropdowns", a, 18, timesheetDatesStatus.get(a).getText());
			}
			System.out.println("------------------------------------------------------------");
			System.out.println(" ");
		}
		
	}
	
	
	public void tsStatusSubmittedforApproval() throws InterruptedException, IOException
	{
		Select dropdownStatus = new Select(timesheetStatus);
		dropdownStatus.selectByVisibleText("Submitted for Approval");
		timesheetSearch.click();
		
		Thread.sleep(10000);
		{
		System.out.println(" ");
		System.out.println("------------- The Submitted for Approval Week --------------");
		for(int a =0;a<timesheetDates.size();a++)
		{
			System.out.println(timesheetDates.get(a).getText()+" - '"+timesheetDatesStatus.get(a).getText()+"'");
			System.out.println(timesheetDates.get(a).getText()+" - '"+timesheetDates.get(a).getText()+"'");
			ExcelUtilis.write("dropdowns", 0, 20, "status-date");
			ExcelUtilis.write("dropdowns", a, 20, timesheetDates.get(a).getText());
			ExcelUtilis.write("dropdowns", 0, 21, "Status -Submitted for Approval ");
			ExcelUtilis.write("dropdowns", a, 21, timesheetDatesStatus.get(a).getText());
		}
		System.out.println("------------------------------------------------------------");
		System.out.println(" ");
		}
	}
	
}
