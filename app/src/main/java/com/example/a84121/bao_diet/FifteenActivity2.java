package com.example.a84121.bao_diet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84121 on 2019/11/26.
 */
public class FifteenActivity2 extends AppCompatActivity implements
        OnChartValueSelectedListener {

    private String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"};
    private String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };
    private Typeface mTfRegular;
    private Typeface mTfLight;
    protected BarChart mChart;
    private HorizontalBarChart hBarChart;
    private LineChart lineChart;
    Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifteen_layout2);
//        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
//        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        mChart = (BarChart) findViewById(R.id.chart1);
        hBarChart = (HorizontalBarChart)findViewById(R.id.hBarChart);
        lineChart =(LineChart) findViewById(R.id.lineChart);
        back=(Button)findViewById(R.id.btn_goback);
        initBarChart();
        initHBarChart();
        initLineChart();
        click();
    }
    public void click() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Intent mIntent=new Intent();//没有任何参数（意图），只是用来传递数据
                setResult(RESULT_OK,mIntent);
                finish();
            }
        });

    }
    /**
     * 初始化柱形图控件属性
     */
    private void initBarChart() {
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(7);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);

//        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);

        //自定义坐标轴适配器，配置在X轴，xAxis.setValueFormatter(xAxisFormatter);
        IAxisValueFormatter xAxisFormatter = new XAxisValueFormatter();
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(xAxisFormatter);
        //自定义坐标轴适配器，配置在Y轴。leftAxis.setValueFormatter(custom);
        IAxisValueFormatter custom = new MyAxisValueFormatter();
        //设置限制临界线
        LimitLine limitLine = new LimitLine(58, "目标体重");
        limitLine.setLineColor(Color.GRAY);
        limitLine.setLineWidth(1f);
        limitLine.setTextColor(Color.GRAY);
        //获取到图形左边的Y轴
        DecimalFormatter formatter = new DecimalFormatter();
        YAxis leftAxis = mChart.getAxisLeft();

        leftAxis.setAxisMinimum(30);
        //leftAxis.setAxisMaximum(80);
        leftAxis.addLimitLine(limitLine);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        //leftAxis.setAxisMinimum(0f);
        leftAxis.setValueFormatter(formatter);

        //获取到图形右边的Y轴，并设置为不显示
        mChart.getAxisRight().setEnabled(false);

        //图例设置
        Legend legend = mChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(9f);
        legend.setTextSize(11f);
        legend.setXEntrySpace(4f);

        //如果点击柱形图，会弹出pop提示框.XYMarkerView为自定义弹出框
        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
        mv.setChartView(mChart);
        mChart.setMarker(mv);

        setBarChartData();
        mChart.animateY(1500);
    }

    /**
     * 初始化水平柱形图图控件属性
     */
    private void initHBarChart() {
        hBarChart.setOnChartValueSelectedListener(this);
        hBarChart.setDrawBarShadow(false);
        hBarChart.setDrawValueAboveBar(true);
        hBarChart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        hBarChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        hBarChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        hBarChart.setDrawGridBackground(false);

        //自定义坐标轴适配器，设置在X轴
        DecimalFormatter formatter = new DecimalFormatter();
        XAxis xl = hBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);

        xl.setLabelRotationAngle(-45f);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(1f);
//        xl.setAxisMinimum(0);
        xl.setValueFormatter(formatter);

        //对Y轴进行设置
        YAxis yl = hBarChart.getAxisLeft();

        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        hBarChart.getAxisRight().setEnabled(false);

        //图例设置
        Legend l = hBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);

        setHBarChartData();
        hBarChart.setFitBars(true);
        hBarChart.animateY(2500);
    }

    /**
     * 初始化折线图控件属性
     */
    private void initLineChart() {
        lineChart.setOnChartValueSelectedListener(this);
        lineChart.getDescription().setEnabled(false);
        lineChart.setBackgroundColor(Color.WHITE);

        //自定义适配器，适配于X轴
        IAxisValueFormatter xAxisFormatter = new XAxisValueFormatter2();
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(xAxisFormatter);

        //自定义适配器，适配于Y轴
        IAxisValueFormatter custom = new MyAxisValueFormatter();
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        //leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMinimum(16);
        lineChart.getAxisRight().setEnabled(false);

        setLineChartData();
    }

    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void setBarChartData() {

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();


        //在这里设置自己的数据源,BarEntry 只接收float的参数，
        //图形横纵坐标默认为float形式，如果想展示文字形式，需要自定义适配器，
        yVals1.add(new BarEntry(0, 62));
        yVals1.add(new BarEntry(1, 63));
        yVals1.add(new BarEntry(2, 62));
        yVals1.add(new BarEntry(3, 61));
        yVals1.add(new BarEntry(4, 60));
        yVals1.add(new BarEntry(5, 60));

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "体重(单位：kg)");
            set1.setDrawIcons(false);
            set1.setColors(getResources().getColor(R.color.round_green));
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTextColor(Color.WHITE);
            data.setBarWidth(0.5f);

            mChart.setData(data);
        }
    }

    /**
     * 设置水平柱形图数据的方法
     */
    private void setHBarChartData() {
        //填充数据，在这里换成自己的数据源
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(0, 4));
        yVals1.add(new BarEntry(1, 2));
        yVals1.add(new BarEntry(2, 6));
        yVals1.add(new BarEntry(3, 1));
        yVals1.add(new BarEntry(4, 1));
        yVals1.add(new BarEntry(5, 1));
        yVals1.add(new BarEntry(6, 1));

        BarDataSet set1;

        if (hBarChart.getData() != null &&
                hBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) hBarChart.getData().getDataSetByIndex(0);

            set1.setValues(yVals1);
            hBarChart.getData().notifyDataChanged();
            hBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "DataSet 1");

            set1.setDrawIcons(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);

            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);//可以去掉，没什么用
            data.setBarWidth(0.5f);

            hBarChart.setData(data);
        }
    }
    /**
     * 设置折线图的数据
     */
    private void setLineChartData() {
        //填充数据，在这里换成自己的数据源
        List<Entry> valsComp1 = new ArrayList<>();
        double a=21.45;
        float aa=(float)a;
        double b=21.79;
        float bb=(float)b;
        double c=21.11;
        float cc=(float)c;
        double d=20.76;
        float dd=(float)d;
        valsComp1.add(new Entry(0,aa));
        valsComp1.add(new Entry(1, bb));
        valsComp1.add(new Entry(2, aa));
        valsComp1.add(new Entry(3, cc));
        valsComp1.add(new Entry(4, dd));
        valsComp1.add(new Entry(5, dd));

        //这里，每重新new一个LineDataSet，相当于重新画一组折线
        //每一个LineDataSet相当于一组折线。比如:这里有两个LineDataSet：setComp1，setComp2。
        //则在图像上会有两条折线图，分别表示公司1 和 公司2 的情况.还可以设置更多
        LineDataSet setComp1 = new LineDataSet(valsComp1, " ");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(getResources().getColor(R.color.round_green));
        setComp1.setDrawCircles(true);
        setComp1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        setComp1.setLineWidth(2f);


        List<ILineDataSet> dataSets = new ArrayList<>();
       dataSets.add(setComp1);
        LineData lineData = new LineData(dataSets);
        lineData.setValueTextSize(10f);

        lineChart.setData(lineData);
        lineChart.invalidate();
    }



    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FifteenActivity2.class);
        context.startActivity(intent);
    }
}
