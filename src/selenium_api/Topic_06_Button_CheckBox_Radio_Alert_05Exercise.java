package selenium_api;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_06_Button_CheckBox_Radio_Alert_05Exercise {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver ();
		System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test 
	public void TC_01_Button(){
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		//Step 02 - Click vào link My Account dưới footer (Sử dụng Javascript Executor code)
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']"));
		clickElementByJavascripts(myAccountLink);
		
		//Step 03 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/login/
		WebElement loginForm = driver.findElement(By.xpath("//form[@id='login-form']"));
		Assert.assertTrue(isControlDisplay(loginForm));
		
		//Step 04 - Click vào button CREATE AN ACCOUNT (Sử dụng Javascript Executor code)
		WebElement createAnAccount = driver.findElement(By.xpath("//a[@class='button']"));
		clickElementByJavascripts(createAnAccount);
		
		//Step 05 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/create/
		WebElement regisPage = driver.findElement(By.xpath("//div[@class='main-container col1-layout']"));
		Assert.assertTrue(isControlDisplay(regisPage));
	
	}

	@Test 
	public void TC_02_Checkbox() throws Exception{
		//Step 01 - Truy cập vào trang: https://demos.telerik.com/kendo-ui/styling/checkboxes
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		//Step 02 - Click vào checkbox: Dual-zone air conditioning (Thẻ input ko được sử dụng thuộc tính id)
		WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		clickElementByJavascripts(dualZoneCheckbox);
		Thread.sleep(2000);
		//Step 03 - Verify checkbox đó đã chọn thành công
		Assert.assertTrue(dualZoneCheckbox.isSelected());
		//Step 04 - Sau khi checkbox đã được chọn - deselect nó và kiểm tra nó chưa được chọn
		if(dualZoneCheckbox.isSelected())
		{
			clickElementByJavascripts(dualZoneCheckbox);
			
		}	Assert.assertFalse(dualZoneCheckbox.isSelected());
	}
	
	
	@Test 
	public void TC_03_Radio(){
		//Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/radios
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		//Step 02 - Click vào radiobutton:  2.0 Petrol, 147kW (Thẻ input ko được sử dụng thuộc tính id)
		WebElement petrolRadio = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		clickElementByJavascripts(petrolRadio);
		// Step 03 - Kiểm tra radio button đó đã chọn hay chưa/ nếu chưa chọn lại
		Assert.assertTrue(petrolRadio.isSelected());
		if (!petrolRadio.isSelected())
		{
			clickElementByJavascripts(petrolRadio);
		}
		
	}
	
	
	@Test 
	public void TC_04_JSAlert() throws Exception{
		//Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("http://daominhdam.890m.com/");
		//Step 02 - Click vào button: Click for JS Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		//Step 03 - Verify message hiển thị trong alert là: I am a JS Alert
		Alert alert = driver.switchTo().alert();
		String jsAlertMessage = alert.getText();
		Assert.assertEquals(jsAlertMessage, "I am a JS Alert");
		//Step 04 - accept alert và verify message hiển thị tại Result là:  You clicked an alert successfully
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result']")).isDisplayed());
		Thread.sleep(3000);
		
	}
	
	@Test 
	public void TC_05_JSConfirm() throws Exception{
		//Step 02 - Click vào button: Click for JS Confirm
		driver.get("http://daominhdam.890m.com/");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		//Step 03 - Verify message hiển thị trong alert là: I am a JS Confirm
		Alert alert = driver.switchTo().alert();
		String jsConfirm = alert.getText();
		Assert.assertEquals(jsConfirm, "I am a JS Confirm");
		//Step 04 - Cancel alert và verify message hiển thị tại Result là:  You clicked: Cancel
		alert.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result']")).isDisplayed());
		Thread.sleep(3000);
	}
	
	@Test 
	public void TC_06_JSPrompt(){
		//Step 02 - Click vào button: Click for JS Prompt
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		//Step 03 - Verify message hiển thị trong alert là: I am a JS prompt
		Alert alert = driver.switchTo().alert();
		String jsPrompt = alert.getText();
		Assert.assertEquals(jsPrompt, "I am a JS prompt");
		
		//Step 04 - Nhập vào text bất kì (daominhdam) và verify message hiển thị tại Result là:  You entered: daominhdam
		String entertext = "automation";
		alert.sendKeys(entertext);
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You entered: " + entertext + "']")).isDisplayed());
		
	}
	
	@Test 
	public void TC_07() throws Exception{
		// Step 01, 02: Truy cập trang và handle User và pass 
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		//Step 03 - Verify message hiển thị sau khi login thành công:Congratulations! You must have the proper credentials.
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
		
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Hàm common click element 
	public void clickElementByJavascripts (WebElement element)
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}
	
	// Hàm common verify thành công sau khi click vào 1 element 
	public boolean isControlDisplay (WebElement element)
	{
		return element.isDisplayed();
	}
	
}
