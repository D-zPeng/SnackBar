package com.dzp.snackbarproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements SnackBarUtil.ISnackBarClickEvent {

    private Button button, button2;
    private String TAG = "SnackBarUtil";
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
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
}