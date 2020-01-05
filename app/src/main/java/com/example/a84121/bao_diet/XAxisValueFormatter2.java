package com.example.a84121.bao_diet;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by 84121 on 2019/11/27.
 */

public class XAxisValueFormatter2 implements IAxisValueFormatter {
    private  String[] xMon=new String[]{"五月","六月","七月","八月","九月","十月"};
    @Override

    public String getFormattedValue(float value, AxisBase axis) {
        int position = (int) value;
        if (position >= 6) {
            position = 0;
        }
        return xMon[position];
    }
}
