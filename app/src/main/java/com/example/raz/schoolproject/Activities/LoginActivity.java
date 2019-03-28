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
import com.example.raz.schoolproject.DAL.UserDAL;

public class LoginActivity extends BaseAppCompatActivity {

    Button submit;
    UserDAL userDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDAL = new UserDAL(this);

        submit = findViewById(R.id.submitLoginButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.username_login)).getText().toString();
                String password = ((EditText) findViewById(R.id.password_login)).getText().toString();

                if (userDAL.isLoginValid(username, password)) {
                    userDAL.setCurrentUser(userDAL.getUserByUsername(username));
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
