package cn.zy.base.shopping.mian.center;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.utils.AppUtils;

public class MyPaymentActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_payment);
        unbinder = ButterKnife.bind(this);
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        mTvTitle.setText("My Payment");
    }

    @OnClick({R.id.img_topbar_back})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.img_topbar_back:
                this.finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
