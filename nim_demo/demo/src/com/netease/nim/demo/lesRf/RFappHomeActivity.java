package com.netease.nim.demo.lesRf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.netease.nim.demo.R;
import com.netease.nim.demo.contact.activity.AddFriendActivity;
import com.netease.nim.demo.lesRf.client.ServiceManager;
import com.netease.nim.demo.lesRf.fragment.MyRFFragment;
import com.netease.nim.demo.lesRf.fragment.OtherFragment;
import com.netease.nim.demo.lesRf.fragment.RFapplicationFragment;
import com.netease.nim.demo.lesRf.fragment.RFhomeFragment;
import com.netease.nim.demo.lesRf.fragment.RFserviceFragment;
import com.netease.nim.demo.main.activity.MainActivity;
import com.netease.nim.uikit.common.activity.UI;

public class RFappHomeActivity extends FragmentActivity {

    private static final String TAG = RFappHomeActivity.class.getSimpleName();
    private static final String EXTRA_APP_QUIT = "APP_QUIT";
    private LayoutInflater layoutInflater;
    private FragmentTabHost mTabHost;
    private final Class fragmentArray[]={ RFapplicationFragment.class,RFhomeFragment.class,RFserviceFragment.class,MyRFFragment.class, OtherFragment.class,  };
    private int mTitleArray[] = {R.string.rf_application,R.string.rf_home,R.string.rf_service,R.string.rf_mine,R.string.rf_other};

    private int mImageViewArray[] = {R.drawable.tab_app_select, R.drawable.tab_home_select, R.drawable.tab_sever_select,R.drawable.tab_mine_select,R.drawable.tab_work_select};
    private String mTextviewArray[] = {"application", "home", "service","myrf","other"};
    private ImageView msgUnread;


    @Override
        protected void onCreate( Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Log.e(TAG,"init ");
            setContentView(R.layout.les_rfyy_home_activity);
            Log.e(TAG,"init  succees");
            initView();


            initNoticeSevice();
        }

    private void initNoticeSevice() {


        // Start the service
        ServiceManager serviceManager = new ServiceManager(this);
        // serviceManager.setNotificationIcon(R.drawable.notification);
        serviceManager.setNotificationIcon(R.drawable.nick_name_icon);
        serviceManager.startService();
        serviceManager.setAlias("xuyusong");
    }

    private void initView() {
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.contentPanel);
        int fragmentCount = fragmentArray.length;
        for (int i = 0; i < fragmentCount; ++i) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.getTabWidget().setDividerDrawable(null);

        }

    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.les_home_tab, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(mImageViewArray[index]);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(mTitleArray[index]);
        if (index == 0){
            msgUnread = (ImageView) view.findViewById(R.id.tabUnread);
        }
        return view;
    }
    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, RFappHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
    public static final void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RFappHomeActivity.class);
        context.startActivity(intent);
    }
    // 注销
    public static void logout(Context context, boolean quit) {
        Intent extra = new Intent();
        extra.putExtra(EXTRA_APP_QUIT, quit);
        start(context, extra);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
