package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private Button btnLogout, btnStorage;

    private ArrayList<Upload> listaUploads = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogout = findViewById(R.id.main_btn_logout);
        btnStorage = findViewById(R.id.main_btn_storage);

        btnStorage.setOnClickListener(v -> {
            //abrir StorageActivity
            Intent intent = new Intent(getApplicationContext(),
                    StorageActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            //deslogar usuario
            auth.signOut();
            finish();
        });
        TextView textEmail = findViewById(R.id.main_text_email);
        textEmail.setText(auth.getCurrentUser().getEmail());

        TextView textNome = findViewById(R.id.main_text_user);
        textNome.setText(auth.getCurrentUser().getDisplayName());


    }

    @Override
    protected void onStart() {
        // onStart:
        /* - faz par do ciclo de vida da Activity,
         * - É executado quando app inicia,
         *  - e quando volta do background
         * */
        super.onStart();
        getData();
    }

    public void getData() {
        //listener p/ o no uploads
        // - caso ocorra alguma alterecao -> retorna TODOS os dados!!
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(   DataSnapshot no_filho: snapshot.getChildren()){
                   listaUploads.add(upload);
                   Log.i("DATABASE","id: " + upload.getId() + ",nome: "
                   + upload.getNomeImagem());
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
