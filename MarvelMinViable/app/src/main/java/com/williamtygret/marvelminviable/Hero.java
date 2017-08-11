package com.williamtygret.marvelminviable;

import android.provider.BaseColumns;

/**
 * Created by williamtygret on 8/9/17.
 */
public class Hero {

    public static abstract class NewHero implements BaseColumns{
        public static final String NAME = "title";
        public static final String DESCRIPTION = "detailDescription";
        public static final String THUMBNAIL = "imageURL";
        public static final String TABLE_NAME = "heroes";
    }

    String mName;
    String mDesc;
    String mImgURL;



    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getImgURL() {
        return mImgURL;
    }

    public void setImgURL(String imgURL) {
        mImgURL = imgURL;
    }

    public Hero(String name, String desc, String imgURL){
        this.mName = name;
        this.mDesc = desc;
        this.mImgURL = imgURL;

    }

}
