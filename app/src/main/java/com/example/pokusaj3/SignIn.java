package com.example.pokusaj3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokusaj3.Common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.example.pokusaj3.Model.User;


public class SignIn extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnSignIn;
    Common user1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);
        final int firstTimeLogin=0;
        user1 = new Common();
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        //init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();
                Log.d("Kliksignin","klikno sam");
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if user exists
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()){
                            Log.d("Kliksignin","Provjera");
                            //Get User information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString()); //set phone ____
                            if(user.getPassword().equals(edtPassword.getText().toString()))
                            {
                                if(user.getFirstLogin().equals("0"))
                                {
                                    /*Log.d("Ulazak","Ulazak u property");
                                    Log.d("Ulazak",user1.currentUser.getName()+user1.currentUser.getPassword());
                                    User userNew = new User(user1.currentUser.getName(),user1.currentUser.getPassword(),"",  "","", "","","1");
                                    Log.d("Ulazak","Ulazak u property2");

                                    table_user.child(user1.currentUser.getPhone()).setValue(userNew);
                                    Log.d("Ulazak","Ulazak u property3");*/

                                    Intent homeIntent = new Intent(SignIn.this,propertyAfterRegister.class);
                                    Common.currentUser = user;
                                    startActivity(homeIntent);
                                    finish();
                                }
                                else {
                                    Intent homeIntent = new Intent(SignIn.this, Index.class);
                                    Common.currentUser = user;
                                    startActivity(homeIntent);
                                    finish();
                                }
                            }
                            else {
                                Toast.makeText(SignIn.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                             }
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this,"User not found, please register", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
