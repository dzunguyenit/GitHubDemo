package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_DropDown_CustomDropDown_04Exercise {
	
	// khởi tạo driver
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver ();
			
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		wait= new WebDriverWait (driver,30);
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	public void TC_01_HTMLDropdown() throws InterruptedException{
		//Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/
		driver.get("https://daominhdam.github.io/basic-form/");
		
		//Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
		Select select = new Select (driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(select.isMultiple());
		
		//Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
		select.selectByVisibleText("Automation Tester");
		
		//Step 04 - Kiểm tra giá trị đã được chọn thành công
		select.getFirstSelectedOption().getText();
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Automation Tester");
	
		//Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
		select.selectByValue("manual");
		
		//Step 06 - Kiểm tra giá trị đã được chọn thành công
		select.getFirstSelectedOption().getText();
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Manual Tester");
		
		//Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
		select.selectByIndex(3);
		
		//Step 08 - Kiểm tra giá trị đã được chọn thành công
		select.getFirstSelectedOption().getText();
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Mobile Tester");
		
		//Step 09 - Kiểm tra dropdown có đủ 5 giá trị
		Assert.assertEquals(select.getOptions().size(),5);
	}


 		@Test
		public void TC_02_CustomDropdown() throws Exception{
		//JQUERY
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		clickAllItemInDropDown("//span[@id='number-button']","//ul[@id='number-menu']//div","19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text'and text()='19']")).isDisplayed()); //kích chọn 19 rồi mới bắt xpath
		
		clickAllItemInDropDown("//span[@id='number-button']","//ul[@id='number-menu']//div", "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text'and text()='5']")).isDisplayed());
		
		//ANGULAR
		driver.get("https://material.angular.io/components/select/examples");
		clickAllItemInDropDown("//mat-select[@placeholder='State']","//mat-option//span", "Indiana"); //kích chọn Indiana rồi mới bắt xpath 
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Indiana']")).isDisplayed());
		
		clickAllItemInDropDown("//mat-select[@placeholder='State']","//mat-option//span", "Wyoming");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Wyoming']")).isDisplayed());
		
		//KENDO-UI
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		clickAllItemInDropDown("//span[@aria-owns='color_listbox']","//ul[@id='color_listbox']/li", "Grey");  
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='k-input' and text()='Grey']")).isDisplayed());
		
		
		//VUE
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		clickAllItemInDropDown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a", "Third Option");  
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='btn-group']/li")).isDisplayed());
		
	}
	
		
		//Hàm common dropdown list - chọn từ dropdown  
		public void clickAllItemInDropDown (String parentLocator, String allItemsLocator, String expectedvalue) throws Exception
		{//1- Lấy xpath của dropdown 
			WebElement parentDropdown = driver.findElement(By.xpath(parentLocator));
							
			//dùng Javascript to kích dropdown
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",parentDropdown);
						
			//Sử dụng list WebElement để khai báo các phần tử trong dropdown 
			List <WebElement> allItemsDropDown = driver.findElements(By.xpath(allItemsLocator));
			//get ra xem có tổng bao nhiêu element
			int numberItem = allItemsDropDown.size(); 
			System.out.println(numberItem);
		//2-Wait cho đến khi các phần tử visible
			wait.until(ExpectedConditions.visibilityOfAllElements(allItemsDropDown));
		//3-Dùng vòng lặp for, duyệt qua từng phần tử và get text ra 
			for(int i=0;i<numberItem;i++)
			{
				//4-Nếu text được get bằng text mong muốn
				if(allItemsDropDown.get(i).getText().equals(expectedvalue))
				{
					//5-Scroll to element và kích nó 
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",allItemsDropDown.get(i) );
					allItemsDropDown.get(i).click();
					break;
				}
			}
				
		}

//------DROPDOWN AUTOCOMPLETE -----------		
		
@Test
	public void TC_03_editCustomDropDown() throws Exception
	{
		//JQUERY - EDITABLE
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		editCustomDropDown("//div[@id='default-place']/input","a","//div[@id='default-place']//li[contains(text(),'a')]", "Audi");  
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']//li[text()='" + "Audi" + "']")).isDisplayed());
	
	}
	

//Hàm common dropdown list - chọn từ dropdown  
	public void editCustomDropDown (String parentLocator,String inputValue, String allItemsLocator, String expectedvalue) throws Exception
	{//1- Lấy xpath của dropdown 
		WebElement parentDropdown = driver.findElement(By.xpath(parentLocator));
						
		//dùng Javascript to kích dropdown
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",parentDropdown);
		
		//input kí tự vào 
		parentDropdown.sendKeys(inputValue);
		Thread.sleep(2000);
					
		//Sử dụng list WebElement để khai báo các phần tử trong dropdown 
		List <WebElement> allItemsDropDown = driver.findElements(By.xpath(allItemsLocator));
		//get ra xem có tổng bao nhiêu element
		int numberItem = allItemsDropDown.size(); 
		System.out.println(numberItem);
	//2-Wait cho đến khi các phần tử visible
		wait.until(ExpectedConditions.visibilityOfAllElements(allItemsDropDown));
	//3-Dùng vòng lặp for, duyệt qua từng phần tử và get text ra 
		for(int i=0;i<numberItem;i++)
		{
			//4-Nếu text được get bằng text mong muốn
			if(allItemsDropDown.get(i).getText().equals(expectedvalue))
			{
				//5-Scroll to element và kích nó 
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",allItemsDropDown.get(i) );
				allItemsDropDown.get(i).click();
				break;
			}
		}
			
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
