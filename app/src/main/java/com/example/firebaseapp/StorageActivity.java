package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class StorageActivity extends AppCompatActivity {
    private FirebaseStorage storage;
    private Button btnUpload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        btnUpload = findViewById(R.id.storage_btn_upload);

        btnUpload.setOnClickListener(v -> {
            storage.getReference().putFile("https://conteudo.imguol.com.br/c/noticias/b4/2020/08/31/novo-galaxy-z-fold-2-celular-dobravel-da-samsung-1598905156632_v2_450x337.jpg")
        });
    }
}
