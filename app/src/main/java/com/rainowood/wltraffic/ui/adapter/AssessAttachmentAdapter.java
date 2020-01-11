package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.AssessBean;
import com.rainowood.wltraffic.ui.activity.ImageActivity;
import com.rainowood.wltraffic.ui.activity.TbsActivity;
import com.rainwood.tools.toast.ToastUtils;

import java.io.File;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @Author: a797s
 * @Date: 2019/12/24 17:33
 * @Desc: 展示所有图片adapter
 */
public class AssessAttachmentAdapter extends BaseAdapter {

    private Context mContext;
    private List<AssessBean> mList;
    private String download = Environment.getExternalStorageDirectory() + "/download/test/document/";

    public AssessAttachmentAdapter(Context mContext, List<AssessBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public AssessBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_assess_manager, parent, false);
            holder = new ViewHolder();

            holder.tv_word_title = convertView.findViewById(R.id.tv_word_title);
            holder.tv_update_time = convertView.findViewById(R.id.tv_update_time);
            holder.ll_download = convertView.findViewById(R.id.ll_download);
            holder.ll_preview = convertView.findViewById(R.id.ll_preview);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_word_title.setText(getItem(position).getName());
        holder.tv_update_time.setText(getItem(position).getUpdateTime() + " 更新");

        holder.ll_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mList.get(position).getType())) {
                    ToastUtils.show("文件地址错误！请确认");
                    return;
                }
                String path = Contants.BASE_URI + mList.get(position).getSrc();
                int i = path.lastIndexOf("/");
                String docName = path.substring(i);
                String[] split = path.split("\\/");
                String s = split[split.length - 4] + split[split.length - 3] + split[split.length - 2] + split[split.length - 1];
                //判断是否在本地/[下载/直接打开]
                File docFile = new File(download, docName);
                if (docFile.exists()) {
                    //存在本地;
                    ToastUtils.show(mList.get(position).getName() + "本地已经存在\n" + docFile.getAbsolutePath());
                } else {
                    OkGo.get(path)//
                            .tag(this)//
                            .execute(new FileCallback(download, docName) {
                                @Override
                                public void onSuccess(File file, Call call, Response response) {
                                    // file 即为文件数据，文件保存在指定目录
                                    ToastUtils.show(mList.get(position).getName() + "下载成功 \n" + file.getAbsolutePath());
                                }

                                @Override
                                public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                                    //这里回调下载进度(该回调在主线程,可以直接更新ui)
                                    Log.d("print", "总大小---" + totalSize + "---文件下载进度---" + progress);
                                }
                            });
                }

            }
        });

        holder.ll_preview.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mList.get(position).getType())) {
                ToastUtils.show("文件地址错误！请确认");
                return;
            }
            // 根据不同的文件类型进行预览
            if ("png".equals(mList.get(position).getType())
                    || "jpg".equals(mList.get(position).getType())) {        // 加载大图
                ImageActivity.start(mContext, Contants.BASE_URI + mList.get(position).getSrc());
            } else {     // 加载文档--- 需要打开权限
                Intent intent = new Intent(mContext, TbsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("path", Contants.BASE_URI + mList.get(position).getSrc());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        notifyDataSetChanged();
        return convertView;
    }


    private class ViewHolder {
        private TextView tv_word_title, tv_update_time;
        private LinearLayout ll_download, ll_preview;
    }
}
