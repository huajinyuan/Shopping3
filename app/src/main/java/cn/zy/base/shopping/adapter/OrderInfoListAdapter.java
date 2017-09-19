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
import cn.zy.base.shopping.mian.order.m.OrderInfoList;


/**
 * Created by  on 2016/9/2.
 */
public class OrderInfoListAdapter extends RecyclerView.Adapter<OrderInfoListAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<OrderInfoList> mData;

    public OrderInfoListAdapter(Context mContext, ArrayList<OrderInfoList> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public AnchorHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_orderinfo_item,
                parent, false);
        return new AnchorHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnchorHotViewHolder holder, final int position) {
        OrderInfoList info = mData.get(position);
        holder.tv_order_number.setText(info.getOrder_number());
        holder.tv_quantity.setText(info.getQuantity());
        holder.tv_price.setText(info.getSubtotal());
//        holder.tv_name.setText(info.getName());
//        holder.tv_status.setText(info.getStatus());
//        holder.tv_platform.setText(info.getPlatform());
//        holder.tv_quantity.setText(info.getQuantity());
//        holder.tv_subtotal.setText(info.getSubtotal());

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
        TextView tv_order_number;
        TextView tv_quantity;
        TextView tv_price;
        TextView tv_action_delete;

        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            this.tv_order_number = (TextView) itemView.findViewById(R.id.tv_order_number);
            this.tv_quantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            this.tv_action_delete = (TextView) itemView.findViewById(R.id.tv_action_delete);
        }
    }
}
