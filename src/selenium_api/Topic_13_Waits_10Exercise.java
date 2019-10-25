package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_13_Waits_10Exercise {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_ImplicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Kích Start button
		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// Wait result text will appear - Check result text is "Hello World!"
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}

	@Test
	public void TC_02_ExplicitWait() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']")));

		// In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No
		// Selected Dates to display."
		WebElement selectedDateBefore = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(selectedDateBefore.getText(), "No Selected Dates to display.");

		// Chọn ngày hiện tại hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại
		driver.findElement(By.xpath("//a[text()='8']")).click();

		// Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility)
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		// Wait cho selected date
		Assert.assertTrue(
				driver.findElement(By.xpath("//td[contains(@class,'rcSelected')]/a[text()='8']")).isDisplayed());

		// Verify ngày đã chọn, do DOM bị render lại, nên phải findelement lại 1 lần nữa
		// WebElement selectedDateAfter =
		// driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thursday, November 08, 2018']")).isDisplayed());

	}

	@Test
	public void TC_03_FluentWait() {
		driver.get("https://daominhdam.github.io/fluent-wait/");

		// Wait cho đến khi countdown time được visible (visibility)
		WebElement countdount = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));

		Assert.assertTrue(countdount.isDisplayed());
		wait.until(ExpectedConditions.visibilityOf(countdount));

		// Khởi tạo Fluent wait
		new FluentWait<WebElement>(countdount)
				// Tổng time wait là 15s
				.withTimeout(15, TimeUnit.SECONDS)
				// Tần số mỗi 1s check 1 lần
				.pollingEvery(1, TimeUnit.SECONDS)
				// Nếu gặp exception là find ko thấy element sẽ bỏ qua
				.ignoring(NoSuchElementException.class)
				// Kiểm tra điều kiện
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						// Kiểm tra điều kiện countdount = 00
						boolean flag = element.getText().endsWith("00");
						System.out.println("Time = " + element.getText());
						// return giá trị cho function apply
						return flag;
					}
				});

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
