package cn.zy.base.shopping.mian.order;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.damnhandy.uri.template.UriTemplate;
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
import cn.zy.base.shopping.adapter.OrderInfoListAdapter;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.order.m.OrderInfo;
import cn.zy.base.shopping.mian.order.m.OrderInfoList;
import cn.zy.base.shopping.mian.order.m.OrderList;
import cn.zy.base.shopping.utils.AppUtils;
import cn.zy.base.shopping.utils.PixelUtil;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.widget.SpaceItemDecoration;
import okhttp3.Response;
import rx.Subscriber;

public class OrderInfoActivity extends AppCompatActivity {
    private Unbinder unbinder;
    LinearLayoutManager manager;
    @BindView(R.id.tv_topbar_title)
    TextView tvTitle;
    @BindView(R.id.rec_order_contetn)
    public RecyclerView mRec;
    ArrayList<OrderInfoList> mData = new ArrayList();
    OrderInfoListAdapter adapter;
    Context mContext;
    OrderInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        unbinder = ButterKnife.bind(this);
        mContext = this;
        info = (OrderInfo) getIntent().getSerializableExtra("OrderInfo");
        if (null != info) {
            tvTitle.setText(info.getName());
            getOrderData(info.getId());
        }
//        tvTitle.setText();
    }

    public void setData(ArrayList<OrderInfoList> data) {
        mData.clear();
        mData.addAll(data);
        if (null == adapter) {
            manager = new LinearLayoutManager(this);
            mRec.addItemDecoration(new DividerGridItemDecoration(this));
            adapter = new OrderInfoListAdapter(this, mData, info);
            mRec.setAdapter(adapter);
            mRec.setLayoutManager(manager);
            int spac = PixelUtil.dp2px(this, 8);
            mRec.addItemDecoration(new SpaceItemDecoration(spac));

        } else {
            adapter.notifyDataSetChanged();
        }
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


    public void getOrderData(String orderId) {
        String url = UriTemplate.fromTemplate(Config.ORDER_GROUPS_BYID)
                .set("order_id", orderId)
                .expand();
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        GetRequest request = OkGo.get(url).params(params);
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
                    ArrayList<OrderInfoList> list = Parsing.getInstance().ResponseToList(response, OrderInfoList.class);
//                    ArrayList<OrderInfo> datas = list.getOrders();
                    setData(list);
                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }

}
