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

import cn.zy.base.shopping.mian.design.DesignInfoActivity;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.design.IItemAddBack;
import cn.zy.base.shopping.mian.design.m.PublishDesignInfo;


/**
 * Created by  on 2016/9/2.
 */
public class PublicDesignAdapter extends RecyclerView.Adapter<PublicDesignAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<PublishDesignInfo> mData;
    private IItemAddBack itemAddBack;

    public PublicDesignAdapter(Context mContext, ArrayList<PublishDesignInfo> mData, IItemAddBack itemAddBack) {
        this.mContext = mContext;
        this.mData = mData;
        this.itemAddBack = itemAddBack;
    }

    @Override
    public AnchorHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_design_item,
                parent, false);
        return new AnchorHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnchorHotViewHolder holder, final int position) {
        PublishDesignInfo info = mData.get(position);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DesignInfoActivity.class);
                intent.putExtra("PublishDesignInfo", mData.get(position));
                mContext.startActivity(intent);
            }
        });
        holder.tv_title.setText(info.getTitle());
        holder.tv_price.setText("$" + info.getPrice_range());
        holder.tv_user.setText(info.getUser());
        if (null != info.getImages() && !info.getImages().isEmpty()) {
            Glide.with(mContext).load(info.getImages().get(0)).into(holder.img_pic);
        }

        if (info.getIs_in_wishlist()) {
            holder.tv_action_add.setVisibility(View.GONE);
            holder.tv_added.setVisibility(View.VISIBLE);
        } else {
            holder.tv_action_add.setVisibility(View.VISIBLE);
            holder.tv_added.setVisibility(View.GONE);
            holder.tv_action_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemAddBack.itemAddBack(mData.get(position));
                }
            });
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
        TextView tv_title;
        TextView tv_price;
        TextView tv_user;
        TextView tv_action_add;
        TextView tv_added;
        ImageView img_pic;

        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            this.tv_user = (TextView) itemView.findViewById(R.id.tv_user);
            this.tv_action_add = (TextView) itemView.findViewById(R.id.tv_action_add);
            this.tv_added = (TextView) itemView.findViewById(R.id.tv_added);
            this.img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
        }
    }
}
