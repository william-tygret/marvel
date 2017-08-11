package com.williamtygret.marvelminviable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by williamtygret on 8/9/17.
 */
public class HeroAdapter extends ArrayAdapter<Hero> {

    private final Context context;
    private ArrayList<Hero> mHeroes ;
    Context mContext;

    public HeroAdapter(Context context, ArrayList<Hero>heroes){
        super(context, -1);
        mHeroes = heroes;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Hero currentHero = mHeroes.get(position);
        Log.d("currentforecast", "current forecast is: " + currentHero.getImgURL());



        mContext = getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View heroLayoutView = inflater.inflate(R.layout.hero_list_item, parent, false);

        TextView heroNameTextView = (TextView)heroLayoutView.findViewById(R.id.textViewName);
        ImageView heroImageView = (ImageView)heroLayoutView.findViewById(R.id.imageView);

        heroNameTextView.setText(" "+ currentHero.getName());
        String iconstring = String.valueOf(currentHero.getImgURL());
        Log.d("iconString","the icon string is: "+iconstring);

        Glide.with(getContext()).load(iconstring)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(heroImageView);



        return heroLayoutView;
    }

    public int getImageId(String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    @Override
    public int getCount() {
        return (mHeroes ==null) ? 0: mHeroes.size();
    }

    @Override
    public Hero getItem(int position){
        if(this.mHeroes != null){
            return this.mHeroes.get(position);
        }else{
            return null;
        }
    }

}
