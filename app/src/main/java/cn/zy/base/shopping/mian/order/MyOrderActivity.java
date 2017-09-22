package cn.zy.base.shopping.mian.order;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.FragmentOrder;
import cn.zy.base.shopping.utils.AppUtils;

public class MyOrderActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Unbinder unbinder;
    FragmentMyOrder Tab2;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        unbinder = ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mTvTitle.setText("My Order");
        Tab2 = new FragmentMyOrder();
        transaction.add(R.id.frame_content, Tab2);
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        transaction.commitAllowingStateLoss();
    }


    @OnClick({R.id.img_topbar_back})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.img_topbar_back:
                finish();
                break;
        }
    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
