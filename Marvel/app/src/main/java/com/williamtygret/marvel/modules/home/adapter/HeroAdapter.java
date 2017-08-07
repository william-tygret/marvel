package com.williamtygret.marvel.modules.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.williamtygret.marvel.MVP.model.Hero;
import com.williamtygret.marvel.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by williamtygret on 8/5/17.
 */
public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.Holder> {

    private LayoutInflater mLayoutInflater;
    private List<Hero> mHeroList = new ArrayList<>();

    public HeroAdapter(LayoutInflater inflater){
        mLayoutInflater = inflater;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mHeroList.get(position));
    }

    @Override
    public int getItemCount() {
        return mHeroList.size();
    }

    public void addHeroes(List<Hero> heros){
        mHeroList.addAll(heros);
        notifyDataSetChanged();
    }

    public void clearHeroes() {
        mHeroList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder{

        @Bind(R.id.hero_icon) protected ImageView mHeroIcon;
        @Bind(R.id.hero_name) protected TextView mHeroName;
        @Bind(R.id.textview_hero_description) protected TextView mHeroDescription;

        private Context mContext;


        public Holder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bind(Hero hero) {

            mHeroName.setText(hero.getName());
            Log.d("getname","the getname is :"+hero.getName());
            mHeroDescription.setText(hero.getDescription());

            Log.d("thumbnail", "getThumbnail is: " + hero.getThumbnail());
            String imgurl = hero.getThumbnail()+"/standard_small.jpg";
            Log.d("imgurl","imgurl is: "+imgurl);

            Glide.with(mContext).load(imgurl)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mHeroIcon);

        }
    }

}
