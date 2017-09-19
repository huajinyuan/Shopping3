package cn.zy.base.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.product.m.Category;

/**
 * Created by gtgs on 17/9/19.
 */

public class SpinnerAdapter extends BaseAdapter {
    private ArrayList<Category> mData;
    private Context mContext;
    private LayoutInflater inflater;

    public SpinnerAdapter(ArrayList<Category> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HandleView handle = null;
        if (null == view) {
            view = inflater.inflate(R.layout.layout_item_spinner, null);
            handle = new HandleView(view);
            view.setTag(handle);
        } else {
            handle = (HandleView) view.getTag();
        }
        handle.tvContent.setText(mData.get(i).getName());

        return view;
    }

    class HandleView {
        TextView tvContent;

        public HandleView(View view) {
            this.tvContent = (TextView) view.findViewById(R.id.tv_content);
        }
    }
}
