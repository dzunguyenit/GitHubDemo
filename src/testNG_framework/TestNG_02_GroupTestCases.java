package testNG_framework;

import org.testng.annotations.Test;

public class TestNG_02_GroupTestCases {
	@Test(groups="customer")
	public void TC_01() {
		System.out.println("Test case 01");
	}

	@Test(groups="customer")
	public void TC_02() {
		System.out.println("Test case 02");
	}

	@Test(groups="customer")
	public void TC_03() {
		System.out.println("Test case 03");
	}

	@Test(groups="manager")
	public void TC_04() {
		System.out.println("Test case 04");
	}

	@Test(groups="driver")
	public void TC_05() {
		System.out.println("Test case 05");
	}

	@Test(groups="payment")
	public void TC_06() {
		System.out.println("Test case 06");
	}
}
