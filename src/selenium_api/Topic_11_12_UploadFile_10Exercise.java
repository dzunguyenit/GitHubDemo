package selenium_api;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_12_UploadFile_10Exercise {
	WebDriver driver;
	
	String projectFolder = System.getProperty("user.dir");
	
	String Image01 = "Image_01.png";
	String Image02 = "Image_02.png";
	String Image03 = "Image_03.png";
	String Image04 = "Image_04.png";
	
	String filePath01 = projectFolder + "\\upload\\" + Image01;
	String filePath02 = projectFolder + "\\upload\\" + Image02;
	String filePath03 = projectFolder + "\\upload\\" + Image03;
	String filePath04 = projectFolder + "\\upload\\" + Image04;
	
	 String email="ltt@gmail.com";
	 String firstName="le";
	
	@BeforeClass
	public void beforeClass() {
		//run trên firefox bản 47.0.2 - 2.53.1
		//driver = new FirefoxDriver();
		
		//Run on Chrome bản mới nhất - 2.53.1
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Run on Firefox bản mới nhất - 3.14.0
//		System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		//Run on IE - 3.14.0
//		System.setProperty("webdriver.ie.driver",".\\lib\\IEDriverServer.exe");
//		driver=new InternetExplorerDriver();
		
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	

	public void TestScript_01_SingleFile_Sendkeys() throws Exception{
		//Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
	
		WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
		upload.sendKeys(filePath01);
		Thread.sleep(2000);
	}

	public void TestScript_01_MultipleFiles_Senkeys() throws Exception{
		//Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		//khai báo mảng 
		String file[] = {filePath01, filePath02,filePath03};
		int size = file.length; //độ dài của mảng 
		System.out.println("Tổng số phần tử của mảng =" +size);
		for(int i=0; i<size; i++)
			{	//sendkeys multiple file
				WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
				System.out.println(file[i]);	
				upload.sendKeys(file[i]);
				Thread.sleep(2000);
			}
	}
	
	
	public void TestScript_01_MultipleFilesOnTime_Sendkeys() throws Exception{
		//Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		//sendkey multiple file cùng một lúc 
		WebElement upload1 = driver.findElement(By.xpath("//input[@type='file']"));
		upload1.sendKeys(filePath01 + "\n" + filePath02 + "\n" + filePath03) ;
		Thread.sleep(3000);
		
		//Kiểm tra file đã được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ Image01 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ Image02 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ Image03 +"']")).isDisplayed());
		
		//Click Start button để upload cho cả 3 file
		 //Khai báo list elemet
			List <WebElement> startBtn = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		 //Dùng vòng lặp for-each duyệt qua từng phần tử và kích nó 
			for(WebElement start:startBtn)
			{
				((JavascriptExecutor)driver).executeScript("arguments[0].click();",start);
				Thread.sleep(2000);
			}
		
		//Verify cả 3 file đã được upload thành công 
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ Image01 +"']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ Image02 +"']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ Image03 +"']")).isDisplayed());

		//check image real
		List <WebElement> images = driver.findElements(By.xpath("//table[@class='table table-striped']"));
		for(WebElement image:images)
		{
			Assert.assertTrue(checkAnyImageLoaded(image));
		}
		}

	@Test
	public void TestScript_03_UploadFile_Robot() throws Exception{
		//Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		String browseName = driver.toString().toLowerCase();
		//Specify the file location with extension
		StringSelection select = new StringSelection(filePath01);

		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		//Click
		if(browseName.contains("chrome"))
		{
			driver.findElement(By.cssSelector(".fileinput-button")).click();
		}else if(browseName.contains("firefox"))
		{
			clickelementByJaVa("//input[@type='file']");
			Thread.sleep(2000);
		}

		Robot robot = new Robot();
		Thread.sleep(1000);
		//ENTER - Set con trỏ chuột vào textbox
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//CTRL - V: nhấn phím 
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		//CTRL - V: Nhả phím 
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		
		//Chọn file - thoát khỏi window dialog 
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
	}
	
	public void TestScript_04_Intergrated_UploadFile() throws Exception
	{   System.out.println(filePath04);
	
		//Step 01 - Open URL: 'https://encodable.com/uploaddemo/'
		driver.get("https://encodable.com/uploaddemo/");
		
		//Step 02 - Choose Files to Upload (Ex: UploadFile.jpg)
		WebElement uploadFile = driver.findElement(By.xpath("//input[@id='uploadname1']"));
		uploadFile.sendKeys(filePath04);
				
		//Step 03 - Select dropdown (Upload to: /uploaddemo/files/)
		Select select = new Select(driver.findElement(By.xpath("//select[@name='subdir1']")));
		select.selectByVisibleText("/uploaddemo/files/");
		select.getFirstSelectedOption().getText();
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "/uploaddemo/files/");
		
		//Step 04 - Input random folder to 'New subfolder? Name:) textbox (Ex: dam1254353)
		String folder ="thinltt" + randomData();
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(folder);
		
		//Step 05 - Input email and firstname 
		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(firstName);
		
		//Step 06 - Click Begin Upload (Note: Wait for page load successfully)
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		
		//Step 07 - Verify information - Email Address/First Name/File name: 
		Assert.assertTrue(driver.findElement(By.xpath("//dl/dd[text()='Email Address: " + email + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dl/dd[text()='First Name: " + firstName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dt/a")).isDisplayed());
		
		//Step 08 - Click 'View Uploaded Files' link
		driver.findElement(By.xpath("//div[@id='fcfooter-text']/a[text()='View Uploaded Files']")).click();
		
		//Step 09 - Click to random folder 
		Actions action = new Actions(driver);
		WebElement folderRandom = driver.findElement(By.xpath("//td[@class='dname diricon']/a[text()='" + folder + "']"));
		action.moveToElement(folderRandom).click().perform();
				
		//Step 10 - Verify file name exist in folder (UploadFile.jpg)
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='fname thumb']//a")).isDisplayed());
		
	}
	//Ham random 
	public int randomData()
	{
		Random random=new Random();
		int number = random.nextInt(9999);
		return number;
	}
	
	// Hàm common check image uploaded real 
	public boolean checkAnyImageLoaded(WebElement image) {
		  JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		  return (boolean) jsExecutor.executeScript("return arguments[0].complete && " + "typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
		 }
	
	//Hàm click element by JAVA
	public Object clickelementByJaVa(String locator)
	{
		try{
			WebElement element = driver.findElement(By.xpath(locator));
			return ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		} catch (Exception e)
		{ e.getMessage();
		  return null;
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
