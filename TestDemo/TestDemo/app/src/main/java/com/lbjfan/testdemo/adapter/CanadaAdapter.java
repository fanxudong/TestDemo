package com.lbjfan.testdemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lbjfan.testdemo.R;
import com.lbjfan.testdemo.model.CanadaInfo;
import com.lbjfan.testdemo.util.ImageLoadUtils;

import java.util.List;

/**
 * Created by ${lbjfan} on 17-2-28.
 */
public class CanadaAdapter extends BaseAdapter {

    public List<CanadaInfo> canadaInfoList;
    private Context context;
    private int width;

    public CanadaAdapter(Context context) {
        this.context = context;
        width = (int) context.getResources().getDimension(R.dimen.hundred_fifty_dip);
    }

    public void setCanadaInfoList(List<CanadaInfo> canadaInfoList) {
        this.canadaInfoList = canadaInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (null == canadaInfoList || canadaInfoList.size() == 0) {
            return 0;
        }
        return canadaInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.view_test_act_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvDes = (TextView) convertView.findViewById(R.id.tvDes);
            viewHolder.ivHref = (ImageView) convertView.findViewById(R.id.ivHref);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CanadaInfo canadaInfo = canadaInfoList.get(position);
        if (!TextUtils.isEmpty(canadaInfo.getTitle())) {
            viewHolder.tvTitle.setText(canadaInfo.getTitle());
        }
        if (!TextUtils.isEmpty(canadaInfo.getDescription())) {
            viewHolder.tvDes.setText(canadaInfo.getDescription());
        }
        if(TextUtils.isEmpty(canadaInfo.getImageHref())){
            viewHolder.ivHref.setVisibility(View.GONE);
        }else {
            viewHolder.ivHref.setVisibility(View.VISIBLE);
            ImageLoadUtils.showImage(canadaInfo.getImageHref(),viewHolder.ivHref,width);
        }
        return convertView;
    }

    class ViewHolder {
        private TextView tvTitle;
        private TextView tvDes;
        private ImageView ivHref;

        public ViewHolder() {

        }
    }
}
