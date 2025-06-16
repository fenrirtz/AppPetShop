package com.example.petshopapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.petshopapp.helpers.DBHelper;
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {
    EditText nomeCliente, nomePet;
    Spinner servicos;
    TextView data, hora;
    Button btnData, btnHora, btnAgendar;
    String usuario;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        usuario = getIntent().getStringExtra("email");
        db = new DBHelper(this);

        nomeCliente = findViewById(R.id.editNomeCliente);
        nomePet = findViewById(R.id.editNomePet);
        servicos = findViewById(R.id.spinnerServicos);
        data = findViewById(R.id.textData);
        hora = findViewById(R.id.textHora);
        btnData = findViewById(R.id.btnEscolherData);
        btnHora = findViewById(R.id.btnEscolherHora);
        btnAgendar = findViewById(R.id.btnConfirmarAgendamento);

        String[] listaServicos = {
                "Banho - R$50", "Tosa - R$60", "Consulta - R$100"
        };
        servicos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaServicos));

        btnData.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                data.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnHora.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                hora.setText(String.format("%02d:%02d", hourOfDay, minute));
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
        });

        btnAgendar.setOnClickListener(v -> {
            String nome = nomeCliente.getText().toString();
            String pet = nomePet.getText().toString();
            String servico = servicos.getSelectedItem().toString();
            String dia = data.getText().toString();
            String horario = hora.getText().toString();

            db.insertSchedule(usuario, nome, pet, servico, dia, horario);

            new AlertDialog.Builder(this)
                    .setTitle("Agendado!")
                    .setMessage("Serviço: " + servico + "\nData: " + dia + "\nHora: " + horario)
                    .setPositiveButton("OK", (d, w) -> finish())
                    .show();
        });
    }
}
