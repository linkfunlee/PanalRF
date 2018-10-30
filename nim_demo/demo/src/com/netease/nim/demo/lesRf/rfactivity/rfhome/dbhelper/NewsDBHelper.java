package com.netease.nim.demo.lesRf.rfactivity.rfhome.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * ���ݿ�����࣬�������ݿ�
 * @author ASUS-H61M
 *
 */
public class NewsDBHelper extends SQLiteOpenHelper{

	public NewsDBHelper(Context context) {
		super(context, "NetNews", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		Log.e("News DBHelper","create table");
		
		db.execSQL("create table news(_id integer, title varchar(200), des varchar(300), "
				+ "icon_url varchar(100), news_url varchar(200), type integer, time varchar(100), comment integer)");



		db.execSQL("create table alarm(id integer primary key autoincrement,type integer,title  varchar(200),time varchar(140))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
