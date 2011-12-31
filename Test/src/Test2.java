public class Test2 {
	public static void main(String[] args) {
		java.util.GregorianCalendar c = new java.util.GregorianCalendar();
		int year = 2005;
		if (c.isLeapYear(year)) {
			System.out.println(year + " 是闰年");
		}
	}
}
