package com.example.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back;
    private Button X;
    private Button Y;
    private Button Z;
    private TextView staticData;
    public LineChart linechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        initView();
        setData(linechart,1);
        float minValue,maxValue,meanValue,totalValue;
        minValue=maxValue=CollectActivity.XAxis.get(0);
        meanValue=totalValue=0;
        for (int i = 0; i < CollectActivity.XAxis.size(); i++) {
            if(minValue>CollectActivity.XAxis.get(i)){
                minValue=CollectActivity.XAxis.get(i);
            }
            if(maxValue<CollectActivity.XAxis.get(i)){
                maxValue=CollectActivity.XAxis.get(i);
            }
            totalValue+=CollectActivity.XAxis.get(i);
        }
        meanValue=totalValue/CollectActivity.XAxis.size();
        staticData.setText("最大值："+Float.toString(maxValue)+"\n最小值："+Float.toString(minValue)+"\n平均值："+Float.toString(meanValue));

    }


    private void initView() {
        back = findViewById(R.id.back);
        X = findViewById(R.id.XAxis);
        Y = findViewById(R.id.YAxis);
        Z = findViewById(R.id.ZAxis);
        staticData=findViewById(R.id.staticData);
        linechart=(LineChart)findViewById(R.id.line_chart);
        back.setOnClickListener(this);
        X.setOnClickListener(this);
        Y.setOnClickListener(this);
        Z.setOnClickListener(this);
    }

    public static float convertToFloat(String number, float defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(number);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    public static void setData(LineChart lineChart,int x) {

//        数据部分
        ArrayList<Entry> times_data = new ArrayList<Entry>();
        String raw1[]=CollectActivity.LS.get(0).split(" ");
        float ydata=0;
        for (int i = 0; i < CollectActivity.LS.size(); i++) {
            String info[]=CollectActivity.LS.get(i).split(" ");
            times_data.add(new Entry((float) (0.02*i),convertToFloat(info[x],ydata)));
        }

        LineDataSet timeslineData = new LineDataSet(times_data,"加速度");
        LineData data = new LineData(timeslineData);
        lineChart.setData(data);

//      去除description
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);

//      去除对图标的触摸操作，如果没有去除，可以通过触摸对折线图进行
//        放大，缩小等操作。
        lineChart.setTouchEnabled(false);
//        将折现转变位曲线
        timeslineData.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        设置曲线颜色
        timeslineData.setColor(Color.BLUE);
//        不显示圆点(默认是在每个数据点为蓝色小圆圈)
        timeslineData.setDrawCircles(false);
//        不显示数据点的数值
        timeslineData.setDrawValues(false);

//        X轴处理
        XAxis xAxis = lineChart.getXAxis();
//        设置X轴的位置为正下方
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        设置X轴坐标的刻度，即X轴上出现几个刻度
        xAxis.setLabelCount(6);
//        去除X轴上网格竖线
        xAxis.setDrawGridLines(false);
//        避免“剪掉”在x轴上的图表或屏幕边缘的第一个和最后一个坐标轴标签项
        xAxis.setAvoidFirstLastClipping(true);
//        自定义X轴上的刻度显示
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) (value) + "s";
            }
        });
        lineChart.invalidate();


//        Y轴处理
        YAxis ylAxis =lineChart.getAxis(YAxis.AxisDependency.LEFT);
//        设置Y轴的最大值
        ylAxis.setAxisMaximum(40);
        ylAxis.setAxisMinimum(-30);
//        Y轴横线设置为虚线
        ylAxis.enableGridDashedLine(20, 15, 0);
//        自定义Y轴的刻度显示
        ylAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "";
            }
        });

        // 隐藏右侧Y轴（默认的折线图是左右两侧各一条Y轴）
        YAxis yrAxis = lineChart.getAxisRight();
        yrAxis.setEnabled(false);

        // 去掉 Legend
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
    }

    public void onClick(View v) {
        float minValue,maxValue,meanValue,totalValue;
        switch (v.getId()) {
            case R.id.back:
                Intent intent1 = new Intent(this, CollectActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.XAxis:
                setData(linechart,1);
                minValue=maxValue=CollectActivity.XAxis.get(0);
                meanValue=totalValue=0;
                for (int i = 0; i < CollectActivity.XAxis.size(); i++) {
                    if(minValue>CollectActivity.XAxis.get(i)){
                        minValue=CollectActivity.XAxis.get(i);
                    }
                    if(maxValue<CollectActivity.XAxis.get(i)){
                        maxValue=CollectActivity.XAxis.get(i);
                    }
                    totalValue+=CollectActivity.XAxis.get(i);
                }
                meanValue=totalValue/CollectActivity.XAxis.size();
                staticData.setText("最大值："+Float.toString(maxValue)+"\n最小值："+Float.toString(minValue)+"\n平均值："+Float.toString(meanValue));
                break;
            case R.id.YAxis:
                setData(linechart,2);
                minValue=maxValue=CollectActivity.YAxis.get(0);
                meanValue=totalValue=0;
                for (int i = 0; i < CollectActivity.YAxis.size(); i++) {
                    if(minValue>CollectActivity.YAxis.get(i)){
                        minValue=CollectActivity.YAxis.get(i);
                    }
                    if(maxValue<CollectActivity.YAxis.get(i)){
                        maxValue=CollectActivity.YAxis.get(i);
                    }
                    totalValue+=CollectActivity.YAxis.get(i);
                }
                meanValue=totalValue/CollectActivity.YAxis.size();
                staticData.setText("最大值："+Float.toString(maxValue)+"\n最小值："+Float.toString(minValue)+"\n平均值："+Float.toString(meanValue));
                break;
            case R.id.ZAxis:
                setData(linechart,3);
                minValue=maxValue=CollectActivity.ZAxis.get(0);
                meanValue=totalValue=0;
                for (int i = 0; i < CollectActivity.ZAxis.size(); i++) {
                    if(minValue>CollectActivity.ZAxis.get(i)){
                        minValue=CollectActivity.ZAxis.get(i);
                    }
                    if(maxValue<CollectActivity.ZAxis.get(i)){
                        maxValue=CollectActivity.ZAxis.get(i);
                    }
                    totalValue+=CollectActivity.ZAxis.get(i);
                }
                meanValue=totalValue/CollectActivity.ZAxis.size();
                staticData.setText("最大值："+Float.toString(maxValue)+"\n最小值："+Float.toString(minValue)+"\n平均值："+Float.toString(meanValue));
                break;
        }
    }
}
