package cn.zy.base.shopping.mian;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.GetRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.zy.base.shopping.base.BaseFragment;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.order.m.OrderInfo;
import cn.zy.base.shopping.mian.order.m.OrderList;
import cn.zy.base.shopping.mian.product.m.ProductInfo;
import cn.zy.base.shopping.mian.product.m.ProductList;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.DividerGridItemDecoration;
import cn.zy.base.shopping.utils.PixelUtil;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.widget.SpaceItemDecoration;
import cn.zy.base.shopping.adapter.OrderAdapter;
import okhttp3.Response;
import rx.Subscriber;


/**
 * Created by gtgs on 2016/9/2.
 */
public class FragmentOrder extends BaseFragment {
    LinearLayoutManager manager;
    ArrayList<OrderInfo> mData = new ArrayList<>();
    OrderAdapter adapter;
    @BindView(R.id.rec_bd_contetn)
    public RecyclerView mRec;
    @BindView(R.id.img_topbar_back)
    ImageView imgBack;
    @BindView(R.id.img_topbar_right)
    ImageView imgRight;
    @BindView(R.id.tv_topbar_title)
    TextView mTvTitle;
    Context mContext;

    private Unbinder unbinder;

    @Override
    public int getLayout() {
        return R.layout.fragment_order;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initData(View view) {
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        imgBack.setVisibility(View.GONE);
        imgRight.setVisibility(View.VISIBLE);
        mTvTitle.setText("Order");
        getOrderData();
//        ArrayList<String> data = new ArrayList<>();
//        data.add("");
//        data.add("");
//        data.add("");
//        data.add("");
//        data.add("");
//        setData(data);
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
    public void getOrderData()
    {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
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
                if (response.code()==200){
                    OrderList list = Parsing.getInstance().ResponseToObject(response,OrderList.class).getData();
                    ArrayList<OrderInfo> datas = list.getOrders();
                    setData(datas);
                }else {
                    ToastUtil.showToast("Fail",mContext);

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
