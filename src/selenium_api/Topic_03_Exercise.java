package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Exercise {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test // Test Script 01: Kiểm tra phần tử hiển thị trên trang
	public void TC_01_VerifyElementIsDisplayed() {
		// Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("https://daominhdam.github.io/basic-form/");

		// Step 02 - Kiểm tra các phần tử sau hiển thị trên trang: Email/ Age (Under
		// 18)/ Education
		// Step 03 - Nếu có nhập giá trị: Automation Testing vào 2 field Email/
		// Education và chọn Age = Under 18
		WebElement emailTxt = driver.findElement(By.xpath("//input[@id='mail']"));
		if (emailTxt.isDisplayed()) {
			emailTxt.sendKeys("Automation Testing");
		}

		WebElement educationTxt = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if (educationTxt.isDisplayed()) {
			educationTxt.sendKeys("Automation Testing");
		}

		WebElement under18Raiobtn = driver.findElement(By.xpath("//input[@id='under_18']"));
		if (under18Raiobtn.isDisplayed()) {
			under18Raiobtn.click();
		}

	}

	@Test // Test Script 02: Kiểm tra phần tử enable/ disable trên trang
	public void TC_02_VerifyElementIsEnableOrDisabled() {
		// Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("https://daominhdam.github.io/basic-form/");

		// Step 02: Kiểm tra các phần tử sau enable trên trang: Email/ Age (Under 18)/
		// Education/ Job Role 01/ Interests (Development)/ Slider 01/ Button is enabled
		WebElement emailTxt = driver.findElement(By.xpath("//input[@id='mail']"));
		WebElement educationTxt = driver.findElement(By.xpath("//textarea[@id='edu']"));
		WebElement under18Raiobtn = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement jobRole01Dropdown = driver.findElement(By.xpath("//select[@id='job1']"));
		WebElement interrestsDevCheckbox = driver.findElement(By.xpath("//input[@id='development']"));
		WebElement slider01 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		WebElement btnIsEnabled = driver.findElement(By.xpath("//button[@id='button-enabled']"));

		isElementEnabled(emailTxt);
		isElementEnabled(educationTxt);
		isElementEnabled(under18Raiobtn);
		isElementEnabled(jobRole01Dropdown);
		isElementEnabled(interrestsDevCheckbox);
		isElementEnabled(slider01);
		isElementEnabled(btnIsEnabled);

		// Step 03: Kiểm tra các phần tử sau disable trên trang: Password / Age
		// (Radiobutton is disabled)/ Biography/ Job Role 02/ Interests (Checkbox is
		// disabled)/ Slider 02/ Button is disabled
		WebElement passlTxt = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement ageRadiobtnIsDisplayed = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		WebElement bioTextear = driver.findElement(By.xpath("//textarea[@id='bio']"));
		WebElement job2Drop = driver.findElement(By.xpath("//select[@id='job2']"));
		WebElement interestscheckbox = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		WebElement slider02 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		WebElement buttonIsDisabled = driver.findElement(By.xpath("//button[@id='button-disabled']"));

		isElementEnabled(passlTxt);
		isElementEnabled(ageRadiobtnIsDisplayed);
		isElementEnabled(bioTextear);
		isElementEnabled(job2Drop);
		isElementEnabled(interestscheckbox);
		isElementEnabled(slider02);
		isElementEnabled(buttonIsDisabled);

	}

	@Test // Test Script 03: Kiểm tra phần tử được chọn trên trang
	public void TC_03_VerifyElementIsSelected() {
		// Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("https://daominhdam.github.io/basic-form/");

		// Step 02 - Click chọn Age (Under 18)/ Interests (Development)
		WebElement under18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		under18.click();
		WebElement interestsDev = driver.findElement(By.xpath("//input[@id='development']"));
		interestsDev.click();

		// Step 03 - Kiểm tra các phần tử tại Step 02 đã được chọn
		// Step 04 - Nếu chưa được chọn thì cho phép chọn lại 1 lần nữa

		if (under18.isSelected()) {
			System.out.println("element" + under18 + "is selected");
		} else
		{
			under18.click();
			System.out.println("element" + under18 + "is selected");
		}

		if (interestsDev.isSelected()) {
			System.out.println("element" + interestsDev  + "is selected");
		}else
		{
			interestsDev.click();
			System.out.println("element" + interestsDev + "is selected");
		}
	}

	// khai báo biến element, nếu có in ra Element is enabled/ ngược lại Element is
	// disabled
	public void isElementEnabled(WebElement element) {

		if (element.isEnabled()) {
			System.out.println("Element is enabled");
		} else
			System.out.println("Element is disabled");
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
