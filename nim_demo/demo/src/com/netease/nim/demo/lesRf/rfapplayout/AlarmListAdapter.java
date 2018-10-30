package com.netease.nim.demo.lesRf.rfapplayout;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.netease.nim.demo.R;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdapter extends BaseAdapter {


    Context mContext;
    Activity mActivity;
    private List<Item> mListDatas;
    private Cursor mCursor;
    private int selectedPosition = -1;// 选中的位置
    public AlarmListAdapter(Activity context)
    {
        mContext = context.getApplicationContext();
        mActivity = context;
        mListDatas = new ArrayList<Item>();
    }
    public AlarmListAdapter(Activity context,Cursor cursor)
    {
        mContext = context.getApplicationContext();
        mActivity = context;
        mCursor = cursor;
        mListDatas = new ArrayList<Item>();
    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mListDatas.size();
        //return mCursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        //return null;
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public void setSelectedPosition(int position) {


        selectedPosition = position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {
        // TODO Auto-generated method stub
        Holder holder = null;
        RFalarmLayout listLay = null;
        //mCursor.moveToPosition(position);
        if (convertView == null) {
            //	convertView = getLayoutInflater().inflate(
            //	R.layout.lv_item_item, null);
            listLay = new RFalarmLayout(mContext, null);

            convertView = listLay;
            holder = new Holder();

            holder.alarm = listLay.getmImgView();

            holder.content =listLay.getmTextViewContent();

            holder.time =listLay.getTextViewTime();

            holder.btn = listLay.getButton();//(Button)convertView.findViewById(R.id.button_nxt);

            convertView.setTag(holder);

            ((ViewGroup) convertView).setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);  //将焦点获取



        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.time.setText(mListDatas.get(position).getRFNM());

        holder.content.setText(mListDatas.get(position).getTitle());

        holder.alarm.setImageResource(R.drawable.alarm_attack);

        if (selectedPosition == position) {
            convertView.setBackgroundColor(0xFF8C8C8C);
        } else {
            convertView.setBackgroundColor(0xFFFFFF);

        }



        return convertView;
    }

    public void setData( List<Item> mData)
    {
        mListDatas = mData;
    }
    private class Holder {

        ImageView alarm;

        TextView content;

        TextView time;

        Button btn;

    }



    private int mGetScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();


        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
