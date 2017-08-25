package cn.zy.base.shopping.mian;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.utils.AppUtils;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    FragmentDashboard Tab1;
    FragmentOrder Tab2;
    FragmentCenter Tab3;
    private Unbinder unbinder;
    @BindView(R.id.activity_main)
    View activity_main;
    @BindView(R.id.img_tab_1)
    ImageView imgTab1;

    @BindView(R.id.img_tab_2)
    ImageView imgTab2;

    @BindView(R.id.img_tab_3)
    ImageView imgTab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //判断版本是否支持沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        unbinder = ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);

    }

    @OnClick({R.id.lin_tab_1, R.id.lin_tab_2, R.id.lin_tab_3})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.lin_tab_1:
                setTabSelection(0);
                break;
            case R.id.lin_tab_2:
                setTabSelection(1);
                break;
            case R.id.lin_tab_3:
                setTabSelection(2);
                break;
        }
    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public void setTabSelection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        clearStatus();
        switch (index) {
            case 0:
                if (null == Tab1) {
                    Tab1 = new FragmentDashboard();
                    transaction.add(R.id.frame_main, Tab1);
                } else {
                    transaction.show(Tab1);
                }
                imgTab1.setImageResource(R.mipmap.ashboard_hover_icon);
                AppUtils.MIUISetStatusBarLightMode(getWindow(), false);
                activity_main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                break;
            case 1:
                if (null == Tab2) {
                    Tab2 = new FragmentOrder();
                    transaction.add(R.id.frame_main, Tab2);
                } else {
                    transaction.show(Tab2);
                }
                imgTab2.setImageResource(R.mipmap.order_hover_icon);
                AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
                activity_main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                break;
            case 2:
                if (null == Tab3) {
                    Tab3 = new FragmentCenter();
                    transaction.add(R.id.frame_main, Tab3);
                } else {
                    transaction.show(Tab3);
                }
                imgTab3.setImageResource(R.mipmap.yaccount_hover_icon);
                AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
                activity_main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                break;
        }

        transaction.commitAllowingStateLoss();
    }

    public void hideFragments(FragmentTransaction transaction) {
        if (Tab1 != null) {
            transaction.hide(Tab1);
        }
        if (Tab2 != null) {
            transaction.hide(Tab2);
        }
        if (Tab3 != null) {
            transaction.hide(Tab3);
        }
    }

    public void clearStatus() {
        imgTab1.setImageResource(R.mipmap.ashboard_normal_icon);
        imgTab2.setImageResource(R.mipmap.order_normal_icon);
        imgTab3.setImageResource(R.mipmap.yaccount_normal_icon);
    }
}
