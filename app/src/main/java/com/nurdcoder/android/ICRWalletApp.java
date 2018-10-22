package com.nurdcoder.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.Stetho;
import com.nurdcoder.android.icr_wallet.BuildConfig;
import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.RemoteHelper;
import com.nurdcoder.android.icr_wallet.data.local.dbstorage.DatabaseService;
import com.nurdcoder.android.icr_wallet.data.remote.RemoteApi;
import com.nurdcoder.android.icr_wallet.data.remote.helper.RemoteApiOption;
import com.nurdcoder.android.util.helper.ShowLog;
import com.nurdcoder.android.util.helper.Toaster;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : Anjan Debnath
 * * Date : 10/25/17
 * * Email : anjan@w3engineers.com
 * *
 * * Purpose: Base Application class
 * *
 * * Last Edited by : SUDIPTA KUMAR PAIK on 03/08/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : SUDIPTA KUMAR PAIK on 03/08/18.
 * ****************************************************************************
 */

@ReportsCrashes(mailTo = "mithun@w3engineers.com", // my email here
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text,
        customReportContent = {
                ReportField.APP_VERSION_NAME,
                ReportField.ANDROID_VERSION,
                ReportField.PACKAGE_NAME,
                ReportField.STACK_TRACE,
                ReportField.APPLICATION_LOG,

        })
public class ICRWalletApp extends MultiDexApplication {

    public static final String TAG = ICRWalletApp.class.getSimpleName();
    private static ICRWalletApp mInstance;
    private RequestQueue mRequestQueue;
//    private Tracker mTracker;

    public static synchronized ICRWalletApp getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }

//    /**
//     * insight.arup@gmail.com
//     * Gets the default {@link Tracker} for this {@link Application}.
//     *
//     * @return tracker
//     */
//    synchronized public Tracker getDefaultTracker() {
//        if (mTracker == null) {
//            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
//            mTracker = analytics.newTracker(R.xml.global_tracker);
//        }
//        return mTracker;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        printKeyHash();
        //Previous versions of Firebase
//        Firebase.setAndroidContext(this);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        mInstance = this;

        new InitRemoteAsync().execute();

//        PrepositionExtractor.init(this);

        if (BuildConfig.DEBUG) {
            ACRA.init(this);
        }
        //LeakCanary(sContext);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    /**
     * Call this method inside onCreate once to get your hash key
     */
    public void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.afchealth.afcpharmacyonline", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                //ShowLog.e("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
        }
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(final String tag) {
        ShowLog.e("CANCEL REQ -> url : ", tag);
        RequestQueue.RequestFilter filter = new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return tag.equals(request.getTag());
            }
        };
        mRequestQueue.cancelAll(filter);
    }

//    private void LeakCanary(Context context) {
//
//        if (!LeakCanary.isInAnalyzerProcess(context)) {
//            LeakCanary.install(this);
//        }
//    }

    private void debugLoader(Context context) {
        if (!BuildConfig.DEBUG) return;

        Stetho.initializeWithDefaults(context);
        //Timber.plant(new Timber.DebugTree());//skpaik: Comment Out
        //new DemoHelper().startUpEvents(context);
    }

    private class InitRemoteAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            RemoteHelper.init();
            RemoteApiOption remoteApiOption = new RemoteApiOption(true);
            remoteApiOption.setStartRightMesh(false);

            RemoteApi.init(getApplicationContext(), remoteApiOption);
            releaseLoader(getApplicationContext());
            debugLoader(getApplicationContext());
            return null;
        }
    }

    private void releaseLoader(Context context) {
        Toaster.init(context);
//        SharedPref.init(context);
//        Notify.init(context);
//        PermissionUtil.init(context);
        DatabaseService.init(context);
//        PackageOperationTracks.init();
//        DownloadManagerHelper.init(context);
    }
}