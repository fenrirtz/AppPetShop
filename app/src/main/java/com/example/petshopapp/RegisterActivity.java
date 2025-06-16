package com.example.petshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petshopapp.R;
import com.example.petshopapp.helpers.DBHelper;

public class RegisterActivity extends AppCompatActivity {
    EditText email, senha;
    Button btnRegistrar;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.editEmail);
        senha = findViewById(R.id.editSenha);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        db = new DBHelper(this);

        btnRegistrar.setOnClickListener(v -> {
            String user = email.getText().toString();
            String pass = senha.getText().toString();
            if (db.register(user, pass)) {
                Toast.makeText(this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, com.example.petshopapp.LoginActivity.class));
            } else {
                Toast.makeText(this, "Usuário já existe!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
