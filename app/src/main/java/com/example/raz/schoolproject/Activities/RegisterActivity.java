package com.example.raz.schoolproject.Activities;

import android.content.Context;
import android.content.Intent;
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

        submit = findViewById(R.id.submitRegisterButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.username_register)).getText().toString();
                String password = ((EditText) findViewById(R.id.password_register)).getText().toString();
                String email = ((EditText) findViewById(R.id.email_register)).getText().toString();
                String phoneNumber = ((EditText) findViewById(R.id.phoneNumber_register)).getText().toString();

                if (userDAL.getUserByUsername(username) == null) {
                    userDAL.saveUser(new User(User.generateNewUserID(userDAL), username, password, email, phoneNumber));
                    userDAL.updateCurrentUser(userDAL.getUserByUsername(username));
                    Toast.makeText(thisContext, "Register successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainMenuActivity.class));
                }
                else {
                    Toast.makeText(thisContext, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}