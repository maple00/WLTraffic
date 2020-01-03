package com.rainowood.wltraffic.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import com.rainowood.wltraffic.helper.SQLiteHelper;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.OnHttpListener;

/**
 * @Author: a797s
 * @Date: 2020/1/3 11:12
 * @Desc: 接口自动更新服务, 对于不常用的数据，定时自动更新
 */
public class ApiAutoUpdateService extends Service implements OnHttpListener {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 更细个人信息数据
        updatePersonal();

        // 更新服务
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 8 * 60 * 60 * 1000;        // 8个小时
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, ApiAutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 定时更新个人信息
     */
    private void updatePersonal(){
        // 创建数据
        SQLiteHelper sqLiteHelper = SQLiteHelper.with(this, 1);

    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {

    }
}
