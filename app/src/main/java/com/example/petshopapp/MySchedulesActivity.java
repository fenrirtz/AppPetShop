package com.example.petshopapp;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.petshopapp.helpers.DBHelper;

public class MySchedulesActivity extends AppCompatActivity {
    DBHelper db;
    LinearLayout container;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedules);

        db = new DBHelper(this);
        container = findViewById(R.id.containerAgendamentos);
        usuario = getIntent().getStringExtra("email");

        Cursor cursor = db.getUserSchedules(usuario);
        if (cursor.moveToFirst()) {
            do {
                String pet = cursor.getString(2);
                String servico = cursor.getString(3);
                String data = cursor.getString(4);
                String hora = cursor.getString(5);

                LinearLayout card = new LinearLayout(this);
                card.setOrientation(LinearLayout.VERTICAL);
                card.setBackground(ContextCompat.getDrawable(this, R.drawable.schedule_item_background));
                card.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, dpToPx(16));
                card.setLayoutParams(params);

                card.setElevation(dpToPx(4));

                TextView txt = new TextView(this);
                txt.setText("Pet: " + pet + "\nServiço: " + servico + "\nData: " + data + " - " + hora);
                txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                txt.setTextColor(Color.parseColor("#000000"));

                card.addView(txt);

                container.addView(card);

            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
