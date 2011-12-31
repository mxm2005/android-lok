import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TTTTT {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException {
		Class cls = Class.forName("Test");
		Object obj = cls.newInstance();
		for (Method m : cls.getMethods()){
			if (m.getName().equals("co")){
				System.out.println(Integer.parseInt(String.valueOf(m.invoke(obj))));
			}
		}
//		cls.getMethod("co").invoke(obj);
	}
	
	class Test{
		public int co(){
//			System.out.println("dddddddddddddd");
			return 1;
		}
	}
}
