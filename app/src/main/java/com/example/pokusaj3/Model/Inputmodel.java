package com.example.pokusaj3.Model;

import android.util.Log;

public class Inputmodel {

    private String ProductId;
    private String ProductName;
    private String Quantity;
    private String Calories;
    private String Datum ;

    public Inputmodel() {
    }

    public Inputmodel(String productId, String productName, String quantity, String calories, String datum) {
        ProductId = productId;
        ProductName = productName;
        Quantity = quantity;
        Calories = calories;
        Datum = datum;
    }

    public Inputmodel(String productId, String productName, String quantity, String calories) {
        ProductId = productId;
        ProductName = productName;
        Quantity = quantity;
        Calories = calories;

    }

    public String getProductId() {
        return ProductId;
    }


    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public String getDatum() {
        Log.d("DATUM prije returna",Datum);
        return Datum;
    }

    public void setDatum(String datum) {
        Datum = datum;
    }
}
