package com.netease.nim.demo.lesRf.rfactivity.rfhome.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.netease.nim.demo.R;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.bean.NewsBean;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.myview.MyImageView;

import java.util.List;

/**
 * ListView��Adapter
 * @author ASUS-H61M
 *
 */
public class NewsAdapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	private List<NewsBean> mDatas;
	public NewsAdapter(Context context, List<NewsBean> listNewsBean){
		this.mLayoutInflater = LayoutInflater.from(context);
		this.mDatas = listNewsBean;
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.layout_item, null);
			viewHolder = new ViewHolder();
			viewHolder.item_img_icon = (MyImageView) convertView.findViewById(R.id.item_img_icon);;
			viewHolder.item_tv_des = (TextView) convertView.findViewById(R.id.item_tv_des);
			viewHolder.item_tv_title = (TextView) convertView.findViewById(R.id.item_tv_title);
			viewHolder.item_tv_comment = (TextView) convertView.findViewById(R.id.item_tv_comment);
			viewHolder.item_tv_type = (TextView) convertView.findViewById(R.id.item_tv_type);
			convertView.setTag(viewHolder);
		} else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		NewsBean newsBean= mDatas.get(position);
		viewHolder.item_img_icon.setImageUrl(newsBean.getIcon_url());
		viewHolder.item_tv_des.setText(newsBean.getDes());
		viewHolder.item_tv_title.setText(newsBean.getTitle());
		viewHolder.item_tv_comment.setText(newsBean.getComment() + "");
		//0 ��ͷ�� 1 ������ 2.����
		switch (newsBean.getType()) {
		case 0:
			viewHolder.item_tv_type.setText("ͷ��");
			break;
		case 1:
			viewHolder.item_tv_type.setText("���� ");
			break;
		case 2:
			viewHolder.item_tv_type.setText("����");
			break;
		default:
			break;
		}
		return convertView;
	}
	
	

}
class ViewHolder{
	MyImageView item_img_icon;
	TextView item_tv_des;
	TextView item_tv_title;
	TextView item_tv_comment;
	TextView item_tv_type;
}
