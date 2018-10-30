package com.netease.nim.demo.lesRf.fragment;

import android.app.Fragment;
import android.content.Intent;

import com.netease.nim.demo.lesRf.RFappHomeActivity;
import com.netease.nim.demo.main.activity.MainActivity;

public class MyRFFragment extends android.support.v4.app.Fragment {


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();

        Intent intent = new Intent();
        intent.setClass(this.getContext(), MainActivity.class);
        getContext().startActivity(intent);
    }
}
