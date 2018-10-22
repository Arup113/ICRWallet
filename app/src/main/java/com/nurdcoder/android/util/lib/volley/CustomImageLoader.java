package com.nurdcoder.android.util.lib.volley;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;

import java.util.Map;

/**
 * Created by to_mu on 1/9/2018.
 */

public class CustomImageLoader extends ImageLoader {
    private Map<String, String> mHeaders;
    private String mBodyType;
    private Context mContext;

    /**
     * Constructs a new ImageLoader.
     *
     * @param queue      The RequestQueue to use for making image requests.
     * @param imageCache The cache to use as an L1 cache.
     */
    public CustomImageLoader(Context aContext, RequestQueue queue, ImageCache imageCache) {
        super(queue, imageCache);
        mContext = aContext;
    }

    @Override
    protected Request<Bitmap> makeImageRequest(String requestUrl, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, final String cacheKey) {
//        return super.makeImageRequest(requestUrl, maxWidth, maxHeight, scaleType, cacheKey);
        return new ImageRequest(requestUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                onGetImageSuccess(cacheKey, response);
            }
        }, maxWidth, maxHeight,
                Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onGetImageError(cacheKey, error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return mHeaders;
            }

            @Override
            public String getBodyContentType() {
                return mBodyType;
            }
        };
    }

    public void setHeaders(Map<String, String> mHeaders) {
        this.mHeaders = mHeaders;
    }

    public void setBodyType(String mBodyType) {
        this.mBodyType = mBodyType;
    }
}
