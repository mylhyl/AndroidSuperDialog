package com.mylhyl.superdialog.sample;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mylhyl.superdialog.SuperDialog;
import com.mylhyl.superdialog.res.values.ColorRes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1
                , new String[]{"提示框", "确定框", "换头像", "消息框"}));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                new SuperDialog.Builder(this).setRadius(10)
//                        .setAlpha(0.5f)
                        .setTitle("标题", 80, 205)
                        .setMessage("可以看到？")
//                        .setDimEnabled(false)
//                        .setBackgroundColor(Color.WHITE)
//                        .setMessage("内容", Color.RED,250)
                        .setPositiveButton("确定", new SuperDialog.OnClickPositiveListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(), "点了确定", Toast.LENGTH_LONG).show();
                            }
                        }).build();
                break;
            case 1:
                new SuperDialog.Builder(this).setTitle("标题").setMessage("内容从这里开始了")
                        .setBackgroundColor(Color.WHITE)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new SuperDialog.OnClickPositiveListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(), "点了确定", Toast.LENGTH_LONG).show();
                            }
                        }).build();
                break;
            case 2:
                final String[] strings = {"拍照", "从相册选择", "小视频"};
                List<People> list = new ArrayList<>();
                list.add(new People(1, "拍照"));
                list.add(new People(2, "从相册选择"));
                list.add(new People(3, "小视频"));
                new SuperDialog.Builder(this)
//                        .setBackgroundColor(Color.WHITE)
                        //.setAlpha(0.5f)
                        //.setGravity(Gravity.CENTER)
                        .setTitle("上传头像", ColorRes.negativeButton)
                        .setCanceledOnTouchOutside(false)
                        //setItems默认是底部位置
                        .setItems(list, new SuperDialog.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Toast.makeText(MainActivity.this, strings[position], Toast
                                        .LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("取消", null)
//                        .setConfigDialog(new SuperDialog.ConfigDialog() {
//                            @Override
//                            public void onConfig(Dialog dialog, Window window, WindowManager
//                                    .LayoutParams wlp, DisplayMetrics dm) {
//                                //window.setWindowAnimations(R.style.dialogWindowAnim);
//                                wlp.y = 100;//底部距离
//                            }
//                        })
                        .setItemsBottomMargin(20)
                        .setWindowAnimations(R.style.dialogWindowAnim)//动画
                        .build();
                break;
            case 3:
                new SuperDialog.Builder(this).setRadius(50)
                        //.setWidth(0.75f)
//                        .setConfigDialog(new SuperDialog.ConfigDialog() {
//                            @Override
//                            public void onConfig(Dialog dialog, Window window, WindowManager
//                                    .LayoutParams wlp, DisplayMetrics dm) {
//                                wlp.gravity = Gravity.TOP;
//                                wlp.y = 200;
//                                wlp.x = 100;
//                                wlp.width = 300;
//                                wlp.height = 300;
//                            }
//                        })
                        .setCanceledOnTouchOutside(false)
                        .setCancelable(false)
                        .setGravity(Gravity.TOP)
                        .setPadding(50,0,50,0)
                        .setTitle("提示", 80, 205)
                        .setMessage("正在授权解锁...").build();
                break;
        }
    }
}
