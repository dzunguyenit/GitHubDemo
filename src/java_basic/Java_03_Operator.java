package java_basic;

public class Java_03_Operator {

	public static void main(String[] args) {
		/* ============ Assign ============ */
		int time = 10;
		String name = "abc";
		boolean value = true;
		System.out.println(time);
		System.out.println(name);
		System.out.println(value);

		/* ======== Math ============ */
		int a, b = 10, c = 5;
		a = b + c;
		System.out.println("Cong = " + a);

		a = b - c;
		System.out.println("Tru = " + a);
		
		a = b*c;
		System.out.println("Nhan = " + a);

		a = b / c;
		System.out.println("Chia lay nguyen = " + a);

		a = b % c;
		System.out.println("Chia lay du = " + a);

		b++;
		System.out.println("Tang len 1 = " + b);

		c--;
		System.out.println("Giam xuong 1 = " + c);

		/* =========== Relation > < >= <= == != (true/false) ========== */
		int ten = 10;
		int twenty = 20;
		int thirty = 30;
		
		System.out.println("Lon hon = " + (ten > twenty));
		System.out.println("Lon hon or bang = " + (thirty >= twenty));
		System.out.println("Nho hon = " + (thirty < twenty));
		System.out.println("Nho hon or bang = " + (thirty <= twenty));
		System.out.println("Bang bang = " + (thirty == twenty + ten));
		System.out.println("Khac bang = " + (thirty != twenty + ten));
		
		/*============ Logic(&&,||, !)================*/
		boolean value1 = true;
		boolean value2 = false;
		System.out.println("Ca hai deu dung = " + (value1 && value2));
		System.out.println("Mot trong 2 dung = " + (value1 || value2));
		System.out.println("Phu dinh = " + (!value2));
		
		
		/*============= Condition ===============*/
		int first = 10;
		int second = 20;
		int third = 30;
		boolean bValue;
		int iValue;
		
		bValue = (third == first + second)? true: false;
		System.out.println("Gia tri = " + bValue);
		
		iValue = (third == second + first)? 50:100;
		System.out.println("Ket qua = " + iValue);
		
		iValue = (!(third == second + first))? 50:100;
		System.out.println("Phu dinh cua ket qua = " + iValue);
		

	}

}
