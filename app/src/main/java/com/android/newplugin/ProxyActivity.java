package com.android.newplugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.android.plugin.PluginInterface;
import com.android.plugin.PluginManager;

public class ProxyActivity extends AppCompatActivity {
    public static final String CLASS_NAME = "classname";
    PluginInterface pluginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String class_name = intent.getStringExtra(CLASS_NAME);
        Log.e("name", class_name);
        //  setContentView(R.layout.activity_proxy);
        try {
            //加载该Activity的字节码对象
            Class<?> aClass = PluginManager.getInstance().getDexClassLoader().loadClass(class_name);
            //创建该Activity的示例
            Object newInstance = aClass.newInstance();
            //是否遵循了我们的标准
            if (newInstance instanceof PluginInterface) {
                pluginInterface = (PluginInterface) newInstance;
                Log.e("pluginInterface", pluginInterface.toString());
                //将代理Activity的上下文 传入到插件Apk中的activity里面
                pluginInterface.attach(this);
                pluginInterface.onCreate(savedInstanceState);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }
}