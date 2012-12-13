package z.s.test;

import java.util.List;

import z.s.test.bean.ContactInfo;
import z.s.test.service.T9Service;

import android.app.Application;
import android.content.Intent;

public class MyApplication extends Application
{

	private List<ContactInfo> ContactInfo;

	public List<ContactInfo> getContactInfo()
	{
		return ContactInfo;
	}

	public void setContactInfo(List<ContactInfo> contactInfo)
	{
		ContactInfo = contactInfo;
	}

	@Override
	public void onCreate()
	{
		System.out.println("¿ªÊ¼ÁË");
		Intent startService = new Intent(MyApplication.this, T9Service.class);
		startService(startService);
	}
}
