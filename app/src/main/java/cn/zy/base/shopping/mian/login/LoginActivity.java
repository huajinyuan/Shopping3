package cn.zy.base.shopping.mian.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_login, R.id.tv_register})
    public void OnClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_login:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.tv_register:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
