package cn.zy.base.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.order.LogisticsActivity;
import cn.zy.base.shopping.mian.order.m.OrderInfo;
import cn.zy.base.shopping.mian.order.m.OrderInfoList;


/**
 * Created by  on 2016/9/2.
 */
public class OrderInfoListAdapter extends RecyclerView.Adapter<OrderInfoListAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<OrderInfoList> mData;
    private OrderInfo mInfo;

    public OrderInfoListAdapter(Context mContext, ArrayList<OrderInfoList> mData, OrderInfo info) {
        this.mContext = mContext;
        this.mData = mData;
        this.mInfo = info;
    }

    @Override
    public AnchorHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_orderinfo_item,
                parent, false);
        return new AnchorHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnchorHotViewHolder holder, final int position) {
        final OrderInfoList info = mData.get(position);
        holder.tv_order_number.setText(info.getOrder_number());
        holder.tv_quantity.setText(info.getQuantity());
        holder.tv_price.setText(info.getSubtotal());
        holder.tv_action_shipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LogisticsActivity.class);
                intent.putExtra("Group_id", mInfo.getId());
                intent.putExtra("order_id", info.getId());
                mContext.startActivity(intent);
            }
        });
//        if (info.get)

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
        TextView tv_action_shipped;

        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            this.tv_order_number = (TextView) itemView.findViewById(R.id.tv_order_number);
            this.tv_quantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            this.tv_action_delete = (TextView) itemView.findViewById(R.id.tv_action_delete);
            this.tv_action_shipped = (TextView) itemView.findViewById(R.id.tv_action_shipped);
        }
    }
}
