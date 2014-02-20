package com.kentuckyspacetracker;

import android.content.Context;
import android.graphics.Color;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import java.util.ArrayList;

/**
 * Created by New admin on 2/6/14.
 */
class twitteradaptor extends BaseAdapter {

    private Context context;
    private ArrayList<Tweet> twits;

    public twitteradaptor (Context context, ArrayList<Tweet> twits)
    {
        this.context = context;
        this.twits = twits;

    }
    @Override
    public int getCount(){

        return twits.size();
    }
    @Override
    public Object getItem(int position){
        return twits.get(position);

    }
    @Override
    public long getItemId(int position){

        return 0;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        TwoLineListItem twoLineListItem;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate( android.R.layout.simple_list_item_2, null);

        }
        else
        {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();
        text1.setTextColor(Color.GRAY);
        text2.setTextColor(Color.WHITE);
        text1.setTextSize(20);
        text2.setTextSize(15);
        //twoLineListItem.setBackgroundResource(R.drawable.plainbg);
        //twoLineListItem.setBackgroundColor(Color.null);
        text2.setAutoLinkMask(15);


        String[] parts = twits.get(position).getDateCreated().split(" ");
        String DateFormed="";
        DateFormed = DateFormed.format("%s %s %s", parts[1], parts[2], parts[5]);

        String TextFormed = "";
        TextFormed = TextFormed.format("%s", twits.get(position).getText() );

        text1.setText(DateFormed);
        text2.setText(TextFormed);

        return twoLineListItem;

    }



}
