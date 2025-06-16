package com.example.petshopapp;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class FaqActivity extends AppCompatActivity {
    String[] perguntas = {
            "Atendem em SP?", "Fazem atendimento a domicílio?", "Quais os horários?"
    };
    String[] respostas = {
            "Sim, atendemos em toda SP.",
            "Sim, mediante agendamento prévio.",
            "Segunda a sábado das 08h às 18h."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        LinearLayout container = findViewById(R.id.containerFaq);

        for (int i = 0; i < perguntas.length; i++) {
            LinearLayout faqItem = new LinearLayout(this);
            faqItem.setOrientation(LinearLayout.VERTICAL);
            faqItem.setBackground(ContextCompat.getDrawable(this, R.drawable.faq_item_background));
            faqItem.setPadding(24, 24, 24, 24);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 24);
            faqItem.setLayoutParams(layoutParams);

            TextView pergunta = new TextView(this);
            pergunta.setText(perguntas[i]);
            pergunta.setTextSize(18);
            pergunta.setTypeface(null, Typeface.BOLD);
            pergunta.setPadding(0, 0, 0, 8);

            TextView resposta = new TextView(this);
            resposta.setText(respostas[i]);
            resposta.setTextSize(16);

            faqItem.addView(pergunta);
            faqItem.addView(resposta);

            container.addView(faqItem);
        }
    }
}
