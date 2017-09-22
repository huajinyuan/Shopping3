package cn.zy.base.shopping.mian.design;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.damnhandy.uri.template.UriTemplate;
import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.GetRequest;
import com.gt.okgo.request.PostRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.center.m.UserInfo;
import cn.zy.base.shopping.mian.design.m.DesignDetail;
import cn.zy.base.shopping.mian.design.m.DesignInfo;
import cn.zy.base.shopping.mian.design.m.PublishDesignInfo;
import cn.zy.base.shopping.mian.product.m.Category;
import cn.zy.base.shopping.mian.product.m.ProductInfo;
import cn.zy.base.shopping.mian.product.m.ProductTypeList;
import cn.zy.base.shopping.mian.wishList.MyWishListActivity;
import cn.zy.base.shopping.mian.wishList.m.WishCount;
import cn.zy.base.shopping.utils.AppUtils;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.bn.CarouselView;
import co.lujun.androidtagview.TagContainerLayout;
import okhttp3.Response;
import rx.Subscriber;

public class DesignInfoActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;
    @BindView(R.id.edt_title)
    TextView edt_title;
    @BindView(R.id.edt_price)
    TextView edt_price;
    @BindView(R.id.tv_category)
    TextView tv_category;
    @BindView(R.id.web_content)
    WebView webContent;
    @BindView(R.id.CarouselView)
    CarouselView mCarouselView;
    private PublishDesignInfo info;
    Context mContext;
    @BindView(R.id.tag_container)
    TagContainerLayout mTag;
    @BindView(R.id.img_topbar_right)
    ImageView imgRight;
    @BindView(R.id.tv_topbar_right)
    TextView tvRight;
    @BindView(R.id.tv_topbar_right_1)
    TextView tvRight_1;
    @BindView(R.id.lin_topbar_right)
    LinearLayout lvRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_info);
        unbinder = ButterKnife.bind(this);
        mContext = this;
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        mTvTitle.setText("Product");
        imgRight.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);
        lvRight.setVisibility(View.VISIBLE);
        tvRight_1.setText("0");
        info = (PublishDesignInfo) getIntent().getSerializableExtra("PublishDesignInfo");
        if (null != info) {
            edt_title.setText(info.getTitle());
            edt_price.setText(info.getPrice_range());
            mCarouselView.setAdapter(new CarouselView.Adapter() {
                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public View getView(final int position) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.ad_item, null);
                    final ImageView imageView = (ImageView) view.findViewById(R.id.image);
                    Glide.with(mContext).load(info.getImages().get(position)).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
                    return view;
                }

                @Override
                public int getCount() {
                    return info.getImages().size();
                }
            });

            setTag(info.getTags());
            getProduct(info.getId());
        }
        getWishCount();
    }

    @OnClick({R.id.img_topbar_back, R.id.lin_topbar_right, R.id.tv_action_add2wishlist, R.id.tv_action_add2products})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.img_topbar_back:
                this.finish();
                break;
            case R.id.tv_action_add2wishlist:
                add2Action(false);
                break;
            case R.id.tv_action_add2products:
                add2Action(true);
                break;
            case R.id.lin_topbar_right:
                Intent intent = new Intent(this, MyWishListActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void add2Action(boolean add2Products) {
        if (null == info) {
            return;
        }
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        params.put("public_design_id", info.getId());
        PostRequest request = null;
        if (add2Products) {
            request = OkGo.post(Config.PUBLISH_DESIGNS_ADD2PRODUCTS).params(params);
        } else {
            request = OkGo.post(Config.PUBLISH_DESIGNS_ADD2WISHLISTS).params(params);
        }
//        GetRequest request = OkGo.get(Config.POST_USERINFO).params(params);
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
                if (response.code() == 200) {
                    getWishCount();
                    ToastUtil.showToast("uccessful", mContext);
                } else {
                    ToastUtil.showToast("Fail", mContext);
                }
            }
        });

    }


    public void getProduct(String designs_id) {
        String url = UriTemplate.fromTemplate(Config.PUBLISH_DESIGNS_BYID)
                .set("designs_id", designs_id)
                .expand();

        HttpParams params = HttpMethods.getInstance().getHttpParams();

        GetRequest request = OkGo.get(url).params(params);
        HttpMethods.getInstance().doGet(request, true).subscribe(new Subscriber<Response>() {
            @Override
            public void onCompleted() {
//                getProductData();
            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.showToast("请求失败，请检查网络", mContext);
            }

            @Override
            public void onNext(Response response) {
                if (response.code() == 200) {
                    DesignDetail in = Parsing.getInstance().ResponseToObject(response, DesignDetail.class).getData();
                    DesignInfo info = in.getDesign();
                    setWebDescription(info.getContent());
                    edt_price.setText(info.getPrice_range());

                    tv_category.setText(info.getCategory());
                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }


    public void setWebDescription(String html) {
        WebSettings settings = webContent.getSettings();
        String encoding = "UTF-8";
        String mimeType = "text/html";
        webContent.loadData(html, mimeType, encoding);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }


    public void setTag(ArrayList<String> tags) {
        mTag.setTags(tags);
    }

    public void getWishCount() {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        GetRequest request = OkGo.get(Config.WISHLIST_COUNT).params(params);
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
                    WishCount count = Parsing.getInstance().ResponseToObject(response, WishCount.class).getData();
                    tvRight_1.setText(count.getCount());

                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });

    }
}
