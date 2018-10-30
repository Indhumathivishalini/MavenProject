package practtice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TakeScreenShot {
	  public WebDriver driver;
	  
	@Test
	  public void openBrowser() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    	  driver = new ChromeDriver();
    	  driver.manage().window().maximize();
    	//  driver.get("https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin");
    	 // call takeScreenshot method
    	//  this.takeScreenshot(driver,"/Screenshots/test.png"); 
    	  driver.get("http://alpha-sb.appspot.com/InitialAccountAction/getAccountInfo.do?accountNumber=0001211294");
  		// sign in 
  		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("indhumathi.rajavel@anywhere.co");
  		driver.findElement(By.xpath("//input[@id='pwd']")).sendKeys("Indhu@123");
  		driver.findElement(By.xpath("//button[@id='login-btn']")).click();

  		driver.findElement(By.xpath("//div[@class='greeting']/i")).click();

	
	}
	
	
	@AfterTest
	public void takeScreenshot() throws IOException 	{
		
		// casting webdriver to use getScreenshotAs method from TakeScreenshot interface
		 TakesScreenshot scrShot =((TakesScreenshot)driver);
		 // import file from java 
		 // import Output Type from selenium
		 
		 //Call getScreenshotAs method to create image file
		 File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
         // move image file to destination
		// File DestFile=new File(fileWithPath);
		 
		 // copy image to destination file
		// FileUtils.copyFile(SrcFile, DestFile);
		 FileUtils.copyFile(SrcFile, new File("/Screenshots/test1.png"));
	}
}