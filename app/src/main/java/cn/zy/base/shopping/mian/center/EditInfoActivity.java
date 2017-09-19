package cn.zy.base.shopping.mian.center;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.mian.center.m.UserInfo;
import cn.zy.base.shopping.utils.AppUtils;

public class EditInfoActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_store_url)
    EditText edtStoreUrl;
    public UserInfo info;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        unbinder = ButterKnife.bind(this);
        mContext = this;
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        mTvTitle.setText("My Account");
        info = (UserInfo) getIntent().getSerializableExtra("userInfo");
        if (null != info) {
            edtName.setText(info.getName());
            edtEmail.setText(info.getEmail());
//            edtStoreUrl.setText(info.getBalance());
            Glide.with(mContext).load(info.getAvatar()).asBitmap().error(R.mipmap.photos_icon).centerCrop().into(new BitmapImageViewTarget(imgAvatar) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imgAvatar.setImageDrawable(circularBitmapDrawable);
                }
            });
        }

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
