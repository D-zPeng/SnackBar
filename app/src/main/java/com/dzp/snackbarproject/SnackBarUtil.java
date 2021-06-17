package com.dzp.snackbarproject;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SnackBarUtil {
    public static Snackbar showSnackBar(Activity activity, View view, String msg, boolean isDismiss, int closeIcon, ISnackBarClickEvent iSnackBarClickEvent) {
        int duringTime = Snackbar.LENGTH_LONG;
        if (isDismiss) {
            duringTime = Snackbar.LENGTH_LONG;
        } else {
            duringTime = Snackbar.LENGTH_INDEFINITE;
        }
        Snackbar snackbar = null;
        snackbar = Snackbar.make(view, "", duringTime);
        snackbar.setDuration(3000);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        snackbar.getView().setPadding(0, 0, 0, 0);
        Snackbar.SnackbarLayout sl = (Snackbar.SnackbarLayout) snackbar.getView();
        ViewGroup.LayoutParams layoutParams = sl.getLayoutParams();
//        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height);
//        flp.gravity = Gravity.CENTER;
//        sl.setLayoutParams(flp);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_snackbar_view, null);
        TextView textView = inflate.findViewById(R.id.textView);
        textView.setText(msg);
        textView.setOnClickListener(v -> {
            if (iSnackBarClickEvent != null) {
                iSnackBarClickEvent.tvClickEvent();
            }
        });
        ImageView imageView = inflate.findViewById(R.id.imageView2);
        Drawable drawable = activity.getResources().getDrawable(closeIcon);
        imageView.setImageDrawable(drawable);
        imageView.setOnClickListener(v -> {
            if (iSnackBarClickEvent != null) {
                iSnackBarClickEvent.imgClickEvent();
            }
        });
        sl.addView(inflate);
        snackbar.show();
        return snackbar;
    }

    public interface ISnackBarClickEvent {
        void tvClickEvent();

        void imgClickEvent();
    }
}
