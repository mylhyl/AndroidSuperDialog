package com.mylhyl.superdialog.sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mylhyl.superdialog.SuperDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1
                , new String[]{"提示框", "确定框", "换头像"}));

        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                new SuperDialog.Builder(this).setRadius(10)
                        .setAlpha(0.5f)
                        .setTitle("标题", 80, 205)
                        .setMessage("可以看到？")
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
                list.add(new People(1,"拍照"));
                list.add(new People(2,"从相册选择"));
                list.add(new People(3,"小视频"));
                new SuperDialog.Builder(this)
                        //.setAlpha(0.5f)
                        //.setGravity(Gravity.CENTER)
                        //.setTitle("上传头像", ColorRes.negativeButton)
                        .setCanceledOnTouchOutside(false)
                        .setItems(list, new SuperDialog.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Toast.makeText(MainActivity.this, strings[position], Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .build();
                break;
        }
    }
}
