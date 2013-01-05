package com.wz.doctor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper
{
	private static final String DBNAME = "doctor.db";
	private static final int version = 1;

	public DBOpenHelper(Context context)
	{
		super(context, DBNAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE IF NOT EXISTS record(recordid INTEGER PRIMARY KEY autoincrement, name text, lenght INTEGER, date text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS record");
		onCreate(db);
	}

}
