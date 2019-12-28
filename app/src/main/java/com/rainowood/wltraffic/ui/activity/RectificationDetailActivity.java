package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.ui.adapter.ImageAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureGridView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: a797s
 * @Date: 2019/12/28 10:20
 * @Desc: 整改详情
 */
public class RectificationDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rectification_detail;
    }

    @ViewById(R.id.btn_back)
    private Button brnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_title_content)
    private TextView titleContent;
    @ViewById(R.id.tv_time)
    private TextView rectificationTime;
    @ViewById(R.id.tv_tel)
    private TextView rectificationTel;
    @ViewById(R.id.tv_word_title)
    private TextView wordTitle;
    @ViewById(R.id.ll_download)
    private LinearLayoutCompat download;
    @ViewById(R.id.ll_preview)
    private LinearLayoutCompat preview;


    @ViewById(R.id.tv_rectified_content)
    private TextView rectifiedContent;
    @ViewById(R.id.ll_rectified_area)
    private LinearLayout rectifiedArea;     // 整改点击区域
    @ViewById(R.id.tv_word_title1)
    private TextView wordTitle1;
    @ViewById(R.id.ll_download1)
    private LinearLayoutCompat download1;
    @ViewById(R.id.ll_preview1)
    private LinearLayoutCompat preview1;

    // 整改中
    @ViewById(R.id.tv_rectifying_content)
    private TextView rectifyingContent;
    @ViewById(R.id.tv_query_rectifying)
    private TextView queryRectifying;
    @ViewById(R.id.ll_rectifying_area)
    private LinearLayout rectifityingArea;      // 整改中点击区域
    @ViewById(R.id.gv_rectifying_img)
    private MeasureGridView rectifyingImg;

    // 未整改
    @ViewById(R.id.tv_unrectify_content)
    private TextView unRectifyContent;
    @ViewById(R.id.tv_query_unrectify)
    private TextView queryUnRectify;
    @ViewById(R.id.ll_unrectify_area)
    private LinearLayout unRectifyArea;
    @ViewById(R.id.gv_unrectify_img)
    private MeasureGridView unRectifyImg;

    @Override
    protected void initView() {
        brnBack.setOnClickListener(this);
        pageTitle.setText("整改详情");

        titleContent.setText(titleContents);
        rectificationTime.setText(time);
        rectificationTel.setText(tel);
        wordTitle.setText(wordTitles);
        download.setOnClickListener(this);
        preview.setOnClickListener(this);

        download1.setOnClickListener(this);
        preview1.setOnClickListener(this);
        rectifiedArea.setOnClickListener(this);
        rectifiedContent.setText(rectifiedContents);
        rectifiedContent.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = rectifiedContent.getLineCount();
                if (lineCount < 6){
                    // 如果小于了6行，则隐藏查看全文字样
                }
            }
        });
        wordTitle1.setText(wordTitles1);

        // 整改中
        rectifyingContent.setText(rectifyingContentStr);
        rectifityingArea.setOnClickListener(this);
        setOnClickAndShowDetail(rectifyingContent, rectifityingArea, queryRectifying, null);
        ImageAdapter rectifityingAdapter = new ImageAdapter(this, imgsList);
        rectifyingImg.setAdapter(rectifityingAdapter);
        rectifyingImg.setNumColumns(3);
        rectifityingAdapter.notifyDataSetChanged();

        // 查看大图
        rectifityingAdapter.setImgClick(new ImageAdapter.ImgOnClickListener() {
            @Override
            public void imgClick(int position) {
                ImageActivity.start(getActivity(), imgsList, position);
            }
        });

        // 未整改
        unRectifyContent.setText(unRectifyContentStr);
        unRectifyArea.setOnClickListener(this);
        setOnClickAndShowDetail(unRectifyContent, unRectifyArea, queryUnRectify, null);
        ImageAdapter unRectifyAdapter = new ImageAdapter(this, unRectifyImgsList);
        unRectifyImg.setAdapter(unRectifyAdapter);
        unRectifyImg.setNumColumns(3);
        unRectifyAdapter.notifyDataSetChanged();

        // 查看大图
        unRectifyAdapter.setImgClick(new ImageAdapter.ImgOnClickListener() {
            @Override
            public void imgClick(int position) {
                ImageActivity.start(getActivity(), imgsList, position);
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        // 整改中的图片
        imgsList = new ArrayList<>();
        imgsList.addAll(Arrays.asList(rectifyingImgPath));

        // 未整改的图片
        unRectifyImgsList = new ArrayList<>();
        unRectifyImgsList.addAll(Arrays.asList(unRectifyImgPath));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_download:
                toast("下载");
                break;
            case R.id.ll_preview:
                toast("预览");
                break;
            case R.id.ll_download1:
                toast("下载");
                break;
            case R.id.ll_preview1:
                toast("预览");
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
                            toast("点击了");
                            /*Intent intent = new Intent(QualitySafetyActivity.this, DocumentShowDetailActivity.class);
                            ParagraphListBean paragraph = new ParagraphListBean();
                            paragraph.setTitle(title);
                            paragraph.setContent(text.getText().toString().trim());
                            intent.putExtra("document", paragraph);
                            startActivity(intent);*/
                        }
                    });
                } else {
                    hideRegion.setVisibility(View.GONE);
                }
            }
        });
    }

    /*
        模拟数据
         */
    private String titleContents = "业务操作中，由于经办人员业务知识欠缺风险意识差，未严格执行规章制度，且个别被业务操作中，由于经办人员业务知识欠缺风险意识差，未严格执行规章制度，且个别被业务操作中，由于经办人员业务知识欠缺风险意识差，未严格执行规章制度，且个别被业务操作中，由于经办人员业务知识欠缺风险意识差，未严格执行规章制度。";
    private String time = "整改时限 3个月";
    private String tel = "联系方式  13512270415";
    private String wordTitles = "检查情况附件.doc";

    private String rectifiedContents = "对检查井内存在的垃圾已经清掏；对检查井内缺少爬梯或爬梯位置不对的已经修补；检查井桶周边抹灰不到位的已经修补，未挂设防坠网的已经补挂。";
    private String wordTitles1 = "对检查井内存在的垃圾已经清掏；对检查井内缺少爬梯或爬梯位置不对的已经修补；检查井桶周边抹灰不到位的已经修补，未挂设防坠网的已经补挂。";

    // 整改中
    private String rectifyingContentStr = "对检查井内存在的垃圾已经清掏；对检查井内缺少爬梯或爬梯位置不对的已经修补；检查井桶周边抹灰不到位的已经修补，未挂设防坠网的已经补挂，避免产生安全隐患。对检查井内存在的垃圾已经清掏；对检查井内缺少爬梯或爬梯位置不对的已经修补；检查井桶周边抹灰不到位的已经修补，未挂设防坠网的已经补挂，...";
    private ArrayList<String> imgsList;
    private String[] rectifyingImgPath = {"https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png",
            "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png",
            "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png"};

    // 未整改
    private String unRectifyContentStr = "对检查井内存在的垃圾已经清掏；对检查井内缺少爬梯或爬梯位置不对的已经修补；检查井桶周边抹灰不到位的已经修补，未挂设防坠网的已经补挂，避免产生安全隐患。对检查井内存在的垃圾已经清掏；对检查井内缺少爬梯或爬梯位置不对的已经修补；检查井桶周边抹灰不到位的已经修补，未挂设防坠网的已经补挂，...";
    private ArrayList<String> unRectifyImgsList;
    private String[] unRectifyImgPath = {"https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png",
            "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png",
            "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png",
            "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png",
            "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png", "https://www.baidu.com/img/bd_logo.png"};
}
