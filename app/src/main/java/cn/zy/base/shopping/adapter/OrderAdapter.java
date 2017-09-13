package cn.zy.base.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.order.m.OrderInfo;


/**
 * Created by  on 2016/9/2.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<OrderInfo> mData;

    public OrderAdapter(Context mContext, ArrayList<OrderInfo> mData) {
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
        OrderInfo info = mData.get(position);
        holder.tv_name.setText(info.getName());
        holder.tv_status.setText(info.getStatus());
        holder.tv_platform.setText(info.getPlatform());
        holder.tv_quantity.setText(info.getQuantity());
        holder.tv_subtotal.setText(info.getSubtotal());

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
        TextView tv_name;
        TextView tv_status;
        TextView tv_platform;
        TextView tv_quantity;
        TextView tv_subtotal;

        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            this.tv_platform = (TextView) itemView.findViewById(R.id.tv_platform);
            this.tv_quantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            this.tv_subtotal = (TextView) itemView.findViewById(R.id.tv_subtotal);
        }
    }
}
