package cn.zy.base.shopping.mian.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.utils.AppUtils;

public class RegisterActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassWord;
    @BindView(R.id.edt_password2)
    EditText edtPassWord2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        unbinder = ButterKnife.bind(this);
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        mTvTitle.setText("Register");
    }

    @OnClick({R.id.img_topbar_back,R.id.tv_action_next})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.img_topbar_back:
                this.finish();
                break;
            case R.id.tv_action_next:
                doNext();
                break;
        }
    }
    public void doNext()
    {
        Intent intent = new Intent(this,RegisterNextActivity.class);
        intent.putExtra("name",edtName.getText().toString());
        intent.putExtra("email",edtEmail.getText().toString());
        intent.putExtra("password",edtPassWord.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
