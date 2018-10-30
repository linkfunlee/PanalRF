package com.netease.nim.demo.lesRf.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.netease.nim.demo.R;

import com.netease.nim.demo.lesRf.rfactivity.BDNaviMainActivity;
import com.netease.nim.demo.lesRf.rfactivity.RFAppHZJY;
import com.netease.nim.demo.lesRf.rfactivity.RFAppHome;
import com.netease.nim.demo.lesRf.rfactivity.RFAppRFJB;
import com.netease.nim.demo.lesRf.rfactivity.RFAppSSYB;
import com.netease.nim.demo.lesRf.rfactivity.RFMapActivity;
import com.netease.nim.demo.lesRf.rfactivity.RFWebActivity;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.myview.MyImageView;

import java.util.ArrayList;
import java.util.List;

public class RFapplicationFragment extends android.support.v4.app.Fragment {

    private  static  String TAG = "RFapplicationFragment";
    private GridView gv;
    private RollPagerView rollPagerView;
    private Context mcontext;

    private String[] title = { "人防警报", "智慧停车", "随手拍", "知识答题", "模拟演练", "导航自救", "疏散掩蔽", "互助救援" };
    private int[] image = { R.drawable.index_btn_alarm, R.drawable.index_btn_car, R.drawable.index_btn_cam,
            R.drawable.index_btn_edit, R.drawable.index_btn_excise, R.drawable.index_btn_gps, R.drawable.index_btn_exit,
            R.drawable.index_btn_help };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext = this.getContext();

      //  gv = (GridView) findViewById(R.id.gv);
       // MyAdapter adapter = new MyAdapter(this);
       // gv.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);

        View rootView =  inflater.inflate(R.layout.les_rfapp_fragment, container, false);

        gv = (GridView) rootView.findViewById(R.id.gv);
        MyAdapter adapter = new MyAdapter(this.getContext());

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                          //System.out.println("click index:"+arg2);

                                          Log.e(TAG,"arg2+ "+arg2);
                                          selectWhichApp(arg2);


                                      }
                                  }
        );
        gv.setAdapter(adapter);

        rollPagerView= (RollPagerView)rootView.findViewById(R.id.roll_view_pager);



        rollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e(TAG,"position + = "+position);

                selectWhichApp(position);
            }
        });



        rollPagerViewSet();
        return rootView;

    }

    private void clickPage()
    {
        Log.e(TAG,"view + = +.getId()");
    }
    private void selectWhichApp(int index) {

        switch (index)
        {
            case 0:
                Intent intent = new Intent();
                intent.setClass(mcontext, RFAppRFJB.class);
                mcontext.startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent();
                intent1.setClass(mcontext, RFMapActivity.class);
                mcontext.startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent();
                intent2.setClass(mcontext, RFAppHome.class);
                mcontext.startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent();
                intent3.putExtra("RFname","http://ccad.jiangsu.gov.cn/");
                intent3.putExtra("Title","知识答题");
                intent3.setClass(mcontext, RFWebActivity.class);
                mcontext.startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent();
                intent4.putExtra("RFname"," http://rfb.nanjing.gov.cn/37004/201809/t20180919_5794564.html");
                intent4.putExtra("Title","模拟演练");
                intent4.setClass(mcontext, RFWebActivity.class);
                mcontext.startActivity(intent4);
                break;

            case 5:
                Intent intent5 = new Intent();
               // intent5.putExtra("RFname"," http://rfb.nanjing.gov.cn/37004/201809/t20180919_5794564.html");
                intent5.setClass(mcontext, BDNaviMainActivity.class);
                mcontext.startActivity(intent5);
                break;
            case 6:
                Intent intent6 = new Intent();
                intent6.setClass(mcontext, RFAppSSYB.class);
                mcontext.startActivity(intent6);
                break;
            case 7:
                Intent intent7 = new Intent();
                intent7.setClass(mcontext, RFAppHZJY.class);
                mcontext.startActivity(intent7);
                break;
                default:
                    break;
        }


    }

    private void rollPagerViewSet() {
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        rollPagerView.setAdapter(new rollViewpagerAdapter());//配置适配器
    }

    private void initView() {

    }






    class MyAdapter extends BaseAdapter {// 创建适配器
        private List<PicEntity> list = new ArrayList<>();
        private Context context;


        public MyAdapter(Context context) {
            this.context = context;
            for (int i = 0; i < image.length; i++) {
                PicEntity picEntity = new PicEntity(title[i], image[i]);
                list.add(picEntity);
            }
        }

        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewholder vh;
            if (convertView == null) {
               convertView = LayoutInflater.from(context).inflate(R.layout.les_grid_item, null);
                vh = new Viewholder();
               vh.iv_pic = (ImageView) convertView.findViewById(R.id.iv_image);
                vh.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(vh);
            } else {
                vh = (Viewholder) convertView.getTag();
            }
            vh.iv_pic.setImageResource(list.get(position).imageId);
            vh.tv_title.setText(list.get(position).title);
            return convertView;
        }
    }

    class Viewholder {
        TextView tv_title;
        ImageView iv_pic;
    }

    class PicEntity {// 创建实体类
        private String title;
        private int imageId;

        public PicEntity() {
            super();
        }

        public PicEntity(String title, int imageId) {
            super();
            this.title = title;
            this.imageId = imageId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }
    }

    private class rollViewpagerAdapter extends StaticPagerAdapter {

        private int[] res={R.drawable.file_ic_detail_html
                ,R.drawable.file_ic_detail_jpg,
                R.drawable.file_ic_detail_pdf,
                R.drawable.file_ic_detail_png};

        private  String[] resUrl ={"http://ccad.nj.gov.cn/36983/201805/W020180512080448554373.jpg",
               "http://ccad.nj.gov.cn/36983/201805/W020180512080449352345.jpg",
                "http://ccad.nj.gov.cn/36983/201805/W020180512080450108606.jpg",
                "http://ccad.nj.gov.cn/36983/201805/W020180512080450108606.jpg"
        };
        @Override
        public View getView(ViewGroup container, int position) {
            //ImageView imageView=new ImageView(container.getContext());//这里最好用MyImageView
            MyImageView imageView=new MyImageView(container.getContext());//这里最好用MyImageView
            //imageView.setImageResource(res[position]);
           imageView.setImageUrl(resUrl[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return imageView;
        }

        @Override
        public int getCount() {
            return res.length;
        }
    }




}
