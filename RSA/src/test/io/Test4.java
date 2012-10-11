package test.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.Ostermiller.util.CircularByteBuffer;

/**
 * 用于把OutputStream 转化为 InputStream。
 * <p>
 * 使用CircilarBuffer 辅助类 <br>
 * 下载地址为 <A href="http://ostermiller.org/utils/download.html
 * http://ostermiller.org/utils/download.html<br>
 * 介绍地址为 http://ostermiller.org/utils/CircularBuffer.html
 * </p>
 * 
 * @author 赵学庆 www.java2000.net
 */
public class Test4 {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// 缓冲所有数据的例子，不使用多线程
		CircularByteBuffer cbb = new CircularByteBuffer(CircularByteBuffer.INFINITE_SIZE);
//		OutputStreamClass4.putDataOnOutputStream(cbb.getOutputStream());
//		InputStreamClass4.processDataFromInputStream(cbb.getInputStream());
		
		byte[] bs = new byte[] { 1, 2, 3, 4, 5 };
		cbb.getOutputStream().write(bs);
		
		byte[] b = new byte[1024];
		int len = cbb.getInputStream().read(b);
		for (int i = 0; i < len; i++) {
			System.out.println(b[i]);
		}
	}
}

class OutputStreamClass4 {
	public static void putDataOnOutputStream(OutputStream out)
			throws IOException {
		byte[] bs = new byte[] { 1, 2, 3, 4, 5 };
		out.write(bs);
	}
}

class InputStreamClass4 {
	public static void processDataFromInputStream(InputStream in)
			throws IOException {
		byte[] bs = new byte[1024];
		int len = in.read(bs);
		for (int i = 0; i < len; i++) {
			System.out.println(bs[i]);
		}
	}
}
