package cn.zy.base.shopping.mian.product;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

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
import cn.zy.base.shopping.adapter.SpinnerAdapter;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.product.m.Category;
import cn.zy.base.shopping.mian.product.m.ProductInfo;
import cn.zy.base.shopping.mian.product.m.ProductList;
import cn.zy.base.shopping.mian.product.m.ProductTypeList;
import cn.zy.base.shopping.utils.AppUtils;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.widget.SpaceItemDecoration;
import cn.zy.base.shopping.adapter.ProductsAdapter;
import cn.zy.base.shopping.utils.PixelUtil;
import okhttp3.Response;
import rx.Subscriber;

public class ProductsActivity extends AppCompatActivity implements IItemClickBack {
    LinearLayoutManager manager;
    ArrayList<ProductInfo> mData = new ArrayList<>();
    ArrayList<ProductInfo> mDataSelecter = new ArrayList<>();
    ProductsAdapter adapter;
    @BindView(R.id.rec_bd_contetn)
    public RecyclerView mRec;
    @BindView(R.id.tv_topbar_title)
    TextView tvTitle;
    private Unbinder unbinder;
    Context mContext;
    @BindView(R.id.spinner)
    public Spinner mSpinner;
    private SpinnerAdapter SpinnerAdapter;
    private ArrayList<Category> mCateData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        mContext = this;
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("My Products");
        SpinnerAdapter = new SpinnerAdapter(mCateData, this);
        mSpinner.setAdapter(SpinnerAdapter);
        getProductData();
        getProductType();
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    getProductData();
                } else {

                    getDataFromType(mCateData.get(i).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setData(ArrayList<ProductInfo> data) {
        mData.clear();
        mData.addAll(data);
        mDataSelecter.clear();
        mDataSelecter.addAll(data);
        if (null == adapter) {
            manager = new LinearLayoutManager(this);
            mRec.addItemDecoration(new DividerGridItemDecoration(this));
            adapter = new ProductsAdapter(this, mDataSelecter, this);
            mRec.setAdapter(adapter);
            mRec.setLayoutManager(manager);
            int spac = PixelUtil.dp2px(this, 1);
            mRec.addItemDecoration(new SpaceItemDecoration(spac));

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public void getProductData() {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        GetRequest request = OkGo.get(Config.PRODUCTS).params(params);
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
                    ProductList list = Parsing.getInstance().ResponseToObject(response, ProductList.class).getData();
                    ArrayList<ProductInfo> datas = list.getProducts();
                    setData(datas);
                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }


    @OnClick({R.id.img_topbar_back})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.img_topbar_back:
                this.finish();
                break;
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void itemback(ProductInfo info) {
        deleteProduct(info.getId());
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

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.showToast("请求失败，请检查网络", mContext);
            }

            @Override
            public void onNext(Response response) {
                if (response.code() == 200) {
                    getProductData();
                    ToastUtil.showToast("successful", mContext);

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
                        Category category = new Category();
                        category.setId("All Types");
                        category.setName("All Types");
                        mCateData.add(0, category);
                        SpinnerAdapter.notifyDataSetChanged();
                    }

//                    setData(datas);
                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }


    private void getDataFromType(String type) {
        ArrayList<ProductInfo> data = new ArrayList<>();
        for (ProductInfo info : mData) {
            if (info.getCategory().getId().equals(type)) {
                data.add(info);
            }
        }
        mDataSelecter.clear();
        mDataSelecter.addAll(data);
        adapter.notifyDataSetChanged();
    }

}
