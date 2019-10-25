package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Window_07Exercise {
	// khởi tạo driver
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01() {
		// Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		String parentWindowID = driver.getWindowHandle();
		System.out.println("parentWindowID = " + parentWindowID);
		// Step 02 - Click "Opening a new window: Click Here" link -> Switch qua tab mới
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		switchAllWindow("Google");
		// Step 03 - Kiểm tra title của window mới = Google
		isTitleDisplayed("Google");
		// Step 04 - Close window mới
		driver.close();
		// Step 05 - Switch về parent window, kiểm tra title của parent window
		driver.switchTo().window(parentWindowID);
		isTitleDisplayed("SELENIUM WEBDRIVER FORM DEMO");

	}

	@Test
	public void TC_02(){
		//	Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
		driver.get("https://www.hdfcbank.com/");
		String parentWindowID = driver.getWindowHandle();
		//	Step 02 - Kiểm tra và close quảng cáo nếu có xuất hiện
		List <WebElement> notificationPopup = driver.findElements(By.xpath("//div[@id='container-div']/img"));
		if(notificationPopup.size()>0)
		{
			driver.switchTo().frame(notificationPopup.get(0));
			WebElement closepopup = driver.findElement(By.xpath("//div[@id='div-close']"));
			Assert.assertTrue(closepopup.isDisplayed());
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",closepopup);
			Assert.assertFalse(closepopup.isDisplayed());
			driver.switchTo().defaultContent();
			
		}
	
		//	Step 03 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		switchAllWindow("HDFC Bank Kisan Dhan Vikas e-Kendra");
		isTitleDisplayed("HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		//Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
		driver.findElement(By.xpath("//a[div[p[text()='Account Details']]]")).click();
		switchAllWindow("Welcome to HDFC Bank NetBanking");
		isTitleDisplayed("Welcome to HDFC Bank NetBanking");
		
		//Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
		WebElement frame = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(frame);
		
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		switchAllWindow("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		isTitleDisplayed("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		
		//Step 06- Click CSR link on Privacy Policy page
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		switchAllWindow("HDFC BANK - CSR - Homepage");
		isTitleDisplayed("HDFC BANK - CSR - Homepage");
		
		//Step 07 - Close tất cả popup khác - chỉ giữ lại parent window (http://www.hdfcbank.com/)
		closeAllWindowsWithoutParentWindow(parentWindowID);
		isTitleDisplayed("HDFC Bank: Personal Banking Services");
			
	}

	// Hàm common switch to all windows
	public void switchAllWindow(String title) {
		// get ra tất cả window ID
		Set<String> allWindowID = driver.getWindowHandles();
		System.out.println("allWindowID =" + allWindowID);
		// dùng vòng lặp for-each duyệt qua từng ID
		for (String runWindow : allWindowID) {
			// Switch vào từng window
			driver.switchTo().window(runWindow);
			// get ra title của từng page đã switch qua
			String currentWindowTitle = driver.getTitle();
			// kiểm tra title switch bằng title mong muốn
			if (currentWindowTitle.equals(title)) {
				break;
			}

		}

	}

	// Hàm common verify title đúng với title đã switch
	public boolean isTitleDisplayed(String title) {
		String currentTitle = driver.getTitle();
		boolean checkTitle = currentTitle.equals(title);
		Assert.assertTrue(currentTitle.equals(title));
		return checkTitle;
	}
	
	//Hàm common close all windows, chỉ giữ lại parent window
	public boolean closeAllWindowsWithoutParentWindow (String parentWindow)
	{	//get tất cả window ID
		Set<String> allWindows = driver.getWindowHandles();
		//dùng vòng lặp for-each duyệt qua từng ID
		for(String runWindow:allWindows)
		{   //Kiểm tra nếu window ID nào khác với parent ID
			if(!runWindow.equals(parentWindow))
			{
				//Switch qua window đó
				driver.switchTo().window(runWindow);
				//đóng cửa sổ window
				driver.close();
			}
		}
				//switch qua parent window
				driver.switchTo().window(parentWindow);
				//kiểm tra chỉ còn duy nhất 1 cửa sổ
				if(driver.getWindowHandles().size()==1)
					return true;
				else 
					return false;
		}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
