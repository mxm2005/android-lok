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
public class Test3 {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// 使用CircularByteBuffer
		final CircularByteBuffer cbb = new CircularByteBuffer();
		// 启动线程，让数据产生者单独运行
		new Thread(new Runnable() {
			public void run() {
				try {
					OutputStreamClass3.putDataOnOutputStream(cbb
							.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		// 数据使用者处理数据
		// 也可以使用线程来进行并行处理
		InputStreamClass3.processDataFromInputStream(cbb.getInputStream());
	}
}

class OutputStreamClass3 {
	public static void putDataOnOutputStream(OutputStream out)
			throws IOException {
		byte[] bs = new byte[2];
		for (int i = 0; i <= 100; i++) {
			bs[0] = (byte) i;
			bs[1] = (byte) (i + 1);
			// 测试写入字节数组
			out.write(bs);
			out.flush();
			try {
				// 等待0.1秒
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class InputStreamClass3 {
	public static void processDataFromInputStream(InputStream in) {
		byte[] bs = new byte[1024];
		int len;
		// 读取数据，并进行处理
		try {
			while ((len = in.read(bs)) != -1) {
				for (int i = 0; i < len; i++) {
					System.out.println(bs[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
