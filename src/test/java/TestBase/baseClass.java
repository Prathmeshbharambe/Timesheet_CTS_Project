package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
public class baseClass 
{
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeTest
	@Parameters({"browser"})
	public void setUp(String br) throws IOException, InterruptedException 
	{
			//Loading property file
			FileReader file=new FileReader(".//src//test//resources//config.properties");
			p=new Properties();
			p.load(file);
			
			//loading log4j file
			logger= LogManager.getLogger(this.getClass());
			
			if(br.equalsIgnoreCase("Chrome"))
			{
				driver=new ChromeDriver(); 
			}
			else if(br.equalsIgnoreCase("Edge")) 
			{
				driver=new EdgeDriver(); 
			}
			
			driver.get(p.getProperty("appURL"));
			Thread.sleep(30000);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
 
	}
	
	@AfterTest
	public void tearDown() 
	{
		driver.quit();
	}
 
	public String captureScreen(String name) 
	
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + name + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
