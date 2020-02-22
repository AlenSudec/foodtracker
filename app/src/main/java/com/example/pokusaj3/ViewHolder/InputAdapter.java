package com.example.pokusaj3.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
//import com.example.pokusaj3.Inputmodel;
import com.example.pokusaj3.Database.Database;
import com.example.pokusaj3.Input;

import com.example.pokusaj3.Interface.ItemClickListener;
import com.example.pokusaj3.R;
import com.example.pokusaj3.Model.Inputmodel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;


import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class InputViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txt_cart_name, txt_calories,txt_Date;
    public ImageView img_cart_count;


    private ItemClickListener itemClickListener;

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }

    public InputViewHolder(View itemView){
        super(itemView);
        txt_cart_name = (TextView) itemView.findViewById(R.id.cart_item_name);
        txt_calories = (TextView) itemView.findViewById(R.id.cart_item_Calories);
        img_cart_count = (ImageView) itemView.findViewById(R.id.cart_item_count);
        txt_Date = (TextView) itemView.findViewById(R.id.txt_date);
    }

    @Override
    public void onClick(View view) {

    }
}

public class InputAdapter extends RecyclerView.Adapter<InputViewHolder>
{

    private List<Inputmodel> listData = new ArrayList<>();
    private Context context;

    public static String test;

    public  InputAdapter(List<Inputmodel> listData, Context context) {
        this.listData = listData;
        this.context = context;


    }

    @Override
    public InputViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart,parent, false);
        return new InputViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(InputViewHolder holder, int position){


        //if(test == text_date)
        //if(test == datumcic){}
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getNumberInstance(locale);     //getCurrencyInstance(locale);
        int calories = (Integer.parseInt(listData.get(position).getCalories()))*(Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_calories.setText(fmt.format(calories));
        /*Date date = new Date();
        SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy");
        String datum = formatt.format(date);
        holder.txt_Date.setText(datum);*/
        test = listData.get(position).getDatum();
        holder.txt_Date.setText(test);

        holder.txt_cart_name.setText(listData.get(position).getProductName());
    }

    public int getItemCount() {
        return listData.size();
    }

}
