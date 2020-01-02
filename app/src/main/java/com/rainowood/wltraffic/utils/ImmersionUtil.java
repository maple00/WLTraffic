package com.rainowood.wltraffic.utils;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rainwood.tools.common.MeasureUtil;
import com.rainwood.tools.statusbar.StatusBarUtil;

/**
 * @Author: a797s
 * @Date: 2020/1/2 13:27
 * @Desc: 状态栏沉浸
 */
public final class ImmersionUtil {

    /**
     * 图片沉浸
     * @param mActivity 当前aty
     * @param actionBar 设置标题栏的高度
     * @param background 设置背景图片的高度
     */
    public static void ImageImmers(Activity mActivity, FrameLayout actionBar, ImageView background){
        //用来设置整体下移，状态栏沉浸
        StatusBarUtil.setRootViewFitsSystemWindows(mActivity, false);
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        if (mActivity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, mActivity.getResources().getDisplayMetrics());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, actionBarHeight);
            int statusBarHeight = StatusBarUtil.getStatusBarHeight(mActivity);
            params.setMargins(0, statusBarHeight, 0, 0);
            actionBar.setLayoutParams(params);
            RelativeLayout.LayoutParams parentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            parentParams.height = (MeasureUtil.dip2px(mActivity,240) + statusBarHeight);
            background.setLayoutParams(parentParams);
        }
    }
}
