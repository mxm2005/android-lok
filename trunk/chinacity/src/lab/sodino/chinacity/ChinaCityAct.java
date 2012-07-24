package lab.sodino.chinacity;

import java.util.Hashtable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * @author Sodino E-mail:sodinoopen@hotmail.com
 * @version Time：2011-8-20 下午08:41:25
 */
public class ChinaCityAct extends Activity implements
		AdapterView.OnItemSelectedListener {
	private ChinaAlphabetComparator comparator;
	private Spinner spinnerProvince;
	private Spinner spinnerCity;
	private Spinner spinnerRegion;
	private TextView txtInfo;
	private Hashtable<String, Hashtable<String, String[]>> hashtable;
	private String[] arrProvince, arrCity, arrRegion;
	private String province, city, region;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		comparator = new ChinaAlphabetComparator();
		hashtable = ChinaCityUtil.initChinaCitysHashtable();
		arrProvince = ChinaCityUtil.findAreaStringArr(hashtable,
				ChinaCityUtil.TYPE_PROVINCE);
		ArrayAdapter<String> adapterProvince = getArrayAdapter(arrProvince);
		spinnerProvince = (Spinner) findViewById(R.id.spinnerProvince);
		spinnerProvince.setAdapter(adapterProvince);
		spinnerProvince.setOnItemSelectedListener(this);
		spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
		spinnerCity.setOnItemSelectedListener(this);
		spinnerRegion = (Spinner) findViewById(R.id.spinnerRegion);
		spinnerRegion.setOnItemSelectedListener(this);
		txtInfo = (TextView) findViewById(R.id.txtInfo);
	}

	private ArrayAdapter<String> getArrayAdapter(String[] arr) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arr);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.sort(comparator);
		return adapter;
	}

	private void modifyCity(String province) {
		arrCity = ChinaCityUtil.findAreaStringArr(hashtable,
				ChinaCityUtil.TYPE_CITY, province);
		ArrayAdapter<String> adapterCity = getArrayAdapter(arrCity);
		spinnerCity.setAdapter(adapterCity);
	}

	private void modifyRegion(String province, String city) {
		arrRegion = ChinaCityUtil.findAreaStringArr(hashtable,
				ChinaCityUtil.TYPE_REGION, province, city);
		ArrayAdapter<String> adapterRegion = getArrayAdapter(arrRegion);
		spinnerRegion.setAdapter(adapterRegion);
	}

	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if (parent == spinnerProvince) {
			province = arrProvince[position];
			modifyCity(province);
		} else if (parent == spinnerCity) {
			city = arrCity[position];
			modifyRegion(province, city);
		} else if (parent == spinnerRegion) {
			region = arrRegion[position];
			txtInfo.setText(province + " " + city + " " + region);
		}
	}

	public void onNothingSelected(AdapterView<?> parent) {

	}
}