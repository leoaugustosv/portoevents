package com.linsysdev.portoevents.usuarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Usuarios {
    private String cpf;
    private String nome;
    private String telefone;
    private String senha;

    public String getCpf() {
        return cpf;
    }

    public boolean setCpf(String cpf) {

        if (cpf.matches(
                "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")) {
            this.cpf = cpf;
            return true;
        } else {
            return false;
        }

    }

    public String getNome() {
        return nome;
    }

    public boolean setNome(String nome) {
        if (nome.matches(
                "/^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/u")) {
            this.nome = nome;
            return true;
        } else {
            return false;
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean setTelefone(String telefone) {
        if (telefone.matches(
                "/^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$/")) {
            this.telefone = telefone;
            return true;
        } else {
            return false;
        }
    }

    public Usuarios() {
    }

    public Usuarios(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Usuarios(String cpf, String nome, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean validarCamposCadastro(Scanner sc) {

        boolean cpfValido = false;
        boolean telefoneValido = false;
        boolean senhaValida = false;
        char tentarNovamente = 'S';

        while ((!cpfValido || !telefoneValido || !senhaValida) && tentarNovamente == 'S') {
            System.out.println("Insira as informações para realizar seu cadastro:");

            System.out.printf("CPF (sem pontuação) --> ");
            cpfValido = this.setCpf(sc.nextLine());

            System.out.printf("Telefone (sem pontuação) --> ");
            telefoneValido = this.setTelefone(sc.nextLine());

            System.out.printf("SENHA --> ");
            String input_senha = sc.nextLine();

            System.out.printf("CONFIRME SUA SENHA --> ");
            if (input_senha.equals(sc.nextLine())) {
                this.setSenha(input_senha);
                senhaValida = true;
            }

            if (!cpfValido) {
                System.out.println("CPF inválido.");
            }
            if (!telefoneValido) {
                System.out.println("Telefone inválido.");
            }
            if (!senhaValida) {
                System.out.println("Os campos de senha não coincidem.");
            }

            if (tentarNovamente == 'S') {
                do {
                    System.out.println("Deseja tentar realizar o cadastro novamente? (S/N)");
                    tentarNovamente = sc.nextLine().toUpperCase().charAt(0);
                } while (tentarNovamente != 'N' && tentarNovamente != 'S');

                if (tentarNovamente == 'N') {
                    return false;
                } else {
                    System.out.println("\nTENTANDO NOVAMENTE...\n====================================");
                }
            }

        }

        if (cpfValido && telefoneValido && senhaValida) {
            return true;
        } else {
            return false;
        }

    }

    public void armazenarUsuario() {
        try {
            File usersFile = new File("users.data");
            if (usersFile.createNewFile()) {
                System.out.println("File created: " + usersFile.getName());
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean cadastrar(Scanner sc) {
        boolean validacao = validarCamposCadastro(sc);
        if (validacao == false) {
            System.out.println("\nCadastro não realizado. Voltando para a tela inicial.");
            return false;
        } else {

            return true;
        }
    }

}
