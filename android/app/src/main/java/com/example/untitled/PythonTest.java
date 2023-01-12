package com.example.untitled;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class PythonTest {
    private String cHANNEL = "Python";

    //创建构造函数
    public PythonTest(String name, FlutterEngine flutterEngine, Context context) {
        super();
        cHANNEL = name;
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), cHANNEL).setMethodCallHandler(
                (call, result) -> {
                    if ("python".equals(call.method)) {
                        if (!Python.isStarted()) {
                            Python.start(new AndroidPlatform(context));
                        }
                        Python python = Python.getInstance();
                        PyObject pyObject = python.getModule("index");
                        PyObject s = pyObject.callAttr("getTxt", "这是传给python的内容");
                        String t = s.toJava(String.class);
                        result.success(t.toString());
                    } else if ("pythonadd".equals(call.method)) {
                        //字符串转换为int
                        int a = Integer.parseInt(call.argument("a").toString());
                        int b = Integer.parseInt(call.argument("b").toString());
                        Python python = Python.getInstance();
                        PyObject pyObject = python.getModule("index");
                        PyObject s = pyObject.callAttr("add", a, b);
                        Object t = s.toJava(Object.class);
                        result.success(t.toString());
                    } else {
                        result.notImplemented();
                    }
                }
        );
    }

}
