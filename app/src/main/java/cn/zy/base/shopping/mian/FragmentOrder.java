package cn.zy.base.shopping.mian;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.zy.base.shopping.base.BaseFragment;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.utils.PixelUtil;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.widget.SpaceItemDecoration;
import cn.zy.base.shopping.adapter.OrderAdapter;


/**
 * Created by gtgs on 2016/9/2.
 */
public class FragmentOrder extends BaseFragment {
    LinearLayoutManager manager;
    ArrayList<String> mData = new ArrayList<>();
    OrderAdapter adapter;
    @BindView(R.id.rec_bd_contetn)
    public RecyclerView mRec;
    @BindView(R.id.img_topbar_back)
    ImageView imgBack;
    @BindView(R.id.img_topbar_right)
    ImageView imgRight;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;

    private Unbinder unbinder;

    @Override
    public int getLayout() {
        return R.layout.fragment_order;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initData(View view) {
        unbinder = ButterKnife.bind(this, view);
        imgBack.setVisibility(View.GONE);
        imgRight.setVisibility(View.VISIBLE);
        mTvTitle.setText("Order");
        ArrayList<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        setData(data);
    }

    public void setData(ArrayList<String> data) {
        mData.clear();
        mData.addAll(data);
        if (null == adapter) {
            manager = new LinearLayoutManager(getActivity());
            mRec.addItemDecoration(new DividerGridItemDecoration(getActivity()));
            adapter = new OrderAdapter(getActivity(), mData);
            mRec.setAdapter(adapter);
            mRec.setLayoutManager(manager);
            int spac = PixelUtil.dp2px(this.getActivity(), 8);
            mRec.addItemDecoration(new SpaceItemDecoration(spac));

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
