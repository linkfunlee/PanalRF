package com.netease.nim.demo.lesRf.rfactivity.rfhome.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.netease.nim.demo.lesRf.rfactivity.rfhome.bean.Alarm;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUtils{
    private SQLiteDatabase sqliteDatabase;

    private Context mContext;
    DatabaseHelper dbHelper;
    public DataBaseUtils(Context Context)
    {
        if (sqliteDatabase == null) {
             dbHelper = new DatabaseHelper(Context, "userDB");
            // 只有调用了DatabaseHelper的getWritableDatabase()方法或者getReadableDatabase()方法之后，才会创建或打开一个连接
            sqliteDatabase = dbHelper.getReadableDatabase();
        }
    }


    public  long insert(Alarm user) {
        sqliteDatabase = dbHelper.getWritableDatabase();
        // 创建ContentValues对象
        ContentValues values = new ContentValues();
        // 向该对象中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
        values.put("time", user.getTime());

        values.put("title", user.getTitle());

        values.put("type", user.getType());

        // 调用insert方法，就可以将数据插入到数据库当中
        // 第一个参数:表名称
        // 第二个参数：SQl不允许一个空列，如果ContentValues是空的，那么这一列被明确的指明为NULL值
        // 第三个参数：ContentValues对象
        return sqliteDatabase.insert("alarm", null, values);

    }

    public  void delUser(String id) {

        sqliteDatabase.delete("user", "id=?", new String[] { id  });

    }

    public ArrayList<Alarm> getAlarm() {
        ArrayList<Alarm> arrayList = new ArrayList<Alarm>();
      //  SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = sqliteDatabase.query("alarm", null, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Alarm newsBean = new Alarm();

                newsBean.setTime(cursor.getString(3));

                newsBean.setTitle(cursor.getString(2));

                newsBean.setType(cursor.getInt(1));
                arrayList.add(newsBean);
            }
        }

        return arrayList;
    }

    public  void closeDB()
    {
        sqliteDatabase.close();
    }
}

