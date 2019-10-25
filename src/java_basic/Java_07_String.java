package java_basic;

public class Java_07_String {

	public static void main(String[] args) {
		String automation = "Automation Testing";
		int length = automation.length();
		System.out.println("Do dai cua chuoi =" + length);
		// 18

		String chuoi = "Automation Testing Tutorials";
		char kitu = chuoi.charAt(0);
		System.out.println("Ki tu = " + kitu);
		// A

		String testing = automation.concat("Tutorials");
		System.out.println("Noi chuoi = " + testing);
		// Automation Testing Tutorial

		boolean compareValue = testing.equals(automation);
		System.out.println("So sanh chuoi = " + compareValue);
		// false

		compareValue = automation.equals("Automation Testing");
		System.out.println("So sanh chuoi = " + compareValue);
		// true

		int index = testing.indexOf("Testing");
		System.out.println("Vi tri chuoi = " + index);
		// 11

		String subStringStart = testing.substring(index);
		System.out.println("Chuoi con = " + subStringStart);
		// Testing Tutorial
		
		String subStringStartToEnd = testing.substring(11,18);
		System.out.println("Chuoi con tu vi tri = " + subStringStartToEnd);
		//Testing
		
		String money = "$100.00";
		money = money.replace("$", "");
		System.out.println(money); //100.00
		double money_ = Double.parseDouble(money);
		System.out.println("Value Double = " + money_); //100.00
		
		String http = "https://tiki.vn/";
		http = http.replace("https","");
		http = http.replace(":","");
		http = http.replace("//","");
		http = http.replace("/","");
		System.out.println(http);
		//tiki.vn

		String page24 = " Viewing 24 of 1879 results";
		String page72 = " Viewing 72 of 1879 results";
		
		String[] subString = page24.trim().split(" ");
		for (int i = 0; i<subString.length; i++){
			System.out.println("Vi tri thu " + i + ": " + subString[i]);
			//0: Viewing
			//1: 24
			//2: of
			//3: 1879
			//4: results
		}
		System.out.println(extractNumberFromString(page24.trim(), 1)); //24
		System.out.println(extractNumberFromString(page24.trim(), 3)); //1879
		System.out.println(extractNumberFromString(page72.trim(), 1)); //72
		System.out.println(extractNumberFromString(page72.trim(), 3)); //1879
		
		String upper = testing.toUpperCase();
		System.out.println("In hoa = " + upper); 
		//AUTOMATION TESTING TUTORIALS
		
		String lower = testing.toLowerCase();
		System.out.println("In thuong = " + lower);
		//automation testing tutorials
		
		testing = " \t \n Automation Testing Tutorials \n ";
		System.out.println(testing); // \t - tab, \n - xuong dong
		String trim = testing.trim(); 
		System.out.println("Xoa space = " + trim); //Automation  Testing Tutorials
		boolean check = trim.equals("Automation Testing Tutorials");
		System.out.println("Check equal after trim = "+check); //true
		
		// int ---> String
		int number = 70;
		String value = String.valueOf(number);
		System.out.println("Value String = "+ value); //70
		
		//Cast (ép kiểu)
		value = number + "";
		System.out.println("Value String ep kieu = " +value); //70
		
		// String ---> int
		String price = "1000";
		int price_ = Integer.parseInt(price);
		System.out.println("Value Integer = "+ price_); //1000
		
		//String ---> Double
		double priceDouble = Double.parseDouble(price);
		System.out.println("Value Double = " + priceDouble);//1000.0
		
	
	}
public static int extractNumberFromString(String text, int number){
	
	String[] subString = text.split(" ");
	int result = Integer.parseInt(subString[number]);
	return result;
}
}