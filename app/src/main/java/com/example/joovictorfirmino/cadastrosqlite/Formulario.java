package com.example.joovictorfirmino.cadastrosqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joovictorfirmino.cadastrosqlite.dao.UserDAO;
import com.example.joovictorfirmino.cadastrosqlite.modelo.User;

public class Formulario extends AppCompatActivity {
    EditText edtNome, edtTelefone, edtEmail, edtCPF;
    Button btnCriar;
    User user, altuser;
    UserDAO userDAO;
    long retornaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        final Intent i = getIntent();
        altuser = (User) i.getSerializableExtra("usuario-enviado");
        user = new User();
        userDAO = new UserDAO(Formulario.this);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtCPF = (EditText) findViewById(R.id.edtCPF);
        btnCriar = (Button) findViewById(R.id.btnCriar);

        if (altuser != null){
            btnCriar.setText("Alterar");
            edtNome.setText(altuser.getNome());
            edtTelefone.setText(altuser.getTelefone());
            edtEmail.setText(altuser.getEmail());
            edtCPF.setText(altuser.getCpf());

            user.setId(altuser.getId());
        }else {
            btnCriar.setText("Salvar");
        }

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setNome(edtNome.getText().toString());
                user.setTelefone(edtTelefone.getText().toString());
                user.setEmail(edtEmail.getText().toString());
                user.setCpf(edtCPF.getText().toString());

                if (btnCriar.getText().toString().equals("Salvar")){
                    retornaDB = userDAO.salvarUser(user);
                    userDAO.close();
                    if (retornaDB == -1){
                        alert("Erro ao cadastar!");
                    }else {
                        alert("Cadastro realizado!");
                    }
                }else {
                    retornaDB = userDAO.alterarUser(user);
                    userDAO.close();
                    if(retornaDB == -1){
                        alert("Erro ao alterar!");
                    }else {
                        alert("Alterado com sucesso!");
                    }
                }

                finish();
            }

        });
    }

    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
