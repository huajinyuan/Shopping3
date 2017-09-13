package cn.zy.base.shopping.mian;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gt.okgo.OkGo;
import com.gt.okgo.model.HttpParams;
import com.gt.okgo.request.GetRequest;
import com.gt.okgo.request.PostRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zy.base.shopping.base.BaseFragment;
import cn.zy.base.shopping.R;
import cn.zy.base.shopping.http.Config;
import cn.zy.base.shopping.http.HttpMethods;
import cn.zy.base.shopping.http.Parsing;
import cn.zy.base.shopping.mian.design.PublicDesignsActivity;
import cn.zy.base.shopping.mian.design.m.Dashboard;
import cn.zy.base.shopping.mian.login.LoginActivity;
import cn.zy.base.shopping.mian.login.m.LoginData;
import cn.zy.base.shopping.mian.product.ProductsActivity;
import cn.zy.base.shopping.utils.ACache;
import cn.zy.base.shopping.utils.ACacheKey;
import cn.zy.base.shopping.utils.ToastUtil;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import okhttp3.Response;
import rx.Subscriber;


/**
 * Created by gtgs on 2016/9/2.
 */
public class FragmentDashboard extends BaseFragment {
    private LineChartView chart;
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;
    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;
    private boolean hasGradientToTransparent = false;

    private Unbinder unbinder;
    Context mContext;
    TextView tv_Balance;
    TextView tv_Products;
    TextView tv_Payment;
    TextView tv_Designs;

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
        chart = (LineChartView) view.findViewById(R.id.chart);
        tv_Balance = (TextView) view.findViewById(R.id.tv_Balance);
        tv_Products = (TextView) view.findViewById(R.id.tv_Products);
        tv_Payment = (TextView) view.findViewById(R.id.tv_Payment);
        tv_Designs = (TextView) view.findViewById(R.id.tv_Designs);
        chart.setOnValueTouchListener(new ValueTouchListener());
        // Generate some random values.
        generateValues();
        generateData();
        // Disable viewport recalculations, see toggleCubic() method for more info.
        chart.setViewportCalculationEnabled(false);

        resetViewport();
        addLineToData();
        getDashboard();
    }
    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 200f;
            }
        }
    }

    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }
            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            line.setHasGradientToTransparent(hasGradientToTransparent);
            if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
//            if (hasAxesNames) {
//                axisX.setName("Axis X");
//                axisY.setName("Axis Y");
//            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);

    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 200;
        v.left = 0;
        v.right = 7;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    /**
     * Adds lines to data, after that data should be set again with
     * {@link LineChartView#setLineChartData(LineChartData)}. Last 4th line has non-monotonically x values.
     */
    private void addLineToData() {
        if (data.getLines().size() >= maxNumberOfLines) {
            Toast.makeText(getActivity(), "Samples app uses max 4 lines!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ++numberOfLines;
        }

        generateData();
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
    public void getDashboard()
    {
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
                if (response.code()==200){
                    Dashboard dash = Parsing.getInstance().ResponseToObject(response,Dashboard.class).getData();
                    tv_Balance.setText(dash.getAccount_balance());
                    tv_Designs.setText(dash.getTotal_public_designs());
                    tv_Payment.setText(dash.getTotal_pending_payments());
                    tv_Products.setText(dash.getTotal_products());
                }else {
                    ToastUtil.showToast("Fail",mContext);

                }
            }
        });
    }
}
