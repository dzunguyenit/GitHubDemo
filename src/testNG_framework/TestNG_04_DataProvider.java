package testNG_framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_04_DataProvider {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(dataProvider="UserAndPass")
	public void TC_01_LoginToGuruSite(String username, String pass) {
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Log out']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		
	}

	@DataProvider(name="UserAndPass")
	public Object[][] UserAndPassDataTest() {
		return new Object[][] { 
			new Object[] { "mngr157302", "aqagYte" }, 
			new Object[] { "mngr162210", "AnUjYta" },
			new Object[] { "mngr162211", "dUzahed" },
			};
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
