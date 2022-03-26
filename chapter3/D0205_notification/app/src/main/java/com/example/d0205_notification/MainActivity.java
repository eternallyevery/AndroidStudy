package com.example.d0205_notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    // 通知Id
    private final static int NOTIFIATION_ID = 100;
    // 通知列表
    private final static String ID_BASIC = "basic";
    private final static String ID_SUBSCRIBE = "subscribe";
    private static final String BASIC_STYLE = "基本样式";
    private static final String FULL_STYLE = "悬浮样式";
    private static final String CUSTOM_STYLE = "自定义View";
    private static final String BIG_TEXT_STYLE = "长文本样式";
    private static final String BIG_PICTURE_STYLE = "大图样式";
    private static final String INBOX_STYLE = "收件箱样式";
    private static final String[] NOTIFICATION_STYLES = {
            "--请选择--", BASIC_STYLE, FULL_STYLE, CUSTOM_STYLE,
            BIG_TEXT_STYLE, BIG_PICTURE_STYLE, INBOX_STYLE
    };
    // TextView的描述数据
    private static final String[] NOTIFICATION_STYLES_DESC = {"",
            "基本样式的通知",
            "高优先级的悬浮样式的通知",
            "自定义View的通知",
            "大文本样式的通知",
            "大图样式的通知","收件箱样式的通知"
    };
    static NotificationManager  notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        1.
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //判断Android版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //创建通知渠道ID
            String channelId = "normal";
            //创建通知渠道名称
            String channelName = "普通通知";
            //创建通知渠道重要性
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
            //创建通知渠道ID
            channelId = "import";
            //创建通知渠道名称
            channelName = "重要通知";
            //创建通知渠道重要性
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);

        }
        Button sendNotification = findViewById(R.id.sendNotification);
        sendNotification.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            RemoteInput remoteInput = new RemoteInput.Builder("reply").build();
            final PendingIntent replyIntent = PendingIntent.getBroadcast(this, 2, new Intent("om.example.notification"),PendingIntent.FLAG_CANCEL_CURRENT);
            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_foreground,"回复",replyIntent).addRemoteInput(remoteInput).build();
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
            Notification chat = new NotificationCompat.Builder(this, "normal")
                    .addAction(action)
                    .setAutoCancel(true)
                    .setContentTitle("收到消息")
                    .setContentText("今晚吃什么")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentIntent(pendingIntent)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.name))
                    .build();

            notificationManager.notify(1,chat);
        });
        Button sendNotification1 = findViewById(R.id.sendNotification1);
        sendNotification1.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

            Notification chat = new NotificationCompat.Builder(this, "import")
                    .setAutoCancel(true)
                    .setContentTitle("收到消息")
                    .setContentText("今晚吃什么！")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentIntent(pendingIntent)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.name))
                    .build();

            notificationManager.notify(2,chat);
        });
        Button sendNotification2 = findViewById(R.id.sendNotification2);
        sendNotification2.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

            Notification chat = new NotificationCompat.Builder(this, "normal")
                    .setStyle(new NotificationCompat.InboxStyle()
                            .addLine("可以应用NotificationCompat.InboxStyle添加多个简短摘要行。")
                            .addLine("可以添加多条内容文本，并且每条文本均截断为一行。")
                            .addLine("不显示为 NotificationCompat.BigTextStyle  提供的连续文本行。")
                            .addLine("可以调用addLine()添加新行")
                            .addLine("可以使用HTML标记添加样式，比如加粗主题，以区分消息主题和内容")
                    )
                    .setAutoCancel(true)
                    .setContentTitle("收到消息")
                    .setContentText("今晚吃什么！")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentIntent(pendingIntent)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.name))
                    .build();
            notificationManager.notify(3,chat);
        });
        Button sendNotification3 = findViewById(R.id.sendNotification3);
        sendNotification3.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.name);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

            Notification chat = new NotificationCompat.Builder(this, "normal")
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                    .setAutoCancel(true)
                    .setContentTitle("收到消息")
                    .setContentText("今晚吃什么！")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentIntent(pendingIntent)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setLargeIcon(bitmap)
                    .build();
            notificationManager.notify(3,chat);
        });
        Button sendNotification4 = findViewById(R.id.sendNotification4);
        sendNotification4.setOnClickListener(view -> {
            String msg = "这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本这是大文本";
            Intent intent = new Intent(this,MainActivity.class);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.name);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

            Notification chat = new NotificationCompat.Builder(this, "normal")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                    .setAutoCancel(true)
                    .setContentTitle("收到消息")
                    .setContentText("今晚吃什么！")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentIntent(pendingIntent)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setLargeIcon(bitmap)
                    .build();
            notificationManager.notify(3,chat);
        });
    }

    @TargetApi(Build.VERSION_CODES.O)
    private static void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        //channel有很多set方法

        //为NotificationManager设置通知渠道
        notificationManager.createNotificationChannel(channel);
    }

}