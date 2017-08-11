package com.williamtygret.marvelminviable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by williamtygret on 8/10/17.
 */
public class HeroActivity extends AppCompatActivity {

    private int mId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);


        int id = getIntent().getIntExtra("id",-1);
        mId = id;
        if(id >= 0){
            HeroDataBase heroDataBase = HeroDataBase.getInstance(HeroActivity.this);
            Typeface tf = Typeface.createFromAsset(getBaseContext().getAssets(), "Comic Book.ttf");
            String name = heroDataBase.getHeroName(id);
            TextView textView = (TextView)findViewById(R.id.hero_activity_name);
            textView.setText(name);
            textView.setTypeface(tf);
        }
        if(id >=0){
            HeroDataBase heroDataBase = HeroDataBase.getInstance(HeroActivity.this);
            String description = heroDataBase.getHeroDescription(id);
            Typeface tf = Typeface.createFromAsset(getBaseContext().getAssets(), "Comic Book.ttf");
            TextView textView = (TextView)findViewById(R.id.hero_activity_desc);
            textView.setText(description);
            textView.setTypeface(tf);
        }
        if(id >=0){
            HeroDataBase heroDataBase = HeroDataBase.getInstance(HeroActivity.this);
            String imgurl = heroDataBase.getHeroImg(id);
            String imgurl2 = imgurl+"/portrait_incredible.jpg";
            ImageView imageView = (ImageView)findViewById(R.id.hero_activity_img);

            Glide.with(getBaseContext()).load(imgurl2)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);


        }


    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent();
        setResult(Activity.RESULT_OK,in);
        finish();
    }

}
