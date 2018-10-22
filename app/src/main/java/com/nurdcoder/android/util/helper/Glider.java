package com.nurdcoder.android.util.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nurdcoder.android.icr_wallet.R;

import java.io.ByteArrayOutputStream;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : SUDIPTA KUMAR PAIK
 * * Date : 2/15/18
 * * Email : sudipta@w3engineers.com
 * *
 * * Purpose:
 * *
 * * Last Edited by : Rezwanur on 10/10/18.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : Rezwanur on 10/10/18.
 * ****************************************************************************
 */

public class Glider {

    public static void show(Context context, String location, ImageView imageView) {
        try {
            if (location != null && !location.isEmpty() && imageView != null && context != null) {

                RequestOptions options = new RequestOptions()
                        .fitCenter()
                        .placeholder(R.drawable.image_placeholder_app)
                        .error(R.drawable.image_placeholder_app)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .skipMemoryCache(false);

                Glide.with(context)
                        .load(location)
                        .apply(options)
                        .into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showScreenShots(Context context, String location, ImageView imageView) {
        try {
            if (location != null && !location.isEmpty() && imageView != null && context != null) {

                RequestOptions options = new RequestOptions()
                        .fitCenter()
                        .placeholder(R.drawable.image_placeholder_screenshots)
                        .error(R.drawable.image_placeholder_screenshots)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .skipMemoryCache(false);

                Glide.with(context)
                        .load(location)
                        .apply(options)
                        .into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showCircleImage(Context context, String location, ImageView imageView) {
        try {
            if (location != null && !location.isEmpty() && imageView != null && context != null) {

                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.ic_user)
                        .error(R.drawable.ic_user)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .skipMemoryCache(false);

                Glide.with(context)
                        .load(location)
                        .apply(RequestOptions.circleCropTransform())
                        .apply(options)
                        .into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * load bitmap in ImageView
     *
     * @param bitmap
     * @param imageView
     */
    public static void loadBitmap(Context context, Bitmap bitmap, ImageView imageView) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Glide.with(context)
                .asBitmap()
                .load(stream.toByteArray())
                .into(imageView);
    }
}