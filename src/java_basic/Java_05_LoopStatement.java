package java_basic;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Java_05_LoopStatement {

	public static void main(String[] args) {
		WebDriver driver;
		List<WebElement> links;
		int i;

		driver = new FirefoxDriver();
		driver.get("https://tiki.vn/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		links = driver.findElements(By.xpath("//a"));

		int number = links.size();
		System.out.println("All link = " + number);

		for (i = 0; i <= number - 1; i++) {
			System.out.println("Link in for loop = [" + i + "] = " + links.get(i).getAttribute("href"));
		}

		for (WebElement link : links) {
			System.out.println("Link in for - each loop = " + link.getAttribute("href"));
		}

		i = 0;
		while (i < number) {
			System.out.println("Link in While = [" + i + "] = " + links.get(i).getAttribute("href"));
			i++;

		}
		i = 0;
		do {
			System.out.println("Link in Do While = [" + i + "] = " + links.get(i).getAttribute("href"));
			i++;
		} while (i<number);

		driver.quit();
	}

}
