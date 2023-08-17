package com.android.newplugin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.newplugin.adapter.MyRVAdapter;
import com.android.plugin.PluginManager;

import java.io.File;
import java.io.FileFilter;

public class MainActivity extends AppCompatActivity {
    public static final String APK_DIR = "Plugin_One";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.goto_plugin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        MyRVAdapter myRVAdapter = new MyRVAdapter();
        RecyclerView recyclerView = findViewById(R.id.main_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(myRVAdapter);
        File file = getExternalFilesDir(APK_DIR);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".apk");
            }
        });
        for (File file1 : files) {
            myRVAdapter.add(file1.getAbsolutePath());
        }
        //  loadApk();
        myRVAdapter.setOnItemClick(new MyRVAdapter.ItemClick() {
            @Override
            public void OnItmClick(int position, String file_path) {
                Log.e("file_name", file_path);
                loadApk(file_path);
                Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
                String otherApkMainActivityName = PluginManager.getInstance().getPackageInfo().activities[0].name;
                intent.putExtra(ProxyActivity.CLASS_NAME, otherApkMainActivityName);
                getActivityResultRegistry().register("start", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                    }
                }).launch(intent);
            }
        });
    }

    //动态申请权限
    private void loadApk(String pluginPath) {
        //给插件加载器传入上下文
        PluginManager instance = PluginManager.getInstance();
        instance.setContext(this);
//        //加载插件APK，路径的话最好写本APP的缓存路径或者私有文件路径，即：/data/data/packageName/cache/，因为高版本安卓中如果使用其他路径会有很多问题
//        String pluginPath = getExternalFilesDir(APK_DIR) + "/plugin.apk";
        if (!new File(pluginPath).exists()) {
            //防止空指针
            Toast.makeText(this, "插件不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        instance.loadFromPath(pluginPath);
    }


}