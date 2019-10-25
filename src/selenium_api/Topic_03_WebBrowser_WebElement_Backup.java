package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebBrowser_WebElement_Backup {
	// khởi tạo driver
	WebDriver driver;
	WebElement element;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_WebBrowserCommands() {
		// mở 1 đường link bất kì
		driver.get("http://daominhdam.890m.com/");

		// Đóng current tab
		driver.close();

		// Đóng all tab
		driver.quit();

		// Trả về đường link hiện tại của page đó
		String homePageUrl = driver.getCurrentUrl();
		System.out.println(homePageUrl);

		// Muốn kiểm tra 1 element/text nằm trong page mà không thể locate
		String homePageSource = driver.getPageSource();
		Assert.assertTrue(homePageSource.contains("Your basic info"));
		System.out.println(homePageSource);

		// Get và kiểm tra 1 title của page có đúng hay không
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "SELENIUM WEBDRIVER FORM DEMO");

		// handle Window/popup
		driver.getWindowHandle();
		driver.getWindowHandles();

		// wait cho element được xuất hiện trong 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// wait cho page được load thành công trong vòng 30s
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		driver.manage().window().fullscreen();
		driver.manage().window().maximize();

		// back về trang hiện tại
		driver.navigate().back();

		// forward tới trang trước
		driver.navigate().forward();

		// tải lại trang
		driver.navigate().refresh();

		// Alert
		driver.switchTo().alert();
		driver.switchTo().frame("");

	}

	@Test
	public void TC_02_WebElementCommands() {
		// thao tác với 1 element
		driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Selenium 06");
		driver.findElement(By.xpath("//input[@id='mail']")).clear();
		driver.findElement(By.xpath("//input[@id='mail']")).click();

		// thao tác với 1 list các element
		driver.findElements(By.xpath("")).get(1);

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));

		// thường đi kèm với sendkey, dùng để xóa dữ liệu trong textbox/ textarea
		emailTextbox.clear();

		// lấy vị trí của element nắm trong độ phân giải màn hình
		element.getLocation();

		// lấy ra chiều rộng/ chiều cao của element
		element.getSize();

		element.getTagName();

		// get text ---> get ra text có đúng hay không
		String emailErrorText = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorText, "This is a required field.");

		// Kiểm tra 1 element có hiển thị hay không ---> apply cho tất cả element 
		Assert.assertTrue(element.isDisplayed());
		
		//Kiểm tra 1 element có enable hay không ----> textbox/text area/ dropdown list/checkbox/radio button
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		//Kiểm tra 1 element đã được chọn hay chưa  ---> Checkbox/radio button
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected()); // chưa được chọn 
		
		// submit tương đương hành động ENTER trong 1 form
		element.submit();
		
	

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}