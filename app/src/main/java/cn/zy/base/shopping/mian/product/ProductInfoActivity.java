package cn.zy.base.shopping.mian.product;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.damnhandy.uri.template.UriTemplate;
import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.DeleteRequest;
import com.gt.okgo.request.GetRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.adapter.SpinnerAdapter;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.product.m.Category;
import cn.zy.base.shopping.mian.product.m.ProductInfo;
import cn.zy.base.shopping.mian.product.m.ProductTypeList;
import cn.zy.base.shopping.utils.AppUtils;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.bn.CarouselView;
import co.lujun.androidtagview.TagContainerLayout;
import okhttp3.Response;
import rx.Subscriber;

public class ProductInfoActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;
    //    @BindView(R.id.edt_description)
//    EditText edtDescription;
    @BindView(R.id.edt_title)
    EditText edtProjectTitle;
    @BindView(R.id.edt_price)
    EditText edtPrice;
    @BindView(R.id.web_content)
    WebView webContent;
    //    @BindView(R.id.edt_category)
//    EditText edtCategory;
    @BindView(R.id.CarouselView)
    CarouselView mCarouselView;
    @BindView(R.id.spinner)
    public Spinner mSpinner;
//    @BindView(R.id.tag_container)
//    TagContainerLayout mTag;
    ProductInfo info;
    Context mContext;
    private cn.zy.base.shopping.adapter.SpinnerAdapter SpinnerAdapter;
    private ArrayList<Category> mCateData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        unbinder = ButterKnife.bind(this);
        mContext = this;
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        info = (ProductInfo) getIntent().getSerializableExtra("product");
        SpinnerAdapter = new SpinnerAdapter(mCateData, this);
        mSpinner.setAdapter(SpinnerAdapter);

        if (null != info) {
            edtProjectTitle.setText(info.getTitle());
//            edtCategory.setText(info.getCategory()+"");
            edtPrice.setText(info.getPrice_range());
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
            getProductType();
            getProduct(info.getId());
        }
    }

    @OnClick({R.id.img_topbar_back, R.id.tv_action_delete, R.id.tv_action_save})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.img_topbar_back:
                this.finish();
                break;
            case R.id.tv_action_delete:
                if (null != info) {
                    deleteProduct(info.getId());
                }
                break;
            case R.id.tv_action_save:
                this.finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void deleteProduct(String productId) {
        String url = UriTemplate.fromTemplate(Config.DELETE_PRODUCT)
                .set("product_id", productId)
                .expand();

        HttpParams params = HttpMethods.getInstance().getHttpParams();

        DeleteRequest request = OkGo.delete(url).params(params);
        HttpMethods.getInstance().doDelete(request, true).subscribe(new Subscriber<Response>() {
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
                    ToastUtil.showToast("Succesefull", mContext);
                    finish();

                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }

    public void SaveProduct(String productId) {
        String url = UriTemplate.fromTemplate(Config.DELETE_PRODUCT)
                .set("product_id", productId)
                .expand();

        HttpParams params = HttpMethods.getInstance().getHttpParams();

        DeleteRequest request = OkGo.delete(url).params(params);
        HttpMethods.getInstance().doDelete(request, true).subscribe(new Subscriber<Response>() {
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
                    ToastUtil.showToast("Succesefull", mContext);
                    finish();

                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }


    public void getProductType() {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        GetRequest request = OkGo.get(Config.PRODUCTS_CATEGORIES).params(params);
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
                    ProductTypeList list = Parsing.getInstance().ResponseToObject(response, ProductTypeList.class).getData();
                    ArrayList<Category> datas = list.getCategories();
                    if (null != datas && !datas.isEmpty()) {
                        mCateData.clear();
                        mCateData.addAll(datas);
                        SpinnerAdapter.notifyDataSetChanged();
//                        getDataFromType(datas.get(0).getId());
                        if (null != info) {
                            if (null != info.getCategory()) {
                                for (int i = 0; i < mCateData.size(); i++) {
                                    if (mCateData.get(i).getId().equals(info.getCategory().getId())) {
                                        mSpinner.setSelection(i);
                                    }
                                }
                            }
                        }
                    }

//                    setData(datas);
                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }

    public void getProduct(String productId) {
        String url = UriTemplate.fromTemplate(Config.DELETE_PRODUCT)
                .set("product_id", productId)
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
                    ProductInfo info = Parsing.getInstance().ResponseToObject(response, ProductInfo.class).getData();
                    setWebDescription(info.getContent());


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
}
