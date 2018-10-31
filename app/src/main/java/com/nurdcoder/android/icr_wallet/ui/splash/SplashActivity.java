/*
 * Copyright (C) 2017 NURDCODER
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://nurdcoder.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.nurdcoder.android.icr_wallet.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.databinding.ActivitySplashBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseActivity;
import com.nurdcoder.android.icr_wallet.ui.main.MainActivity;
import com.nurdcoder.android.icr_wallet.ui.sign_in.SignInActivity;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;
import com.race604.drawable.wave.WaveDrawable;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 10/25/2018
 * * Email : muktadir@nurdcoder.com
 * *
 * * Purpose :
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 10/25/2018.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 10/25/2018.
 * ****************************************************************************
 */

public class SplashActivity extends BaseActivity<SplashMvpView, SplashPresenter> implements SplashMvpView {

    private static final int SPLASH_TIME = 0;
    private ActivitySplashBinding activitySplashBinding;
    private WaveDrawable mWaveDrawable;

    /**
     * Start Activity (Pass value as a 2nd Parameter)
     * Date: 2018-03-13
     * Added By: Sudipta K Paik
     *
     * @param context
     **/
    public static void runActivity(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        runCurrentActivity(context, intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void startUI() {
        activitySplashBinding = (ActivitySplashBinding) getViewDataBinding();
        mWaveDrawable = new WaveDrawable(this, R.mipmap.ic_launcher);
        mWaveDrawable.setLevel(0);
        mWaveDrawable.setWaveAmplitude(0);
        mWaveDrawable.setWaveLength(600);
        mWaveDrawable.setWaveSpeed(0);
        activitySplashBinding.imageAppName.setImageDrawable(mWaveDrawable);
//        Animation downTop = AnimationUtils.loadAnimation(this, R.anim.downtop);
//        activitySplashBinding.imageAppName.setAnimation(downTop);
        goToNextPage();
    }

    @Override
    protected void stopUI() {
    }

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    public void goToNextPage() {
        new Handler().postDelayed(new Runnable() {
            public int mProgress = 0;

            @Override
            public void run() {
                new CountDownTimer(3000, 30) {

                    public void onTick(long millisUntilFinished) {
                        mWaveDrawable.setLevel(++mProgress * 100);
                    }

                    public void onFinish() {
                        mWaveDrawable.setLevel(++mProgress * 100);

                        if (SharedPreferencesManager.getBooleanSetting(SplashActivity.this, PreferenceKey.KEY_IS_LOGGED_IN, false)) {
                            MainActivity.runActivity(SplashActivity.this);
                        } else {
                            SignInActivity.runActivity(SplashActivity.this, null);
                        }
                        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
                        finish();
                    }
                }.start();
            }
        }, SPLASH_TIME);
    }
}