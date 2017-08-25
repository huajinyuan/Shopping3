package cn.zy.base.shopping.mian.product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.utils.AppUtils;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.widget.SpaceItemDecoration;
import cn.zy.base.shopping.adapter.ProductsAdapter;
import cn.zy.base.shopping.utils.PixelUtil;

public class ProductsActivity extends AppCompatActivity {
    LinearLayoutManager manager;
    ArrayList<String> mData = new ArrayList<>();
    ProductsAdapter adapter;
    @BindView(R.id.rec_bd_contetn)
    public RecyclerView mRec;
    @BindView(R.id.tv_topbar_title)
    TextView tvTitle;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("My Products");
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
            manager = new LinearLayoutManager(this);
            mRec.addItemDecoration(new DividerGridItemDecoration(this));
            adapter = new ProductsAdapter(this, mData);
            mRec.setAdapter(adapter);
            mRec.setLayoutManager(manager);
            int spac = PixelUtil.dp2px(this, 1);
            mRec.addItemDecoration(new SpaceItemDecoration(spac));

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.img_topbar_back})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.img_topbar_back:
                this.finish();
                break;
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
