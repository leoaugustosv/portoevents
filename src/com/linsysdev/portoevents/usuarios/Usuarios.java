package com.linsysdev.portoevents.usuarios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                "^\\d{10,11}$")) {
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

    public boolean setSenha(String senha) {

        final String regex = "\\|";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(senha);

        if (matcher.find()) {
            return false;
        } else {
            this.senha = senha;
            return true;
        }

    }

    private boolean jaRegistrado() {
        try {
            File dir = new File("data");
            dir.mkdirs();

            File usersfile = new File(dir, "users.data");

            usersfile.createNewFile();

            Scanner fsc = new Scanner(usersfile);

            while (fsc.hasNextLine()) {
                String[] userdata = fsc.nextLine().split(Pattern.quote("|"));
                if (userdata[0].equals(this.getCpf())) {
                    return true;
                }
            }

            fsc.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    private boolean validarCamposCadastro(Scanner sc) {

        boolean cpfValido = false;
        boolean telefoneValido = false;
        boolean senhaValida = false;
        boolean senhaPermitida = false;
        char tentarNovamente = 'S';

        while ((!cpfValido || !telefoneValido || !senhaValida || !senhaPermitida) && tentarNovamente == 'S') {
            System.out.println("Insira as informações para realizar seu cadastro:");

            System.out.printf("CPF (sem pontuação) --> ");
            cpfValido = this.setCpf(sc.nextLine());

            System.out.printf("Telefone (sem pontuação) --> ");
            telefoneValido = this.setTelefone(sc.nextLine());

            System.out.printf("SENHA --> ");
            String input_senha = sc.nextLine();

            System.out.printf("CONFIRME SUA SENHA --> ");
            if (input_senha.equals(sc.nextLine())) {
                senhaPermitida = this.setSenha(input_senha);
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

            if (!senhaPermitida) {
                System.out.println(
                        "\nVocê incluiu um caractere ilegal ('|') em sua senha.\nPor favor, remova esse caractere e tente novamente.\n");
            }

            if ((!cpfValido || !telefoneValido || !senhaValida || !senhaPermitida) && tentarNovamente == 'S') {
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

        if (cpfValido && telefoneValido && senhaValida && senhaPermitida) {
            if (jaRegistrado()) {
                System.out.println();
                System.out.println(
                        ">> ERRO: ESSE USUÁRIO JÁ POSSUI CADASTRO.\nAperte enter e faça login, ou tente novamente.");
                sc.nextLine();
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public void armazenarUsuario() {
        try {
            File dir = new File("data");
            dir.mkdirs();

            File usersfile = new File(dir, "users.data");
            usersfile.createNewFile();

            FileWriter fw = new FileWriter(usersfile, true);
            fw.write(this.getCpf() + "|" + this.getSenha() + "|" + getNome() + "|" + getTelefone()
                    + System.getProperty("line.separator"));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean cadastrar(Scanner sc) {
        boolean validacao = validarCamposCadastro(sc);
        if (validacao == false) {
            System.out.println("\nCADASTRO NÃO REALIZADO.\nVoltando para a tela inicial...");
            return false;
        } else {
            armazenarUsuario();
            System.out.println("\n>> CADASTRO REALIZADO COM SUCESSO!\nAperte enter para continuar!");
            sc.nextLine();
            return true;
        }
    }

}
