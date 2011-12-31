

public class Test {
	public static void main(String[] args) {
//		String str = "风向：东南、网速：12米/秒";
////		System.out.println();
//		System.out.println(str.substring(0, str.indexOf("、")));
//		System.out.println(str.substring(str.indexOf("、") + 1));
		
		
		System.out.println(TimeUtil.addDay("2011-09-30", 2));
		System.out.println(Integer.parseInt(String.valueOf(TimeUtil.addDay("2011-09-28", 3).charAt(5))));
	}
}
