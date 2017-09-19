package cn.zy.base.shopping.mian;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.GetRequest;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.base.BaseFragment;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.design.PublicDesignsActivity;
import cn.zy.base.shopping.mian.design.m.ChatInfo;
import cn.zy.base.shopping.mian.design.m.Dashboard;
import cn.zy.base.shopping.mian.m.SliderDatas;
import cn.zy.base.shopping.mian.m.Slidersinfo;
import cn.zy.base.shopping.mian.product.ProductsActivity;
import cn.zy.base.shopping.utils.DayAxisValueFormatter;
import cn.zy.base.shopping.utils.ToastUtil;
import cn.zy.base.shopping.widget.bn.CarouselView;
import okhttp3.Response;
import rx.Subscriber;


/**
 * Created by gtgs on 2016/9/2.
 */
public class FragmentDashboard extends BaseFragment {
    private BarChart mChart;
    private Unbinder unbinder;
    Context mContext;
    TextView tv_Balance;
    TextView tv_Products;
    TextView tv_Payment;
    TextView tv_Designs;
    CarouselView mCarouselView;

    @Override
    public int getLayout() {
        return R.layout.fragment_home_a;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void initData(View view) {
        mContext = getActivity();
        unbinder = ButterKnife.bind(this, view);
        mChart = (BarChart) view.findViewById(R.id.chart);
        tv_Balance = (TextView) view.findViewById(R.id.tv_Balance);
        tv_Products = (TextView) view.findViewById(R.id.tv_Products);
        tv_Payment = (TextView) view.findViewById(R.id.tv_Payment);
        tv_Designs = (TextView) view.findViewById(R.id.tv_Designs);
        mCarouselView = (CarouselView) view.findViewById(R.id.CarouselView);
        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);
        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);

//        XAxis xAxis = mChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(12);
        xAxis.setValueFormatter(xAxisFormatter);

        mChart.getAxisLeft().setDrawGridLines(false);
        // add a nice and smooth animation
        mChart.animateY(2500);

        mChart.getLegend().setEnabled(false);
        getDashboard();
        getad();
    }

    private void generateValues(ArrayList<ChatInfo> chart) {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        HashMap<Integer, Float> map = new HashMap<>();
        for (ChatInfo info : chart) {
            float f = Float.valueOf(info.getTotal_orders());
            if (info.getMonth().endsWith("Jan")) {
                map.put(1, f);
            } else if (info.getMonth().endsWith("Feb")) {
                map.put(2, f);
            } else if (info.getMonth().endsWith("Mar")) {
                map.put(3, f);
            } else if (info.getMonth().endsWith("Apr")) {
                map.put(4, f);
            } else if (info.getMonth().endsWith("May")) {
                map.put(5, f);
            } else if (info.getMonth().endsWith("Jun")) {
                map.put(6, f);
            } else if (info.getMonth().endsWith("Jul")) {
                map.put(7, f);
            } else if (info.getMonth().endsWith("Aug")) {
                map.put(8, f);
            } else if (info.getMonth().endsWith("Sep")) {
                map.put(9, f);
            } else if (info.getMonth().endsWith("Oct")) {
                map.put(10, f);
            } else if (info.getMonth().endsWith("Nov")) {
                map.put(11, f);
            } else if (info.getMonth().endsWith("Dec")) {
                map.put(12, f);
            }

        }

        for (int i = 0; i < 12; i++) {
            if (map.containsKey(i + 1)) {
                yVals1.add(new BarEntry(i, map.get(i + 1)));
            } else {
                yVals1.add(new BarEntry(i, 0.0f));
            }
        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "Data Set");
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            mChart.setData(data);
            mChart.setFitBars(true);
        }

        mChart.invalidate();
    }


    @OnClick({R.id.lin_product, R.id.lin_design})
    public void OnClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.lin_product:
                intent = new Intent(getActivity(), ProductsActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.lin_design:
                intent = new Intent(getActivity(), PublicDesignsActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public void getDashboard() {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        GetRequest request = OkGo.get(Config.DASHBOARD).params(params);
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
                    Dashboard dash = Parsing.getInstance().ResponseToObject(response, Dashboard.class).getData();
                    tv_Balance.setText(dash.getAccount_balance());
                    tv_Designs.setText(dash.getTotal_public_designs());
                    tv_Payment.setText(dash.getTotal_pending_payments());
                    tv_Products.setText(dash.getTotal_products());
                    try {
                        generateValues(dash.getChart());
                    } catch (Exception e) {

                    }

                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }

    ArrayList<Slidersinfo> datas = new ArrayList<>();

    public void getad() {
        HttpParams params = HttpMethods.getInstance().getHttpParams();
        GetRequest request = OkGo.get(Config.SLIDERS).params(params);
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
                    SliderDatas info = Parsing.getInstance().ResponseToObject(response, SliderDatas.class).getData();
                    datas = info.getSliders();
                    mCarouselView.setAdapter(new CarouselView.Adapter() {
                        @Override
                        public boolean isEmpty() {
                            return false;
                        }

                        @Override
                        public View getView(final int position) {
                            View view = LayoutInflater.from(getActivity()).inflate(R.layout.ad_item, null);
                            final ImageView imageView = (ImageView) view.findViewById(R.id.image);
                            Glide.with(getActivity()).load(datas.get(position).getImg()).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
                            return view;
                        }

                        @Override
                        public int getCount() {
                            return datas.size();
                        }
                    });

//                    Dashboard dash = Parsing.getInstance().ResponseToObject(response, Dashboard.class).getData();
//                    tv_Balance.setText(dash.getAccount_balance());
//                    tv_Designs.setText(dash.getTotal_public_designs());
//                    tv_Payment.setText(dash.getTotal_pending_payments());
//                    tv_Products.setText(dash.getTotal_products());
//                    try {
//                        generateValues(dash.getChart());
//                    } catch (Exception e) {
//
//                    }


                } else {
                    ToastUtil.showToast("Fail", mContext);

                }
            }
        });
    }
}
