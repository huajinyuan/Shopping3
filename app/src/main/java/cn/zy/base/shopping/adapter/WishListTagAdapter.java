package cn.zy.base.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.design.DesignInfoActivity;
import cn.zy.base.shopping.mian.design.m.PublishDesignInfo;
import cn.zy.base.shopping.mian.wishList.IItemclickBack;
import cn.zy.base.shopping.mian.wishList.m.WishInfo;


/**
 * Created by  on 2016/9/2.
 */
public class WishListTagAdapter extends RecyclerView.Adapter<WishListTagAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<String> mData;

    public WishListTagAdapter(Context mContext, ArrayList<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public AnchorHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wish_tag_item,
                parent, false);
        return new AnchorHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnchorHotViewHolder holder, final int position) {
        holder.tv_tag.setText(mData.get(position));

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
        TextView tv_tag;

        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            this.tv_tag = (TextView) itemView.findViewById(R.id.tv_tag);
        }
    }
}
