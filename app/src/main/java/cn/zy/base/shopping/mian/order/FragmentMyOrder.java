package cn.zy.base.shopping.mian.order;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.GetRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.adapter.CommonAdapter;
import cn.zy.base.shopping.adapter.CommonViewHolder;
import cn.zy.base.shopping.adapter.OrderAdapter;
import cn.zy.base.shopping.base.BaseFragment;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.order.m.OrderInfo;
import cn.zy.base.shopping.mian.order.m.OrderList;
import cn.zy.base.shopping.mian.order.m.OrderStatuses;
import cn.zy.base.shopping.utils.PixelUtil;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.widget.SpaceItemDecoration;
import okhttp3.Response;
import rx.Subscriber;


/**
 * Created by gtgs on 2016/9/2.
 */
public class FragmentMyOrder extends BaseFragment {


    LinearLayoutManager manager;
    ArrayList<OrderInfo> mData = new ArrayList<>();
    OrderAdapter adapter;
    @BindView(R.id.rec_bd_contetn)
    public RecyclerView mRec;
    @BindView(R.id.spinner)
    public Spinner mSpinner;
    Context mContext;
    private CommonAdapter<OrderStatuses> SpinnerAdapter;
    private ArrayList<OrderStatuses> mStatusData = new ArrayList<>();


    private Unbinder unbinder;

    @Override
    public int getLayout() {
        return R.layout.fragment_myorder;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initData(View view) {
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        getOrderData(null);

        SpinnerAdapter = new CommonAdapter<OrderStatuses>(getActivity(), mStatusData, R.layout.layout_item_spinner) {
            @Override
            public void convert(CommonViewHolder helper, OrderStatuses item) {

                ((TextView) helper.getView(R.id.tv_content)).setText(item.getValue());
            }
        };
        mSpinner.setAdapter(SpinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {

                    getOrderData(null);
                } else {

                    getOrderData(mStatusData.get(i).getKey());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void setData(ArrayList<OrderInfo> data) {
        mData.clear();
        mData.addAll(data);
        if (null == adapter) {
            manager = new LinearLayoutManager(getActivity());
            mRec.addItemDecoration(new DividerGridItemDecoration(getActivity()));
            adapter = new OrderAdapter(getActivity(), mData);
            mRec.setAdapter(adapter);
            mRec.setLayoutManager(manager);
            int spac = PixelUtil.dp2px(this.getActivity(), 8);
            mRec.addItemDecoration(new SpaceItemDecoration(spac));

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public void getOrderData(String stutas) {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        if (null != stutas) {
            params.put("status", stutas);
        }
        GetRequest request = OkGo.get(Config.ORDER_GROUPS).params(params);
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
                        if (null != list.getStatuses()) {
                            mStatusData.clear();
                            mStatusData.addAll(list.getStatuses());
                            OrderStatuses orderStatuses = new OrderStatuses();
                            orderStatuses.setKey("All Status");
                            orderStatuses.setValue("All Status");
                            mStatusData.add(0, orderStatuses);
                            SpinnerAdapter.notifyDataSetChanged();
                        }
                    }

                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });

    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }


}
