package com.sk.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
	public static void main(String[] args) {
		System.out.println("abc".matches("..."));//一点.代表一个字符
		System.out.println("fdlfj34l3l43j4;101.39;fs".replaceAll("\\d", "-"));//\d是表示一个数字
		
		Pattern p = Pattern.compile("[a-z]{3}");
		Matcher m = p.matcher("ddf");
		System.out.println(m.matches());
		
		System.out.println("ddf".matches("[a-z]{3}"));
		
		System.out.println("--------------------------------");
		// * + ?
		System.out.println("a".matches("."));
		System.out.println("aa".matches("aa"));
		System.out.println("aaaa".matches("a*"));//a出现0个或多个
		System.out.println("aaaa".matches("a+"));//a出现1个或多个
		System.out.println("".matches("a*"));
		System.out.println("aaaa".matches("a?"));//a出现1个或者没有
		System.out.println("".matches("a?"));
		System.out.println("a".matches("a?"));
		System.out.println("32134343431566".matches("\\d{3,100}"));//{n,} 至少出现n次
		System.out.println("192.168.0.aaa".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));
		System.out.println("192".matches("[0-2][0-9][0-9]"));
		
		System.out.println("--------------------------------");
		//范围 []里只为一个字节   ^为除乜之外的
		System.out.println("a".matches("[abc]"));
		System.out.println("a".matches("[^abc]"));
		
		//该三种方法相同
		System.out.println("A".matches("[a-zA-Z]"));
		System.out.println("A".matches("[a-z]|[A-Z]"));
		System.out.println("A".matches("[a-z[A-Z]]"));
		
		System.out.println("R".matches("[A-Z&&[RFG]]"));
		
		System.out.println("--------------------------------");
		//认识\s \w \d \				\d代表[0-9]   \D代表[^0-9]   \s代表空白字符[ \t\n\x0B\f\r]   \S代表[^\s]   \w代表[a-zA-Z_0-9]   \W代表[^\w]
		System.out.println(" \n\r\t".matches("\\s{4}"));
		System.out.println(" ".matches("\\S"));
		System.out.println("a_8".matches("\\w{3}"));
		System.out.println("abc888&^%".matches("[a-z]{1,3}\\d+[&^#%]+"));
		System.out.println("\\".matches("\\\\"));
		
		System.out.println("--------------------------------");
		//POSIX style
		System.out.println("a".matches("\\p{Lower}"));
		//boundary 边界匹配
		System.out.println("hello sir".matches("^h.*"));//^位于[]里头的第一个为取反，位于[]外面是开头
		System.out.println("hello sir".matches(".*ir$"));//$为以ir结尾
		System.out.println("hello sir".matches("^h[a-z]{1,3}o\\b.*"));
		System.out.println("hellosir".matches("^h[a-z]{1,3}o\\b.*"));//\b为单词为界即为空格
		//white lines
		System.out.println(" \n".matches("^[\\s&&[^\\n]]*\\n$"));//开头是空白符，但不是换行符。。。并且以换行符结束
		
		System.out.println("--------------------------------");
		//email
		System.out.println("aaaaaaafdfdfsajfd-fdaf@qq.com".matches("[\\w[.-]]+[@][\\w[.-]]+\\.[\\w]+"));
		
		System.out.println("--------------------------------");
		Pattern pp = Pattern.compile("\\d{3,5}");
		Matcher mm = pp.matcher("123-435-322-32142-34");
		System.out.println(mm.matches());//matches()跟find()方法有冲突的
		mm.reset();
		System.out.println(mm.find());
		System.out.println(mm.start() + "-" + mm.end());
		System.out.println(mm.find());
		System.out.println(mm.start() + "-" + mm.end());
		System.out.println(mm.find());
		System.out.println(mm.start() + "-" + mm.end());
		System.out.println(mm.find());
		System.out.println(mm.start() + "-" + mm.end());
		System.out.println(mm.find());
//		System.out.println(mm.start() + "-" + mm.end());//能找到才能输出
		System.out.println(mm.find());
		
		System.out.println(mm.lookingAt());//lookingAt()每次都从第一个字符开始找，所以为true
		System.out.println(mm.lookingAt());
		
		System.out.println("--------------------------------");
		//replacement
		Pattern ppp = Pattern.compile("java", Pattern.CASE_INSENSITIVE);//case_insensitive大小写不敏感的
		Matcher mmm = ppp.matcher("java JAVA i love javajavaJavajAVA");
		while (mmm.find()) {
			System.out.println(mmm.group());
		}
		System.out.println(mmm.replaceAll("JAVA"));
		
		System.out.println("--------------------------------");
		//强大的替换功能
		Pattern pppp = Pattern.compile("java", Pattern.CASE_INSENSITIVE);//case_insensitive大小写不敏感的
		Matcher mmmm = pppp.matcher("java JAVA i love javajavaJavajAVAaafdf");
		StringBuffer buf = new StringBuffer();
		int i = 0;
		while (mmmm.find()) {
			i++;
			if (i%2 == 0) {
				mmmm.appendReplacement(buf, "java");
			} else {
				mmmm.appendReplacement(buf, "JAVA");
			}
		}
		mmmm.appendTail(buf);
		System.out.println(buf);
		
		System.out.println("--------------------------------");
		//group 分组
		Pattern ppppp = Pattern.compile("(\\d{3,5})([a-z]{2})");//()小括号为分组
		String s = "12aa-3213df-13323kl-3232";
		Matcher mmmmm = ppppp.matcher(s);
		while (mmmmm.find()) {
			System.out.println(mmmmm.group(2));//数左小括号，第一个左小括号为第一组，依此类推
		}
		
	}
}
