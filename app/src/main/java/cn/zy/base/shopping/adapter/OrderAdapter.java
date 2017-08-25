package cn.zy.base.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.zy.base.shopping.R;


/**
 * Created by  on 2016/9/2.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<String> mData;

    public OrderAdapter(Context mContext, ArrayList<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

//    public BDListAdapter(ArrayList<BDInfo> data, Context context) {
//        this.mContext = context;
//        this.mData = data;
//    }

    @Override
    public AnchorHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_item,
                parent, false);
        return new AnchorHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnchorHotViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class AnchorHotViewHolder extends RecyclerView.ViewHolder {

        View item;

        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
        }
    }
}
