package cn.zy.base.shopping.mian;

import android.app.Application;

import cn.zy.base.shopping.utils.ACache;

/**
 * Created by 51090 on 2017/8/31.
 */

public class SApplication extends Application {
    private static SApplication instance;
    public ACache aCache;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        aCache = ACache.get(this);
    }
    public static SApplication getInstance() {
        return instance;
    }
}
