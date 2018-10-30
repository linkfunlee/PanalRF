package com.netease.nim.demo.lesRf.rfactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.netease.nim.demo.R;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.adapter.NewsAdapter;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.bean.NewsBean;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.dbhelper.NewsDBUtils;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.utils.NewsUtils;
import com.netease.nim.demo.main.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class RFAppHome extends Activity implements AdapterView.OnItemClickListener {
    private static String result = "http://172.16.0.65:8089/girl/girlJSON";
    private Context mContext;
    private TextView textView;
    private Button btnRtn;
    NewsUtils newsUtils;
    List<NewsBean> listNewsBean;
    ListView listview;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            listNewsBean = (List<NewsBean>) msg.obj;
            NewsAdapter newsAdapter = new NewsAdapter(RFAppHome.this, listNewsBean);
            listview.setAdapter(newsAdapter);

        };
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.les_home_activity);
        mContext = RFAppHome.this;
        listview = (ListView) findViewById(R.id.list_news);
        btnRtn = (Button)findViewById(R.id.btn_rtn);

        btnRtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        newsUtils = new NewsUtils();
        NewsDBUtils newsDatabase = new NewsDBUtils(mContext);

        // 1.先去数据库中获取缓存的新闻数据展示到listview
        ArrayList<NewsBean> allnews_database = NewsUtils.getDBNews(mContext);

        if (allnews_database != null && allnews_database.size() > 0) {
            // 创建一个adapter设置给listview
            NewsAdapter newsAdapter = new NewsAdapter(mContext, allnews_database);
            listview.setAdapter(newsAdapter);
        }

        new Thread(new Runnable() {

            @Override
            public void run() {
                listNewsBean = newsUtils.getNetNews(mContext, result);
                Message message = Message.obtain();
                message.obj = listNewsBean;
                mHandler.sendMessage(message);
            }
        }).start();

        listview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsBean news = (NewsBean) parent.getItemAtPosition(position);
        String url = news.getNews_url();// 这里是查看新闻连接的地方
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }


}

