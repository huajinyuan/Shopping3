package cn.zy.base.shopping.mian.design;

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

public class DesignInfoActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_info);
        unbinder = ButterKnife.bind(this);
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        mTvTitle.setText("Product");
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
