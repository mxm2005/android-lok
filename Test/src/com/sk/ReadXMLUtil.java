package com.sk;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLUtil {

	// 用于返回的密钥字符串
	private static String keyMessage;

	/***
	 * 根据xml文件路径，得到密钥
	 * 
	 * @throws Exception
	 */
	public static String getKey(String src) {
		try {
			keyMessage = getMessage(src, "encryptKey");
		} catch (Exception e) {
			System.out.println("出现异常：" + e.getMessage());
		}
		return keyMessage;
	}

	/**
	 * 公共方法
	 * 
	 * @param xmlsrc xml文件地址
	 * @param name  节点名称
	 * @return 该节点的值
	 * @throws Exception
	 */
	public static String getMessage(String xmlsrc, String name) throws Exception {
		String returnValue = null;
		// 得到DOM解析器的工厂实例
		DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
		// 从DOM工厂获得DOM解析器
		DocumentBuilder dombuilder = domfac.newDocumentBuilder();
		// 把要解析的XML文档转化为输入流，以便DOM解析器解析它,InputStream是一个接口
		InputStream is = new FileInputStream(xmlsrc);
		// 解析XML文档的输入流，得到一个Document,由XML文档的输入流得到一个org.w3c.dom.Document对象，以后的处理都是对Document对象进行的
		Document doc = dombuilder.parse(is);
		// 得到XML文档的根节点,在DOM中只有根节点是一个org.w3c.dom.Element对象
		Element root = doc.getDocumentElement();
		// 得到节点的子节点,这是用一个org.w3c.dom.NodeList接口来存放它所有子节点的
		NodeList nodeList = root.getChildNodes();
		if (nodeList != null) {
			int i = 0;
			for (i = 0; i < nodeList.getLength(); i++) {
				Node securityData = nodeList.item(i);
				if (securityData.getNodeType() == Node.ELEMENT_NODE) {
					Node node = null;
					for (node = securityData.getFirstChild(); node != null; node = node.getNextSibling()) {
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							if (node.getNodeName().equals(name)) {
								returnValue = node.getFirstChild().getNodeValue();// 是真正的值，
								// 这是因为DOM把<driver>com.mysql.jdbc.Driver</driver>也当作是两层结构的节点
							}
						}
					}
					node = null;
				}
				securityData = null;
			}
			i = 0;
		} else {
			throw new Exception("该" + xmlsrc + "对应的XML文档根节点下无子节点!");
		}
		return returnValue;
	}
}
