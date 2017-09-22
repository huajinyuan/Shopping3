package cn.zy.base.shopping.utils;

/**
 * Created by Administrator on 2016/7/20.
 */
public class StringUtils {
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !StringUtils.isEmpty(cs);
    }

    public static String NumFormat(String num) {
        // TODO Auto-generated method stub
        if (StringUtils.isEmpty(num)) {
            return "";
        }
        String s = null;
        try {
            float f = Float.parseFloat(num);
            boolean m = false;
            if (f >= 10000) {
                f = f / 10000.0f;
                m = true;
            }
            s = f + "";
            // s = decimalFormat.format(f);
            if (s.contains(".")) {
                String ss;
                if (s.length() > s.indexOf(".") + 2) {
                    ss = s.substring(0, s.indexOf(".") + 3);
                    if (ss.endsWith("00")) {
                        ss = s.substring(0, s.indexOf("."));
                    } else if (ss.endsWith("0")) {
                        ss = s.substring(0, s.indexOf(".") + 2);
                    }
                } else if (s.length() > s.indexOf(".") + 1) {
                    ss = s.substring(0, s.indexOf(".") + 2);
                    if (ss.endsWith("0")) {
                        ss = s.substring(0, s.indexOf("."));
                    }
                } else {
                    ss = s.substring(0, s.indexOf("."));
                }
                s = ss;
            }

            if (m) {
                s = s + "万";
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return s;
    }

    public static String getRoundString(int i) {
        String str = "种子轮";
        switch (i) {
            case 1:
                str = "种子轮";
                break;
            case 2:
                str = "天使轮";
                break;
            case 3:
                str = "A轮";
                break;
            case 4:
                str = "B轮";
                break;
            case 5:
                str = "C轮";
                break;
        }
        return str;
    }

    public static String getRoundString(String i) {
        String str = "无";
        if (null==i){
            return str;
        }
        if (i.contains("种子") || i.contains("1")) {
            str = "种子轮";
        }
        if (i.contains("天使") || i.contains("2")) {
            str = "天使轮";
        }
        if (i.contains("A") || i.contains("a") || i.contains("3")) {
            str = "A轮";
        }
        if (i.contains("B") || i.contains("b") || i.contains("4")) {
            str = "B轮";
        }
        if (i.contains("C") || i.contains("c") || i.contains("5")) {
            str = "种子轮";
        }

        return str;
    }

    public static int getRoundInt(String rounds) {
        if (null==rounds){
            return 1;
        }
        if (rounds.contains("种子轮"))
            return 1;
        if (rounds.contains("天使轮"))
            return 2;
        if (rounds.contains("A轮"))
            return 3;
        if (rounds.contains("B轮"))
            return 4;
        if (rounds.contains("C轮"))
            return 5;

        return 1;

    }
}
