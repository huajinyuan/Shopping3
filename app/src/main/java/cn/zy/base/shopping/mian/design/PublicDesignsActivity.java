package cn.zy.base.shopping.mian.design;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.GetRequest;
import com.gt.okgo.request.PostRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.design.m.PublishDesign;
import cn.zy.base.shopping.mian.design.m.PublishDesignInfo;
import cn.zy.base.shopping.mian.order.m.OrderInfo;
import cn.zy.base.shopping.mian.order.m.OrderList;
import cn.zy.base.shopping.utils.AppUtils;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.widget.SpaceItemDecoration;
import cn.zy.base.shopping.adapter.PublicDesignAdapter;
import cn.zy.base.shopping.utils.PixelUtil;
import okhttp3.Response;
import rx.Subscriber;

public class PublicDesignsActivity extends AppCompatActivity implements IItemAddBack {
    LinearLayoutManager manager;
    ArrayList<PublishDesignInfo> mData = new ArrayList<>();
    PublicDesignAdapter adapter;
    @BindView(R.id.rec_bd_contetn)
    public RecyclerView mRec;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;
    private Unbinder unbinder;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_designs);
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        unbinder = ButterKnife.bind(this);
        mTvTitle.setText("Total Products");
        mContext = this;
        getPublishDesign();
    }

    public void setData(ArrayList<PublishDesignInfo> data) {
        mData.clear();
        mData.addAll(data);
        if (null == adapter) {
            manager = new LinearLayoutManager(this);
            mRec.addItemDecoration(new DividerGridItemDecoration(this));
            adapter = new PublicDesignAdapter(this, mData, this);
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


    public void getPublishDesign() {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        GetRequest request = OkGo.get(Config.PUBLISH_DESIGNS).params(params);
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
                    PublishDesign list = Parsing.getInstance().ResponseToObject(response, PublishDesign.class).getData();
                    ArrayList<PublishDesignInfo> datas = list.getPublic_designs();
                    setData(datas);
                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }

    @Override
    public void itemAddBack(PublishDesignInfo info) {
        add2Action(info.getId());
    }

    public void add2Action(String id) {
        if (null == id) {
            return;
        }
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        params.put("public_design_id", id);
        PostRequest request = null;
        request = OkGo.post(Config.PUBLISH_DESIGNS_ADD2WISHLISTS).params(params);
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
                    getPublishDesign();
                    ToastUtil.showToast("uccessful", mContext);

                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });

    }
}
