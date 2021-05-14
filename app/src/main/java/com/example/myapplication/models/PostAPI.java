package com.example.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PostAPI implements Parcelable {
    String creatorName ,key, date, title, content, imageUrl;
    boolean like;

    public PostAPI() {
    }

    public PostAPI(String creatorName ,String date, String title, String content, String imageUrl, boolean like) {
        this.creatorName = creatorName;
        this.date = date;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.like = like;
    }

    protected PostAPI(Parcel in) {
        creatorName = in.readString();
        key = in.readString();
        date = in.readString();
        title = in.readString();
        content = in.readString();
        imageUrl = in.readString();
        like = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(creatorName);
        dest.writeString(key);
        dest.writeString(date);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(imageUrl);
        dest.writeByte((byte) (like ? 1 : 0));
    }
//get information from the user
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PostAPI> CREATOR = new Creator<PostAPI>() {
        @Override
        public PostAPI createFromParcel(Parcel in) {
            return new PostAPI(in);
        }

        @Override
        public PostAPI[] newArray(int size) {
            return new PostAPI[size];
        }
    };

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
