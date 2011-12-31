public class T {
	public static void main(String[] args) {
//		a = a.subStirng(a.index("s") + 1， a.length());
//		String b = a.subtring(0, a.index("s"));

//		String a = "LSP=1,\"1b,33,a28d2d\",\"禽兽\"LSP=2,\"9463,d1,dbe319\",\"GT-S5570\"LSP=3,\"1a,dc,d2775d\",\"刘欣芳\"";
//		a = a.substring(a.indexOf("LSP=") + 1, a.length());
//		String b = a.substring(0, a.indexOf("LSP="));
//		System.out.println(b);
//		System.out.println(a.lastIndexOf("LSP"));
		
//		System.out.println(a.indexOf("LSP=2"));
//		System.out.println(a.substring(0, a.indexOf("LSP=2")));
//		System.out.println(a.substring(a.indexOf("LSP=2"), a.indexOf("LSP=3")));
//		System.out.println(a.substring(a.indexOf("LSP=3")));
		
		 String a = "LSP=1,\"1b,33,a28d2d\",\"禽兽\"LSP=2,\"9463,d1,dbe319\",\"GT-S5570\"LSP=3,\"1a,dc,d2775d\",\"刘欣芳\"";
         String[] ary = a.split("=");
         for (int i = 1; i < ary.length; i++) {
//        	 System.out.println(ary[i].replace("LSP", ""));
        	 System.out.println("id: " + ary[i].replace("LSP", "").toString().substring(0, ary[i].indexOf(",")));
        	 System.out.println("address: " + ary[i].replace("LSP", "").substring(ary[i].indexOf(",") + 1, ary[i].lastIndexOf(",")).replace("\"", ""));
        	 System.out.println("name: " + ary[i].replace("LSP", "").substring(ary[i].lastIndexOf(",") + 1).replace("\"", ""));
         }
	}
}
