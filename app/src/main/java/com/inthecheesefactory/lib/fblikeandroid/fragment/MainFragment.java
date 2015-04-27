package com.inthecheesefactory.lib.fblikeandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.share.widget.LikeView;
import com.inthecheesefactory.lib.fblike.widget.FBLikeView;
import com.inthecheesefactory.lib.fblikeandroid.R;

/**
 * Created by nuuneoi on 4/26/2015.
 */
public class MainFragment extends Fragment {

    Button btnLogout;

    TextView tvUrl1;
    TextView tvUrl2;
    FBLikeView fbLikeView1;
    FBLikeView fbLikeView2;

    String url1 = "http://inthecheesefactory.com/blog/understand-android-activity-launchmode/en";
    String url2 = "http://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en";

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
        tvUrl1 = (TextView) rootView.findViewById(R.id.tvUrl1);
        tvUrl2 = (TextView) rootView.findViewById(R.id.tvUrl2);
        fbLikeView1 = (FBLikeView) rootView.findViewById(R.id.fbLikeView1);
        fbLikeView2 = (FBLikeView) rootView.findViewById(R.id.fbLikeView2);

        tvUrl1.setText(url1);
        tvUrl2.setText(url2);
        fbLikeView1.getLikeView().setObjectIdAndType(url1, LikeView.ObjectType.OPEN_GRAPH);
        fbLikeView2.getLikeView().setObjectIdAndType(url2, LikeView.ObjectType.OPEN_GRAPH);

        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FBLikeView.logout();
            }
        });
    }

}
