package com.example.fir_filing_system.admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fir_filing_system.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdminLoginActivity extends AppCompatActivity {

    //we will use these constants later to pass the artist email and id to another activity
    public static final String ADMIN_EMAIL = "com.example.fir_filing_system.EmailID";
    public static final String ADMIN_PASS = "com.example.fir_filing_system.Password";

    EditText ed_email, ed_pass;
    Button addAdminbtn;

    //a list to store all the artist from firebase database
    List<Admin> admins;

    //our database reference object
    DatabaseReference databaseAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        //getting the reference of artists node
        databaseAdmin = FirebaseDatabase.getInstance().getReference("admins");

        ed_email = (EditText) findViewById(R.id.admin_login_email);
        ed_pass = (EditText) findViewById(R.id.admin_login_password);
        addAdminbtn = (Button) findViewById(R.id.admin_login_button);

        //list to store artists
        admins = new ArrayList<>();


        //adding an onclicklistener to button
        addAdminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist() the method is defined below
                //this method is actually performing the write operation
                addAdmin();
                Intent intent=new Intent(AdminLoginActivity.this, AdminHome.class);
                startActivity(intent);
            }
        });
    }

    /*
     * This method is saving a new admin to the Firebase Realtime Database* */
    private void addAdmin() {
        //getting the values to save
        String email = ed_email.getText().toString().trim();
        String pass = ed_pass.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(email)) {
            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Admin
            String id = databaseAdmin.push().getKey();

            //creating an Artist Object
            Admin artist = new Admin(email, pass);
            //Saving the Artist
            databaseAdmin.child(id).setValue(artist);

            //setting edittext to blank again
            ed_email.setText("");

            //displaying a success toast
            Toast.makeText(this, "Andmin added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a email", Toast.LENGTH_LONG).show();
        }
    }
}
