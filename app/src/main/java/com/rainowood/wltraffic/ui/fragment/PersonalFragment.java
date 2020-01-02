package com.rainowood.wltraffic.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseFragment;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.UserInfoBean;
import com.rainowood.wltraffic.ui.activity.CheckPhoneActivity;

/**
 * @Author: a797s
 * @Date: 2019/12/21 15:41
 * @Desc: 个人中心
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int initLayout() {
        return R.layout.fragment_personal;
    }

    private UserInfoBean userInfo;

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
                .load(userInfo.getLogoAddress())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()).circleCrop())
                .into(showImage);
        // 公司
        TextView company = view.findViewById(R.id.tv_company);
        company.setText(userInfo.getCompany());
        // 姓名
        TextView userName = view.findViewById(R.id.tv_name);
        userName.setText(userInfo.getUserName());
        /*
        性别与负责人根据登录的人员的类型判定
         */
        // 性别
        ImageView sex = view.findViewById(R.id.iv_sex);
        sex.setImageResource(R.drawable.ic_icon_man);
        // 负责人
        TextView type = view.findViewById(R.id.tv_type);
        type.setText("负责人");
        // 职位
        TextView post = view.findViewById(R.id.tv_post);
        post.setText(userInfo.getPost());
        // 公司地址
        TextView companyPosition = view.findViewById(R.id.tv_company_position);
        companyPosition.setText(userInfo.getCompanyPosition());
        // 电话
        TextView tel = view.findViewById(R.id.tv_user_tel);
        tel.setText(userInfo.getTel());
    }

    @Override
    protected void initData(Context mContext) {
        // 模拟数据
        userInfo = new UserInfoBean();
        userInfo.setUserName("罗钟石");
        userInfo.setUserSex("男");
        userInfo.setLogoAddress("https://www.baidu.com/img/bd_logo.png");
        userInfo.setCompany("武隆交通局");
        userInfo.setPost("工程部·项目经理");
        userInfo.setCompanyPosition("重庆市南岸区弹子石国际商务大厦A座22-2");
        userInfo.setTel("13512270415");

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
                showDialog();
                break;
            case R.id.tv_change_tel:        // 修改电话
                Contants.CHANGEFLAG = 1;
                Intent intent = new Intent(this.getActivity(), CheckPhoneActivity.class);
                intent.putExtra("key", "changeTel");
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    private void showDialog() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tvLogout.setVisibility(View.GONE);
    }

}
