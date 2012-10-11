package com.sk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author panhf2003
 * @version 2008/09/05,
 */

public class CsvFileUtil {

	/**
	 * 构造，禁止实例化
	 */
	private CsvFileUtil() {
	}

	public static void main(String[] args) {
		try {
			List<String[]> lists = readCsvFile("e://sms_20121010143330.csv");
			for (String[] str : lists) {
				System.out.println(str[0]);
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(CsvFileUtil.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CsvFileUtil.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	/**
	 * csv文件读取<BR/>
	 * 读取绝对路径为argPath的csv文件数据，并以List返回。
	 * 
	 * @param argPath
	 *            csv文件绝对路径
	 * @return csv文件数据（List<String[]>）
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String[]> readCsvFile(String argPath)
			throws FileNotFoundException, IOException {
		CsvFileUtil util = new CsvFileUtil();
		File cvsFile = new File(argPath);
		List<String[]> list = new ArrayList<String[]>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(cvsFile);
			bufferedReader = new BufferedReader(fileReader);
			String regExp = util.getRegExp();

			String strLine = "";
			String str = "";
			while ((strLine = bufferedReader.readLine()) != null) {
				Pattern pattern = Pattern.compile(regExp);
				Matcher matcher = pattern.matcher(strLine);
				List<String> listTemp = new ArrayList<String>();
				while (matcher.find()) {
					str = matcher.group();
					str = str.trim();
					if (str.endsWith(",")) {
						str = str.substring(0, str.length() - 1);
						str = str.trim();
					}
					if (str.startsWith("\"") && str.endsWith("\"")) {
						str = str.substring(1, str.length() - 1);
						if (util.isExisted("\"\"", str)) {
							str = str.replaceAll("\"\"", "\"");
						}
					}
					if (!"".equals(str)) {
						System.out.print(str + " ");
						listTemp.add(str);
					}
				}
				// test
				System.out.println();
				list.add((String[]) listTemp.toArray(new String[listTemp.size()]));
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return list;
	}

	/**
	 * csv文件做成<BR/>
	 * 将argList写入argPath路径下的argFileName文件里。
	 * 
	 * @param argList 要写入csv文件的数据（List<String[]>）
	 * @param argPath csv文件路径
	 * @param argFileName csv文件名
	 * @param isNewFile 是否覆盖原有文件
	 * @throws IOException
	 * @throws Exception
	 */
	public static void writeCsvFile(List<String[]> argList, String argPath,
			String argFileName, boolean isNewFile) throws IOException,
			Exception {
		CsvFileUtil util = new CsvFileUtil();
		// 数据check
		if (argList == null || argList.size() == 0) {
			throw new Exception("没有数据");
		}
		for (int i = 0; i < argList.size(); i++) {
			if (!(argList.get(i) instanceof String[])) {
				throw new Exception("数据格式不对");
			}
		}
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		String strFullFileName = argPath;
		if (strFullFileName.lastIndexOf("\\") == (strFullFileName.length() - 1)) {
			strFullFileName += argFileName;
		} else {
			strFullFileName += "\\" + argFileName;
		}
		File file = new File(strFullFileName);
		// 文件路径check
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			if (isNewFile) {
				// 覆盖原有文件
				fileWriter = new FileWriter(file);
			} else {
				// 在原有文件上追加数据
				fileWriter = new FileWriter(file, true);
			}
			bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < argList.size(); i++) {
				String[] strTemp = (String[]) argList.get(i);
				for (int j = 0; j < strTemp.length; j++) {
					if (util.isExisted("\"", strTemp[j])) {
						strTemp[j] = strTemp[j].replaceAll("\"", "\"\"");
						bufferedWriter.write("\"" + strTemp[j] + "\"");
					} else if (util.isExisted(",", strTemp[j])
							|| util.isExisted("\n", strTemp[j])
							|| util.isExisted(" ", strTemp[j])
							|| util.isExisted("??", strTemp[j])) {
						bufferedWriter.write("\"" + strTemp[j] + "\"");
					} else {
						bufferedWriter.write(strTemp[j]);
					}
					if (j < strTemp.length - 1) {
						bufferedWriter.write(",");
					}
				}
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}

	/**
	 * @param argChar
	 * @param argStr
	 * @return
	 */
	private boolean isExisted(String argChar, String argStr) {

		boolean blnReturnValue = false;
		if ((argStr.indexOf(argChar) >= 0)
				&& (argStr.indexOf(argChar) <= argStr.length())) {
			blnReturnValue = true;
		}
		return blnReturnValue;
	}

	/**
	 * 正则表达式。
	 * @return 匹配csv文件里最小单位的正则表达式。
	 */
	private String getRegExp() {
		StringBuffer strRegExps = new StringBuffer();
		strRegExps.append("\"((");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*[,\\n 　])*(");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*\"{2})*)*");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*\"[ 　]*,[ 　]*");
		strRegExps.append("|");
		strRegExps.append(SPECIAL_CHAR_B);
		strRegExps.append("*[ 　]*,[ 　]*");
		strRegExps.append("|\"((");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*[,\\n 　])*(");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*\"{2})*)*");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*\"[ 　]*");
		strRegExps.append("|");
		strRegExps.append(SPECIAL_CHAR_B);
		strRegExps.append("*[ 　]*");
		return strRegExps.toString();
	}

	private static final String SPECIAL_CHAR_A = "[^\",\\n 　]";

	private static final String SPECIAL_CHAR_B = "[^\",\\n]";
}