package com.orgcent.ftp;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.ftpserver.FtpServer;
import org.apache.http.conn.util.InetAddressUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.storage.IMountService;
import android.widget.Button;
import android.widget.TextView;


public class FtpServerActivity extends Activity {

	private FtpServer mFtpServer;
	
	private TextView tv;
	private Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
//		try { 
//			FtpServerFactory serverFactory= new FtpServerFactory(); 
//			ListenerFactory factory=new ListenerFactory(); 
//			factory.setPort(21); //注释掉的代码是使用文件加密传输用的 
//			// SSLCon ssl = new SSLCon(); 
//			// KeyStore k=KeyStore.getInstance(“jks”); 
//			// k.load(null); 
//			// File keystoreFile=new File(“D:\\up\\ftpserver.jks”); 
//			// ssl.setKeystoreFile(keystoreFile); 
//			// ssl.setKeystorePassword(“password”); 
//			// SslConfiguration sc=ssl.createSslConfiguration(); 
//			// Moxifloxacin Dosage Renal Failure factory.setSslConfiguration(sc); 
//			// factory.setServerAddress(“127.0.0.1″); 
//			// factory.setImplicitSsl(true); 
//			serverFactory.addListener("default", factory.createListener()); 
//			//serverFactory.setUserManager(ftpService.userManager()); 
//			FtpServer server = serverFactory.createServer();
//		server.start();
//		} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();	
//		}
//		}
		
		
//		String host = "202.104.131.3";
//		int port = 2221;
//
//		ClientSocketHandler handler = new ClientSocketHandler();
//		InetSocketAddress remoteAddr = new InetSocketAddress(host, port);
//
//		NioSocketConnector connector = new NioSocketConnector();
//		connector.setHandler(handler);
//		connector.setDefaultRemoteAddress(remoteAddr);
//		connector.setConnectTimeoutMillis(60);
//		connector.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 1000);
//
//		ConnectFuture future = connector.connect();
//		future.awaitUninterruptibly();
////		if (future.isConnected()) {
//			System.out.println("connect success");
//			FtpServerFactory serverFactory = new FtpServerFactory();
//			mFtpServer = serverFactory.createServer();
//			PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
//			UserManager um = userManagerFactory.createUserManager();
//			BaseUser user = new BaseUser();
//			user.setName("jinhua");
//			user.setPassword("jinhua");
//			user.setHomeDirectory("/mnt/sdcard/");
//			
////			final ListenerFactory lfactory = new ListenerFactory();
////			lfactory.setServerAddress("127.0.0.1"); 
//			
//			user.setMaxIdleTime(60000);
//			List<Authority> authorities = new ArrayList<Authority>();
//			authorities.add(new ConcurrentLoginPermission(500, 10));
//			authorities.add(new WritePermission());
//			user.setAuthorities(authorities);
//			
//			try {
//				um.save(user);
//			} catch (FtpException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			final ListenerFactory factory = new ListenerFactory();
//			// set the port of the listener
//			factory.setPort(2221);
//			// replace the default listener
//			Listener l = factory.createListener();
//			serverFactory.addListener("default", l);
//			serverFactory.setUserManager(um);
//			// start the server
//			try {
//				mFtpServer.start();
//			} catch (FtpException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////		} else {
////			System.out.println("connect failure");
////		}
//
//		tv = (TextView) findViewById(R.id.tv);
//		button = (Button) findViewById(R.id.button);
//		button.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
////				String address = factory.getServerAddress();
////				List<InetAddress> lists = factory.getBlockedAddresses();
//				System.out.println(getLocalIpAddress());
////				byte[] b = lists.get(0).getAddress();
////				String bb = new String(b);
////				tv.setText(bb);
////				System.out.println(bb + "对外IP");
//			}
//		});
		
		String path = Environment.getExternalStorageDirectory().getAbsolutePath();
		System.out.println(path);
		formatSD(path);
//		formatSD();
		
	}
	
	public void formatSD() {
		String SDstatus = Environment.getExternalStorageState();
		 try
	        {
	         Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);//利用反射得到ServiceManager类中的getService方法
	         IBinder binder = (IBinder) method.invoke(null, "mount");
	         Class<?> mIMountService = Class.forName("android.os.storage.IMountService");
	         Class<?>[] classes = mIMountService.getClasses();//获取IMountService的所有内部类
	         Class<?> mStub = classes[0];//获取IMountService的内部类Stub
	         Method asInterface = mStub.getMethod("asInterface", new Class[]{IBinder.class});//获取Stub的asInterface(IBinder binder)方法，
	         Object iMountService = asInterface.invoke(classes[0], new Object[]{binder});//通过asInterface(IBinder binder)方法获得IMountService类的一个实例对象mIMountService
	         Class<?> mIMountService1 = iMountService.getClass();//通过实例对象获取Class对象
	         Method unmountVolume = mIMountService1.getMethod("unmountVolume", new Class[]{String.class, boolean.class});//获取反挂载方法
	         Method formatVolume = mIMountService1.getMethod("formatVolume", new Class[]{String.class});//获取格卡方法
	         Object result = formatVolume.invoke(iMountService, SDstatus);//格卡
	         //调试时发现格卡这条代码没有执行，直接报异常了；
	         System.out.println((Integer)result);
	         
	        }
	        catch(Exception e)
	        {
	         e.printStackTrace();
	        }                         
	}
	
	
	private void formatSD(final String sDStateString) {
		// 利用反射得到ServiceManager类中的getService方法
		try {
			Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
			IBinder binder = (IBinder) method.invoke(null, "mount");
			final IMountService iMountService = IMountService.Stub.asInterface(binder);
			iMountService.unmountVolume(sDStateString, true);//卸载sdcard
			new Thread() {
				public void run() {
					// 当卸载完sdcard后才能格式化sdcard 给sdcard卸载的时间时间不能超过5秒 超过5秒后
					// iMountService.formatVolume(sDStateString);不起作用！
					try {
						sleep(4000);
						iMountService.formatVolume(sDStateString);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取ipv4地址
	 * @return
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(inetAddress.getHostAddress())) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}