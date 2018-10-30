package com.netease.nim.demo.lesRf.rfactivity.rfhome.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;



import android.content.Context;
import android.util.Log;

import com.netease.nim.demo.lesRf.rfactivity.rfhome.bean.NewsBean;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.dbhelper.NewsDBUtils;

public class NewsUtils {
	
	// �������л�ȡJson���ݣ�����Json����
	public static ArrayList<NewsBean> getNetNews(Context context, String urlString) {
		ArrayList<NewsBean> arraylistNews = new ArrayList<NewsBean>();
		try
		{
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(20 * 1000);
			int responseCode = conn.getResponseCode();
			Log.e("NewsUtils","url"+url);
			Log.e("NewsUtils","responseCode"+responseCode);
			if (responseCode == 200) {

				InputStream is = conn.getInputStream();
				Log.e("NewsUtils","is = "+is);
				String result = StreamUtils.convertStream(is);
				Log.e("NewsUtils","result = "+result.toString());
				JSONObject root_json = new JSONObject(result);



				Log.e("NewsUtils","root_json = "+root_json.getString("data"));
				JSONObject root_json1 = new JSONObject(root_json.getString("data"));
				NewsBean newsBean1 = new NewsBean();
				newsBean1.setId(root_json1.getInt("id"));
				newsBean1.setTime(root_json1.getString("time"));
				newsBean1.setDes(root_json1.getString("des"));
				newsBean1.setTitle(root_json1.getString("title"));
				newsBean1.setNews_url(root_json1.getString("news_url"));
				newsBean1.setIcon_url(root_json1.getString("icon_url"));
				Log.e("NewsUtils", newsBean1.getIcon_url());
				newsBean1.setComment(root_json1.getInt("comment"));
				newsBean1.setType(root_json1.getInt("type"));
				arraylistNews.add(newsBean1);



			//	JSONArray jsonArray  = root_json.getJSONArray("newss");//这里用的多个
				JSONArray jsonArray  = root_json.getJSONArray("data");//这里用的多个
				Log.e("NewsUtils","url"+jsonArray.length());
				for (int i = 0; i < jsonArray .length(); i ++ ){
					JSONObject news_json = jsonArray.getJSONObject(i);
					NewsBean newsBean = new NewsBean();
					newsBean.setId(news_json.getInt("id"));
					newsBean.setTime(news_json.getString("time"));
					newsBean.setDes(news_json.getString("des"));
					newsBean.setTitle(news_json.getString("title"));
					newsBean.setNews_url(news_json.getString("news_url"));
					newsBean.setIcon_url(news_json.getString("icon_url"));
					Log.e("NewsUtils", newsBean.getIcon_url());
					newsBean.setComment(news_json.getInt("comment"));
					newsBean.setType(news_json.getInt("type"));
					arraylistNews.add(newsBean);
					
				}
				
				// �����ȡ�������ϵ����ݣ���ɾ��֮ǰ��ȡ���������ݣ������µ���������
				new NewsDBUtils(context).deleteNews();
				new NewsDBUtils(context).saveNews(arraylistNews);
				
				is.close();
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("NewsUtils","error");
			e.printStackTrace();
		}

		return arraylistNews;
	}
	
	// �������ݿ⻺�浽������
	public static ArrayList<NewsBean> getDBNews(Context context){
		
		return new NewsDBUtils(context).getNews();
	}

	

}
