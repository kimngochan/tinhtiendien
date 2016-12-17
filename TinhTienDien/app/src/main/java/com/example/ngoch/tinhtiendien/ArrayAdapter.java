package com.example.ngoch.tinhtiendien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by ngoch on 11/16/2016.
 */

public class ArrayAdapter extends BaseAdapter {
    private List<?> mList;
    private LayoutInflater mLayout;
    private Context mContext;
    private int mResId;

    public ArrayAdapter(List<?> mList, Context mContext, int mRedId) {
        this.mList = mList;
        this.mLayout = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mResId = mRedId;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return ((TienDienKH) mList.get(position)).getmMa();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayout.inflate(mResId, parent, false);
            viewHolder = new ViewHolder(convertView, mContext);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.setData(mList.get(position));
        return convertView;
    }
}
