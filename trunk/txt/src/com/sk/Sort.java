package com.sk;

import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Sort
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("分类手机号码");
		frame.setSize(400, 400);
		frame.setLocation(100, 100);
		Button button1 = new Button("select txt file");
		final TextArea ta = new TextArea();
		frame.add(ta);
		frame.add(button1, "North");
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent arg0)
			{
				System.exit(0);
			}
		});
		frame.setVisible(true);
		button1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				FileReader fr = null;
				// 文件选择器以当前的目录打开
				JFileChooser jfc = new JFileChooser(".");
				jfc.showSaveDialog(new javax.swing.JFrame());
				// 获取当前的选择文件引用
				File savedFile = jfc.getSelectedFile();

				// 已经选择了文件
				if(savedFile != null)
				{
					// 读取文件的数据，可以每次以快的方式读取数据
					try
					{
						fr = new FileReader(savedFile);
						String str = "";
						StringBuffer mSb = new StringBuffer();
						StringBuffer lSb = new StringBuffer();
						StringBuffer tSb = new StringBuffer();
						StringBuffer xSb = new StringBuffer();
						List<String> ms = new ArrayList<String>();// 移动
						List<String> ls = new ArrayList<String>();// 联通
						List<String> ts = new ArrayList<String>();// 电信
						List<String> xs = new ArrayList<String>();// 非号码
						BufferedReader br = new BufferedReader(fr);
						LineNumberReader input = new LineNumberReader(br);
						for(;;)
						{
							str = input.readLine();
							if(str != null)
							{
								switch (sortNumber(str))
								{
								case 1:
									ts.add(str);
									break;
								case 2:
									ls.add(str);
									break;
								case 3:
									ms.add(str);
									break;
								case 4:
									xs.add(str);
									break;
								}
							}
							else
							{
								mSb.append("移动号码： " + "\n");
								lSb.append("联通号码： " + "\n");
								tSb.append("电信号码： " + "\n");
								xSb.append("非手机号码： " + "\n");
								for(String m : ms)
								{
									System.out.println("移动号码：" + m + ", ");
									mSb.append(m + "," + "\n");
								}
								System.out.println();
								System.out.println();
								System.out.println();
								System.out.println();
								for(String l : ls)
								{
									System.out.println("联通号码：" + l + ", ");
									lSb.append(l + "," + "\n");
								}
								System.out.println();
								System.out.println();
								System.out.println();
								System.out.println();
								for(String t : ts)
								{
									System.out.println("电信号码：" + t + ", ");
									tSb.append(t + "," + "\n");
								}
								System.out.println();
								System.out.println();
								System.out.println();
								System.out.println();
								for(String x : xs)
								{
									System.out.println("非手机号码：" + x + ", ");
									xSb.append(x + "," + "\n");
								}
								mSb.deleteCharAt(mSb.length() - 2);
								lSb.deleteCharAt(lSb.length() - 2);
								tSb.deleteCharAt(tSb.length() - 2);
								xSb.deleteCharAt(xSb.length() - 2);
								ta.setText(mSb.toString() + "\n" + lSb.toString() + "\n"
										+ tSb.toString() + "\n" + xSb.toString());
								break;
							}
						}
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		});
	}

	private static int sortNumber(String number)
	{
		String[] telecom = new String[] { "133", "153", "180", "189" };
		String[] link = new String[] { "130", "131", "132", "155", "156", "185", "186" };
		String[] monternet = new String[] { "134", "135", "136", "137", "138", "139", "147", "150",
				"151", "152", "157", "158", "159", "181", "182", "183", "187", "188" };
		int type = 4;
		for(String t : telecom)
		{
			if(t.equals(number.substring(0, 3)))
			{
				type = 1;
			}
		}
		for(String l : link)
		{
			if(l.equals(number.substring(0, 3)))
			{
				type = 2;
			}
		}
		for(String m : monternet)
		{
			if(m.equals(number.substring(0, 3)))
			{
				type = 3;
			}
		}
		return type;
	}
}
