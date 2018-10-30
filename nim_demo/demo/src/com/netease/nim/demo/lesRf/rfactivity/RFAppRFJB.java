package com.netease.nim.demo.lesRf.rfactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.netease.nim.demo.R;
import com.netease.nim.demo.lesRf.RFappHomeActivity;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.bean.Alarm;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.dbhelper.DataBaseUtils;
import com.netease.nim.demo.lesRf.rfapplayout.AlarmListAdapter;
import com.netease.nim.demo.lesRf.rfapplayout.Item;
import com.netease.nim.demo.main.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RFAppRFJB extends Activity {

   private ListView mListItem;
    private RelativeLayout mainLay;
    private List<Item> mListDatas;
    private AlarmListAdapter adapter;
    private String TAG = "RFAppRFJB" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
        initDatas();

        adapter = new AlarmListAdapter(this);
        adapter.setData(mListDatas);
        mListItem.setAdapter(adapter);
        mListItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e(TAG,"onItemClick position id = "+position);

            }
        });
        mListItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e(TAG,"onItemLongClick position id = "+position);


                return false;
            }
        });
        initDialog();

    }

    public void initDialog()
    {
        final AlertDialog.Builder normalDialog =
            new AlertDialog.Builder(RFAppRFJB.this);
        normalDialog.setIcon(R.drawable.user_account_icon);
        normalDialog.setTitle("空袭警报");
        normalDialog.setMessage("是否查看疏散路径?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        startActivity(new Intent(RFAppRFJB.this, RFAppSSYB.class));
                    }
                });
                 normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });

        normalDialog.show();
    }


    private void initDatas() {

        mListDatas = new ArrayList<Item>();
      ArrayList<Alarm> malrm =  new DataBaseUtils(this.getApplicationContext()).getAlarm();

        Log.e(TAG,"initDatae malarm size = "+malrm.size());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
      //  time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
        String tm = simpleDateFormat.format(date);
        String Tme = "发放时间\n"+tm;
         for (int i=0;i<3;i++)
        {
            Item item = new Item();
            item.setRFNM(Tme);
            item.setTitle("空袭警报");
            mListDatas.add(item);
        }

        for(Alarm tmp:malrm )
        {
            Item item = new Item();
            item.setRFNM(tmp.getTime().toString());
            Log.e(TAG,"time = "+tmp.getTime());
            item.setTitle(tmp.getTitle());
            mListDatas.add(item);
        }
    }
    private void initUI() {
        mainLay = new RelativeLayout(this.getApplicationContext());
        mainLay.setBackgroundColor(0xFFFFFFFF);


        mListItem = new ListView(this.getApplicationContext());
        // mListItem.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        mListItem.setPadding(1, 1, mListItem.getWidth(), mListItem.getHeight());

        mListItem.setDivider(new ColorDrawable(Color.GRAY));
        mListItem.setDividerHeight(1);

        mListItem.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);// liatview
        // get
        // foucus
        RelativeLayout.LayoutParams layoutParamsList = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsList.topMargin = 5;

        mainLay.addView(mListItem,layoutParamsList);

        addContentView(mainLay,layoutParamsList);
    }
}
