package com.example.joovictorfirmino.cadastrosqlite.modelo;

import java.io.Serializable;

/**
 * Created by João Victor Firmino on 15/11/2017.
 */

public class User implements Serializable{
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return nome.toString();
    }
}
