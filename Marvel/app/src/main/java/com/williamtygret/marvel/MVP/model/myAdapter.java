package com.williamtygret.marvel.MVP.model;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.williamtygret.marvel.R;
import com.williamtygret.marvel.modules.home.adapter.HeroAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by williamtygret on 8/7/17.
 */
public abstract class myAdapter extends CursorAdapter {

    private LayoutInflater mLayoutInflater;
    private List<Hero> mHeroList = new ArrayList<>();


    Context mContext;
    public myAdapter(Context context, Cursor c) {
        super(context,c,0);
        mContext=context;
    }

    //customer adapter to give my list view a custom font
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameText = (TextView)view.findViewById(R.id.hero_name);

        String s = cursor.getString(cursor.getColumnIndex(Storage.NAME));
        nameText.setText(s);

        //Any other modifications you want
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_layout,parent,false);
    }


}
