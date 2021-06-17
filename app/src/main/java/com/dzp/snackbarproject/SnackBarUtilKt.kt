package com.dzp.snackbarproject

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class SnackBarUtilKt {
    fun showSnackBar(activity: Activity, view: View, msg: String,  isDismiss: Boolean, closeIcon: Int?, iSnackBarClickEventKt: ISnackBarClickEventKt): Snackbar {
//        设置展示时间
        val duringTime = when {
            isDismiss -> Snackbar.LENGTH_LONG
            else -> Snackbar.LENGTH_INDEFINITE
        }
//        获取实例
        val snackbar: Snackbar = Snackbar.make(view,"",duringTime)
//        设置展示时间，如果设置的话则Snackbar.make所设置的时间无效
//        snackbar.duration = 3000
//        设置背景透明
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
//        设置深度值
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            snackbar.view.elevation = 0f
        }
//        设置padding
        snackbar.view.setPadding(0,0,0,0)
//        获取SnackBarView
        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
//        获取SnackBarView的layoutparams
        val layoutParams = snackbarLayout.layoutParams
//        新建LayoutParams
        val flp = FrameLayout.LayoutParams(layoutParams.width, layoutParams.height)
//        设置显示位置
        flp.gravity = Gravity.TOP
//        将新的LayoutParams设置给snackbarView
        snackbarLayout.layoutParams = flp
//        获取自定义布局
        val inflate = LayoutInflater.from(activity).inflate(R.layout.layout_snackbar_view, null)
//        获取布局中的控件
        val textView = inflate.findViewById<TextView>(R.id.textView)
//        设置文案
        textView.text = msg
//        设置点击事件
        textView.setOnClickListener{ iSnackBarClickEventKt.tvClickEventKt() }
//        获取布局中的控件
        val imageView = inflate.findViewById<ImageView>(R.id.imageView2)
//        获取到图片
        val drawable = closeIcon?.let { activity.resources.getDrawable(it) }
//        设置图片
        imageView.setImageDrawable(drawable)
//        设置点击事件
        imageView.setOnClickListener { iSnackBarClickEventKt.imgClickEventKt() }
//        添加view
        snackbarLayout.addView(inflate)
//        展示
        snackbar.show()
        return snackbar
    }

    /**
     * 点击事件
     */
    interface ISnackBarClickEventKt{
        fun tvClickEventKt()
        fun imgClickEventKt()
    }
}