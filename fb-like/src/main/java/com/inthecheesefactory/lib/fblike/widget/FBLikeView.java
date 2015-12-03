package com.inthecheesefactory.lib.fblike.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.widget.LikeView;
import com.inthecheesefactory.lib.fblike.R;
import com.inthecheesefactory.lib.fblike.bus.BusEventCallbackManagerActivityResult;
import com.inthecheesefactory.lib.fblike.bus.BusEventLoginStatusUpdated;
import com.inthecheesefactory.lib.fblike.bus.FBLikeBus;
import com.squareup.otto.Subscribe;

import java.util.Arrays;

/**
 * Created by nuuneoi on 4/25/2015.
 */
public class FBLikeView extends FrameLayout {

    LinearLayout btnLoginToLike;
    LikeView likeView;
    TextView tvLogin;

    public FBLikeView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public FBLikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public FBLikeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FBLikeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.login_to_like, this);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        int[] set = {
            android.R.attr.text        // idx 0
        };
        TypedArray a = getContext().obtainStyledAttributes(attrs, set, defStyleAttr, defStyleRes);
        try {
            if (a.hasValue(0)) {
                String text = a.getString(0);
                setText(text);
            }
        } finally {
            a.recycle();
        }
    }

    private void initInstances() {
        btnLoginToLike = (LinearLayout) findViewById(R.id.btnLoginToLike);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        likeView = (LikeView) findViewById(R.id.internalLikeView);
        likeView.setLikeViewStyle(LikeView.Style.STANDARD);
        likeView.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE);

        btnLoginToLike.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() != null && getContext() instanceof Activity)
                    LoginManager.getInstance().logInWithReadPermissions((Activity) getContext(), Arrays.asList("public_profile"));
            }
        });

        initializeCallbackManager();

        refreshButtonsState();
    }

    public LikeView getLikeView() {
        return likeView;
    }

    public void setText(@Nullable CharSequence text) {
        tvLogin.setText(text);
    }

    public void setText(@Nullable CharSequence text, @Nullable TextView.BufferType type) {
        tvLogin.setText(text, type);
    }

    public void setText(char[] text, int start, int len) {
        tvLogin.setText(text, start, len);
    }

    public void setText(@StringRes int resId) {
        tvLogin.setText(resId);
    }

    public void setText(@StringRes int resId, @Nullable TextView.BufferType type) {
        tvLogin.setText(resId, type);
    }

    public CharSequence getText() {
        return tvLogin.getText();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        refreshButtonsState();
        FBLikeBus.getInstance().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FBLikeBus.getInstance().unregister(this);
    }

    @Subscribe
    public void busReceived(BusEventLoginStatusUpdated event) {
        refreshButtonsState();
    }

    @Subscribe
    public void busReceived(BusEventCallbackManagerActivityResult event) {
        _onActivityResult(event.getRequestCode(), event.getResultCode(), event.getData());
    }

    private void refreshButtonsState() {
        if (!isLoggedIn()) {
            btnLoginToLike.setVisibility(View.VISIBLE);
            likeView.setVisibility(View.GONE);
        } else {
            btnLoginToLike.setVisibility(View.GONE);
            likeView.setVisibility(View.VISIBLE);
        }
    }

    private static CallbackManager callbackManager;
    private static void initializeCallbackManager() {
        if (callbackManager == null) {
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    FBLikeView.loginStatusChanged();
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException e) {

                }
            });
        }
    }

    public static void loginStatusChanged() {
        FBLikeBus.getInstance().post(new BusEventLoginStatusUpdated());
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        FBLikeBus.getInstance().post(new BusEventCallbackManagerActivityResult(requestCode, resultCode, data));
    }

    public static void _onActivityResult(int requestCode, int resultCode, Intent data) {
        if (callbackManager != null)
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public static boolean isLoggedIn() {
        return AccessToken.getCurrentAccessToken() != null;
    }

    public static void logout() {
        LoginManager.getInstance().logOut();
        FBLikeView.loginStatusChanged();
    }

}
