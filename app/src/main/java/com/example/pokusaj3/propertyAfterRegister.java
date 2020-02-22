package com.example.pokusaj3;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pokusaj3.Database.Database;
import com.example.pokusaj3.Model.Inputmodel;
import com.example.pokusaj3.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.pokusaj3.Common.Common;

public class propertyAfterRegister extends AppCompatActivity {

    Spinner spolSelection;
    EditText ageNumber;
    EditText heightNumber;
    EditText weightNumber;
   // Spinner activitySelection;
    Button saveChanges;
    Common user;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_property_after_register);
        user = new Common();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spol, android.R.layout.simple_spinner_item);

        spolSelection = (Spinner) findViewById(R.id.SpinnerSpol);
        ageNumber = (EditText) findViewById(R.id.ageText);
        heightNumber = (EditText) findViewById(R.id.heightText);
        weightNumber = (EditText) findViewById(R.id.weightText);
       // activitySelection = (Spinner) findViewById(R.id.SpinnerAktivnost);
        saveChanges = (Button) findViewById(R.id.btnUpdateProperty) ;
        delete = (Button) findViewById(R.id.buttonDel);

        //init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        if(user.currentUser.getFirstLogin().equals("1")) {
            ageNumber.setText(user.currentUser.getAge());
            heightNumber.setText(user.currentUser.getHeight());
            weightNumber.setText(user.currentUser.getWeight());
        }

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*database.getReference("User/" + user.currentUser.getPhone()).setValue( {
                        activity: activitySelection.getSelectedItem().toString();

                        }
                );*/
                User userNew = new User(user.currentUser.getName(),user.currentUser.getPassword(),spolSelection.getSelectedItem().toString(),  ageNumber.getText().toString(),heightNumber.getText().toString(),weightNumber.getText().toString(),"","1");
                table_user.child(user.currentUser.getPhone()).setValue(userNew);
                Toast.makeText(propertyAfterRegister.this,"Promjene spremljene",Toast.LENGTH_SHORT);
                Intent homeIntent = new Intent(propertyAfterRegister.this, Index.class);
                startActivity(homeIntent);
                finish();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Database.cleanInput();
                new Database(getBaseContext()).cleanInput(new Inputmodel());
                Log.d("CLEAN","obrisani inputi");
            }
        });



        /*User user = new User(ageNumber.getText().toString(),heightNumber.getText().toString(),weightNumber.getText().toString());
        table_user.child(edtPhone.getText().toString()).setValue(user);
        Toast.makeText(SignUp.this , "Signup successfull",Toast.LENGTH_SHORT).show();
        finish();*/
    }
}
