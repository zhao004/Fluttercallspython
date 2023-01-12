import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:get/get.dart';

class HomeController extends GetxController {
  //TODO: Implement HomeController

  //调用java 方法通道
  var platform = const MethodChannel('Python');

  @override
  void onInit() {
    super.onInit();
  }

  @override
  void onReady() {
    super.onReady();
  }

  @override
  void onClose() {
    super.onClose();
  }

  //创建一个按钮点击事件
  void bu() {
    // 调用java 方法
    platform.invokeMethod('python').then((value) {
      {
        debugPrint('Flutter调用python返回的结果：$value');
      }
    });
    //调用python的add方法
    platform
        .invokeMethod('pythonadd', {"a": 2323, "b": 32434}).then((value) {
      {
        debugPrint('Flutter调用python返回的结果：$value');
      }
    });
  }
}
