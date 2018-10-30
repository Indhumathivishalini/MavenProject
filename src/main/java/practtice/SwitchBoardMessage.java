package practtice;



import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;



public class SwitchBoardMessage {

	public static WebDriver driver;
	@BeforeTest
	public void openBrowser() throws InterruptedException {
		// set the driver path
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");

		// create instance for chromediver

		 driver = new ChromeDriver();

		// implicitly wait for each element to load in a page
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// loads the url
		driver.get("http://alpha-sb.appspot.com/InitialAccountAction/getAccountInfo.do?accountNumber=0001211294");
		// sign in 
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("indhumathi.rajavel@anywhere.co");
		driver.findElement(By.xpath("//input[@id='pwd']")).sendKeys("Indhu@123");
		driver.findElement(By.xpath("//button[@id='login-btn']")).click();
		// Close button
		driver.findElement(By.xpath("//div[@class='greeting']/i")).click();
		Thread.sleep(1000);
		// enter name
		driver.findElement(By.xpath("(//input[@type='text'])[9]")).sendKeys("johnie");
		// verify the name
		//driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//div[@id='step_ef198b0d-71d2-4371-b9ea-6ce8ed8a762a']/label[contains(@id,'verification')]")).click();
		// enter email id
		driver.findElement(By.xpath("(//input[@type='text'])[10]")).sendKeys("johnie@gmail.com");
		// verify email
	//	driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='step_3ff45ff5-b47e-464d-807e-13f83f9980ef']/label[contains(@id,'verification')]")).click();
		//enter the message
		driver.findElement(By.xpath("//div[@class=\"paragraph-field\"]//following::textarea")).sendKeys("Hi!!!");
		// enter company name
		driver.findElement(By.xpath("(//input[@type='text'])[11]")).sendKeys("Full Creative");
		// verify company name
		//driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
		
		driver.findElement(By.xpath("//div[@id='step_b7909e8f-2d7d-4350-da92-cb35d0f21d70']/label[contains(@id,'verification')]")).click();

		// select country code
		driver.findElement(By.xpath("//p[@class='phoneCodeDwn']")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

		WebElement cc = driver.findElement(By.xpath("//ul[@class='phoneCountryCode']/li[2]"));
		cc.click();		

		// enter phone num
		driver.findElement(By.xpath("//input[@class='phoneNo']")).sendKeys("1234560");
		// enter extension
		driver.findElement(By.xpath("//input[@class='ext']")).sendKeys("04288");

		// click on check box
		//driver.findElement(By.xpath("(//input[@type='checkbox'])[4]")).click();
 driver.findElement(By.xpath("//div[@id='step_f94eeffd-5d1e-4f77-efc5-4225805e7a00']/label[contains(@id,'verification')]")).click();
		// select deliver method
		WebElement selectdropdown = driver.findElement(By.xpath("//select[@style='border-color:red;']"));

		Select dropdown = new Select(selectdropdown);
		//dropdown.selectByVisibleText("test");
		dropdown.selectByIndex(1);

		// finish call dropdown

		WebElement finishdd = driver.findElement(By.xpath("//a[@class='fa fa-caret-down dropdown-toggle']"));
		finishdd.click();
		driver.findElement(By.xpath("//ul[@id='substatusfinishcall']/li[2]")).click();

		/*   List<WebElement> options = driver.findElements(By.xpath("//ul[@id='substatusfinishcall']/li"));
            for(int i= 0;i<options.size();i++  ) {
            	System.out.println(options.get(i).getText());
            }

		 */
		
		  
		

	}



}
