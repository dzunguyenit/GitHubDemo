package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_XpathCSS {
WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver ();
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
  }
  @Test
  public void TC_02() {
 // email address textbox
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Automation01");
	  driver.findElement(By.xpath("//input[@id='email']")).clear ();
	  
	  driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("Automation02");
	  driver.findElement(By.xpath("//input[@title='Email Address']")).clear ();
	  
	  driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("Automation03");
	  driver.findElement(By.xpath("//input[@name='login[username]']")).clear();
	  
	  
  }


  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
