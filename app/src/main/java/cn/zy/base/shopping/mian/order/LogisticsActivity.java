package cn.zy.base.shopping.mian.order;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.damnhandy.uri.template.UriTemplate;
import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.DeleteRequest;
import com.gt.okgo.request.GetRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.order.m.OrderLogistics;
import cn.zy.base.shopping.utils.AppUtils;
import cn.zy.base.shopping.utils.StringUtils;
import cn.zy.base.shopping.utils.ToastUtil;
import okhttp3.Response;
import rx.Subscriber;

public class LogisticsActivity extends AppCompatActivity {

    @BindView(R.id.tv_topbar_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_quantity)
    TextView tv_quantity;
    @BindView(R.id.tv_total_price)
    TextView tv_total_price;
    @BindView(R.id.tv_express)
    TextView tv_express;
    @BindView(R.id.tv_Number)
    TextView tv_Number;
    private Unbinder unbinder;
    Context mContext;
    private String Group_id;
    private String order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);
        mContext = this;
        Group_id = getIntent().getStringExtra("Group_id");
        order_id = getIntent().getStringExtra("order_id");
        AppUtils.MIUISetStatusBarLightMode(getWindow(), true);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("Logistics");
        if (StringUtils.isNotEmpty(Group_id) && StringUtils.isNotEmpty(order_id)) {
            getData(Group_id, order_id);
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

    public void getData(String groupId, final String order_id) {
        String url = UriTemplate.fromTemplate(Config.ORDER_BY_ID)
                .set("group_id", groupId)
                .set("order_id", order_id)
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
                    OrderLogistics order = Parsing.getInstance().ResponseToSimPle(response, OrderLogistics.class);
                    if (null != order) {
                        if (StringUtils.isNotEmpty(order.getId())) {
                            tv_name.setText(order.getId());
                        }
                        if (StringUtils.isNotEmpty(order.getQuantity())) {
                            tv_quantity.setText(order.getQuantity());
                        }
                        if (StringUtils.isNotEmpty(order.getSubtotal())) {
                            tv_total_price.setText(order.getSubtotal());
                        }
                        if (StringUtils.isNotEmpty(order.getExpress_agency())) {
                            tv_express.setText(order.getExpress_agency());
                        }
                        if (StringUtils.isNotEmpty(order.getTracking_number())) {
                            tv_Number.setText(order.getTracking_number());
                        }


                    }


                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }
}
