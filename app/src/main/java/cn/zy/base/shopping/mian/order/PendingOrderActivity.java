package cn.zy.base.shopping.mian.order;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.GetRequest;
import com.gt.okgo.request.PostRequest;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.adapter.OrderAdapter;
import cn.zy.base.shopping.adapter.PendingOrderAdapter;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.order.m.OrderCost;
import cn.zy.base.shopping.mian.order.m.OrderInfo;
import cn.zy.base.shopping.mian.order.m.OrderList;
import cn.zy.base.shopping.utils.PixelUtil;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.widget.SpaceItemDecoration;
import okhttp3.Response;
import rx.Subscriber;

public class PendingOrderActivity extends AppCompatActivity implements IOrderitemBack {
    private Unbinder unbinder;
    LinearLayoutManager manager;
    @BindView(R.id.tv_topbar_title)
    TextView tvTitle;
    @BindView(R.id.rec_order_content)
    public RecyclerView mRec;
    Context mContext;
    ArrayList<OrderInfo> mData = new ArrayList<>();
    PendingOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_order);
        unbinder = ButterKnife.bind(this);
        mContext = this;
        tvTitle.setText("Pending Payment");
        getOrderData();
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


    public void setData(ArrayList<OrderInfo> data) {
        mData.clear();
        mData.addAll(data);
        if (null == adapter) {
            manager = new LinearLayoutManager(this);
            mRec.addItemDecoration(new DividerGridItemDecoration(this));
            adapter = new PendingOrderAdapter(this, mData, this);
            mRec.setAdapter(adapter);
            mRec.setLayoutManager(manager);
            int spac = PixelUtil.dp2px(this, 8);
            mRec.addItemDecoration(new SpaceItemDecoration(spac));

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public void getOrderData() {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        GetRequest request = OkGo.get(Config.ORDER_GROUPS_PENDING).params(params);
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
                    OrderList list = Parsing.getInstance().ResponseToObject(response, OrderList.class).getData();
                    if (null != list) {
                        ArrayList<OrderInfo> datas = list.getOrders();
                        setData(datas);
                    }

                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });

    }

    @Override
    public void back(OrderInfo o) {

        payOrder(o.getId(), o.getDelivery() == 1 ? "DHL" : "EMS");
    }

    @Override
    public void CheckDelivery(int postion, int isCheck) {
        getCost(postion, isCheck);
    }


    public void payOrder(String orderId, String delivery) {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        params.put("order_group_id", orderId);
        params.put("carrier", delivery);
        PostRequest request = OkGo.post(Config.ORDER_GROUPS_PAY).params(params);
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
                    ToastUtil.showToast("Sucessful", mContext);
                    getOrderData();

                } else {

                    try {
                        String Str = response.body().string();
                        JSONObject ob = JSON.parseObject(Str);
                        String msg = ob.getString("data");
                        ToastUtil.showToast(msg, mContext);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    String msg = Parsing.getInstance().ResponseToObject(response, String.class).getData();

                }
            }
        });

    }

    public void getCost(final int postion, final int Dev) {
        if (Dev == 1 || Dev == 2) {
            OrderInfo orderInfo = mData.get(postion);
            HttpParams params = HttpMethods.getInstance().getHttpParams();
            params.put("order_group_id", orderInfo.getId());
            String deliver = "";
            if (Dev == 1) {
                deliver = "DHL";
            } else {
                deliver = "EMS";
            }
            params.put("carrier", deliver);
            PostRequest request = OkGo.post(Config.ORDER_GROUPS_CALCULATE).params(params);
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
                        OrderCost orderCost = Parsing.getInstance().ResponseToObject(response, OrderCost.class).getData();
                        OrderInfo info = mData.get(postion);
                        info.setCost(orderCost.getCost());
                        info.setTotal(orderCost.getTotal());
                        info.setShipping(orderCost.getShipping());
                        info.setDelivery(Dev);
                        mData.remove(postion);
                        mData.add(postion, info);
                        adapter.notifyDataSetChanged();

                    } else {
                        ToastUtil.showToast("Fail", mContext);

                    }
                }
            });
        } else {
            OrderInfo orderInfo = mData.get(postion);
            orderInfo.setCost("");
            orderInfo.setTotal("");
            orderInfo.setShipping("");
            orderInfo.setDelivery(Dev);
            mData.remove(postion);
            mData.add(postion, orderInfo);
            adapter.notifyDataSetChanged();
        }

    }
}
