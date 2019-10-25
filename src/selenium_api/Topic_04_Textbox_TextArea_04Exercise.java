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

public class Topic_04_Textbox_TextArea_04Exercise {
	WebDriver driver;
	String customerID, customername, gender, dateofbirth, add, city, state, pin, mobile, email, pass; //khai báo biến 
	String editadd, editcity, editstate, editpin, editmobile, editemail;
	
		
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// Access website http://demo.guru99.com/v4
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	@Test
	public void TC_01_CreateNewCustomer() {
		
		customername="auto test"; 
		gender="male";
		dateofbirth="2000-01-01";
		add="30 Giap Nhi";
		city="Ha Noi";
		state="Hoang Mai";
		pin="123654";
		mobile="0915223300";
		email="autotest" + randomdata()+ "@gmail.com";
		pass="abc123";
		
		// Login with User = mngr155533 and Pass = aqAtAda
		driver.findElement(By.xpath("//input[@name ='uid']")).sendKeys("mngr155533");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("aqAtAda");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	
		// Verify HomePage được hiển thị thành công
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		// Chọn menu New Customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		// Nhập toàn bộ dữ liệu đúng > Click Submit
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customername);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(By.xpath("//input[@name='dob']")).sendKeys(dateofbirth);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(add);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobile);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		
		// Verify new customer created with message 
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		//Get ra thông tin của Customer ID
		customerID= driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("customerID = " + customerID );
		
	
		// verify new customer created with above information 
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customername ); //kiểm tra đầu ra có đúng đầu vào ko
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateofbirth );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),add );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobile );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email );

		
	}
	
	@Test 
	public void TC_02_EditCustomer() {
		
		editadd="123 Ben Thuy";
		editcity="Nghe An";
		editstate="Ben Thuy";
		editpin="111111";
		editmobile="0984221122";
		editemail="editautotest" + randomdata() + "@gmail.com";

		// Chọn menu Edit Customer
		
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		
		// Enter value into CustomerID và kích btn 
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		// verify màn hình displayed 
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Edit Customer']")).isDisplayed());
		
		//Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu khi tạo mới 
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"),customername);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(),add);
		
		//Nhập giá trị mới tại tất cả các field (ngoại trừ những field bị disable) > Submit
		driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		driver.findElement(By.xpath("//input[@name='city']")).clear();
		driver.findElement(By.xpath("//input[@name='state']")).clear();
		driver.findElement(By.xpath("//input[@name='pinno']")).clear();
		driver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
		driver.findElement(By.xpath("//input[@name='emailid']")).clear();
		
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(editadd);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(editcity);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(editstate);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(editpin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(editmobile);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(editemail);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		// Verify new customer created with message 
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());
				
		
		//Verify giá trị tất cả các field đúng với dữ liệu sau khi đã Edit thành công
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),editadd );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),editcity );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),editstate );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),editpin );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),editmobile );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editemail );
		
	} 


		//Tạo hàm random data
	public int randomdata()
	{
		Random random = new Random();
		int number=random.nextInt(999999);
		return number;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
