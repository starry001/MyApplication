package com.starry.library;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by starry on 2016/6/23.
 */
public class ToastUtils {

    private ToastUtils() {
        throw new UnsupportedOperationException("ToastUtils can't be init");
    }

    public static void show(Context context, String msg) {
        if (TextUtils.isEmpty(msg)){
            return;
        }
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showNetError(Context context) {
        Toast.makeText(context.getApplicationContext(), "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
    }

    public static void showTokenError(Context context) {
        Toast.makeText(context.getApplicationContext(), "授权过期，请重新登录", Toast.LENGTH_SHORT).show();
    }

    public static void showEmptyHint(Context context, String str) {
        if (TextUtils.isEmpty(str)){
            return;
        }
        Toast.makeText(context.getApplicationContext(), str + "不能为空", Toast.LENGTH_SHORT).show();
    }

    public static void showNetRetryHint(Context context) {
        Toast.makeText(context.getApplicationContext(), "网络错误请重试", Toast.LENGTH_SHORT).show();
    }

    public static void showNoPrivilegeHint(Context context) {
        Toast.makeText(context.getApplicationContext(), "没有权限", Toast.LENGTH_SHORT).show();
    }
}
