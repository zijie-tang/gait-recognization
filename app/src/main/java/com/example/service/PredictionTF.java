package com.example.service;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;


public class PredictionTF {
    private static final String TAG = "PredictionTF";
    //设置模型输入/输出节点的数据维度
    private static final int IN_COL = 1;
    private static final int IN_ROW = 28*28;
    private static final int OUT_COL = 1;
    private static final int OUT_ROW = 1;
    //模型中输入变量的名称
    private static final String inputName = "input/x_input";
    //模型中输出变量的名称
    private static final String outputName = "output";

    TensorFlowInferenceInterface inferenceInterface;
    static {
        //加载libtensorflow_inference.so库文件
        System.loadLibrary("tensorflow_inference");
        Log.e(TAG,"libtensorflow_inference.so库加载成功");
    }

    public PredictionTF(AssetManager assetManager, String modePath) {
        //初始化TensorFlowInferenceInterface对象
        inferenceInterface = new TensorFlowInferenceInterface(assetManager,modePath);
        Log.e(TAG,"TensoFlow模型文件加载成功");
    }

    public int[] getPredict(float []inputdata) {
        //将数据feed给tensorflow的输入节点
        inferenceInterface.feed(inputName, inputdata, IN_COL, IN_ROW);
        //运行tensorflow
        String[] outputNames = new String[] {outputName};
        inferenceInterface.run(outputNames);
        ///获取输出节点的输出信息
        int[] outputs = new int[OUT_COL*OUT_ROW]; //用于存储模型的输出数据
        inferenceInterface.fetch(outputName, outputs);
        return outputs;
    }
}
