package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        user = findViewById(R.id.user);
        senha = findViewById(R.id.senha);
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

    public void cadastrar(View v){
        String textuser = user.getText().toString();
        String textsenha = senha.getText().toString();

        boolean verificar = db.novo_user(textuser, textsenha);

        if (verificar){
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "não ok", Toast.LENGTH_SHORT).show();
        }

        Cursor dados = db.get_dados();

        while (dados.moveToNext()){
            Log.e("print", String.valueOf(dados.getString(0)));
            Log.e("print", String.valueOf(dados.getString(1)));
        }

    }
}