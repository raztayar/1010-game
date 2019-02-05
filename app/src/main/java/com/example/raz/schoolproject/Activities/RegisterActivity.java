package com.example.raz.schoolproject.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raz.schoolproject.R;
import com.example.raz.schoolproject.User;
import com.example.raz.schoolproject.UserDAL;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    Button submit;
    UserDAL userDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Context thisContext = this;

        userDAL = new UserDAL(this);

        submit = findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String phoneNumber = ((EditText) findViewById(R.id.phoneNumber)).getText().toString();

                if (userDAL.getUserByUsername(username) == null) {
                    Random rnd = new Random();
                    long userID = rnd.nextInt(9998) + 2;
                    while (userDAL.idExists(userID)) {
                        userID = rnd.nextInt(9998) + 2;
                    }
                    userDAL.saveUser(new User(userID, username, password, email, phoneNumber));
                }
                else {
                    Toast.makeText(thisContext, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
