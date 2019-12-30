package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.ui.adapter.AttachmentListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.Arrays;

/**
 * @Author: a797s
 * @Date: 2019/12/30 11:03
 * @Desc: 质疑答疑、补漏列表详情
 */
public class TenderListDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tender_list_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;

    @ViewById(R.id.tv_content_title)
    private TextView title;
    @ViewById(R.id.lv_question)
    private MeasureListView question;
    @ViewById(R.id.lv_answer)
    private MeasureListView answer;
    @ViewById(R.id.tv_content)
    private TextView content;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);

        String key = (String) getIntent().getCharSequenceExtra("key");
        if ("question".equals(key)){        // 质疑答疑
            pageTitle.setText("质疑答疑详情");
            title.setText(infos[0]);
            content.setText(infos[1]);
            // 质疑
            AttachmentListAdapter adapterQ = new AttachmentListAdapter(this, Arrays.asList(attachsQ));
            question.setAdapter(adapterQ);
            adapterQ.setAttachListener(new AttachmentListAdapter.OnAttachListener() {
                @Override
                public void onAttachDownloadClick(int position) {
                    toast("下载");
                }

                @Override
                public void onAttachPreviewClick(int position) {
                    toast("预览");
                }
            });

            AttachmentListAdapter adapterA = new AttachmentListAdapter(this, Arrays.asList(attachsA));
            answer.setAdapter(adapterQ);
            adapterA.setAttachListener(new AttachmentListAdapter.OnAttachListener() {
                @Override
                public void onAttachDownloadClick(int position) {
                    toast("下载");
                }

                @Override
                public void onAttachPreviewClick(int position) {
                    toast("预览");
                }
            });
        }

        if ("bare".equals(key)){            // 补漏列表
            pageTitle.setText("补遗详情");

            title.setText(infos1[0]);
            content.setText(infos1[1]);
            // 质疑
            AttachmentListAdapter adapterQ = new AttachmentListAdapter(this, Arrays.asList(aaa1));
            question.setAdapter(adapterQ);
            adapterQ.setAttachListener(new AttachmentListAdapter.OnAttachListener() {
                @Override
                public void onAttachDownloadClick(int position) {
                    toast("下载");
                }

                @Override
                public void onAttachPreviewClick(int position) {
                    toast("预览");
                }
            });

            AttachmentListAdapter adapterA = new AttachmentListAdapter(this, Arrays.asList(aaa2));
            answer.setAdapter(adapterQ);
            adapterA.setAttachListener(new AttachmentListAdapter.OnAttachListener() {
                @Override
                public void onAttachDownloadClick(int position) {
                    toast("下载");
                }

                @Override
                public void onAttachPreviewClick(int position) {
                    toast("预览");
                }
            });
        }
    }

    /*
    模拟数据
     */
    private String[] infos = {"评标办法前附表2.2.3条评标偏差率计算公式修评标办法前附表", "这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容"};
    private String[] attachsQ = {"关于施工造成的城市污染的问....doc", "关于施工造成的城市污染的问....doc", "关于施工造成的城市污染的问....doc"};
    private String[] attachsA = {"施工造成的城市污染的解决方....doc", "施工造成的城市污染的解决方....doc","施工造成的城市污染的解决方....doc"};

    private String[] infos1 = {"评标办法前附表2.2.3条评标偏差率计算公式修评标办法前附表", "这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容"};
    private String[] aaa1 = {"关于施工造成的城市污染的问....doc","关于施工造成的城市污染的问....doc", "关于施工造成的城市污染的问....doc"};
    private String[] aaa2 = {"施工造成的城市污染的解决方....doc", "施工造成的城市污染的解决方....doc","施工造成的城市污染的解决方....doc"};

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
