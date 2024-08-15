package commonFunctions;

import java.io.FileInputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class FunctionLibrary 
{
	public static Properties conpro;
	public static WebDriver driver;
	//method for launching browser
	public static WebDriver startBrowser() throws Throwable 	
	{
		conpro=new Properties();
		//load propertyfile
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();	
			driver.manage().window().maximize();
		}else if (conpro.getProperty("Browser").equalsIgnoreCase("fireFox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			Reporter.log("Browseer value does not matching",true);
		}

		return driver;
	}

	//method for open url
	public static void openUrl ()
	{
		driver.get("http://webapp.qedgetech.com/");
	}

	//wait for element
	public static void waitForElement (String LocatorType,String Locatorvalue,String testData)
	{
		WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(testData)));
		if(LocatorType.equalsIgnoreCase(testData)) 
		{
			//wait until element is visible
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locatorvalue)));
		}if(LocatorType.equalsIgnoreCase("xpath"))
		{
			//wait until element is visible
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locatorvalue)));
		}if(LocatorType.equalsIgnoreCase("id"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locatorvalue)));
		}

	}
	//method for any textbox
	public static  void typeAction(String LocatorType,String Locatorvalue,String TestData)
	{
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(Locatorvalue)).clear();
			driver.findElement(By.xpath(Locatorvalue)).sendKeys(TestData);
		}if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(Locatorvalue)).clear();
			driver.findElement(By.xpath(Locatorvalue)).sendKeys(TestData);
		}
		if(LocatorType.equalsIgnoreCase("id"))

		{
			driver.findElement(By.id(Locatorvalue)).clear();
			driver.findElement(By.id(Locatorvalue)).sendKeys(TestData);
		}
	}
	//method for click actions 

	public static void clickAction(String LocatorType,String Locatorvalue)
	{
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(Locatorvalue)).click();
		}

		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(Locatorvalue)).click();
		}if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(Locatorvalue)).sendKeys(Keys.ENTER);
		}
	}
	//method for validate any page title
	public static void validateTitle(String Expected_Title)
	{
		String Actual_Title = driver.getTitle();
		try {
			Assert.assertEquals(Actual_Title, Expected_Title,"Title is  not Matching  ");
		}
		catch(AssertionError a)
		{
			System.out.println(a.getMessage());
		}

	}
	//method for close browser
	public static void closeBrowser()
	{
		driver.close();
	}

//method for generting the date
	/*public static String  generateDate()
	{
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("YY_MM_DD_HH");
				return df.format(date);
		
	}*/
	
	
	
	
	
	
	
}
