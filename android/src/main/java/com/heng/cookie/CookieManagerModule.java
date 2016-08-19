package com.heng.cookie;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

/**
 * Created by heng on 16/1/22.
 * <p>
 * Edited by heng on 16/08/19
 * 1. Removed all ArrayList<String>
 * 2. set(options,callback) -> setCookie(options)
 * 3. getAll(callback) -> getCookie()
 * 4. clearAll(callback) -> removeAllCookies()
 */
public class CookieManagerModule extends ReactContextBaseJavaModule {

    private static final String REACT_CLASS = "CookieManager";

    private static final String OPTIONS_NAME = "name";
    private static final String OPTIONS_VALUE = "value";
    private static final String OPTIONS_DOMAIN = "domain";
    private static final String OPTIONS_ORIGIN = "origin";
    private static final String OPTIONS_PATH = "path";
    private static final String OPTIONS_EXPIRATION = "expiration";

    public CookieManagerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void setCookie(ReadableMap options) {
        String name = null;
        String value = null;
        String domain = null;
        String origin = null;
        String path = null;
        String expiration = null;
        if (options.hasKey(OPTIONS_NAME)) {
            name = options.getString(OPTIONS_NAME);
        }
        if (options.hasKey(OPTIONS_VALUE)) {
            value = options.getString(OPTIONS_VALUE);
        }
        if (options.hasKey(OPTIONS_DOMAIN)) {
            domain = options.getString(OPTIONS_DOMAIN);
        }
        if (options.hasKey(OPTIONS_ORIGIN)) {
            origin = options.getString(OPTIONS_ORIGIN);
        }
        if (options.hasKey(OPTIONS_PATH)) {
            path = options.getString(OPTIONS_PATH);
        }
        if (options.hasKey(OPTIONS_EXPIRATION)) {
            expiration = options.getString(OPTIONS_EXPIRATION);
        }
        CookieManager.getInstance().setCookie(origin, name + "=" + value + ";"
                + OPTIONS_PATH + "=" + path + ";"
                + OPTIONS_EXPIRATION + "=" + expiration + ";"
                + OPTIONS_DOMAIN + "=" + domain);
    }

    /**
     * Gets the cookies for the given URL.
     *
     * @param url the URL for which the cookies are requested
     * @return value the cookies as a string, using the format of the 'Cookie'
     * HTTP request header
     */
    @ReactMethod
    public void getCookie(String url, Callback callback) {
        String cookie = CookieManager.getInstance().getCookie(url);
        callback.invoke(cookie);
    }

    @ReactMethod
    public void removeAllCookies() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().removeAllCookies(new ValueCallback<Boolean>() {
                @Override
                public void onReceiveValue(Boolean value) {
                }
            });
        } else {
            CookieManager.getInstance().removeAllCookie();
        }
    }
}