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
import com.example.raz.schoolproject.Objects.User;
import com.example.raz.schoolproject.DAL.UserDAL;
import com.example.raz.schoolproject.Utilities;

public class RegisterActivity extends BaseAppCompatActivity {

    Button submit;
    UserDAL userDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
                    User user = userDAL.createUser(new User(0, username, Utilities.md5(password), email, phoneNumber, 0));
                    userDAL.setCurrentUser(user);
                    Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainMenuActivity.class));
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
