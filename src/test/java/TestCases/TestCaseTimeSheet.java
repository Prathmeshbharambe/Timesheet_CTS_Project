package TestCases;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.beCognizant;
import PageObjects.oneCognizant;
import PageObjects.timeSheet;
import TestBase.baseClass;

public class TestCaseTimeSheet extends baseClass
{
	   beCognizant bec ;
	  timeSheet ts ;
	
	@Test(priority=1)
	public void userVerification() throws InterruptedException, IOException 
	{ 
		logger.info("****TestCase Started****");
		 bec = new beCognizant(driver);
		logger.info("Click on user profile");
		bec.clickProfile();
		Thread.sleep(4000);
		logger.info("verifying the user details ");
		bec.getProfile();
		logger.info("Account Verified Successfully ");
		System.out.println("Account verified");
		captureScreen("img_userprofile");
		Thread.sleep(3000);
		bec.closeProfile();
		logger.info("profile window closed");
	}
	
	
	@Test(priority = 2)
	public void onecognizant() throws InterruptedException {
		System.out.println("abcd");
		bec = new beCognizant(driver);
		System.out.println("efg");
		Thread.sleep(3000);
		logger.info("Verifying OneCognizant");
		bec.openOneCognizant(driver);
		logger.info("OneCognizant verified ");
		logger.info("click on OneCognizant text");
		Thread.sleep(3000);
		logger.info("driver switch to OneCognizant window");
		bec.windowHandlesOneCog(driver);
		captureScreen("img_onecognizant");
	
	}
	
	@Test(priority=3)
	@Parameters("browser")
	
	public void oneCognizantFunctions(String br) throws InterruptedException
	{
		oneCognizant one = new oneCognizant(driver); 
		Thread.sleep(1000);
		//one.clickSearchIcon();
		logger.info("click on search box ");
		logger.info("Pass the Text input (TimeSheet)");
		one.inputSearchBar(br);
		//one.timeSheetIcon();
		logger.info("Submit timesheet option get selected");
		captureScreen("img_timesheetIcon");
		Thread.sleep(3000);
		logger.info("TimeSheet window open ");
		one.windowHandelsTimesheet(driver);
		logger.info("driver switch to timeshhet window");
		
	}
	
	@Test(priority=4)
	public void timeSheetNavigation() throws InterruptedException
	{    ts = new timeSheet(driver);
	logger.info("Verify the timeshhet page");
	ts.headerValidation();
	logger.info("Page verified ");
	captureScreen("img_timesheet");	
	Thread.sleep(1000);
	}
	
	
	@Test(priority = 5)
	public void threeweek() throws IOException, InterruptedException, ParseException {
		ts = new timeSheet(driver);
		logger.info("Verify the FistThreeWeeks of timesheet ");
		ts.threeWeeksTimesheet();
		logger.info("Weeks verified");
		captureScreen("img_firstThreeWeeks");
		Thread.sleep(1000);
	}
	
	
	@Test(priority = 6)
	public void currentdate() throws InterruptedException, IOException {
		ts = new timeSheet(driver);
		logger.info("Current week timesheet");
		logger.info("search by date");
		ts.currentWeek();
		captureScreen("img_currentWeek");
		logger.info("Verify the timesheet with current date");
		ts.dateValidationTimesheet();
		logger.info("Timesheet verified");
		Thread.sleep(3000);
	}
	
	
	@Test(priority = 7)
	public void apr() throws InterruptedException, IOException {
		ts = new timeSheet(driver);
		logger.info("Search by - Status");
		logger.info("Status name - Approved ");
		ts.tsStatusApproved();
		logger.info("Approved status verified ");
		captureScreen("img_StatusApproved");
		Thread.sleep(3000);
	}
	
	
	@Test(priority = 8)
	public void overDue() throws InterruptedException, IOException {
		ts = new timeSheet(driver);
		logger.info("Status name - OverDue ");
		ts.tsStatusOverdue();
		captureScreen("img_StatusOverdue");
		logger.info("OverDue status verified ");
		Thread.sleep(3000);
	}
	
	
	@Test(priority = 9)
	public void partially_aprv() throws InterruptedException, IOException {
		ts = new timeSheet(driver);
		logger.info("Status name - Partially Approved ");
		ts.tsStatusPartiallyApproved();
		captureScreen("img_StatusPartiallyApproved");
		logger.info("Partially Approved status verified ");
		
		Thread.sleep(3000);
	}
	
	
	@Test(priority = 10)
	public void pending() throws InterruptedException, IOException {
		ts = new timeSheet(driver);
		logger.info("Status name - Pending ");
		ts.tsStatusPending();
		captureScreen("img_StatusPending");
		logger.info("Pending status verified ");
		Thread.sleep(3000);
	}
	
	
	@Test(priority = 11)
	public void saved() throws InterruptedException, IOException {
		ts = new timeSheet(driver);
		logger.info("Status name - Saved ");
		ts.tsStatusSaved();
		captureScreen("img_StatusSaved");
		logger.info("Saved status verified ");
		Thread.sleep(3000);
	}
	
	
	@Test(priority = 12)
	public void sendback() throws InterruptedException, IOException {
		ts = new timeSheet(driver);
		logger.info("Status name - SentBackForRevision ");
		ts.tsStatusSentBackforRevision();
		captureScreen("img_StatusSentBackforRevision");
		logger.info("SentBackforRevision status verified ");
		Thread.sleep(3000);
	}
	
	
	@Test(priority = 13)
	public void submit() throws InterruptedException, IOException {
		ts = new timeSheet(driver);
		logger.info("Status name - SubmittedForApproval ");
		ts.tsStatusSubmittedforApproval();
		captureScreen("img_StatusSubmittedforApproval");
		logger.info("SubmittedforApproval status verified ");
		logger.info("TestCases Passed ");
	}
	
	}
  
	
	

