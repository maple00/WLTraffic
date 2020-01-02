package com.rainowood.wltraffic.utils;


import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.rainowood.wltraffic.R;

import java.util.Objects;

/**
  * @Author: sxs797
  * @Date: 2020/1/2 17:36
  * @Desc: Dialog 工具类
  */
public class DialogUtils {

    private static Dialog dialog;

    public DialogUtils(Context context, String tips) {

        dialog = new Dialog(context, R.style.progress_dialog);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        TextView tvMsg = dialog.findViewById(R.id.tv_wait_message);
        tvMsg.setText(tips);
    }

    public void showDialog() {
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }



}
