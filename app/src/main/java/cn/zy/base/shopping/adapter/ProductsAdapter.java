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
import cn.zy.base.shopping.mian.product.m.ProductInfo;


/**
 * Created by  on 2016/9/2.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<ProductInfo> mData;
    private IItemClickBack clickBack;

    public ProductsAdapter(Context mContext, ArrayList<ProductInfo> mData, IItemClickBack clickBack) {
        this.mContext = mContext;
        this.mData = mData;
        this.clickBack = clickBack;
    }

    @Override
    public AnchorHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_item,
                parent, false);
        return new AnchorHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnchorHotViewHolder holder, final int position) {
        ProductInfo info = mData.get(position);
        holder.tv_name.setText(info.getTitle());
        holder.tv_category.setText(info.getCategory().getName());
        holder.tv_price.setText(info.getPrice_range());
        holder.actionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProductInfoActivity.class);
                intent.putExtra("product", mData.get(position));
                mContext.startActivity(intent);
            }
        });
        holder.actionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBack.itemback(mData.get(position));
            }
        });
        if (null != info.getImages() && !info.getImages().isEmpty()) {

            Glide.with(mContext).load(info.getImages().get(0)).into(holder.img_product_pic);
        }

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
        TextView actionEdit;
        TextView actionDelete;
        TextView tv_name;
        TextView tv_category;
        TextView tv_price;
        ImageView img_product_pic;

        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            this.actionEdit = (TextView) itemView.findViewById(R.id.tv_action_edit);
            this.actionDelete = (TextView) itemView.findViewById(R.id.tv_action_delete);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_category = (TextView) itemView.findViewById(R.id.tv_category);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            this.img_product_pic = (ImageView) itemView.findViewById(R.id.img_product_pic);
        }
    }
}
