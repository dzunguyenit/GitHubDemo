package java_basic;

public class Java_DoExercise {

	public static void main(String[] args) {
		String chuoi = "Automation 01 Testing 345 Tutorials 0nline 123456";
		char kyTu = 'A';
		int count = 0;
		for (int i = 0; i < chuoi.length(); i++) {
			if (Character.toUpperCase(chuoi.charAt(i)) == kyTu)
				count++;
			}
		

		System.out.println("Số lượng của ký tự " + kyTu + " trong chuỗi " + chuoi + " = " + count);

		boolean contains_ = chuoi.contains("Testing");
		System.out.println("Ket qua contains = " + contains_);

		boolean startsWith_ = chuoi.startsWith("Automation");
		System.out.println("Ket qua starts with = " + startsWith_);

		boolean endWith_ = chuoi.endsWith("Online");
		System.out.println("Ket qua end with = " + endWith_);

		int index = chuoi.indexOf("Tutorials");
		System.out.println("Vi tri chuoi 'Tutorials' = " + index);

		String replaceString = chuoi.replace("Online", "Offline");
		System.out.println("Ket qua replace = " + replaceString);

		count = 0;
		for (int i = 0; i < chuoi.length(); i++) {
			if (Character.isDigit(chuoi.charAt(i)))
				count++;
		}
		System.out.println("Ket qua = " + count);
	}
}
