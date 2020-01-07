package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.QualitySafeDetailBean;
import com.rainowood.wltraffic.ui.adapter.ImageAdapter;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureGridView;
import com.rainwood.tools.widget.MeasureListView;

/**
 * @Author: a797s
 * @Date: 2019/12/25 15:04
 * @Desc: 外业检测
 */
public final class QuailtySafeDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;

    @ViewById(R.id.tv_content_title)
    private TextView contentTitle;
    @ViewById(R.id.tv_content)
    private TextView content;
    @ViewById(R.id.lv_word_list)
    private MeasureListView wordList;
    @ViewById(R.id.gv_img_list)
    private MeasureGridView imgList;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        super.initData();
        /*
        获取传递过来的数据
         */
        QualitySafeDetailBean key = (QualitySafeDetailBean) getIntent().getExtras().getSerializable("quality");
        if (key != null) {
            if (key.getTitle() != null) {            // 外业检测
                pageTitle.setText("外业检测");
                contentTitle.setText(key.getTitle());
                content.setText(key.getContent());

                ItemAttachListAdapter wordListAdapter = new ItemAttachListAdapter(this, key.getmWordList());
                wordList.setAdapter(wordListAdapter);
                wordListAdapter.notifyDataSetChanged();

                imgList.setVisibility(View.GONE);
            } else {                     //  质量检测
                pageTitle.setText("质量鉴定(检测)意见");
                contentTitle.setVisibility(View.GONE);
                content.setText(key.getContent());
                imgList.setVisibility(View.GONE);

                ItemAttachListAdapter attachListAdapter = new ItemAttachListAdapter(this, key.getmImgList());
                wordList.setAdapter(attachListAdapter);
                /*ImageAdapter imageAdapter = new ImageAdapter(this, key.getmImgList());
                imgList.setAdapter(imageAdapter);
                imageAdapter.notifyDataSetChanged();*/
            }
        } else {
            throw new RuntimeException("可能数据传输错误");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            finish();
        }
    }
}
