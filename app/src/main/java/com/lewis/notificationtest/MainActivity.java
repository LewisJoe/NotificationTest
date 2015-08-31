package com.lewis.notificationtest;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                //获取Notification管理器
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //设置状态栏显示样式
                Notification notification = new Notification(R.drawable.notification_template_icon_bg
                        ,"This ia ticker text",System.currentTimeMillis());
                //点击Notification后的触发事件
                Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0, intent,PendingIntent.FLAG_CANCEL_CURRENT);
                //设置Notification在通知栏中的样式
                notification.setLatestEventInfo(MainActivity.this,"This is content title","This is content text",pi);
                //发布该通知
                manager.notify(1, notification);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
