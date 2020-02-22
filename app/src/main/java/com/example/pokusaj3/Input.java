package com.example.pokusaj3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokusaj3.Database.Database;
import com.example.pokusaj3.Model.Inputmodel;
import com.example.pokusaj3.ViewHolder.InputAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Input extends AppCompatActivity {

    private static final String TAG = "Input";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference request;

    TextView txtTotalCalories;
    Button btnPovratak;
    TextView date_picker;
    ImageView Calendar_img;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    List<Inputmodel> input = new ArrayList<>();
    InputAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_input);

        //Firebase
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Requests");

        //Init
        recyclerView = (RecyclerView) findViewById(R.id.listCart);
        date_picker = (TextView) findViewById(R.id.datePicker);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalCalories = (TextView) findViewById(R.id.total);
        btnPovratak = (Button) findViewById(R.id.btnReturnHome);
        Calendar_img = (ImageView) findViewById(R.id.calendar_img);

        Date date = new Date();
        SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy");
        String datum = formatt.format(date);
        date_picker.setText(datum);

        btnPovratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Home = new Intent(Input.this,Home.class);
                startActivity(Home);
            }
        });
        Calendar_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Input.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                    String Dodatak = "" + month;
                if(month < 10 && month > 0) {
                    Dodatak = "0" + month;

                }
                    String date = day + "/" + Dodatak + "/" + year;
                    date_picker.setText(date);
                    loadListFood();

            }
        };

        loadListFood();
    }

    public void loadListFood() {
        input = new Database(this).getInputs(date_picker.getText().toString());
    adapter = new InputAdapter(input,this);
    recyclerView.setAdapter(adapter);

    //Calculate total calories
        int total = 0;
        for(Inputmodel input:input)
            total+=(Integer.parseInt(input.getCalories()))*(Integer.parseInt(input.getQuantity()));
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getNumberInstance(locale);

        txtTotalCalories.setText(fmt.format(total));

    }


    protected void onRefresh() {


        //Firebase
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Requests");

        //Init
        recyclerView = (RecyclerView) findViewById(R.id.listCart);
        date_picker = (TextView) findViewById(R.id.datePicker);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalCalories = (TextView) findViewById(R.id.total);
        btnPovratak = (Button) findViewById(R.id.btnReturnHome);
        Calendar_img = (ImageView) findViewById(R.id.calendar_img);


        String Datum = date_picker.getText().toString();



        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                date_picker.setText(date);
                loadListFood();
            }
        };

        loadListFood();
    }

}
