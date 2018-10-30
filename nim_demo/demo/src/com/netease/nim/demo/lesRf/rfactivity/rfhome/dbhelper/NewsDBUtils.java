package com.netease.nim.demo.lesRf.rfactivity.rfhome.dbhelper;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.netease.nim.demo.lesRf.rfactivity.rfhome.bean.Alarm;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.bean.NewsBean;

import java.util.ArrayList;

/**
 * ���ݿ⹤���࣬��װ�����ݿ������ɾ�Ĳ�ķ���
 * @author ASUS-H61M
 *
 */
public class NewsDBUtils {
	private NewsDBHelper dbHelper;
	
	public NewsDBUtils (Context context) {
		dbHelper = new NewsDBHelper(context);
	}
	
	// �������ŵ����ݿ���
	public void saveNews(ArrayList<NewsBean> arrayList) {
		SQLiteDatabase sqLite = dbHelper.getWritableDatabase();
		for(NewsBean newsBean : arrayList) {
			ContentValues value = new ContentValues();
			value.put("_id", newsBean.getId());
			value.put("time", newsBean.getTime());
			value.put("des", newsBean.getDes());
			value.put("title", newsBean.getTitle());
			value.put("news_url", newsBean.getNews_url());
			value.put("icon_url", newsBean.getIcon_url());
			value.put("comment", newsBean.getComment());
			value.put("type", newsBean.getType());
			sqLite.insert("news", null, value);
		}
		sqLite.close();
	}
	
	
	// ɾ�����ݿ�����
	public void deleteNews (){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.delete("news", null, null);
		db.close();
	}
	
	// �����ݿ��л�ȡ�洢����Ϊ
	public ArrayList<NewsBean> getNews() {
		ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("news", null, null, null, null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				NewsBean newsBean = new NewsBean();
				newsBean.setId(cursor.getInt(0));
				newsBean.setTime(cursor.getString(1));
				newsBean.setDes(cursor.getString(2));
				newsBean.setTitle(cursor.getString(3));
				newsBean.setNews_url(cursor.getString(4));
				newsBean.setIcon_url(cursor.getString(5));
				newsBean.setComment(cursor.getInt(6));
				newsBean.setType(cursor.getInt(7));
				arrayList.add(newsBean);
			}
		}
		
		return arrayList;
	}

	public void saveAlarm(ArrayList<Alarm> arrayList) {
		SQLiteDatabase sqLite = dbHelper.getWritableDatabase();
		for(Alarm newsBean : arrayList) {
			ContentValues value = new ContentValues();

			value.put("time", newsBean.getTime());

			value.put("title", newsBean.getTitle());

			value.put("type", newsBean.getType());
			sqLite.insert("alarm", null, value);
		}
		sqLite.close();
	}
	public ArrayList<Alarm> getAlarm() {
		ArrayList<Alarm> arrayList = new ArrayList<Alarm>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("alarm", null, null, null, null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Alarm newsBean = new Alarm();

				newsBean.setTime(cursor.getString(1));

				newsBean.setTitle(cursor.getString(2));

				newsBean.setType(cursor.getInt(2));
				arrayList.add(newsBean);
			}
		}

		return arrayList;
	}
	public void deleteAlarm (){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.delete("alarm", null, null);
		db.close();
	}
}
