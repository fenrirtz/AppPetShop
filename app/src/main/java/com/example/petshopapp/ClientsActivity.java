package com.example.petshopapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ClientsActivity extends AppCompatActivity {
    int[] fotos = {R.drawable.animal_1, R.drawable.animal_2, R.drawable.animal_3};
    String[] nomes = {"Luna", "Rex", "Mimi"};
    String[] servicos = {"Banho", "Tosa", "Consulta"};
    String[] comentarios = {
            "Excelente atendimento!",
            "Muito cuidadosos!",
            "Veterinário muito atencioso!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        LinearLayout container = findViewById(R.id.containerClientes);

        for (int i = 0; i < nomes.length; i++) {
            // Container do cliente
            LinearLayout bloco = new LinearLayout(this);
            bloco.setOrientation(LinearLayout.VERTICAL);
            bloco.setBackground(ContextCompat.getDrawable(this, R.drawable.client_item_background));
            bloco.setPadding(24, 24, 24, 24);

            // Margem entre caixas
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 24);
            bloco.setLayoutParams(params);

            // Imagem
            ImageView img = new ImageView(this);
            img.setImageResource(fotos[i]);
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(300, 300);
            imgParams.bottomMargin = 16;
            img.setLayoutParams(imgParams);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            bloco.addView(img);

            // Nome
            TextView nome = new TextView(this);
            nome.setText("Nome: " + nomes[i]);
            nome.setTypeface(null, Typeface.BOLD);
            nome.setTextSize(18);
            nome.setPadding(0, 0, 0, 8);
            bloco.addView(nome);

            // Serviço
            TextView serv = new TextView(this);
            serv.setText("Serviço: " + servicos[i]);
            serv.setTextSize(16);
            serv.setPadding(0, 0, 0, 8);
            bloco.addView(serv);

            // Comentário
            TextView com = new TextView(this);
            com.setText("Comentário: " + comentarios[i]);
            com.setTextSize(16);
            bloco.addView(com);

            container.addView(bloco);
        }
    }
}
