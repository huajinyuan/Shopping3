package cn.zy.base.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.order.IOrderitemBack;
import cn.zy.base.shopping.mian.order.OrderInfoActivity;
import cn.zy.base.shopping.mian.order.m.OrderInfo;
import cn.zy.base.shopping.utils.StringUtils;
import cn.zy.base.shopping.utils.ToastUtil;


/**
 * Created by  on 2016/9/2.
 */
public class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<OrderInfo> mData;
    private IOrderitemBack mBack;

    public PendingOrderAdapter(Context mContext, ArrayList<OrderInfo> mData, IOrderitemBack back) {
        this.mContext = mContext;
        this.mData = mData;
        this.mBack = back;
    }

    @Override
    public AnchorHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pending_order_item,
                parent, false);
        return new AnchorHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnchorHotViewHolder holder, final int position) {
        final OrderInfo info = mData.get(position);
        holder.tv_name.setText(info.getName());
        holder.tv_status.setText(info.getStatus());
        holder.tv_platform.setText(info.getPlatform());
        holder.tv_quantity.setText(info.getQuantity());

        String price = "";

        if (StringUtils.isNotEmpty(info.getCost())) {
            price = price + info.getCost();
        }
        if (StringUtils.isNotEmpty(info.getShipping())) {
            if (StringUtils.isNotEmpty(price)) {
                price = price + " + " + info.getShipping();
            } else {
                price = info.getShipping();

            }
        }
        if (StringUtils.isEmpty(price)) {
            price = info.getSubtotal();
        }
        holder.tv_subtotal.setText(price);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, OrderInfoActivity.class);
                intent.putExtra("OrderInfo", mData.get(position));
                mContext.startActivity(intent);

            }
        });
        holder.tv_action_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderInfo order = mData.get(position);
                if (order.getDelivery() == 1 || order.getDelivery() == 2) {
                    if (null != mBack) {
                        mBack.back(mData.get(position));
                    }
                } else {
                    ToastUtil.showToast("Please Choose Delivery!", mContext);
                }

            }
        });

        if (info.getDelivery() == 0) {
            holder.tv_DHL.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_radius_edt_bg));
            holder.tv_DHL.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextGrey));
            holder.tv_EMS.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_radius_edt_bg));
            holder.tv_EMS.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextGrey));
        } else if (info.getDelivery() == 1) {
            holder.tv_DHL.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_radius_black));
            holder.tv_DHL.setTextColor(ContextCompat.getColor(mContext, R.color.color_white));
            holder.tv_EMS.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_radius_edt_bg));
            holder.tv_EMS.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextGrey));
        } else {
            holder.tv_EMS.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_radius_black));
            holder.tv_EMS.setTextColor(ContextCompat.getColor(mContext, R.color.color_white));
            holder.tv_DHL.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_radius_edt_bg));
            holder.tv_DHL.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextGrey));
        }

        holder.tv_DHL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mBack) {

                    int d = info.getDelivery();
                    if (d == 1) {
                        d = 0;
                    } else {
                        d = 1;
                    }
                    mBack.CheckDelivery(position, d);
                }

            }
        });
        holder.tv_EMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int d = info.getDelivery();
                if (d == 2) {
                    d = 0;
                } else {
                    d = 2;
                }
                mBack.CheckDelivery(position, d);
            }
        });

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
        TextView tv_action_pay;
        TextView tv_DHL;
        TextView tv_EMS;

        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            this.tv_platform = (TextView) itemView.findViewById(R.id.tv_platform);
            this.tv_quantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            this.tv_subtotal = (TextView) itemView.findViewById(R.id.tv_subtotal);
            this.tv_action_pay = (TextView) itemView.findViewById(R.id.tv_action_pay);
            this.tv_DHL = (TextView) itemView.findViewById(R.id.tv_DHL);
            this.tv_EMS = (TextView) itemView.findViewById(R.id.tv_EMS);
        }
    }
}
