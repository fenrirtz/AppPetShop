package com.example.petshopapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.petshopapp.helpers.DBHelper;

public class LoginActivity extends AppCompatActivity {
    EditText email, senha;
    Button loginBtn, registerBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String userEmail = prefs.getString("email", null);
        if (userEmail != null) {
            startActivity(new Intent(this, MainMenuActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editEmail);
        senha = findViewById(R.id.editSenha);
        loginBtn = findViewById(R.id.btnLogin);
        registerBtn = findViewById(R.id.btnRegister);
        db = new DBHelper(this);

        loginBtn.setOnClickListener(v -> {
            String user = email.getText().toString().trim();
            String pass = senha.getText().toString().trim();

            if (db.login(user, pass)) {
                SharedPreferences.Editor editor = getSharedPreferences("user_session", MODE_PRIVATE).edit();
                editor.putString("email", user);
                editor.apply();

                Intent i = new Intent(LoginActivity.this, MainMenuActivity.class);
                i.putExtra("email", user);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(this, "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show();
            }
        });

        registerBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}
