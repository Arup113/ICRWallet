/*
 *  ****************************************************************************
 *  * Created by : Azizul Islam on 13-Oct-17 at 4:02 PM.
 *  * Email : azizul@w3engineers.com
 *  *
 *  * Last edited by : Azizul Islam on 13-Oct-17.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
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