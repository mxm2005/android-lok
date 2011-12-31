package cn.itcast.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.domain.Person;
import cn.itcast.service.DOMPersonService;
import cn.itcast.service.PullPersonService;
import cn.itcast.service.SAXPersonService;

import android.test.AndroidTestCase;
import android.util.Log;

public class PersonServiceTest extends AndroidTestCase {
	private static final String TAG = "PersonServiceTest";
	
	public void testSAXgetPersons() throws Throwable{
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("person.xml");
		SAXPersonService service = new SAXPersonService();
		List<Person> persons = service.getPersons(inStream);
		for(Person person : persons){
			Log.i(TAG, person.toString());
		}
	}
	
	public void testDOMgetPersons() throws Throwable{
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("person.xml");
		List<Person> persons = DOMPersonService.getPersons(inStream);
		for(Person person : persons){
			Log.i(TAG, person.toString());
		}
	}
	
	public void testPullgetPersons() throws Throwable{
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("person.xml");
		List<Person> persons = PullPersonService.getPersons(inStream);
		for(Person person : persons){
			Log.i(TAG, person.toString());
		}
	}
	
	public void testSave() throws Throwable{
		File file = new File(this.getContext().getFilesDir(), "person.xml");
		FileOutputStream outStream = new FileOutputStream(file);
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(90, "zhangxiaoxiao", (short)13));
		persons.add(new Person(35, "liming", (short)23));
		persons.add(new Person(78, "laobi", (short)33));
		PullPersonService.save(persons, outStream);
	}
}
