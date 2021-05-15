package com.example.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    int itemId,categoryId,itemImage,itemPrice;
    String itemName,itemDescription;

    public Item(int itemId, int categoryId, int itemImage, int itemPrice, String itemName, String itemDescription) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.itemImage = itemImage;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    protected Item(Parcel in) {
        itemId = in.readInt();
        categoryId = in.readInt();
        itemImage = in.readInt();
        itemPrice = in.readInt();
        itemName = in.readString();
        itemDescription = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemId);
        dest.writeInt(categoryId);
        dest.writeInt(itemImage);
        dest.writeInt(itemPrice);
        dest.writeString(itemName);
        dest.writeString(itemDescription);
    }
}
