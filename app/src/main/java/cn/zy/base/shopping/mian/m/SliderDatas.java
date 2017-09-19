package cn.zy.base.shopping.mian.m;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gtgs on 17/9/15.
 */

public class SliderDatas implements Serializable {
    private ArrayList<Slidersinfo> sliders;

    public ArrayList<Slidersinfo> getSliders() {
        return sliders;
    }

    public void setSliders(ArrayList<Slidersinfo> sliders) {
        this.sliders = sliders;
    }

    @Override
    public String toString() {
        return "SliderDatas{" +
                "sliders=" + sliders +
                '}';
    }
}
