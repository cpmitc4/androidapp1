package com.kentuckyspacetracker;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by New admin on 2/10/14.
 */
public class MyFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String HEADING = "HEADING";

    public static final MyFragment newInstance(String message, String resource)
    {
        MyFragment f = new MyFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        Bundle bd2 = new Bundle(2);
        bd2.putString(EXTRA_MESSAGE, message);
        bd2.putString(HEADING, resource);
        f.setArguments(bd2);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        String heading = getArguments().getString(HEADING);
        View v = inflater.inflate(R.layout.myfragment_layout, container, false);
        TextView messageTextView = (TextView)v.findViewById(R.id.textView);
        messageTextView.setText(message);

        TextView headingTextView = (TextView)v.findViewById(R.id.textView2);
        headingTextView.setText(heading);

        return v;
    }
}