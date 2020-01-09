package com.rainowood.wltraffic.jpush;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.os.Bundle;
import android.util.Log;

import com.rainowood.wltraffic.common.Badge;
import com.rainowood.wltraffic.ui.activity.MessageDetailActivity;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class MyMessageReceiver extends JPushMessageReceiver {

    private static final String TAG = "PushMessageReceiver";

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        processCustomMessage(context, customMessage);
    }

    @Override
    public Notification getNotification(Context context, NotificationMessage notificationMessage) {
        return super.getNotification(context, notificationMessage);
    }

    /**
     * 点击了通知栏
     *
     * @param context
     * @param message
     */
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        Intent i = new Intent(context, MessageDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(JPushInterface.EXTRA_NOTIFICATION_TITLE, message.notificationTitle);
        bundle.putString(JPushInterface.EXTRA_ALERT, message.notificationContent);
        bundle.putString(JPushInterface.EXTRA_EXTRA, message.notificationExtras);
        i.putExtras(bundle);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }

    /**
     * 收到了推送消息
     */
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        // 有通知送达的时候，添加桌面红点
        Badge.add(context);
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
    }

    /**
     * 取消通知栏的通知消息
     * @param context
     * @param message
     */
    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {

        Badge.reset(context);
    }

    @Override
    public void onRegister(Context context, String registrationId) {

    }

    /**
     * 连接状态
     *
     * @param context
     * @param isConnected
     */
    @Override
    public void onConnected(Context context, boolean isConnected) {
        Log.e("sxs_jpush", "connection: " + isConnected);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onTagOperatorResult(context, jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context, jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context, jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onMobileNumberOperatorResult(context, jPushMessage);
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    /**
     * 自定义推送消息的格式
     *
     * @param context
     * @param customMessage
     */
    private void processCustomMessage(Context context, CustomMessage customMessage) {

    }

    /**
     * 自定义推送的声音
     *
     * @param context
     * @return
     */
    //播放自定义的声音
    private static Ringtone mRingtone;
    private boolean allowMusic = true;
    /*public synchronized void playSound(Context context) {
        if (!allowMusic) {
            return;
        }
        if (mRingtone == null) {
            String uri = "android.resource://" + context.getPackageName() + "/" + R.raw.new_order_tips_music;
            Uri no = Uri.parse(uri);
            mRingtone = RingtoneManager.getRingtone(context.getApplicationContext(), no);
        }
        if (!mRingtone.isPlaying()) {
            mRingtone.play();
        }
    }*/
}

