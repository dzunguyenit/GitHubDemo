package selenium_api;

import java.util.List;
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


public class Topic_08_Frame_IFrame_07Exercise {
	// khởi tạo driver
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TestScript_01_HandleFrame(){
		
		//Step 01: Truy cập vào trang: http://www.hdfcbank.com/
		driver.get("http://www.hdfcbank.com/");
		//Step 02: Close popup nếu có hiển thị (switch qua iframe nếu có)
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); // cài đặt timeout cho chạy trong 10s
		//Sử dụng list WebElement trong trường hợp popup xuất hiện random, nhớ quay về top window để tìm tất cả iframe, hover vào "iframe" highlight thì lấy xpath
		List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("List element = " +notificationIframe.size());
		if(notificationIframe.size()>0)
		{
			driver.switchTo().frame(notificationIframe.get(0));
			WebElement closePopup = driver.findElement(By.xpath("//div[@id='div-close']"));
			//Kiểm tra closepopup được hiển thị
			Assert.assertTrue(closePopup.isDisplayed());
			//Click closepopup bằng javascript
			JavascriptExecutor je = (JavascriptExecutor)driver;
			je.executeScript("arguments[0].click();", closePopup);
			//kiểm tra closepopup ko còn hiển thị 
			Assert.assertFalse(closePopup.isDisplayed());
			//switch to parent window/top window
			driver.switchTo().defaultContent();
		}
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); //trả lại timeout 30s
		//Step 03:Verify đoạn text được hiển thị:  What are you looking for? (switch qua iframe nếu có)
		 WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		 driver.switchTo().frame(lookingForIframe);
		 Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']")).isDisplayed());
		 driver.switchTo().defaultContent();
		 
		//Step 04:Verify banner image được hiển thị (switch qua iframe nếu có)
		 WebElement slidingBannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe[starts-with(@id,'viz_iframe')]"));
		 driver.switchTo().frame(slidingBannerIframe);
		 //sử dụng list element để kiểm tra có 6 image 
		 List <WebElement> slidingImage = driver.findElements(By.xpath("//div[@id='productcontainer']//img[@class='bannerimage']"));
		 Assert.assertEquals(slidingImage.size(), 6);
		 driver.switchTo().defaultContent();
				
		//Step 05:Verify flipper banner được hiển thị và có 8 items, nếu image user nhìn thấy được, được hiển thị (front icon), còn user ko nhìn thấy được (back icon)
		 List <WebElement> flipBanner = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		 Assert.assertEquals(flipBanner.size(),8);
		//check banner image displayed
		 for(int i=0;i<flipBanner.size();i++)
		 {
			 Assert.assertTrue(flipBanner.get(i).isDisplayed());
		 }
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
