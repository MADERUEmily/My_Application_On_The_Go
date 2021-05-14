package com.example.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.ServerValue;

public class Comment implements Parcelable {
    long timeStamp;
    String key, content, userId, userName;


    public Comment() {
    }

    public Comment(String content, String userId, String userName) {
        this.content = content;
        this.timeStamp = System.currentTimeMillis();
        this.userId = userId;
        this.userName = userName;
    }

    protected Comment(Parcel in) {
        timeStamp = in.readLong();
        key = in.readString();
        content = in.readString();
        userId = in.readString();
        userName = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timeStamp);
        dest.writeString(key);
        dest.writeString(content);
        dest.writeString(userId);
        dest.writeString(userName);
    }
}
