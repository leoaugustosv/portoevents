package com.linsysdev.portoevents.autenticacao;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

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

        while (true) {

            try {

                File dir = new File("data");
                dir.mkdirs();

                File usersfile = new File(dir, "users.data");
                usersfile.createNewFile();

                Scanner fsc = new Scanner(usersfile);

                while (fsc.hasNextLine()) {
                    String[] userdata = fsc.nextLine().split(Pattern.quote("|"));
                    if (userdata[0].equals(this.getCpf()) && userdata[1].equals(this.getSenha())) {
                        System.out.println(System.lineSeparator().repeat(50));
                        System.out.println("LOGIN REALIZADO COM SUCESSO!");
                        return true;
                    }
                }

                fsc.close();
                System.out.println("\nLOGIN E/OU SENHA INVÁLIDOS.");
                return false;
            } catch (IOException e) {
                System.out.println("Tentando novamente...");
                e.printStackTrace();
                return false;
            }
        }

    }

}
