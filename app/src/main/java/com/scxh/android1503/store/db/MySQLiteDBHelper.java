package com.scxh.android1503.store.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scxh.android1503.util.Logs;

public class MySQLiteDBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "mydatabase.db";
	
	private static int DB_VERSION = 2;

	public MySQLiteDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	/**
	 * 初始化创建数据库表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Logs.v("oncreate >>>>>>>>>>>");
		
		String sql = "create table "+DataColumns.UserTable.TABLE_NAME +" (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username varchar(50), password varchar(10))";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Logs.v("onUpgrade >>>>>>>>>>>oldVersion  : " + oldVersion + ", newVersion :" + newVersion);
		if(oldVersion == 2) {
			String sql = "alter table " + DataColumns.UserTable.TABLE_NAME + " add column number varchar(5)";
			db.execSQL(sql);
		}
		if(oldVersion == 3 ){

		}
	}

}
