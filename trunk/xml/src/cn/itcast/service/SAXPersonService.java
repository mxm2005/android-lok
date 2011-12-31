package cn.itcast.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cn.itcast.domain.Person;
/**
 * 采用SAX解析xml文件
 */
public class SAXPersonService {

	public List<Person> getPersons(InputStream inStream) throws Exception{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		PersonHandler handler = new PersonHandler();
		parser.parse(inStream, handler);
		return handler.getPersons();
	}
	
	private final class PersonHandler extends DefaultHandler{
		private List<Person> persons;
		private Person person = null;
		private String tag = null;
		
		public List<Person> getPersons() {
			return persons;
		}

		@Override
		public void startDocument() throws SAXException {
			persons = new ArrayList<Person>();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			if("person".equals(localName)){
				person = new Person();
				person.setId(new Integer(attributes.getValue(0)));
			}
			tag = localName;
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if(tag!=null){
				String data = new String(ch, start, length);
				if("name".equals(tag)){					
					person.setName(data);
				}else if("age".equals(tag)){
					person.setAge(new Short(data));
				}				
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			if("person".equals(localName)){
				persons.add(person);
				person = null;
			}
			tag = null;
		}
		
	}
}
