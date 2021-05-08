package com.example.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PostAPI implements Parcelable {
    String creatorName ,key, date, title, content, imageUrl;

    public PostAPI() {
    }

    public PostAPI(String creatorName ,String date, String title, String content, String imageUrl) {
        this.creatorName = creatorName;
        this.date = date;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    protected PostAPI(Parcel in) {
        creatorName = in.readString();
        key = in.readString();
        date = in.readString();
        title = in.readString();
        content = in.readString();
        imageUrl = in.readString();
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(creatorName);
        dest.writeString(key);
        dest.writeString(date);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(imageUrl);
    }

    @Override
    public String toString() {
        return "PostAPI{" +
                "creatorName='" + creatorName + '\'' +
                ", key='" + key + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
