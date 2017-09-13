package cn.zy.base.shopping.mian.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
import cn.zy.base.shopping.mian.login.m.LoginData;
import cn.zy.base.shopping.utils.ACache;
import cn.zy.base.shopping.utils.ACacheKey;
import cn.zy.base.shopping.utils.ToastUtil;
import okhttp3.Response;
import rx.Subscriber;

public class LoginActivity extends AppCompatActivity {
    private Unbinder unbinder;
    Context mContext;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick({R.id.tv_login, R.id.tv_register})
    public void OnClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_login:
                doLogin();
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

    public void doLogin()
    {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        params.put("grant_type", "password");
        params.put("client_id", Config.client_id);
        params.put("client_secret", Config.client_secret);
        params.put("username", edtEmail.getText().toString());
        params.put("password", edtPassword.getText().toString());

        final PostRequest request = OkGo.post(Config.POST_LOGIN).params(params);
        HttpMethods.getInstance().doPost(request, true).subscribe(new Subscriber<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.showToast("请求失败，请检查网络", mContext);
            }

            @Override
            public void onNext(Response response) {
                if (response.code()==200){
                    LoginData loginData = Parsing.getInstance().ResponseToObject(response,LoginData.class).getData();
                    ACache.get(LoginActivity.this).put(ACacheKey.CURRENT_ACCOUNT,loginData);
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtil.showToast("Fail",mContext);

                }
            }
        });
    }
}
