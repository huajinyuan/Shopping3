package cn.zy.base.shopping.mian;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.adapter.OrderAdapter;
import cn.zy.base.shopping.base.BaseFragment;
import cn.zy.base.shopping.mian.center.EditInfoActivity;
import cn.zy.base.shopping.mian.center.MyPaymentActivity;
import cn.zy.base.shopping.mian.design.PublicDesignsActivity;
import cn.zy.base.shopping.mian.product.ProductsActivity;
import cn.zy.base.shopping.utils.PixelUtil;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.widget.SpaceItemDecoration;


/**
 * Created by gtgs on 2016/9/2.
 */
public class FragmentCenter extends BaseFragment {
    @BindView(R.id.img_topbar_back)
    ImageView imgBack;
    @BindView(R.id.img_topbar_right)
    ImageView imgRight;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;


    private Unbinder unbinder;

    @Override
    public int getLayout() {
        return R.layout.fragment_center;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initData(View view) {
        unbinder = ButterKnife.bind(this, view);
        mTvTitle.setText("My Account");
        imgBack.setImageResource(R.mipmap.set_icon);
        imgRight.setImageResource(R.mipmap.editinfo_icon);
        imgRight.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.img_topbar_right, R.id.lin_product, R.id.lin_order, R.id.lin_wish_list, R.id.lin_mypayment})
    public void Onclick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.img_topbar_right:
                intent = new Intent(getActivity(), EditInfoActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.lin_product:
                intent = new Intent(getActivity(), ProductsActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.lin_mypayment:
                intent = new Intent(getActivity(), MyPaymentActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.lin_order:
                ((MainActivity) getActivity()).setTabSelection(1);
                break;
            case R.id.lin_wish_list:
                intent = new Intent(getActivity(), PublicDesignsActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
