package com.kentuckyspacetracker;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by New admin on 2/10/14.
 */
public class MyFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String HEADING = "HEADING";
    public static final String PICID = "picid";

    public static final MyFragment newInstance(String message, String resource, int picid)
    {
        MyFragment f = new MyFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        Bundle bd3 = new Bundle(3);
        bd3.putString(EXTRA_MESSAGE, message);
        bd3.putString(HEADING, resource);
        bd3.putInt(PICID, picid);
        f.setArguments(bd3);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String message = getArguments().getString(EXTRA_MESSAGE);
        String heading = getArguments().getString(HEADING);
        int picid   = getArguments().getInt(PICID);

        View v = inflater.inflate(R.layout.myfragment_layout, container, false);
        TextView messageTextView = (TextView)v.findViewById(R.id.textView);
        messageTextView.setAutoLinkMask(15);
        messageTextView.setText(message);

        TextView headingTextView = (TextView)v.findViewById(R.id.textView2);
        headingTextView.setText(heading);

        ImageView fragmentImage = (ImageView)v.findViewById(R.id.fragmentImage);
        switch (picid)
        {
            case 1:
                fragmentImage.setImageResource(R.drawable.kstransparenthdlogo);
                break;
            case 2:
                fragmentImage.setImageResource(R.drawable.kysat2pic);
                break;
            case 3:
                fragmentImage.setImageResource(R.drawable.tlogoqubepic);
                break;
            case 4:
                fragmentImage.setImageResource(R.drawable.fiftysatpic);
                break;
            case 5:
                fragmentImage.setImageResource(R.drawable.cxbnpic);
                break;
            case 6:
                fragmentImage.setImageResource(R.drawable.exomedlogo);
                break;

            default:
                fragmentImage.setImageResource(R.drawable.kstransparenthdlogo);
                break;

        }//end of switch


        return v;
    }
}