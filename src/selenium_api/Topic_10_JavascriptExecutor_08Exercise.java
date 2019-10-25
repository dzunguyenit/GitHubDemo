package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_JavascriptExecutor_08Exercise {
	WebDriver driver;
	JavascriptExecutor javascript;

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver ();
		//chạy trên chrome
		System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
		driver=new ChromeDriver();
		
		javascript = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC_01_JavascriptExcecutor(){
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Sử dụng JE để get domain của page
		String homePageDomain = (String) executeForBrowse("return document.domain;");
		Assert.assertEquals(homePageDomain, "live.guru99.com");
					
		//Step 03 - Sử dụng JE để get URL của page
		String homePageURL = (String) executeForBrowse("return document.URL"); //ép kiểu string
		Assert.assertEquals(homePageURL, "http://live.guru99.com/");
		
		//Step 04 - Open MOBILE page (Sử dụng JE)
		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		highlightElement(mobileLink);
		clickToElementByJS(mobileLink);
		
		//Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
		WebElement addToCart = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
		highlightElement(addToCart);
		clickToElementByJS(addToCart);
		
		//Step 06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get innertext of the entire webpage )
		
		String succesMessage = (String)executeForBrowse("return document.documentElement.innerText;");
		Assert.assertTrue(succesMessage.contains("Samsung Galaxy was added to your shopping cart."));		
				 
		//Step 07 - Open PRIVACY POLICY page (Sử dụng JE)
		WebElement privacyPolicyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		highlightElement(privacyPolicyLink);
		clickToElementByJS(privacyPolicyLink);
		 //Verify title của page = Privacy Policy (Sử dụng JE)
		String privacyTitle = (String) executeForBrowse("return document.title;");
		Assert.assertEquals(privacyTitle, "Privacy Policy");
		
		//Step 08 - Srcoll xuống cuối page
		scrollToBottomPage();
			
		//Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath
		Assert.assertTrue(driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']")).isDisplayed());
		
		//Step 10 - Navigate tới domain: http://demo.guru99.com/v4/(Sử dụng JE) - verify domain sau khi navigate
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String homeDomain = (String) executeForBrowse("return document.domain;");
		Assert.assertEquals(homeDomain, "demo.guru99.com");
	}
	
	@Test
	public void TC_02_Removeattribute() throws Exception{
		String firstName = "Automation Testing", lastName ="Adhoc Testing";
		
		//Step 01 - Truy cập vào trang: https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		
		//Step 02 - switch qua iframe nếu có
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(iframe);
				
		//Step 03 - Sendkey vào field First name 
		WebElement firstNameField = driver.findElement(By.xpath("//input[@name='fname']"));
		sendkeyToElementByJS(firstNameField,firstName);
		
		//sendkey vào field Last name
		WebElement lastNameField = driver.findElement(By.xpath("//input[@name='lname']"));
		
		//Remove thuộc tính disabled của field Last name, 
		removeAttributeInDOM(lastNameField,"disabled");
		Thread.sleep(3000);
		sendkeyToElementByJS(lastNameField,lastName);
				
		//Step 04 - Click Submit button
		WebElement submitBtn = driver.findElement(By.xpath("//input[@value='Submit']"));
		highlightElement(submitBtn);
		clickToElementByJS(submitBtn);
				
		//Step 05 - Verify dữ liệu sau khi submit chứa đoạn text đã fill trong field Lastname 
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'fname=" + firstName + "&lname=" + lastName + "')]")).isDisplayed());
		
		
	}
	@Test
	public void TC_03_CreateAnAccount(){
		String firstName, lastName, email, pass, confirmpass;
		firstName="auto test";
		lastName="adhoc test";
		email="autotest" + randomData() +"@gmail.com";
		pass="abc123";
		confirmpass="abc123";
		
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập (Sử dụng JE)
		WebElement myacc = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		highlightElement(myacc);
		clickToElementByJS(myacc);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='account-login']")).isDisplayed());
		
		//Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản (Sử dụng JE)
		WebElement createAnAccount = driver.findElement(By.xpath("//a[@class='button']"));
		highlightElement(createAnAccount);
		clickToElementByJS(createAnAccount);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='account-create']")).isDisplayed());
		
		//Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password (Sử dụng JE)
		WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstname']"));
		sendkeyToElementByJS(firstNameField,firstName);
		
		WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastname']"));
		sendkeyToElementByJS(lastNameField,lastName);
		
		WebElement emailField = driver.findElement(By.xpath("//input[@ id='email_address']"));
		sendkeyToElementByJS(emailField,email);
		
		WebElement passField = driver.findElement(By.xpath("//input[@id='password']"));
		sendkeyToElementByJS(passField,pass);
		
		WebElement confirmPassField = driver.findElement(By.xpath("//input[@id='confirmation']"));
		sendkeyToElementByJS(confirmPassField,confirmpass);
				
		//Step 05 - Click REGISTER button (Sử dụng JE)
		WebElement registerBtn = driver.findElement(By.xpath("//form[@id='form-validate']//button[@class='button']"));
		highlightElement(registerBtn);
		clickToElementByJS(registerBtn);
		
		//Step 05 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store. 
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
		//Step 06 - Logout khỏi hệ thống (Sử dụng JE)
		WebElement account = driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']"));
		Actions action = new Actions(driver);
		action.moveToElement(account).click().perform();
		
		WebElement logout = driver.findElement(By.xpath("//a[text()='Log Out']"));
		action.moveToElement(logout).click().perform();
				
		//Step 07 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công (Sử dụng JE)
		navigateToUrlByJS("http://live.guru99.com/index.php/");
		
	}
	//Hàm common execute browse (get domain, get URL, thực thi browse bằng chuỗi js)
	public Object executeForBrowse (String javaScript) 
	{
		try{
			return javascript.executeScript(javaScript);
		}catch(Exception e){
			e.getMessage();
			return null;
		}
		
	}
	
	//Hàm common kích element by JS
	public Object clickToElementByJS (WebElement element)
	{
	
		try{
			return javascript.executeScript("arguments[0].click();",element);
		}catch(Exception e)
		{ e.getMessage();
		 return null;
		}
				
	}
	//hàm common scroll xuống cuối page 
	public Object scrollToBottomPage()
	{
		try{
			return javascript.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}catch(Exception e){
			e.getMessage();
			return null;
			
		}
	}
	//Hàm common navigate
	public Object navigateToUrlByJS(String url) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.location = '" + url + "'");
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
	//Hàm common sendkeys element by JS
	public Object sendkeyToElementByJS(WebElement element, String value) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
	
	//Hàm common highlight 
	 public void highlightElement(WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].style.border='6px groove red'", element);
	    }
	 
	//Hàm common remove thuộc tính trong DOM
	 public Object removeAttributeInDOM(WebElement element, String attribute) {
	        try {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	        } catch (Exception e) {
	            e.getMessage();
	            return null;
	        }
	    }
	 //Hàm random data
	 public int randomData()
	 { Random random = new Random();
	 	int number = random.nextInt(99999);
	 	return number;
	 }
	
	 @AfterClass
	public void afterClass() {
		driver.quit();
	}

}
