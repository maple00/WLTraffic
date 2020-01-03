package com.rainowood.wltraffic.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseFragment;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.UserInfoBean;
import com.rainowood.wltraffic.ui.activity.CheckPhoneActivity;
import com.rainowood.wltraffic.ui.activity.LoginActivity;
import com.rainowood.wltraffic.utils.DialogUtils;

/**
 * @Author: a797s
 * @Date: 2019/12/21 15:41
 * @Desc: 个人中心
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    @SuppressLint("StaticFieldLeak")
    private static PersonalFragment fragment = new PersonalFragment();

    public static PersonalFragment newInstance(Bundle bundle) {
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_personal;
    }

    private UserInfoBean userInfo;

    private DialogUtils dialog;

    // 退出登录
    TextView tvLogout;

    @Override
    protected void initView(View view) {
        ImageView logoutIv = view.findViewById(R.id.iv_logout);
        logoutIv.setOnClickListener(this);
        logoutFalg = true;
        tvLogout = view.findViewById(R.id.tv_logout);
        tvLogout.setOnClickListener(this);
        TextView changeTel = view.findViewById(R.id.tv_change_tel);
        changeTel.setOnClickListener(this);


        // Logo
        ImageView showImage = view.findViewById(R.id.iv_show_image);
        Glide.with(view)
                .load(Contants.BASE_URI + "img/IDCardFront/LEE129055049EK.jpg")
                .apply(RequestOptions.bitmapTransform(new CircleCrop()).circleCrop())
                .into(showImage);
        // 公司
        TextView company = view.findViewById(R.id.tv_company);
        company.setText(userInfo.getCompanyName());
        // 姓名
        TextView userName = view.findViewById(R.id.tv_name);
        userName.setText(userInfo.getAdName());
        /*
        性别与负责人根据登录的人员的类型判定
        如果是平台只显示性别，其他的显示type
        平台中，没有公司地址
         */
        if ("平台".equals(userInfo.getType())){
            // 性别
            ImageView sex = view.findViewById(R.id.iv_sex);
            sex.setVisibility(View.VISIBLE);
            if ("男".equals(userInfo.getSex())){
                sex.setImageResource(R.drawable.ic_icon_man);
            }else if ("女".equals(userInfo.getSex())){
                sex.setImageResource(R.drawable.ic_icon_woman);
            }else {
                sex.setVisibility(View.GONE);
            }
            // 公司地址
            LinearLayout companyAddress = view.findViewById(R.id.ll_company_position);
            companyAddress.setVisibility(View.GONE);
        }else {
            // 负责人
            TextView type = view.findViewById(R.id.tv_type);
            type.setVisibility(View.VISIBLE);
            type.setText("负责人");
            // 公司地址
            TextView companyPosition = view.findViewById(R.id.tv_company_position);
            companyPosition.setText(userInfo.getAddress());
        }
        // 职位
        TextView post = view.findViewById(R.id.tv_post);
        post.setText(userInfo.getDepartmentName());
        // 电话
        TextView tel = view.findViewById(R.id.tv_user_tel);
        tel.setText(userInfo.getAdtel());
    }

    @Override
    protected void initData(Context mContext) {
        // 获取数据
        userInfo = (UserInfoBean)fragment.getArguments().getSerializable("userInfo");
    }

    // 退出登录显示
    private boolean logoutFalg;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_logout:        // 退出登录
                if (logoutFalg) {
                    tvLogout.setVisibility(View.VISIBLE);
                } else {
                    tvLogout.setVisibility(View.GONE);
                }
                logoutFalg = !logoutFalg;
                break;
            case R.id.tv_logout:
                tvLogout.setVisibility(View.GONE);
                dialog = new DialogUtils(getActivity(), "登录中");
                dialog.showDialog();
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PersonalFragment.this.getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                }, 1000);
                break;
            case R.id.tv_change_tel:        // 修改电话
                Contants.CHANGEFLAG = 1;
                startActivity(CheckPhoneActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tvLogout.setVisibility(View.GONE);
        if (dialog != null){
            dialog.dismissDialog();
        }
    }
}
