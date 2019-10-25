package java_basic;

public class Java_09_SystemProperties {

	public static void main(String[] args) {
		String root = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		System.out.println("root = " + root);
		System.out.println(System.getProperty("java.home"));
		System.out.println(System.getProperty("java.version"));
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("os.name"));
	}

}
