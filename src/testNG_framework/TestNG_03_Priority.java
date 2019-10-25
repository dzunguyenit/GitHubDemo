package testNG_framework;

import org.testng.annotations.Test;

public class TestNG_03_Priority {
	@Test(groups="customer",priority= 4 )
	public void TC_01() {
		System.out.println("Test case 01");
	}

	@Test(groups="customer", priority= 2)
	public void TC_02() {
		System.out.println("Test case 02");
	}

	@Test(groups="customer",priority= 1)
	public void TC_03() {
		System.out.println("Test case 03");
	}

	@Test(groups="manager")
	public void TC_04() {
		System.out.println("Test case 04");
	}

	@Test
	public void TC_05() {
		System.out.println("Test case 05");
	}
	@Test
	public void TC_06() {
		System.out.println("Test case 06");
	}

}
