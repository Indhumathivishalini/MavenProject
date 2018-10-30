package dataprovider;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.api.services.sheets.v4.Sheets;

public class RDFGoogleSheet {
	public WebDriver driver;
	public WebDriverWait wait;

	String appURL = "https://docs.google.com/spreadsheets/d/1tpKVNz0CeByk2bHuiLMIV3MtJvTj0jNerl5W6MtRSe0/edit#gid=0";

	public String spreadsheetId = "1tpKVNz0CeByk2bHuiLMIV3MtJvTj0jNerl5W6MtRSe0"; 
	public String range = "Sheet1!A2:B2";
	String userName = "indhumathi.rajavel@anywhere.co";
	String password = "Indhu@123";

	@Test
	public void launchbrowser() {
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,10);
	}

	public void login() {
		driver.get(appURL);
		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(userName);

		//click on next
		driver.findElement(By.xpath("//span[text()='Next']")).click(); 

		//enter the password
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']"))).sendKeys(password);
		//driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Indhu@123");


		WebElement nextuntil = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[@class='RveJvd snByac']"))));
		nextuntil.click();



	}
	public void getSheetData() {

		
		//Sheets.Spreadsheets.Values.Get(SPREADSHEET_KEY, Range);

	}


}