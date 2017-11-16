package com.example.joovictorfirmino.cadastrosqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Buscar extends AppCompatActivity {
    EditText edtNome2, edtTelefone2, edtEmail2, edtCPF2, edtPesquisar;
    Button btnBuscarUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        edtNome2 = (EditText) findViewById(R.id.edtNome2);
        edtTelefone2 = (EditText) findViewById(R.id.edtTelefone2);
        edtEmail2 = (EditText) findViewById(R.id.edtEmail2);
        edtCPF2 = (EditText) findViewById(R.id.edtCPF2);
        edtPesquisar = (EditText) findViewById(R.id.edtPesquisar);
        btnBuscarUser = (Button) findViewById(R.id.btnBuscarUser);
    }
}
