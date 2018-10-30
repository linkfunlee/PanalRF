/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netease.nim.demo.lesRf.client;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.netease.nim.demo.lesRf.rfactivity.RFAppRFJB;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.bean.Alarm;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.dbhelper.DataBaseUtils;
import com.netease.nim.demo.lesRf.rfactivity.rfhome.dbhelper.NewsDBUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/** 
 * This class is to notify the user of messages with NotificationManager.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class Notifier {

    private static final String LOGTAG = LogUtil.makeLogTag(Notifier.class);

    private static final Random random = new Random(System.currentTimeMillis());

    private Context context;

    private SharedPreferences sharedPrefs;

    private NotificationManager notificationManager;

    private  NewsDBUtils dbUtil;

    public Notifier(Context context) {
        this.context = context;
        this.sharedPrefs = context.getSharedPreferences(
                Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void notify(String notificationId, String apiKey, String title,
                       String message, String uri) {
        Log.d(LOGTAG, "notify()...");

        Log.e(LOGTAG, "notificationId=" + notificationId);
        Log.e(LOGTAG, "notificationApiKey=" + apiKey);
        Log.e(LOGTAG, "notificationTitle=" + title);
        Log.e(LOGTAG, "notificationMessage=" + message);
        Log.e(LOGTAG, "notificationUri=" + uri);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        //  time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
        String tm = simpleDateFormat.format(date);
        String Tme = "发放时间\n"+tm;
///////////////////////////////////////////////////////////////////
        ArrayList<Alarm> lst = new ArrayList<Alarm>();
        Alarm alarm = new Alarm();
        alarm.setType(1);
        alarm.setTitle(message);
        alarm.setTime(Tme);
        lst.add(alarm);
        //llf added

        new DataBaseUtils(context).insert(alarm);
      ////////////////////////////////////////////////////////////////////

        String snd = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/raw/jiechujb";
        Log.e(LOGTAG, "notificationsndUri=" + snd);
        Uri sound = Uri.parse(snd) ;
        if (isNotificationEnabled()) {
            // Show the toast
            if (isNotificationToastEnabled()) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }

            // Notification
            //Notification notification = new Notification();



            //            Intent intent;
            //            if (uri != null
            //                    && uri.length() > 0
            //                    && (uri.startsWith("http:") || uri.startsWith("https:")
            //                            || uri.startsWith("tel:") || uri.startsWith("geo:"))) {
            //                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            //            } else {
            //                String callbackActivityPackageName = sharedPrefs.getString(
            //                        Constants.CALLBACK_ACTIVITY_PACKAGE_NAME, "");
            //                String callbackActivityClassName = sharedPrefs.getString(
            //                        Constants.CALLBACK_ACTIVITY_CLASS_NAME, "");
            //                intent = new Intent().setClassName(callbackActivityPackageName,
            //                        callbackActivityClassName);
            //                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //            }

            Intent intent = new Intent(context,
                    RFAppRFJB.class);
            intent.putExtra(Constants.NOTIFICATION_ID, notificationId);
            intent.putExtra(Constants.NOTIFICATION_API_KEY, apiKey);
            intent.putExtra(Constants.NOTIFICATION_TITLE, title);
            intent.putExtra(Constants.NOTIFICATION_MESSAGE, message);
            intent.putExtra(Constants.NOTIFICATION_URI, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);


            Notification notification = new Notification.Builder(context)
                    .setContentTitle("New mail from " + title.toString())
                    .setContentText(message)
                    .setSound(sound)
                    .setAutoCancel(true)
                    .setContentIntent(contentIntent)
                    .build();//点击通知头自动取消;
            notification.icon = getNotificationIcon();
            notification.defaults = Notification.DEFAULT_LIGHTS;

          /*  if (isNotificationSoundEnabled()) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            if (isNotificationVibrateEnabled()) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }*/
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notification.when = System.currentTimeMillis();
            notification.tickerText = message;
         //   notification.setLatestEventInfo(context, title, message,
           //        contentIntent);
           // notification.contentIntent.cancel();
            notificationManager.notify(random.nextInt(), notification);
            //context.startActivity(intent);//llf added

            //            Intent clickIntent = new Intent(
            //                    Constants.ACTION_NOTIFICATION_CLICKED);
            //            clickIntent.putExtra(Constants.NOTIFICATION_ID, notificationId);
            //            clickIntent.putExtra(Constants.NOTIFICATION_API_KEY, apiKey);
            //            clickIntent.putExtra(Constants.NOTIFICATION_TITLE, title);
            //            clickIntent.putExtra(Constants.NOTIFICATION_MESSAGE, message);
            //            clickIntent.putExtra(Constants.NOTIFICATION_URI, uri);
            //            //        positiveIntent.setData(Uri.parse((new StringBuilder(
            //            //                "notif://notification.adroidpn.org/")).append(apiKey).append(
            //            //                "/").append(System.currentTimeMillis()).toString()));
            //            PendingIntent clickPendingIntent = PendingIntent.getBroadcast(
            //                    context, 0, clickIntent, 0);
            //
            //            notification.setLatestEventInfo(context, title, message,
            //                    clickPendingIntent);
            //
            //            Intent clearIntent = new Intent(
            //                    Constants.ACTION_NOTIFICATION_CLEARED);
            //            clearIntent.putExtra(Constants.NOTIFICATION_ID, notificationId);
            //            clearIntent.putExtra(Constants.NOTIFICATION_API_KEY, apiKey);
            //            //        negativeIntent.setData(Uri.parse((new StringBuilder(
            //            //                "notif://notification.adroidpn.org/")).append(apiKey).append(
            //            //                "/").append(System.currentTimeMillis()).toString()));
            //            PendingIntent clearPendingIntent = PendingIntent.getBroadcast(
            //                    context, 0, clearIntent, 0);
            //            notification.deleteIntent = clearPendingIntent;
            //
            //            notificationManager.notify(random.nextInt(), notification);

        } else {
            Log.w(LOGTAG, "Notificaitons disabled.");
        }
    }

    private int getNotificationIcon() {
        return sharedPrefs.getInt(Constants.NOTIFICATION_ICON, 0);
    }

    private boolean isNotificationEnabled() {
        return sharedPrefs.getBoolean(Constants.SETTINGS_NOTIFICATION_ENABLED,
                true);
    }

    private boolean isNotificationSoundEnabled() {
        return sharedPrefs.getBoolean(Constants.SETTINGS_SOUND_ENABLED, true);
    }

    private boolean isNotificationVibrateEnabled() {
        return sharedPrefs.getBoolean(Constants.SETTINGS_VIBRATE_ENABLED, true);
    }

    private boolean isNotificationToastEnabled() {
        return sharedPrefs.getBoolean(Constants.SETTINGS_TOAST_ENABLED, false);
    }

}
