package testNG_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestNG_05_Parameter_Multibrowsers {
	WebDriver driver;
	String email,firstname,middlename,lastname,password,confirmpass;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		if(browserName.equals("firefox"))
		{	driver = new FirefoxDriver();
		
		}
		else
			if(browserName.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
				driver = new ChromeDriver();
			}else
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				options.addArguments("window-size=1920x1080");
				driver = new ChromeDriver(options);
			}

		firstname="selenium06";
		middlename="abc";
		lastname="abc";
		password="123@123";
		confirmpass="123@123";
		email = "seleniumonline" + randomData() + "@gmail.com";
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_CreateAnAccount() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstname);
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(middlename);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(confirmpass);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		String registerMessageSuccessful = driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]")).getText();
		Assert.assertEquals(registerMessageSuccessful, "Thank you for registering with Main Website Store.");
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
		
	}
	@Parameters({"email","pass"})
	@Test
	public void TC_02_LoginToSystem(String email,  String pass) {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		
		
	}

	public int randomData() {
		Random random = new Random();
		int number = random.nextInt(99999999);
		return number;
	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

}
