package cn.zy.base.shopping.mian;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.GetRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.adapter.OrderAdapter;
import cn.zy.base.shopping.base.BaseFragment;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.center.EditInfoActivity;
import cn.zy.base.shopping.mian.center.MyPaymentActivity;
import cn.zy.base.shopping.mian.center.m.UserInfo;
import cn.zy.base.shopping.mian.design.PublicDesignsActivity;
import cn.zy.base.shopping.mian.design.m.Dashboard;
import cn.zy.base.shopping.mian.order.MyOrderActivity;
import cn.zy.base.shopping.mian.order.PendingOrderActivity;
import cn.zy.base.shopping.mian.product.ProductsActivity;
import cn.zy.base.shopping.mian.wishList.MyWishListActivity;
import cn.zy.base.shopping.utils.PixelUtil;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.widget.SpaceItemDecoration;
import okhttp3.Response;
import rx.Subscriber;


/**
 * Created by gtgs on 2016/9/2.
 */
public class FragmentCenter extends BaseFragment {
    @BindView(R.id.img_topbar_back)
    ImageView imgBack;
    @BindView(R.id.img_topbar_right)
    ImageView imgRight;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;
    Context mContext;

    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;

    private Unbinder unbinder;
    public UserInfo info;

    @Override
    public int getLayout() {
        return R.layout.fragment_center;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initData(View view) {
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        mTvTitle.setText("My Account");
        imgBack.setVisibility(View.INVISIBLE);
        imgRight.setImageResource(R.mipmap.editinfo_icon);
        imgRight.setVisibility(View.GONE);
        getUserInfo();
    }

    @OnClick({R.id.img_topbar_right, R.id.lin_balance, R.id.lin_product, R.id.lin_order, R.id.lin_wish_list, R.id.lin_mypayment})
    public void Onclick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.img_topbar_right:
                intent = new Intent(getActivity(), EditInfoActivity.class);
                if (null != info) {
                    intent.putExtra("userInfo", info);
                }
                getActivity().startActivity(intent);
                break;
            case R.id.lin_balance:
                intent = new Intent(getActivity(), MyPaymentActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.lin_product:
                intent = new Intent(getActivity(), ProductsActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.lin_mypayment:
                intent = new Intent(getActivity(), PendingOrderActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.lin_order:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                getActivity().startActivity(intent);
//                ((MainActivity) getActivity()).setTabSelection(1);
                break;
            case R.id.lin_wish_list:
                intent = new Intent(getActivity(), MyWishListActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }


    public void getUserInfo() {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        GetRequest request = OkGo.get(Config.POST_USERINFO).params(params);
        HttpMethods.getInstance().doGet(request, true).subscribe(new Subscriber<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.showToast("请求失败，请检查网络", mContext);
            }

            @Override
            public void onNext(Response response) {
                if (response.code() == 200) {
                    info = Parsing.getInstance().ResponseToObject(response, UserInfo.class).getData();
                    tvName.setText(info.getName());
                    tvEmail.setText(info.getEmail());
                    tvBalance.setText("$" + info.getBalance());

                    Glide.with(mContext).load(info.getAvatar()).asBitmap().error(R.mipmap.photos_icon).centerCrop().into(new BitmapImageViewTarget(imgAvatar) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imgAvatar.setImageDrawable(circularBitmapDrawable);
                        }
                    });

                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }
}
