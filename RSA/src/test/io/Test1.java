package test.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 用于把OutputStream 转化为 InputStream。 适合于数据量不大，且内存足够全部容纳这些数据的情况。
 * 
 * @author 赵学庆 www.java2000.net
 * 
 */
public class Test1 {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    byte[] bs = new byte[] { 1, 2, 3, 4, 5 };  
	    out.write(bs); 
	
	    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
	    byte[] b = new byte[1024];   
	    int len = in.read(b);   
	    for (int i = 0; i < len; i++) {   
	      System.out.println(b[i]);   
	    }   
	  }
}
