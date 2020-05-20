package com.navneet.ns4u;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Database {

    static String mydatabase="Mydatabase";
    static String name="name";
    private static SharedPreferences.Editor editor;
    static String number="number";

    SharedPreferences sharedpreferences;
    public Database(Context context){
        sharedpreferences = context.getSharedPreferences(mydatabase, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public String getName() {
        return sharedpreferences.getString(name, "");
    }

    public void setName(String name) {
        editor.putString(this.name  , name).apply();
    }

    public  String getNumber() {
        return sharedpreferences.getString(number, "");
    }

    public  void setNumber(String number) {
        editor.putString(this.getNumber()  , name).apply();
    }
}
