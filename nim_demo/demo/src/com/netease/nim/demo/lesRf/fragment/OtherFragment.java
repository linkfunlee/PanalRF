package com.netease.nim.demo.lesRf.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.netease.nim.demo.R;

public class OtherFragment extends android.support.v4.app.Fragment {





    @Override
    public void onCreate(Bundle savedInstanceState) {


        call("02582285312");

        super.onCreate(savedInstanceState);
    }
    /**
     * 调用拨号界面
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
