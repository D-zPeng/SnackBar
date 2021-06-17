package com.dzp.snackbarproject;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SnackBarUtil {
    /**
     * Snackbar 展示工具
     * @param activity  上下文
     * @param view      配合SnackBar使用的CoordinatorLayout
     * @param msg       展示文案
     * @param isDismiss 是否自动关闭
     * @param closeIcon 关闭按钮
     * @param iSnackBarClickEvent   点击事件
     * @return          Snackbar实例
     */
    public static Snackbar showSnackBar(Activity activity, View view, String msg, boolean isDismiss, int closeIcon, ISnackBarClickEvent iSnackBarClickEvent) {
        int duringTime = Snackbar.LENGTH_LONG;
        // 设置显示状态
        if (isDismiss) {
//            会自动消失
            duringTime = Snackbar.LENGTH_LONG;
        } else {
//            需要手动点击消失
            duringTime = Snackbar.LENGTH_INDEFINITE;
        }
        Snackbar snackbar = null;
//        获取SnackBar实例
        snackbar = Snackbar.make(view, "", duringTime);
        snackbar.setDuration(3000);
//        设置snackbar的深度，避免被其他控件遮挡
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            snackbar.getView().setElevation(0);
        }
//        设置背景透明，避免自带黑色背景影响
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
//        设置padding 取消自定义时黑色边框
        snackbar.getView().setPadding(0, 0, 0, 0);
        Snackbar.SnackbarLayout sl = (Snackbar.SnackbarLayout) snackbar.getView();
//        设置SnackBar的显示位置
//        ViewGroup.LayoutParams layoutParams = sl.getLayoutParams();
//        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height); // 将原来Snackbar的宽高传入新的LayoutParams
//        flp.gravity = Gravity.CENTER; // 设置显示位置
//        sl.setLayoutParams(flp);
//        获取自定义布局
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_snackbar_view, null);
//        获取布局内控件
        TextView textView = inflate.findViewById(R.id.textView);
//        设置文案
        textView.setText(msg);
//        设置点击事件
        textView.setOnClickListener(v -> {
            if (iSnackBarClickEvent != null) {
                iSnackBarClickEvent.tvClickEvent();
            }
        });
        //        获取布局内控件
        ImageView imageView = inflate.findViewById(R.id.imageView2);
        //        获取图片资源
        Drawable drawable = activity.getResources().getDrawable(closeIcon);
//        设置图片
        imageView.setImageDrawable(drawable);
//        设置点击事件
        imageView.setOnClickListener(v -> {
            if (iSnackBarClickEvent != null) {
                iSnackBarClickEvent.imgClickEvent();
            }
        });
//        将自定义布局添加到SnackbarView中
        sl.addView(inflate);
//        显示
        snackbar.show();
        return snackbar;
    }

//    点击事件
    public interface ISnackBarClickEvent {
        void tvClickEvent();

        void imgClickEvent();
    }
}
