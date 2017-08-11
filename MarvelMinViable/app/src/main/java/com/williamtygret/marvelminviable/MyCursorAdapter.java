package com.williamtygret.marvelminviable;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by williamtygret on 8/10/17.
 */
public class MyCursorAdapter extends CursorAdapter {

    Context mContext;
    public MyCursorAdapter(Context context, Cursor cursor){
        super(context,cursor,0);
        mContext = context;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView)view.findViewById(R.id.textViewName);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        ImageView iconView = (ImageView)view.findViewById(R.id.avenger_icon);
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"Comic Book.ttf");

        textView.setTypeface(tf);


        String textString = cursor.getString(cursor.getColumnIndex(HeroDataBase.NAME));
        Log.d("textString","thest string is: "+textString);
        textView.setText(textString);
        String iconstring;
        iconstring = cursor.getString(cursor.getColumnIndex(HeroDataBase.THUMBNAIL));
        String iconString2 = iconstring+"/standard_small.jpg";
        Log.d("iconString", "the icon string is: " + iconString2);

        if(textString.equals("Iron Man") || textString.equals("The Hulk")  || textString.equals("Wasp")
                || textString.equals("Captain America")  || textString.equals("Quicksilver")
                || textString.equals("Thor")
                || textString.equals("Black Widow")
                || textString.equals("Hawkeye")
                || textString.equals("War Machine")
                || textString.equals("Scarlet Witch")){
            iconView.setVisibility(View.VISIBLE);
        }

        Glide.with(mContext).load(iconString2)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);


    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.hero_list_item,parent,false);
    }


}
