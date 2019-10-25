package java_basic;

public class Java_04_ConditionStatement {

	public static void main(String[] args) {
		/* ========== if ========= */
		boolean value = true;
		if (value = true) {
			System.out.println("Value =" + value);
		}
		System.out.println("===========");

		/* ======= if - else ========== */
		int diem = 10;
		if (diem == 10) {
			System.out.println("Hoc sinh xuat sac");
		} else
		{
			System.out.println("Hoc sinh gioi");

		}
	/* ======= switch - case ===============*/
	
	String browser_= "Safari";
	switch (browser_){
	case "Chrome":
		System.out.println("Khoi tao trinh duyet Chrome");
		break;
		
	case "Firefox":
		System.out.println("Khoi tao trinh duyet Firefox");
		break;
		
	case "ie":
		System.out.println("Khoi tao trinh duyet IE");
		break;
		
	default:
		System.out.println("Khoi tao trinh duyet Safari");
		break;
		
	}
	System.out.println("Khong khoi tao trinh duyet nao");

	}
	
}
