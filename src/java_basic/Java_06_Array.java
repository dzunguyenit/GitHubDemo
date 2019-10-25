package java_basic;

public class Java_06_Array {

	public static void main(String[] args) {
		
		/* ============== init array ==========*/
		String [] testingPart = new String[4];
		testingPart[0] = "Automation Testing";
		testingPart[1] = "Manual Testing";
		testingPart[2] = "Performance Testing";
		testingPart[3] = "Security Testing";
		
		String[] testing = {"Automation Testing", "Manual Testing", "Performance Testing","Stress Testing", "Security Testing"};
				
		/* ========= Access value in Array =======*/
		int iLength = testing.length;
		System.out.println("Do dai cua Array = " + iLength);
		
		String auto = testing[0];
		System.out.println("Gia tri dau tien = " + auto);
		
		String security = testing[iLength - 1];
		System.out.println("Gia tri cuoi cung = " + security);
		
		for (int i = 0 ;i<iLength; i++){
			System.out.println("Phan tu thu [" + i + "] = "+ testing[i]);
		}
		
		for(String test: testing)
		{
			System.out.println("Gia tri = "+ test);
		}

		/* ========= Parse Array to Method =======*/
		parse_Array_To_Method(testingPart);
		System.out.println("============");
		
		parse_Array_To_Method(testing);
		System.out.println("============");
		
		/* ======== return array from method =========*/
		String[]newArray = return_Array_To_Method();
		for(String array: newArray){
			System.out.println("In tat ca gia tri trong mang = " + array);
		}
	}
	public static void parse_Array_To_Method(String [] array)
	{
		for (int i = 0; i<array.length; i++){
			System.out.println("In tat ca gia tri trong mang = "+ array[i]);
			
		}
	}
	
	public static String[] return_Array_To_Method(){
		String []aArray = {"Developer", "Tester", "Business Analysist", "Product Owner"};
		return aArray;
		
	}
}
