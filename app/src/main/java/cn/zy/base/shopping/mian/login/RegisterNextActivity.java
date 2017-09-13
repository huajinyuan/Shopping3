package cn.zy.base.shopping.mian.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.PostRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.MainActivity;
import cn.zy.base.shopping.utils.AppUtils;
import cn.zy.base.shopping.utils.ToastUtil;
import okhttp3.Response;
import rx.Subscriber;

public class RegisterNextActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;
    @BindView(R.id.edt_phone)
    EditText phone;
    @BindView(R.id.edt_country)
    EditText country;
    @BindView(R.id.edt_url)
    EditText edturl;
    @BindView(R.id.edt_platform)
    EditText platform;
    @BindView(R.id.edt_manthly)
    EditText manthly;
    @BindView(R.id.edt_customers_from)
    EditText customersFrom;
    String name;
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_next);
        unbinder = ButterKnife.bind(this);
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        mTvTitle.setText("Register");
        name = getIntent().getStringExtra("name");
        email =getIntent().getStringExtra("email");
        password =getIntent().getStringExtra("password");
    }

    @OnClick({R.id.img_topbar_back,R.id.tv_action_back,R.id.tv_action_applay})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.img_topbar_back:
                this.finish();
                break;
            case R.id.tv_action_back:
                this.finish();
                break;
            case R.id.tv_action_applay:
                doApply();
                break;
        }
    }
    public void doApply()
    {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        params.put("password_confirmation", password);
        params.put("phone", phone.getText().toString());
        params.put("country", country.getText().toString());
        params.put("store_website", edturl.getText().toString());
        params.put("store_platform", platform.getText().toString());
        params.put("store_sales", manthly.getText().toString());
        params.put("store_client_area",customersFrom.getText().toString());
        final PostRequest request = OkGo.post(Config.POST_REG).params(params);
        HttpMethods.getInstance().doPost(request, true).subscribe(new Subscriber<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.showToast("请求失败，请检查网络", RegisterNextActivity.this);
            }

            @Override
            public void onNext(Response response) {
                if (response.code()==200){
                   Intent intent = new Intent(RegisterNextActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtil.showToast("Fail",RegisterNextActivity.this);

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
