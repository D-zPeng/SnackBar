package com.dzp.snackbarproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements SnackBarUtil.ISnackBarClickEvent, SnackBarUtilKt.ISnackBarClickEventKt {

    private Button button, button2, button3;
    private String TAG = "SnackBarUtil";
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        View viewById = this.getWindow().getDecorView().findViewById(android.R.id.content);
        button.setOnClickListener(v -> {
            snackbar = Snackbar.make(viewById, "这是默认的SnackBar", Snackbar.LENGTH_LONG);
            snackbar.setAction("关闭", v1 -> {
                Log.d(TAG, "setAction: 点击了关闭");
                snackbar.dismiss();
            });
            snackbar.show();
        });

        button2.setOnClickListener(v -> {
            snackbar = SnackBarUtil.showSnackBar(this, viewById, "这是自定义的SnackBar", true, R.mipmap.close, this);
        });
        button3.setOnClickListener(v -> {
            snackbar = new SnackBarUtilKt().showSnackBar(this,viewById,"这是kotlin代码编写的",false,R.mipmap.close,this);
        });
    }

    @Override
    public void tvClickEvent() {
        Log.d(TAG, "tvClickEvent: 点击了");
    }

    @Override
    public void imgClickEvent() {
        Log.d(TAG, "imgClickEvent: 点击了");
        snackbar.dismiss();
    }

    @Override
    public void tvClickEventKt() {
        Log.d(TAG, "tvClickEventKt: 点击了文字");
    }

    @Override
    public void imgClickEventKt() {
        Log.d(TAG, "imgClickEventKt: 点击了图片");
        snackbar.dismiss();
    }
}