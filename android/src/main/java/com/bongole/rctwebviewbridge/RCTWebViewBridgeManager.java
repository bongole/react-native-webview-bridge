package com.bongole.rctwebviewbridge;

import android.app.Activity;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by bongole on 12/16/15.
 */
public class RCTWebViewBridgeManager extends SimpleViewManager<RCTWebViewBridge> {
    private static final String REACT_CLASS = "RCTWebViewBridge";
    private Activity mActivity;

    public RCTWebViewBridgeManager(Activity activity){
        mActivity = activity;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected RCTWebViewBridge createViewInstance(ThemedReactContext reactContext) {
        return new RCTWebViewBridge(reactContext, mActivity);
    }

    @ReactProp(name = "contentInset")
    public void setContentInset(RCTWebViewBridge view, ReadableMap map){
        int top = map.getInt("top");
        int left = map.getInt("left");
        int bottom = map.getInt("bottom");
        int right = map.getInt("right");
        view.setPadding(left, top, right, bottom);
    }

    @ReactProp(name = "html")
    public void setHtml(RCTWebViewBridge view, String html) {
        view.loadData(html, "text/html", "UTF-8");
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(RCTWebViewBridge view, String injectedJavaScript){
        view.setInjectedJavaScript(injectedJavaScript);
    }

    @ReactProp(name = "url")
    public void setUrl(RCTWebViewBridge view, String url) {
        view.loadUrl(url);
    }

    @Nullable
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                RCTMessageEvent.EVENT_NAME, MapBuilder.of("registrationName", "onBridgeMessage")
        );
    }
}
