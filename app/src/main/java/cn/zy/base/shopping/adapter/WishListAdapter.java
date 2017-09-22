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
import cn.zy.base.shopping.mian.design.IItemAddBack;
import cn.zy.base.shopping.mian.design.m.PublishDesignInfo;
import cn.zy.base.shopping.mian.wishList.IItemclickBack;
import cn.zy.base.shopping.mian.wishList.m.WishInfo;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;


/**
 * Created by  on 2016/9/2.
 */
public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<WishInfo> mData;
    private IItemclickBack itemclickBack;

    public WishListAdapter(Context mContext, ArrayList<WishInfo> mData, IItemclickBack itemclickBack) {
        this.mContext = mContext;
        this.mData = mData;
        this.itemclickBack = itemclickBack;

    }

    @Override
    public AnchorHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wish_item,
                parent, false);
        return new AnchorHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnchorHotViewHolder holder, final int position) {
        WishInfo info = mData.get(position);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DesignInfoActivity.class);
                intent.putExtra("PublishDesignInfo", getInfoFromWishInfo(mData.get(position)));
                mContext.startActivity(intent);
            }
        });
        if (null != info.getImages() && !info.getImages().isEmpty()) {
            Glide.with(mContext).load(info.getImages().get(0)).into(holder.img_pic);
        }

        holder.tv_title.setText(info.getTitle());
        holder.tv_price.setText("$" + info.getPrice_range());
        holder.tv_user.setText(info.getUser());
        holder.tv_action_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemclickBack.backadd(mData.get(position));
            }
        });
        holder.tv_action_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemclickBack.backdelete(mData.get(position));
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rec_tag.setLayoutManager(manager);
        WishListTagAdapter ad = new WishListTagAdapter(mContext, info.getTags());
        holder.rec_tag.setAdapter(ad);

    }

    public PublishDesignInfo getInfoFromWishInfo(WishInfo wishinfo) {
        PublishDesignInfo info = new PublishDesignInfo();
        if (null != wishinfo) {
            info.setId(wishinfo.getPublic_design_id());
            info.setImages(wishinfo.getImages());
            info.setIs_in_wishlist(true);
            info.setPrice_range(wishinfo.getPrice_range());
            info.setTags(wishinfo.getTags());
            info.setTitle(wishinfo.getTitle());
            info.setUser(wishinfo.getUser());
        }
        return info;
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
        TextView tv_action_delete;
        ImageView img_pic;
        RecyclerView rec_tag;

        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            this.tv_user = (TextView) itemView.findViewById(R.id.tv_user);
            this.tv_action_add = (TextView) itemView.findViewById(R.id.tv_action_add);
            this.tv_action_delete = (TextView) itemView.findViewById(R.id.tv_action_delete);
            this.img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
            this.rec_tag = (RecyclerView) itemView.findViewById(R.id.rec_tag);
        }
    }
}
