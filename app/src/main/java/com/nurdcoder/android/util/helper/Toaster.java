package com.nurdcoder.android.util.helper;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.widget.Toast;

import com.nurdcoder.android.icr_wallet.BuildConfig;
import com.nurdcoder.android.icr_wallet.R;

import es.dmoral.toasty.Toasty;

import static android.graphics.Typeface.BOLD_ITALIC;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : SUDIPTA KUMAR PAIK
 * * Date : 2/1/18
 * * Email : sudipta@w3engineers.com
 * *
 * * Purpose : Toaster for all type of toast showing
 * *
 * * Last Edited by : SUDIPTA KUMAR PAIK on 2/1/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : SUDIPTA KUMAR PAIK on 2/1/18.
 * ****************************************************************************
 */

public class Toaster {

    /*
     * Private constructor. Don't make it public
     * */
    private Toaster() {
    }


    /*
     * Show long toast message
     * */
    public static void show(Context context, String txt) {
        Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
    }

    /*
     * Init Toast message context only one time
     * */
    public static void init(Context context) {
        Toasty.Config.getInstance()
                //.setErrorColor(getColor(R.color.active_color))
                //.setInfoColor(getColor(R.color.active_color))
                //.setSuccessColor(getColor(R.color.active_color))
                //.setWarningColor(getColor(R.color.active_color))
                //.setTextColor(getColor(R.color.active_color))
                .tintIcon(true)
                //.setToastTypeface(Typeface.createFromAsset(sContext.getAssets(), "PCap Terminal.otf"))
                //.setTextSize(25)
                .apply();
    }

    private static int getColor(Context context, int active_color) {
        return context.getResources().getColor(active_color);
    }

    public static void showLong(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toast.makeText(context, txt, Toast.LENGTH_LONG).show();
        }
    }

    /*
     * Show short toast message
     * */
    public static void showShort(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * Show short toast message only in debug mode
     * */
    public static void showDebugToast(Context context, String txt) {
        if (!TextUtils.isEmpty(txt) && BuildConfig.DEBUG) {
            Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
        }
    }

    public static void error(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.error(context, txt, Toast.LENGTH_SHORT, true).show();
        }
    }

    public static void success(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.success(context, txt, Toast.LENGTH_SHORT, true).show();
        }
    }

    public static void info(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.info(context, txt, Toast.LENGTH_SHORT, true).show();
        }
    }

    public static void warning(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.warning(context, txt, Toast.LENGTH_SHORT, true).show();
        }
    }

    public static void normal(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.normal(context, txt).show();
        }
    }

    public static void normalWithIcon(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.normal(context, txt, R.drawable.near_by_tab_icon).show();
        }
    }

    private CharSequence getFormattedMessage() {
        final String prefix = "Formatted ";
        final String highlight = "bold italic";
        final String suffix = " text";

        SpannableStringBuilder ssb = new SpannableStringBuilder(prefix).append(highlight).append(suffix);
        int prefixLen = prefix.length();
        ssb.setSpan(new StyleSpan(BOLD_ITALIC),
                prefixLen, prefixLen + highlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ssb;
    }


}