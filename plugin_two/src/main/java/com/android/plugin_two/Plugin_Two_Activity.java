package com.android.plugin_two;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.plugin.BaseActivity;

import java.util.Random;

public class Plugin_Two_Activity extends BaseActivity {
    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //  super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_two);
        TextView textView = findViewById(R.id.plugin_two_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("two", index + ":点击次数");
                index++;
                textView.setText("随机数字：" + new Random().nextInt(Integer.MAX_VALUE));
            }
        });
    }

    int index = 0;
}