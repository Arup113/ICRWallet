package com.nurdcoder.android.util.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by: MD. REZWANUR RAHMAN KHAN on 9/11/2018 at 6:06 PM.
 * Email: rezwanur@w3engineers.com
 * Code Responsibility: Progress dialog helper class
 * Last edited by : Rezwanur on 9/11/18.
 * Last Reviewed by : <NAME> on <DATE>.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class ProgressDialogHelper {

    ProgressDialog mProgressDialog;
    private Activity mContext;

    public ProgressDialogHelper() {

    }

    public ProgressDialogHelper(Activity context, String title) {
        this.mContext = context;
        mProgressDialog = new ProgressDialog(context);
//        mProgressDialog.setTitle("Please wait");
        mProgressDialog.setMessage(title);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setIndeterminate(true);
    }

    public ProgressDialogHelper(Context context, String title, String message) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    public ProgressDialog getProgressDialog() {
        return mProgressDialog;
    }

    public void setProgressDialog(ProgressDialog mProgressDialog) {
        //make sure the previous dialog is hidden
        hide();
        this.mProgressDialog = mProgressDialog;
    }

    public void show() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void create(Context context, String title, String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = ProgressDialog.show(context, title, message);
    }

    public void hide() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public void dismiss() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgressDialog.dismiss();
                }
            });
            mProgressDialog = null;
        }
    }

    public void onDestroy() {
        hide();
    }
}
