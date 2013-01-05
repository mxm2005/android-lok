package com.wz.doctor.db;

import java.util.ArrayList;
import java.util.List;

import com.wz.doctor.bean.Record;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RecordService
{
	private DBOpenHelper dbOpenHelper;

	public RecordService(Context context)
	{
		dbOpenHelper = new DBOpenHelper(context);
	}

	public boolean saveRecord(Record record)
	{
		SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
		try
		{
			database.execSQL("insert into record(name, lenght, date) values(?,?,?)", new Object[] {
					record.getName(), record.getLenght(), record.getDate() });
			database.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateRecord(Record record)
	{
		SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
		try
		{
			database.execSQL("update record set name=? where recordid=?",
					new Object[] { record.getName(), record.getId() });
			database.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public List<Record> findAllRecord()
	{
		List<Record> records = new ArrayList<Record>();
		SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
		Cursor cursor = database.rawQuery("select * from record", null);
		while(cursor.moveToNext())
		{
			Record record = new Record(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
					cursor.getString(3));
			records.add(record);
		}
		cursor.close();
		database.close();
		return records;
	}

	public void deleteRecord(int id)
	{
		SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
		database.beginTransaction();
		database.execSQL("delete from record where recordid=?", new String[] { String.valueOf(id) });
		database.setTransactionSuccessful();
		database.endTransaction();
		database.close();
	}

}
