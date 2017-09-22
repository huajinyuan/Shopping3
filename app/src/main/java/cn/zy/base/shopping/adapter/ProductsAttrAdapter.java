package cn.zy.base.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.product.IItemClickBack;
import cn.zy.base.shopping.mian.product.ProductInfoActivity;
import cn.zy.base.shopping.mian.product.m.ProductAttribute;
import cn.zy.base.shopping.mian.product.m.ProductInfo;


/**
 * Created by  on 2016/9/2.
 */
public class ProductsAttrAdapter extends RecyclerView.Adapter<ProductsAttrAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<ProductAttribute> mData;
    private IItemClickBack clickBack;

    public ProductsAttrAdapter(Context mContext, ArrayList<ProductAttribute> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.clickBack = clickBack;
    }

    @Override
    public AnchorHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_attr_item,
                parent, false);
        return new AnchorHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnchorHotViewHolder holder, final int position) {
        ProductAttribute info = mData.get(position);
        holder.tv_key.setText(info.getKey());
        holder.tv_value.setText(info.getValue());

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
        TextView tv_key;
        TextView tv_value;

        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            this.tv_key = (TextView) itemView.findViewById(R.id.tv_key);
            this.tv_value = (TextView) itemView.findViewById(R.id.tv_value);
        }
    }
}
