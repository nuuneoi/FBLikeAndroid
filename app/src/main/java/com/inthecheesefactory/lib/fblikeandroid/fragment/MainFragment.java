package com.inthecheesefactory.lib.fblikeandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.inthecheesefactory.lib.fblike.widget.FBLikeView;
import com.inthecheesefactory.lib.fblikeandroid.R;

/**
 * Created by nuuneoi on 4/26/2015.
 */
public class MainFragment extends Fragment {

    Button btnLogout;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                FBLikeView.loginStatusChanged();
            }
        });
    }

}
