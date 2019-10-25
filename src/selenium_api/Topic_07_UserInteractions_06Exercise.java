package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_UserInteractions_06Exercise {
	Actions action;
	WebDriver driver;
	

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver ();
		System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
		driver=new ChromeDriver();
		action = new Actions(driver); 
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01_MoveMouseToElement(){
		//Step 01 - Truy cập vào trang: http://www.myntra.com/
		driver.get("http://www.myntra.com/");
		//Step 02 - Hover chuột vào Menu để login
		WebElement hoverMenu = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		action.moveToElement(hoverMenu).perform();
		//Step 03 - Chọn Login button
		WebElement loginBtn = driver.findElement(By.xpath("//a[text()='login']"));
		action.click(loginBtn).perform();
		
		//Step 04 - Verify Login form được hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());

	}
	@Test
	public void TC_02_ClickAndHold(){
		// click chọn từ 1---> 4
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> listAllItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println(listAllItems.size());
		
		action.clickAndHold(listAllItems.get(0)).moveToElement(listAllItems.get(3)).release().perform();
		
		List<WebElement> selectedItems = driver.findElements(By.xpath("//li[contains(@class,'ui-selectee ui-selected')]"));
		System.out.println(selectedItems.size());
		Assert.assertEquals(selectedItems.size(), 4);
		
		//random 1-4-6-8
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> AllItemsList = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println(AllItemsList.size());
		
		action.keyDown(Keys.LEFT_CONTROL).click(AllItemsList.get(0)).click(AllItemsList.get(3)).click(AllItemsList.get(5)).click(AllItemsList.get(7)).keyUp(Keys.LEFT_CONTROL).perform();
		
		List <WebElement> randomItems = driver.findElements(By.xpath("//li[contains(@class,'ui-selectee ui-selected')]"));
		System.out.println(randomItems.size());
		Assert.assertEquals(randomItems.size(), 4);
		
	}
	@Test
	public void TC_03_DoubleClick(){
		//Step 01 - Truy cập vào trang: http://www.seleniumlearn.com/double-click
		driver.get("http://www.seleniumlearn.com/double-click");
		//Step 02 - Double click vào element: Double-Click Me!
		WebElement doubleClick = driver.findElement(By.xpath("//button[@type='button' and text()='Double-Click Me!']"));
		action.doubleClick(doubleClick).perform();
		
		//Step 03 - Verify text trong alert được hiển thị: 'The Button was double-clicked.'
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		Assert.assertEquals(alertMessage, "The Button was double-clicked.");
		//Step 04 - Accept Javascript alert
		alert.accept();
		
		
	}
	@Test
	public void TC_04_RightClick(){
		//Truy cập vào trang: http://swisnl.github.io/jQuery-contextMenu/demo.html
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//Right click vào element: right click me
		WebElement rightclick=driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightclick).perform();
		
		//Verify element quit (visible + not hover)
		WebElement quitBtn = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and not(contains(@class,'context-menu-hover'))]"));
		Assert.assertTrue(quitBtn.isDisplayed());
		
		//hover to quit
		action.moveToElement(quitBtn).perform();
		
		//Verify element Quit (visible + hover)
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and (contains(@class,'context-menu-hover'))]")).isDisplayed());
		
		//Click chọn Quit
		action.click(quitBtn).perform();
				
		//Accept Javascript alert
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
		
		}
	@Test 
	public void TC_05_DragAndDrop(){
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		//Step 02 - Kéo hình tròn nhỏ vào hình tròn lớn
		WebElement cicleSource = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement cicleTarget = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(cicleSource, cicleTarget).perform();		
		//Step 03 - Verify message đã thay đổi: You did great!
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='You did great!']")).isDisplayed());
		
		
		//Step 01 - Truy cập vào trang: http://jqueryui.com/resources/demos/droppable/default.html
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		
		//Step 02 - Kéo hình chữ nhật: Drag me to my target vào hình Drop here
		WebElement dragSource = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement dropTarget = driver.findElement(By.xpath("//div[@id='droppable']"));
		action.dragAndDrop(dragSource, dropTarget).perform();
		
		//Step 03 - Verify message đã thay đổi: Dropped!
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droppable']/p[text()='Dropped!']")).isDisplayed());
	
	
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
