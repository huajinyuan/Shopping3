package cn.zy.base.shopping.mian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.login.LoginActivity;
import cn.zy.base.shopping.mian.login.m.LoginData;
import cn.zy.base.shopping.utils.ACache;
import cn.zy.base.shopping.utils.ACacheKey;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View view = findViewById(R.id.img_b);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
//                LoginData loginData = (LoginData) ACache.get(SplashActivity.this).getAsObject(ACacheKey.CURRENT_ACCOUNT);
//                if (null!= loginData){
//                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    SplashActivity.this.finish();
//                }else {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
//                }

            }
        }, 3000);
    }
}
