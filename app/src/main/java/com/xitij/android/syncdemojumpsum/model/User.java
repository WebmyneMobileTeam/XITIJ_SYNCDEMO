package com.xitij.android.syncdemojumpsum.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Android on 12-06-2015.
 */
public class User {

    @SerializedName("Userid")
    public String userID;
    @SerializedName("Name")
    public String first_name;
    @SerializedName("LastName")
    public String last_name;
    @SerializedName("Mobile")
    public String mobile;
    @SerializedName("Pincode")
    public String pincode;
    @SerializedName("Area")
    public String area;
    @SerializedName("City")
    public String city;
    @SerializedName("Address")
    public String address;
    @SerializedName("DateOfBirth")
    public String birthDate;
    @SerializedName("Height")
    public String height;
    @SerializedName("Weight")
    public String weight;
    @SerializedName("Email")
    public String email;
    @SerializedName("Password")
    public String password;
    @SerializedName("Picture")
    public String photo;
    @SerializedName("Gender")
    public String gender;
    @SerializedName("Description")
    public String description;
    @SerializedName("State")
    public String state;
    @SerializedName("Country")
    public String country;
    @SerializedName("Username")
    public String username;

    @SerializedName("Sportid")
    public int sportId;

    @SerializedName("Playerid")
    public int playerId;
    @SerializedName("Ismobiledisplay")
    public String isMobileDisplay;
    @SerializedName("Isemaildisplay")
    public String isEmailDisplay;

    public String associate_sports_string;
    public ArrayList<String> associated_Sports;

    public boolean isProfileComplete(){
        boolean isComplete = false;
        ArrayList<String> values = new ArrayList<>();
        values.add(first_name);
        values.add(last_name);
        values.add(mobile);
        values.add(pincode);
        values.add(birthDate);
        values.add(email);
       /* values.add(area);
        values.add(city);
        values.add(address);
        values.add(birthDate);
        values.add(height);
        values.add(weight);
        values.add(email);
        values.add(associate_sports_string);*/
        for(String str : values){
            if(str == null || str.isEmpty()){
                isComplete = false;
               return  isComplete;
            }else{
                isComplete = true;
            }
        }
        return isComplete;
    }

    public float getCompletePercentage(){

        float percentage = 0f;
        int total = 12;
        int count = 0;

        ArrayList<String> values = new ArrayList<>();
        values.add(first_name);
        values.add(last_name);
        values.add(mobile);
        values.add(pincode);
        values.add(area);
        values.add(city);
        values.add(address);
        values.add(birthDate);
        values.add(height);
        values.add(weight);
        values.add(email);
        values.add(associate_sports_string);

        for(String str : values){
            if(str == null || str.isEmpty()){

            }else {
                count = count+1;
            }
        }

        percentage = (count*100)/total;

       return percentage;
    }




}
