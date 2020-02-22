package com.example.pokusaj3.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.pokusaj3.Model.Inputmodel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.example.pokusaj3.ViewHolder.InputAdapter;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static  String DB_NAME ="FoodTrackerDB.db";
    private static int DB_VER=1;

    public Database(Context context){
        super(context,DB_NAME,null,DB_VER);
    }

    public List<Inputmodel> getInputs(String date){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

       // String queryGet = String.format("SELECT * FROM InputDetail WHERE Datum = '%s';", date);


        String[] sqlSelect = {"ProductName","ProductId","Quantity", "Calories","Datum"};
        String sqlTable="InputDetail";
        qb.setTables(sqlTable);

        Cursor c = qb.query(db,sqlSelect,"Datum = '" + date +"'",null,null,null,null);

        final List<Inputmodel> result = new ArrayList<>();

        if(c.moveToFirst())
        {
            do
            {
                Log.d("DATUMBAZA",c.getString(c.getColumnIndex("Datum")));


                //if(InputAdapter.test == c.getString(c.getColumnIndex("Datum"))){
                result.add(new Inputmodel(c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                         c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Calories")),
                                c.getString(c.getColumnIndex("Datum")))
                        );
                Log.d("DATUMposljecitanja",c.getString(c.getColumnIndex("ProductId")) + c.getString(c.getColumnIndex("ProductName"))+  c.getString(c.getColumnIndex("Quantity")) + c.getString(c.getColumnIndex("Calories")) + c.getString(c.getColumnIndex("Datum")));
                //}
            }while(c.moveToNext());
        }
        return result;
    }

    public void addToInput(Inputmodel input){
        SQLiteDatabase db = getReadableDatabase(); Log.d("DATUMprilikomunosa",input.getDatum());
        String query = String.format("INSERT INTO InputDetail(ProductId,ProductName,Quantity,Calories,Datum) VALUES('%s','%s','%s','%s','%s');",
                input.getProductId(),
                input.getProductName(),
                input.getQuantity(),
                input.getCalories(),
                input.getDatum());
        db.execSQL(query);
    }

    public void cleanInput(Inputmodel input){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM InputDetail");
        db.execSQL(query);
    }
}
