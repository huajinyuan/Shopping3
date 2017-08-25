package cn.zy.base.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.zy.base.shopping.mian.design.DesignInfoActivity;
import cn.zy.base.shopping.R;


/**
 * Created by  on 2016/9/2.
 */
public class PublicDesignAdapter extends RecyclerView.Adapter<PublicDesignAdapter.AnchorHotViewHolder> {
    private Context mContext;
    private ArrayList<String> mData;

    public PublicDesignAdapter(Context mContext, ArrayList<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public AnchorHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_design_item,
                parent, false);
        return new AnchorHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnchorHotViewHolder holder, final int position) {
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DesignInfoActivity.class);
                mContext.startActivity(intent);
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

        //        TextView actionEdit;
        public AnchorHotViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
//            this.actionEdit = (TextView) itemView.findViewById(R.id.tv_action_edit);
        }
    }
}
