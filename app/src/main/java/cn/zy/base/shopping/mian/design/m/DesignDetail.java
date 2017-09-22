package cn.zy.base.shopping.mian.design.m;

import java.io.Serializable;

/**
 * Created by gtgs on 17/9/22.
 */

public class DesignDetail implements Serializable {
    private DesignInfo design;

    public DesignInfo getDesign() {
        return design;
    }

    public void setDesign(DesignInfo design) {
        this.design = design;
    }

    @Override
    public String toString() {
        return "DesignDetail{" +
                "design=" + design +
                '}';
    }
}
