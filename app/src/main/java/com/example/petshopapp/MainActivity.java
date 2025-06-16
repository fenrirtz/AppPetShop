package com.example.petshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAgendar, btnMeusAgendamentos, btnClientes, btnDuvidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgendar = findViewById(R.id.btnAgendar);
        btnMeusAgendamentos = findViewById(R.id.btnMeusAgendamentos);
        btnClientes = findViewById(R.id.btnClientes);
        btnDuvidas = findViewById(R.id.btnDuvidas);

        btnAgendar.setOnClickListener(v -> startActivity(new Intent(this, ScheduleActivity.class)));

        btnMeusAgendamentos.setOnClickListener(v -> startActivity(new Intent(this, MySchedulesActivity.class)));

        btnClientes.setOnClickListener(v -> startActivity(new Intent(this, ClientsActivity.class)));

        btnDuvidas.setOnClickListener(v -> startActivity(new Intent(this, FaqActivity.class)));
    }
}
