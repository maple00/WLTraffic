package com.rainowood.wltraffic.ui.activity;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.NotifyContentBean;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

/**
 * @Author: a797s
 * @Date: 2020/1/8 16:33
 * @Desc: 通报详情----
 */
public class NotifyDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notify_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_top_dec)
    private TextView topDesc;
    @ViewById(R.id.tv_change_limit)
    private TextView changeLimit;
    @ViewById(R.id.tv_tel)
    private TextView tel;
    @ViewById(R.id.tv_comment)
    private TextView comment;
    @ViewById(R.id.lv_attach_list)
    private MeasureListView topAttachList;

    // content
    @ViewById(R.id.tv_content_changed)
    private TextView contentChanged;
    @ViewById(R.id.lv_attach_changed)
    private MeasureListView attachChanged;
    @ViewById(R.id.tv_content_changing)
    private TextView contentChanging;
    @ViewById(R.id.lv_attach_changing)
    private MeasureListView attachChanging;
    @ViewById(R.id.tv_content_unchange)
    private TextView contentUnChange;
    @ViewById(R.id.lv_attach_unchange)
    private MeasureListView attachUnChange;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("整改详情");
        NotifyContentBean content = (NotifyContentBean) getIntent().getSerializableExtra("content");

        postDelayed(() -> {
            topDesc.setText(content.getMatter());
            // 整改时限
            changeLimit.setText(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.colorLabel) + "\" font-size=\"12sp\">"
                    + "整改时限 " + "\t" + "</font>" + "<font color=\"" + getResources().getColor(R.color.colorWord) + "\" font-size=\"12sp\">"
                    + content.getChangeTime() + "\t" + "</font>"));
            // 联系电话
            tel.setText(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.colorLabel) + "\" font-size=\"12sp\">"
                    + "联系电话 " + "\t" + "</font>" + "<font color=\"" + getResources().getColor(R.color.colorWord) + "\" font-size=\"12sp\">"
                    + content.getTel() + "\t" + "</font>"));
            // 备注
            if (TextUtils.isEmpty(content.getText())){
                comment.setText("暂无备注");
            }else {
                comment.setText(content.getText());
            }
            // 交通局附件
            ItemAttachListAdapter listAdapter = new ItemAttachListAdapter(NotifyDetailActivity.this, content.getTongFile());
            topAttachList.setAdapter(listAdapter);

            //已整改
            contentChanged.setText(content.getChangeHave());
            ItemAttachListAdapter changedAdapter = new ItemAttachListAdapter(NotifyDetailActivity.this, content.getChangeHaveFile());
            attachChanged.setAdapter(changedAdapter);
            // 整改中
            contentChanging.setText(content.getChangeIng());
            ItemAttachListAdapter changingAdapter = new ItemAttachListAdapter(NotifyDetailActivity.this, content.getChangeIngFile());
            attachChanging.setAdapter(changingAdapter);
            // 未整改
            contentUnChange.setText(content.getChangeNot());
            ItemAttachListAdapter unUhangedAdapter = new ItemAttachListAdapter(NotifyDetailActivity.this, content.getChangeNotFile());
            attachUnChange.setAdapter(unUhangedAdapter);

        }, 500);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            finish();
        }
    }
}
