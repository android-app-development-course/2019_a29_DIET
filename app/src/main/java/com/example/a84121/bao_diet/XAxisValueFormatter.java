package com.example.a84121.bao_diet;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by 84121 on 2019/11/26.
 */

public class XAxisValueFormatter implements IAxisValueFormatter {
    private String[] xStrs = new String[]{"周一", "周二", "周三", "周四","周五","周六","周日"};

    @Override

    public String getFormattedValue(float value, AxisBase axis) {
        int position = (int) value;
        if (position >= 7) {
            position = 0;
        }
        return xStrs[position];
    }}
