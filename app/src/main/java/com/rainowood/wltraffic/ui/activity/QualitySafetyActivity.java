package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.AttachBean;
import com.rainowood.wltraffic.domain.QualityBean;
import com.rainowood.wltraffic.domain.QualitySafeDetailBean;
import com.rainowood.wltraffic.domain.SubItemLabelBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.ImageAdapter;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainowood.wltraffic.ui.adapter.QualitySafeAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureGridView;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/24 13:35
 * @Desc: 质量安全
 */
public class QualitySafetyActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_quality_safe_main;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.lv_quality_content)
    private MeasureListView qualityContent;
    @ViewById(R.id.tv_title_safe)
    private TextView safeTitle;

    @ViewById(R.id.tv_title_field)
    private TextView fieldTitle;
    @ViewById(R.id.ll_construction_content)
    private LinearLayout wordContent;
    @ViewById(R.id.tv_label_field)
    private TextView labelField;
    @ViewById(R.id.tv_label_content)
    private TextView mContentField;
    @ViewById(R.id.tv_query_field)
    private TextView querField;
    @ViewById(R.id.lv_field_test)
    private MeasureListView mFieldTest;

    @ViewById(R.id.tv_title_quality)
    private TextView qualityTitle;
    @ViewById(R.id.ll_quality_content)
    private LinearLayout qualityLabel;
    @ViewById(R.id.tv_label_content_quality)
    private TextView contentQuality;
    @ViewById(R.id.tv_query_quality)
    private TextView queryQuality;
    @ViewById(R.id.gv_img)
    private MeasureGridView gvImg;

    private DialogUtils dialog;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("质量安全管理");
        safeTitle.setText("质量安全检查及整改情况");

        // 外业检测
        fieldTitle.setText("外业检测");
        labelField.setText(label.getTitle());
        mContentField.setText(label.getContent());

        setOnClickAndShowDetail(mContentField, wordContent, querField, fieldTitle.getText().toString().trim());

        ItemAttachListAdapter wordAdapter = new ItemAttachListAdapter(this, mSubItemWordList);
        mFieldTest.setAdapter(wordAdapter);
        wordAdapter.notifyDataSetChanged();

        // 质量检查
        qualityTitle.setText("质量鉴定(检测)意见");
        contentQuality.setText(labelQuality.getContent());
        // 查看全文
        setOnClickAndShowDetail(contentQuality, qualityLabel, queryQuality, qualityTitle.getText().toString().trim());

        ImageAdapter imageAdapter = new ImageAdapter(this, imgsList);
        gvImg.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();

        // 查看大图
        imageAdapter.setImgClick(new ImageAdapter.ImgOnClickListener() {
            @Override
            public void imgClick(int position) {
                ImageActivity.start(getActivity(), imgsList, position);
            }
        });
    }

    private List<QualityBean> mSafeList;
    private SubItemLabelBean label;

    private List<AttachBean> mSubItemWordList;
    private String[] mBackTitles = {"外业检测报告", "武隆交委计[2016]15号2016.07.20", "外业检测报告"};

    private SubItemLabelBean labelQuality;

    private ArrayList<String> imgsList;
    private String[] imgPath = {"https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png",
            "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png"};

    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();
        // 请求数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestPost.getItemQSManagerData(Contants.ITEM_ID, QualitySafetyActivity.this);
            }
        }).start();

        // 外业检测
        label = new SubItemLabelBean();
        label.setTitle("2019.12.17申请");
        label.setContent("长安华都·华韵苑15号楼工程为长安华都小区二期工程中的一幢，正负零下为地下人防工程，建筑面积1807㎡为住宅楼，地上11+1层建筑面积16437㎡住宅楼。结构为：基础独立柱基，带形基础，剪力墙–薄壁框架，全高40.05m，平面布置为平行三个单元，每层12户，顶上11+1为跃层，其余为平层加局部错层，外形设...");
        // 文档列表
        mSubItemWordList = new ArrayList<>();
        for (int j = 0; j < mBackTitles.length; j++) {
            AttachBean mSubItemWord = new AttachBean();
            mSubItemWord.setName(mBackTitles[j]);
            mSubItemWordList.add(mSubItemWord);
        }
        // 质量检测
        labelQuality = new SubItemLabelBean();
        labelQuality.setContent("长安华都·华韵苑15号楼工程为长安华都小区二期工程中的一幢，正负零下为地下人防工程，建筑面积1807㎡为住宅楼，地上11+1层建筑面积16437㎡住宅楼。结构为：基础独立柱基，带形基础，剪力墙–薄壁框架，全高40.05m，平面布置为平行三个单元，每层12户，顶上11+1为跃层，其余为平层加局部错层，外形设...");
        imgsList = new ArrayList<>();
        imgsList.addAll(Arrays.asList(imgPath));
    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismissDialog();
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                openActivity(ProjectDetailActivity.class);
                break;
            default:
                break;

        }
    }

    /**
     * 文档的点击和展示method
     *
     * @param text        需要展示的文档
     * @param clickRegion 可以点击的区域
     * @param hideRegion  隐藏的区域
     */
    private void setOnClickAndShowDetail(final TextView text, final LinearLayout clickRegion, final TextView hideRegion, final String title) {
        text.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = text.getLineCount();
                if (lineCount > 5) {
                    clickRegion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //toast("点击了");
                            Intent intent = new Intent(QualitySafetyActivity.this, QuailtySafeDetailActivity.class);
                            Bundle bundle = new Bundle();
                            QualitySafeDetailBean qualitySafeDetail;
                            if ("外业检测".equals(title)) {
                                qualitySafeDetail = new QualitySafeDetailBean();
                                qualitySafeDetail.setTitle(labelField.getText().toString().trim());
                                qualitySafeDetail.setContent(mContentField.getText().toString().trim());
                                qualitySafeDetail.setmWordList(mSubItemWordList);
                                bundle.putSerializable("quality", qualitySafeDetail);
                            }

                            if ("质量鉴定(检测)意见".equals(title)) {
                                qualitySafeDetail = new QualitySafeDetailBean();
                                qualitySafeDetail.setContent(contentQuality.getText().toString().trim());
                                qualitySafeDetail.setmImgList(imgsList);
                                bundle.putSerializable("quality", qualitySafeDetail);
                            }
                            intent.putExtras(bundle);
                            startActivity(intent, bundle);
                        }
                    });
                } else {
                    hideRegion.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {
            Map<String, String> data = JsonParser.parseJSONObject(body.get("data"));
            // 质量安全及整改情况
            mSafeList = JsonParser.parseJSONArray(QualityBean.class, data.get("allNews"));
            // 外业检测

            dismissDialog();
            Message msg = new Message();
            msg.what = 0x1324;
            mHandler.sendMessage(msg);
        } else {
            dismissDialog();
            toast(body.get("warn"));
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0x1324:
                    // 质量安全检查及整改情况
                    QualitySafeAdapter safeAdapter = new QualitySafeAdapter(QualitySafetyActivity.this, mSafeList);
                    qualityContent.setAdapter(safeAdapter);
                    safeAdapter.setContentOnClick(new QualitySafeAdapter.IContentOnClick() {
                        @Override
                        public void contentClick(int position) {
                            //质量安全及整改情况
                            Intent intent = new Intent(QualitySafetyActivity.this, RectificationActivity.class);
                            intent.putExtra("id", mSafeList.get(position).getId());
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };
}
