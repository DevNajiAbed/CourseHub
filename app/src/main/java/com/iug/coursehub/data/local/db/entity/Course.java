package com.iug.coursehub.data.local.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "course"
)
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] imageByteArray;
    private float price;
    private int noOfHours;
    private String lecturerName;
    private String category;

    public Course(int id, String category, byte[] imageByteArray, String lecturerName, String name, int noOfHours, float price) {
        this.id = id;
        this.category = category;
        this.imageByteArray = imageByteArray;
        this.lecturerName = lecturerName;
        this.name = name;
        this.noOfHours = noOfHours;
        this.price = price;
    }

    public Course(String category, byte[] imageByteArray, String lecturerName, String name, int noOfHours, float price) {
        this.category = category;
        this.imageByteArray = imageByteArray;
        this.lecturerName = lecturerName;
        this.name = name;
        this.noOfHours = noOfHours;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfHours() {
        return noOfHours;
    }

    public void setNoOfHours(int noOfHours) {
        this.noOfHours = noOfHours;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
