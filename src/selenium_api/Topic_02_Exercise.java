package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Exercise {
	// khởi tạo driver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test  //Test Script 01: Verify URL and title
	public void TC_01_VerifyUrlAndTitle() {
		//Step 01 - Truy cập vào trang: http://live.guru99.com
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Step 02 - Kiểm tra title của page là: "Home page"
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle,"Home page");
		
		//Step 03 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[text()='My Account']")).click();
		
		//Step 04 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
		
		//Step 05 - Back lại trang đăng nhập
		driver.navigate().back();
		
		//Step 06 - Kiểm tra url của page đăng nhập là: http://live.guru99.com/index.php/customer/account/login/
		String loginPageUrl= driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.guru99.com/index.php/customer/account/login/");
		
		//Step 07 - Forward tới trang tạo tài khoản
		driver.navigate().forward();
		
		//Step 08 - Kiểm tra url của page tạo tài khoản là: http://live.guru99.com/index.php/customer/account/create/
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.guru99.com/index.php/customer/account/create/");
	}

	@Test //Test Script 02: Login empty
	public void TC_02_LoginWithEmailAndPasswordEmpty(){
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[text()='My Account']")).click();
		
		//Step 03 - Để trống Username/Password - Step 04 - Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Step 05 - Verify error message xuất hiện:  This is a required field.
		String emailErrorText = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorText,"This is a required field.");
		System.out.println(emailErrorText);
		
		String passwordErrorText = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorText,"This is a required field.");
		System.out.println(passwordErrorText);
	}
	
	@Test //Test Script 03: Login with Email invalid
	public void TC_03_LoginWithEmailInvalid() {
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[text()='My Account']")).click();
		
		//Step 03 - Nhập email invalid: 123434234@12312.123123
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		
		//Step 04 - Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Step 05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
		String emailErrorMessage = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailErrorMessage, "Please enter a valid email address. For example johndoe@domain.com.");
		System.out.println(emailErrorMessage);

	}
	@Test //Test Script 04: Login with Password incorrect
	public void TC_04_LoginWithPasswordIncorrect() {
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[text()='My Account']")).click();
		
		//Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		
		//Step 04 - Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Step 05 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
		String passwordErrorMessage = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordErrorMessage, "Please enter 6 or more characters without leading or trailing spaces.");
		System.out.println(passwordErrorMessage);
	}
	

	@Test //Test Script 05: Create an account
	public void TC_05_CreateAnAccount(){
				
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[text()='My Account']")).click();
		
		//Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
		
		//Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("abc");
		
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("abc");
		
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("abc");
		
		String email = "seleniumonline" + randomData() + "@gmail.com";	
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123@123");
		
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123@123");
		
		//Step 05 - Click REGISTER button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		//Step 05 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store.
		String registerMessageSuccessful = driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]")).getText();
		Assert.assertEquals(registerMessageSuccessful,"Thank you for registering with Main Website Store.");
		
		//Step 06 - Logout khỏi hệ thống
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
					
		//Step 07 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công
		Assert.assertTrue (driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
			

	}
	// Tạo random cho dữ liệu
	public int randomData()
	{
		Random random = new Random();
		int number = random.nextInt(99999999);
		return number;
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
