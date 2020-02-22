package com.example.pokusaj3.ViewHolder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokusaj3.Interface.ItemClickListener;
import com.example.pokusaj3.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView food_Name;
    public ImageView food_Image;

    private ItemClickListener itemClickListener;




    public FoodViewHolder(View itemView) {
        super(itemView);

        food_Name = (TextView) itemView.findViewById(R.id.food_name);
        food_Image = (ImageView) itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view ,getAdapterPosition(),false);
    }
}
