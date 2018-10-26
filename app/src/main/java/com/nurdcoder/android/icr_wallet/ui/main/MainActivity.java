package com.nurdcoder.android.icr_wallet.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nurdcoder.android.icr_wallet.R;
import com.nurdcoder.android.icr_wallet.data.helper.keys.PreferenceKey;
import com.nurdcoder.android.icr_wallet.databinding.ActivityMainBinding;
import com.nurdcoder.android.icr_wallet.ui.base.BaseActivity;
import com.nurdcoder.android.icr_wallet.ui.home.HomeFragment;
import com.nurdcoder.android.icr_wallet.ui.my_addresses.MyAddressesFragment;
import com.nurdcoder.android.icr_wallet.ui.send.SendFragment;
import com.nurdcoder.android.icr_wallet.ui.sign_in.SignInActivity;
import com.nurdcoder.android.icr_wallet.ui.transactions.TransactionsFragment;
import com.nurdcoder.android.util.helper.BarUtil;
import com.nurdcoder.android.util.helper.ScreenUtils;
import com.nurdcoder.android.util.helper.SharedPreferencesManager;

import java.util.Objects;

public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView, NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding mBinding;

    private AppCompatImageView mUserAvatar;
    private TextView mUserName;
    private TextView mUserEmail;
    private Toolbar mToolbar;

    public static void runActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        runCurrentActivity(context, intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBinding.drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityMainBinding) getViewDataBinding();
        setUpToolbar();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);

        setUpNavHeader();
        commitFragment(R.id.main_fragment_container, HomeFragment.newInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNavHeaderData();
    }

    private void setUpNavHeader() {
        View hView = mBinding.navView.getHeaderView(0);
        mUserAvatar = hView.findViewById(R.id.nav_header_main_avatar_iv);
        mUserName = hView.findViewById(R.id.nav_header_main_name_tv);
        mUserEmail = hView.findViewById(R.id.nav_header_main_email_tv);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int statusBarHeight = BarUtil.getStatusBarHeight(Objects.requireNonNull(this));
            ScreenUtils.setMarginInPixel(mUserAvatar, 0, statusBarHeight, 0, 0);
        }
    }

    private void setNavHeaderData() {
//        ParseFile imageFile = ParseUser.getCurrentUser().getParseFile(Constants.KEY_PICTURE);
//        if (imageFile != null) {
//            Glider.showCircleImage(imageFile.getUrl(), mUserAvatar);
//        }
        mUserName.setText(SharedPreferencesManager.getStringSetting(this, PreferenceKey.KEY_USER_NAME, ""));
        mUserEmail.setText(SharedPreferencesManager.getStringSetting(this, PreferenceKey.KEY_USER_EMAIL, ""));
    }

    @Override
    protected void startUI() {

    }

    @Override
    protected void stopUI() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_home:
                commitFragment(R.id.main_fragment_container, HomeFragment.newInstance());
                break;
            case R.id.nav_send:
                commitFragment(R.id.main_fragment_container, SendFragment.newInstance());
                break;
            case R.id.nav_my_addresses:
                commitFragment(R.id.main_fragment_container, MyAddressesFragment.newInstance());
                break;
            case R.id.nav_transfer_amount:
                commitFragment(R.id.main_fragment_container, HomeFragment.newInstance());
                break;
            case R.id.nav_transactions:
                commitFragment(R.id.main_fragment_container, TransactionsFragment.newInstance());
                break;
            case R.id.nav_log_out:
                SharedPreferencesManager.setBooleanSetting(this, PreferenceKey.KEY_IS_LOGGED_IN, false);
                SignInActivity.runActivity(this, "");
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
