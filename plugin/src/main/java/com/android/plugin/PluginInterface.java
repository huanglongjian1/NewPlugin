package com.android.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: Q
 * @Description: 所有插件APP必须要实现的接口，此接口规范所有插件APK的Activity标准
 **/
public interface PluginInterface {
    void setContentView(int layoutResID);

    <T extends View> T findViewById(int id);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onDestroy();

    void onPause();

    void onSaveInstanceState(Bundle outState);

    /**
     * 需要宿主app注入给插件app上下文
     */
    void attach(Activity activity);
}