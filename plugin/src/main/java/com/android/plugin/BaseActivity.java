package com.android.plugin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements PluginInterface {

    private Activity that;

    @Override
    public void attach(Activity activity) {
        //上下文注入进来了
        this.that = activity;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (that == null) {
            super.onCreate(savedInstanceState);
        } else {
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (that == null) {
            super.setContentView(layoutResID);
        } else {
            that.setContentView(layoutResID);
        }

    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (that == null) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }

    }

    //重写startActivity实现页面间的跳转
    @Override
    public void startActivity(Intent intent) {
        if (that != null) {
            //ProxyActivity --->className
            Intent m = new Intent();
            //传入要跳转的activity的类名，以便使用反射来获取相应的类
            m.putExtra("className", intent.getComponent().getClassName());
            that.startActivity(m);
        } else {
            super.startActivity(intent);
        }
    }

    //重写startService实现activity调用service
    @Override
    public ComponentName startService(Intent service) {
        if (that != null) {
            Intent m = new Intent();
            m.putExtra("serviceName", service.getComponent().getClassName());
            return that.startService(m);
        }
        return super.startService(service);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (that == null) {
            super.onSaveInstanceState(outState);
        } else {

        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (that == null) {
            return super.getClassLoader();
        } else {
            return that.getClassLoader();
        }
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        if (that == null) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }
    }


    @Override
    public WindowManager getWindowManager() {
        if (that == null) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();
        }
    }

    @Override
    public Window getWindow() {
        if (that == null) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }

    public void onStart() {
        if (that == null) {
            super.onStart();
        } else {
        }
    }

    public void onDestroy() {
        if (that == null) {
            super.onDestroy();
        } else {
        }
    }

    public void onPause() {
        if (that == null) {
            super.onPause();
        } else {
        }
    }

    public void onResume() {
        if (that == null) {
            super.onResume();
        } else {
        }
    }
}
