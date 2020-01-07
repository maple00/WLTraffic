package com.rainowood.wltraffic.ui.activity;

import android.content.Context;
import android.content.Intent;

import androidx.viewpager.widget.ViewPager;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.ui.adapter.ImagePagerAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

/**
 * @Author: a797s
 * @Date: 2019/12/25 9:56
 * @Desc: 查看图片大图
 */
public final class ImageActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

    @ViewById(R.id.vp_image_pager)
    ViewPager mViewPager;
    @ViewById(R.id.pv_image_indicator)
    PageIndicatorView mIndicatorView;

    @Override
    protected void initView() {

        mIndicatorView.setViewPager(mViewPager);
    }

    @Override
    protected void initData() {
        ArrayList<String> images = getIntent().getStringArrayListExtra(Contants.PICTURE);
        int index = getIntent().getIntExtra(Contants.INDEX, 0);
        if (images != null && images.size() > 0) {
            mViewPager.setAdapter(new ImagePagerAdapter(this, images));
            if (index != 0 && index <= images.size()) {
                mViewPager.setCurrentItem(index);
            }
        } else {
            finish();
        }
    }

    public static void start(Context context, String url) {
        ArrayList<String> images = new ArrayList<>(1);
        images.add(url);
        start(context, images);
    }

    public static void start(Context context, ArrayList<String> urls) {
        start(context, urls, 0);
    }

    public static void start(Context context, ArrayList<String> urls, int index) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(Contants.PICTURE, urls);
        intent.putExtra(Contants.INDEX, index);
        context.startActivity(intent);
    }


}
