package com.linsysdev.portoevents.autenticacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.linsysdev.portoevents.usuarios.Usuarios;

public class Autenticacao {
    private String cpf;
    private String senha;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Autenticacao(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public boolean autenticar() {
        try {
            File usersfile = new File("users.data");
            Scanner fsc = new Scanner(usersfile);

            while (fsc.hasNextLine()) {
                String data = fsc.nextLine();
                System.out.println(data);
            }

            fsc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // verificar
        return false;
    }

}
