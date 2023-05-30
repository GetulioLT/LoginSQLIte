package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    EditText user, senha;
    CheckBox mostrar_senha;

    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        user = findViewById(R.id.main_user);
        senha = findViewById(R.id.main_senha);
        mostrar_senha = findViewById(R.id.mostrar_senha);
        db = new DB(this);

        senha.setInputType(129);
    }

    public void m_senha(View v){
        if (mostrar_senha.isChecked()){
            senha.setInputType(1);
        }
        else{
            senha.setInputType(129);
        }
    }

    public void m_login(View v){
        String textuser = user.getText().toString();
        String textsenha = senha.getText().toString();

        Cursor dados = db.get_dados();

        while (dados.moveToNext()){
            if (dados.getString(0).equals(textuser)){
                Toast.makeText(this, "Usuario já existente",
                        Toast.LENGTH_LONG).show();
            }else{
                Intent i = new Intent(this, Cadastro.class);
                startActivity(i);
            }
        }


    }
}

/*
boolean verificar = db.novo_user(textuser, textsenha);

                if (verificar){
                    Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "não ok", Toast.LENGTH_SHORT).show();
                }
 */