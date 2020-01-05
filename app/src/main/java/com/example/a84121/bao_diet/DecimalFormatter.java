package com.example.a84121.bao_diet;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by 84121 on 2019/11/26.
 */
public class DecimalFormatter implements IAxisValueFormatter {
    private DecimalFormat format;

    public DecimalFormatter() {
        format = new DecimalFormat("#");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return format.format(value) ;
    }
}
