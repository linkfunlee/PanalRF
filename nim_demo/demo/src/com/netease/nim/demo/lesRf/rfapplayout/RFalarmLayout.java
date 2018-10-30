package com.netease.nim.demo.lesRf.rfapplayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netease.nim.demo.R;

public class RFalarmLayout extends RelativeLayout {


    private TextView mTextViewContent;
    private ImageView mImgView;
    private TextView mTextViewTime;
    private Button mBtn;
    Context mContext;
    private final int IMAGEVIEW = 1;
    private final int RELAY = 5;
    private final int TEXTVIEWCONTENT = 2;
    private final int TEXTVIEWTIME = 3;
    private final int BTN = 4;

    public RFalarmLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        RelativeLayout.LayoutParams outLayPara = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        //this.setMinimumHeight(130);

        int strokeWidth = 1; // 3dp 边框宽度
        int roundRadius = 5; // 8dp 圆角半径
        int strokeColor = Color.parseColor("#8c8c8c");//边框颜色
        int fillColor = Color.parseColor("#FFFFFF");//内部填充颜色

        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(fillColor);

        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, strokeColor);

        this.setPadding(8, 18, 8, 18);

        // this.setBackgroundResource(R.drawable.top_bg);
        this.setBackgroundColor(0xffffffff);//设置背景色

        mContext = this.getContext();
        mTextViewContent = new TextView(this.getContext());

        RelativeLayout.LayoutParams mRadiobtnLayPara =new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        mTextViewContent.setId(TEXTVIEWCONTENT);

        mTextViewContent.setBackgroundColor(0x008c8c8c);
        mTextViewContent.setClickable(false);

        mRadiobtnLayPara.addRule(RelativeLayout.CENTER_VERTICAL);
        mRadiobtnLayPara.addRule(RelativeLayout.RIGHT_OF,IMAGEVIEW);
        mTextViewContent.setGravity(Gravity.CENTER_VERTICAL);
        mTextViewContent.setTextSize(20.0f);


        RelativeLayout mLay = new RelativeLayout(this.getContext());
        mLay.setId(RELAY);
        RelativeLayout.LayoutParams mLayLayPara =new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
       // mLayLayPara.addRule(RelativeLayout.RIGHT_OF,IMAGEVIEW);

        mImgView = new ImageView(mLay.getContext());
        RelativeLayout.LayoutParams mGridViewLayPara =new RelativeLayout.LayoutParams(100,
                100);
        mGridViewLayPara.leftMargin = 40;

        mGridViewLayPara.rightMargin = 40;
        mGridViewLayPara.addRule(RelativeLayout.CENTER_VERTICAL);
        mImgView.setId(IMAGEVIEW);

        mGridViewLayPara.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        //mLay.setBackgroundDrawable(gd);// remove  when need
        mLay.addView(mImgView, mGridViewLayPara);
        mLay.addView(mTextViewContent, mRadiobtnLayPara);
        mBtn = new Button(mLay.getContext());
        RelativeLayout.LayoutParams mBtnLayPara =new RelativeLayout.LayoutParams(36,
                36);
        mBtnLayPara.addRule(RelativeLayout.CENTER_VERTICAL);
        mBtnLayPara.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mBtn.setGravity(Gravity.CENTER_VERTICAL);
        mBtn.setId(BTN);
        //mBtn.setText("->");
        mBtn.setBackgroundResource(R.drawable.alarm_detail);


        mLay.addView(mBtn, mBtnLayPara);


        mTextViewTime = new TextView(mLay.getContext());
        RelativeLayout.LayoutParams mTextViewLayPara =new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        mTextViewLayPara.addRule(RelativeLayout.LEFT_OF,BTN);
        mTextViewTime.setText("鸿意星城二期\n南京市秦淮区星海路6号\n采集时间：2010。5。22");

        mTextViewLayPara.addRule(RelativeLayout.CENTER_VERTICAL);

        mTextViewTime.setGravity(Gravity.CENTER_VERTICAL);
        mTextViewLayPara.rightMargin = 40;
        mTextViewTime.setTextColor(0xff000000);
        mTextViewTime.setTextSize(12.0f);
        mLay.addView(mTextViewTime, mTextViewLayPara);

        this.addView(mLay,mLayLayPara);//add relative layout

    }

    public TextView getmTextViewContent()
    {
        return mTextViewContent;
    }

    public ImageView getmImgView() {
        return mImgView;
    }


    public TextView getTextViewTime()
    {
        return mTextViewTime;
    }
    public Button getButton()
    {
        return mBtn;
    }
}
