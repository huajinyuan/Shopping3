package cn.zy.base.shopping.mian.wishList;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.damnhandy.uri.template.UriTemplate;
import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.DeleteRequest;
import com.gt.okgo.request.GetRequest;
import com.gt.okgo.request.PostRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.adapter.PublicDesignAdapter;
import cn.zy.base.shopping.adapter.WishListAdapter;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.design.m.PublishDesign;
import cn.zy.base.shopping.mian.design.m.PublishDesignInfo;
import cn.zy.base.shopping.mian.wishList.m.WishCount;
import cn.zy.base.shopping.mian.wishList.m.WishInfo;
import cn.zy.base.shopping.mian.wishList.m.WishList;
import cn.zy.base.shopping.utils.AppUtils;
import cn.zy.base.shopping.utils.PixelUtil;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.widget.SpaceItemDecoration;
import okhttp3.Response;
import rx.Subscriber;

public class MyWishListActivity extends AppCompatActivity implements IItemclickBack {
    LinearLayoutManager manager;
    ArrayList<WishInfo> mData = new ArrayList<>();
    WishListAdapter adapter;
    @BindView(R.id.rec_bd_contetn)
    public RecyclerView mRec;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;
    private Unbinder unbinder;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list);
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        unbinder = ButterKnife.bind(this);
        mTvTitle.setText("My WishLists");
        mContext = this;
        getWishList();
    }

    public void setData(ArrayList<WishInfo> data) {
        mData.clear();
        mData.addAll(data);
        if (null == adapter) {
            manager = new LinearLayoutManager(this);
            mRec.addItemDecoration(new DividerGridItemDecoration(this));
            adapter = new WishListAdapter(this, mData, this);
            mRec.setAdapter(adapter);
            mRec.setLayoutManager(manager);
            int spac = PixelUtil.dp2px(this, 1);
            mRec.addItemDecoration(new SpaceItemDecoration(spac));

        } else {
            adapter.notifyDataSetChanged();
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
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }


    @Override
    public void backadd(WishInfo info) {
        add2Action(info.getPublic_design_id());
    }

    @Override
    public void backdelete(WishInfo info) {
        deleteWish(info.getId());
    }

    public void getWishList() {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        GetRequest request = OkGo.get(Config.GET_MYWISHLISTS).params(params);
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
                    WishList list = Parsing.getInstance().ResponseToObject(response, WishList.class).getData();
                    ArrayList<WishInfo> datas = list.getWishlists();
                    setData(datas);
                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }


    public void deleteWish(String id) {

        String url = UriTemplate.fromTemplate(Config.DELETE_MYWISHLISTS)
                .set("id", id)
                .expand();
        DeleteRequest request = OkGo.delete(url);
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
                    getWishList();
                    ToastUtil.showToast("uccessful", mContext);
                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }


    public void add2Action(String public_design_id) {

        HttpParams params = HttpMethods.getInstance().getHttpParams();
        params.put("public_design_id", public_design_id);
        PostRequest request = null;
        request = OkGo.post(Config.PUBLISH_DESIGNS_ADD2PRODUCTS).params(params);
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
                    ToastUtil.showToast("uccessful", mContext);
                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });

    }

}
