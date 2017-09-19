package cn.zy.base.shopping.widget.bn;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cn.zy.base.shopping.R;

//import cn.gcs.base.gcsapp.R;
//import cn.gcs.base.gcsapp.activity.main.fg_friendCircle.dynamic.m.CSGDynamic;

/**
 *
 */

/**
 * 广告轮播页
 */
public class CarouselView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private Context context;
    private int totalCount = 100;
    private int showCount;
    private int currentPosition = 0;
    private ViewPager viewPager;
    private LinearLayout carouselLayout;
    private TextView tvTip;
    private Adapter adapter;
    private int pageItemWidth;
    private boolean isUserTouched = false;
    private Timer mTimer = new Timer();
//    private ArrayList<CSGDynamic> mDatas = new ArrayList<>();

    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (!isUserTouched) {
                currentPosition = (currentPosition + 1) % totalCount;
                handler.sendEmptyMessage(100);
            }
        }
    };

    public void cancelTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (currentPosition == totalCount - 1) {
                viewPager.setCurrentItem(showCount - 1, false);
            } else {
                viewPager.setCurrentItem(currentPosition);
            }
        }
    };

    public CarouselView(Context context) {
        super(context);
        this.context = context;
    }

    public CarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CarouselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CarouselView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        viewPager.setAdapter(null);
        carouselLayout.removeAllViews();
        if (adapter.isEmpty()) {
            return;
        }
        int count = adapter.getCount();
        showCount = adapter.getCount();
        for (int i = 0; i < count; i++) {
            View view = new View(context);
            if (currentPosition == i) {
                view.setPressed(true);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pageItemWidth + ConvertUtils.dip2px(context, 3), pageItemWidth + ConvertUtils.dip2px(context, 3));
                params.setMargins(pageItemWidth, 0, 0, 0);
                view.setLayoutParams(params);
            } else {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pageItemWidth, pageItemWidth);
                params.setMargins(pageItemWidth, 0, 0, 0);
                view.setLayoutParams(params);
            }
            view.setBackgroundResource(R.drawable.carousel_layout_page);
            carouselLayout.addView(view);
        }
        viewPager.setAdapter(new ViewPagerAdapter());
        viewPager.setCurrentItem(0);
        this.viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isUserTouched = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isUserTouched = false;
                        break;
                }
                return false;
            }
        });
        mTimer.schedule(mTimerTask, 3000, 3000);
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        if (adapter != null) {
            init();
        }
    }

//    public void setNews(ArrayList<CSGDynamic> datas) {
//        this.mDatas = datas;
//    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_carousel, null);
        this.viewPager = (ViewPager) view.findViewById(R.id.gallery);
        this.carouselLayout = (LinearLayout) view.findViewById(R.id.CarouselLayoutPage);
        this.tvTip = (TextView) view.findViewById(R.id.tv_CarouselLayoutPage);
        pageItemWidth = ConvertUtils.dip2px(context, 5);
        this.viewPager.addOnPageChangeListener(this);
        addView(view);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("CarouselView", "onPageScrolled was invoke()");
    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
        int count = carouselLayout.getChildCount();
//        if (position < mDatas.size()) {
//            this.tvTip.setText(mDatas.get(position).getTitle());
//        }
        for (int i = 0; i < count; i++) {
            View view = carouselLayout.getChildAt(i);
            if (position % showCount == i) {
                view.setSelected(true);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pageItemWidth + ConvertUtils.dip2px(context, 3), pageItemWidth + ConvertUtils.dip2px(context, 3));
                params.setMargins(pageItemWidth, 0, 0, 0);
                view.setLayoutParams(params);
            } else {
                view.setSelected(false);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pageItemWidth, pageItemWidth);
                params.setMargins(pageItemWidth, 0, 0, 0);
                view.setLayoutParams(params);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("CarouselView", "onPageScrollStateChanged was invoke()");
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return totalCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= showCount;
            View view = adapter.getView(position);
            container.addView(view);
//            if (position < mDatas.size()) {
//                tvTip.setText(mDatas.get(position).getTitle());
//            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            int position = viewPager.getCurrentItem();
            if (position == 0) {
                position = showCount;
                viewPager.setCurrentItem(position, false);
            } else if (position == totalCount - 1) {
                position = showCount - 1;
                viewPager.setCurrentItem(position, false);
            }
        }
    }

    public interface Adapter {
        boolean isEmpty();

        View getView(int position);

        int getCount();
    }
}
