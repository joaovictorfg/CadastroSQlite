package com.example.joovictorfirmino.cadastrosqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.joovictorfirmino.cadastrosqlite.dao.UserDAO;
import com.example.joovictorfirmino.cadastrosqlite.modelo.User;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView listLista;
    Button btnCadastro, btnBuscar;
    User user;
    UserDAO userDAO;
    ArrayList<User> arrayListUser;
    ArrayAdapter<User> arrayAdapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listLista = (ListView) findViewById(R.id.listLista);
        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        registerForContextMenu(listLista);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Formulario.class);
                startActivity(i);
            }
        });

        listLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                User envioUser = (User) arrayAdapterUser.getItem(position);
                Intent i = new Intent(MainActivity.this, Formulario.class);
                i.putExtra("usuario-enviado", envioUser);
                startActivity(i);
            }
        });

        listLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                user = arrayAdapterUser.getItem(position);
                return false;
            }
        });
    }

    public void escreveLista(){
        userDAO = new UserDAO(MainActivity.this);
        arrayListUser = userDAO.selectAllUser();
        userDAO.close();

        if (listLista != null){
            arrayAdapterUser = new ArrayAdapter<User>(MainActivity.this,
                    android.R.layout.simple_list_item_1, arrayListUser);
            listLista.setAdapter(arrayAdapterUser);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        escreveLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add("Delete registro");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retornaDB;
                userDAO = new UserDAO(MainActivity.this);
                retornaDB = userDAO.deletarUser(user);
                userDAO.close();

                if (retornaDB == -1){
                    alert("Erro para excluir!");
                }else {
                    alert("Excluido com sucesso!");
                }
                escreveLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
