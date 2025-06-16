package com.example.petshopapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        usuario = prefs.getString("email", null);

        if (usuario == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        Button btnAgendar = findViewById(R.id.btnAgendar);
        Button btnMeus = findViewById(R.id.btnMeusAgendamentos);
        Button btnClientes = findViewById(R.id.btnClientes);
        Button btnDuvidas = findViewById(R.id.btnDuvidas);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnAgendar.setOnClickListener(v -> {
            Intent i = new Intent(this, ScheduleActivity.class);
            i.putExtra("email", usuario);
            startActivity(i);
        });

        btnMeus.setOnClickListener(v -> {
            Intent i = new Intent(this, MySchedulesActivity.class);
            i.putExtra("email", usuario);
            startActivity(i);
        });

        btnClientes.setOnClickListener(v -> startActivity(new Intent(this, ClientsActivity.class)));

        btnDuvidas.setOnClickListener(v -> startActivity(new Intent(this, FaqActivity.class)));

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("user_session", MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
}
