package com.example.pokusaj3;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pokusaj3.Database.Database;

import com.example.pokusaj3.Common.Common;
import com.example.pokusaj3.Model.Inputmodel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import static com.example.pokusaj3.R.drawable.background1;

public class Index extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView userName;
    ProgressBar progressbar;
    TextView progressText;
    Spinner profileSelect;
    TextView txtFullName;
    int  godina, visina, težina;
    String spol;
    int max_calories;
    int max_calor;
    TextView result;
    String resultText;
    int math;




    List<Inputmodel> input1 = new ArrayList<>();
    int total;
    View main_layout;
    Button addFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_index1);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);

        max_calor = getMaxCalories();

       userName = (TextView) findViewById(R.id.username);

       //Current date
        Date date = new Date();
        SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy");
        String datum = formatt.format(date);

        userName.setText(datum);
        String currentCalories = getCalories();
        Log.d("CALORIESCUR",currentCalories);
        if (currentCalories.indexOf(",") > 0) {
            currentCalories.replace(",","");
        }
        progressbar = (ProgressBar) findViewById(R.id.progressBarReal);
        progressbar.setProgress(Integer.parseInt(currentCalories));
        progressbar.setMax(max_calor);
        progressText = (TextView) findViewById(R.id.progress_text);
        progressText.setText(getCalories() + " / " + max_calor);
        if(Integer.parseInt(currentCalories) >= max_calor){
            progressbar.setBackgroundColor(Color.RED);
        }
        //resultText
        result = (TextView) findViewById(R.id.result1);
        math = max_calor - Integer.parseInt(getCalories());

        result.setText("Na današnji datum imate uneseno " + getCalories() +  "kalorija, da bi postigli cilj od " + max_calor + ", trebate unjeti jos " + math);


        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inputIntent = new Intent(Index.this,Input.class);
                startActivity(inputIntent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        NavigationView navigationView = findViewById(R.id.nav_view1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //Set name for user
        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView) headerView.findViewById(R.id.txtFullName1);
        txtFullName.setText(Common.currentUser.getName());


    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume(){
        super.onResume();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_index1);
        findViewById(R.id.layout_main).bringToFront();
       main_layout = (View) findViewById(R.id.layout_main);
        //main_layout.setBackgroundResource(background1);

       userName = (TextView) findViewById(R.id.username);
        //Current date
        Date date = new Date();
        SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy");
        String datum = formatt.format(date);

        userName.setText(datum);
        String currentCalories = getCalories();
        Log.d("CALORIESCUR",currentCalories);
        if (currentCalories.indexOf(",") > 0) {
            currentCalories.replace(",","");
        }
        max_calor = getMaxCalories();
      progressbar = (ProgressBar) findViewById(R.id.progressBarReal);
        progressbar.setProgress(Integer.parseInt(currentCalories));
        progressbar.setMax(max_calor);

       progressText = (TextView) findViewById(R.id.progress_text);
        progressText.setText(getCalories() + " / " + max_calor);

        if(Integer.parseInt(currentCalories) >= max_calor){
            progressbar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

        //resultText
        result = (TextView) findViewById(R.id.result1);
        if(max_calor>Integer.parseInt(getCalories()))
        {math = max_calor - Integer.parseInt(getCalories());

            result.setText("Na današnji datum imate uneseno " + getCalories() +  " kcal, da bi postigli cilj od " + max_calor + " kcal, trebate unjeti jos " + math + " kcal");}
        else if(max_calor<Integer.parseInt(getCalories())){
            math = Integer.parseInt(getCalories()) - max_calor;

            result.setText("Na današnji datum imate uneseno " + getCalories() +  " kcal, premašili ste cilj od " + max_calor + " kcal, višak unesenih kalorija iznosi " + math + " kcal");
        }

        Log.d("nov", "onresume");


        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);



        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inputIntent = new Intent(Index.this,Input.class);
                startActivity(inputIntent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        NavigationView navigationView = findViewById(R.id.nav_view1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //Set name for user
        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView) headerView.findViewById(R.id.txtFullName1);
        txtFullName.setText(Common.currentUser.getName());



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu1) {
            Log.d("Menu","NAv menu pressed");
            Intent Index = new Intent (Index.this,Home.class);
            startActivity(Index);

        } else if (id == R.id.nav_cart1) {
            Intent popisHrane = new Intent(Index.this,Input.class);
            //popisHrane.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(popisHrane);

        } else if (id == R.id.nav_orders1) {
            Intent postavke = new Intent(Index.this,propertyAfterRegister.class);
            startActivity(postavke);

        } else if (id == R.id.nav_log_out1) {
            Intent signIn = new Intent(Index.this,SignIn.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(signIn);

        } else if (id == R.id.nav_home1) {
            Intent polazna = new Intent(Index.this,Index.class);
            startActivity(polazna);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public String getCalories() {

        Date date = new Date();
        SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy");
        String datum = formatt.format(date);
        total= 0;

        input1 = new Database(this).getInputs(datum);
        for(Inputmodel input:input1)

            total+=(Integer.parseInt(input.getCalories()))*(Integer.parseInt(input.getQuantity()));
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getNumberInstance(locale);
        Log.d("KALORIE",total+"");


        return total +"";
    }

    public int getMaxCalories(){

        //init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        //spol, godine, visina,težina
        spol = ((Common.currentUser.getSpol()));
        visina = (Integer.parseInt(Common.currentUser.getHeight()));
        težina = (Integer.parseInt(Common.currentUser.getWeight()));
        godina = (Integer.parseInt(Common.currentUser.getAge()));

        if(spol.equals("Muško")){
            max_calories = (int) (10*težina + 6.25*visina - 5*godina + 5);
        }
        else if(spol.equals("Žensko"))
        {
            max_calories = (int) (10*težina + 6.25*visina - 5*godina - 161);
        }

        return max_calories;
    }
}
