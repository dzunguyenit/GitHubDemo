package java_basic;

public class Java_08_RestParameter {

	public static void main(String[] args) {
		String locator_00 = "//div[text()='New Customer']";
		String locator_01 = "//div[text()='%s']";
		String locator_02 = "//div[text()='%s']//div[text()='%s']";
		String locator_03 = "//div[text()='%s']//div[text()='%s']//div[text()='%s']";
		String locator_04 = "//div[text()='%s']//div[text()='%s']//div[text()='%s']//div[text()='%s']";

		clickToElement(locator_00);
		clickToElement(locator_01);
		clickToElement(locator_02);
		clickToElement(locator_03);
		clickToElement(locator_04);
		
	}

	public static void clickToElement(String locator, String...values){
		locator = String.format(locator,(Object[]) values);
		System.out.println(locator);
	}
}
