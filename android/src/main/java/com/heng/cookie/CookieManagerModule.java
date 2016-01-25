package com.heng.cookie;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import java.util.ArrayList;

/**
 * Created by heng on 16/1/22.
 */
public class CookieManagerModule extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "RCTCookieManager";

    public static final String OPTIONS_NAME = "name";
    public static final String OPTIONS_VALUE = "value";
    public static final String OPTIONS_DOMAIN = "domain";
    public static final String OPTIONS_ORIGIN = "origin";
    public static final String OPTIONS_PATH = "path";
    public static final String OPTIONS_EXPIRATION = "expiration";

    String name;
    String value;
    String domain;
    String origin;
    String path;
    String expiration;

    CookieManager manager;
    ArrayList<String> nameList;
    ArrayList<String> valueList;
    ArrayList<String> domainList;
    ArrayList<String> originList;
    ArrayList<String> pathList;
    ArrayList<String> versionList;
    ArrayList<String> expirationList;

    public CookieManagerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        manager = CookieManager.getInstance();
        nameList = new ArrayList<String>();
        valueList = new ArrayList<String>();
        domainList = new ArrayList<String>();
        originList = new ArrayList<String>();
        pathList = new ArrayList<String>();
        versionList = new ArrayList<String>();
        expirationList = new ArrayList<String>();
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void set(ReadableMap options, Callback callback) {
        if (options.hasKey(OPTIONS_NAME)) {
            name = options.getString(OPTIONS_NAME);
            nameList.add(name);
        }
        if (options.hasKey(OPTIONS_VALUE)) {
            value = options.getString(OPTIONS_VALUE);
            valueList.add(value);
        }
        if (options.hasKey(OPTIONS_DOMAIN)) {
            domain = options.getString(OPTIONS_DOMAIN);
            domainList.add(domain);
        }
        if (options.hasKey(OPTIONS_ORIGIN)) {
            origin = options.getString(OPTIONS_ORIGIN);
            originList.add(origin);
        }
        if (options.hasKey(OPTIONS_PATH)) {
            path = options.getString(OPTIONS_PATH);
            pathList.add(path);
        }
        if (options.hasKey(OPTIONS_EXPIRATION)) {
            expiration = options.getString(OPTIONS_EXPIRATION);
            expirationList.add(expiration);
        }
        manager.setCookie(origin,
                name + "=" + value
                        + ";" + OPTIONS_PATH + "=" + path
                        + ";" + OPTIONS_EXPIRATION + "=" + expiration
                        + ";" + OPTIONS_DOMAIN + "=" + domain);
        if (callback != null) {
            callback.invoke("success");
        }
    }

    @ReactMethod
    public void getAll(Callback callback) {
        if (originList != null) {
            WritableMap map = Arguments.createMap();
            for (int i = 0; i < originList.size(); i++) {
                WritableMap obj = Arguments.createMap();
                obj.putString(OPTIONS_DOMAIN, domainList.get(i));
                obj.putString(OPTIONS_PATH, pathList.get(i));
                obj.putString(OPTIONS_VALUE, valueList.get(i));
                obj.putString(OPTIONS_NAME, nameList.get(i));
                map.putMap(nameList.get(i), obj);
            }
            if (callback != null) {
                callback.invoke(map,"success");
            }
        }else{
            if (callback != null) {
                callback.invoke(null,"success");
            }
        }
    }

    @ReactMethod
    public void clearAll(final Callback callback) {
        if (manager != null) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                manager.removeAllCookies(new ValueCallback<Boolean>() {
                    @Override
                    public void onReceiveValue(Boolean value) {
                        if (callback != null) {
                            if(value){
                                callback.invoke("succes");    
                            }else{
                                callback.invoke("failed");
                            }
                        }
                    }
                });
            } else {
                manager.removeAllCookie();
                if (callback != null) {
                    callback.invoke("success");
                }
            }
        }
    }
}