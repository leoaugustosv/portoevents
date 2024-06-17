/*
* Desenvolvido pelos alunos:
* Elton Lopes de Meneses
* Guilherme Cabral Mendes Mariano
* Leonardo Augusto Silveira
* Data da última revisão: 16/06/2024
*/

package com.linsysdev.portoevents.usuarios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Usuarios {
    private String cpf;
    private String nome;
    private String telefone;
    private String senha;

    public String getCpf() {
        return cpf;
    }

    public boolean setCpf(String cpf) {
        // VALIDACAO VIA REGEX
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
        // VALIDACAO VIA REGEX
        if (nome.matches(
                "^(?=.{1,40}$)[a-zA-Z]+(?:[-'\\s][a-zA-Z]+)*$")) {
            String nomeCapitalized = Arrays.stream(nome.split("\\s"))
                    .map(palavra -> Character.toTitleCase(palavra.charAt(0)) + palavra.substring(1))
                    .collect(Collectors.joining(" "));
            this.nome = nomeCapitalized;
            return true;
        } else {
            return false;
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean setTelefone(String telefone) {
        // VALIDACAO VIA REGEX
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

    public String getSenha() {
        return senha;
    }

    public boolean setSenha(String senha) {
        // VALIDACAO DE CAMPO VAZIO
        if (senha.isBlank()) {
            return false;
        }

        // VALIDACAO DE CARACTERE ILEGAL
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

    // VERIFICAR SE USUARIO JÁ TEM CONTA
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

    // VALIDACAO DE CADASTRO DAS INFORMACOES INSERIDAS PELO USER EM TODOS OS CAMPOS
    private boolean validarCamposCadastro(Scanner sc) {

        boolean cpfValido = false;
        boolean telefoneValido = false;
        boolean nomeValido = false;
        boolean senhaValida = false;
        boolean senhaPermitida = true;
        String input_senha, input_confirmSenha = "";
        char tentarNovamente = 'S';

        while ((!cpfValido || !telefoneValido || !nomeValido || !senhaValida || !senhaPermitida)
                && tentarNovamente == 'S') {
            System.out.println("Insira as informações para realizar seu cadastro:");
            System.out.println();

            System.out.printf("CPF (sem pontuação) --> ");
            cpfValido = this.setCpf(sc.nextLine());

            System.out.printf("Nome --> ");
            nomeValido = this.setNome(sc.nextLine());

            System.out.printf("Telefone (sem pontuação) --> ");
            telefoneValido = this.setTelefone(sc.nextLine());

            System.out.printf("SENHA --> ");
            input_senha = sc.nextLine();

            System.out.printf("CONFIRME SUA SENHA --> ");
            input_confirmSenha = sc.nextLine();
            if (input_senha.equals(input_confirmSenha)) {
                senhaPermitida = this.setSenha(input_senha);
                senhaValida = true;
            } else {
                senhaPermitida = true;
                senhaValida = false;
            }

            System.out.println();

            // MENSAGENS DE ERRO

            if (!cpfValido) {
                System.out.println(">>> ERRO: CPF inválido.");
            }

            if (!nomeValido) {
                System.out.println(">>> ERRO: Nome inválido.");
            }

            if (!telefoneValido) {
                System.out.println(">>> ERRO: Telefone inválido.");
            }

            if (!senhaValida) {
                System.out.println(">>> ERRO: Os campos de senha não coincidem.");
            }

            if (!senhaPermitida) {
                System.out.println(
                        "\n>>> ERRO: Você incluiu um caractere ilegal ('|') em sua senha, ou ela está em branco.\nPor favor, verifique a senha inserida e tente novamente.\n");
            }

            if ((!cpfValido || !telefoneValido || !nomeValido || !senhaValida || !senhaPermitida)
                    && tentarNovamente == 'S') {
                do {
                    try {
                        System.out.println();
                        System.out.println("Deseja tentar realizar o cadastro novamente? (S/N)");
                        tentarNovamente = sc.nextLine().toUpperCase().charAt(0);
                    } catch (Exception e) {
                        tentarNovamente = Character.MIN_VALUE;
                    }

                } while (tentarNovamente != 'N' && tentarNovamente != 'S');

                if (tentarNovamente == 'N') {
                    return false;
                } else {
                    System.out.println("\nTENTANDO NOVAMENTE...\n====================================");
                }
            }

        }

        // VERIFICACAO FINAL DOS CAMPOS
        if (cpfValido && telefoneValido && nomeValido && senhaValida && senhaPermitida) {
            if (jaRegistrado()) {
                System.out.println();
                System.out.println(
                        ">>> ERRO: ESSE USUÁRIO JÁ POSSUI CADASTRO.\nAperte enter e faça login, ou tente novamente.");
                sc.nextLine();
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    // METODO PARA ARMAZENAR USUARIO EM ARQUIVO USERS.DATA
    private void armazenarUsuario() {
        try {
            File dir = new File("data");
            dir.mkdirs();

            File usersfile = new File(dir, "users.data");
            usersfile.createNewFile();

            FileWriter fw = new FileWriter(usersfile, true);
            fw.write(this.getCpf() + "|" + this.getSenha() + "|" + this.getNome() + "|" + this.getTelefone()
                    + System.getProperty("line.separator"));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METODO DE CADASTRO CHAMADO PELA CLASSE MAIN
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

    // METODO DE LOGIN CHAMADO PELA CLASSE MAIN
    public static boolean autenticar(String cpf, String senha) {
        while (true) {

            try {

                File dir = new File("data");
                dir.mkdirs();

                File usersfile = new File(dir, "users.data");
                usersfile.createNewFile();

                Scanner fsc = new Scanner(usersfile);

                while (fsc.hasNextLine()) {
                    String[] userdata = fsc.nextLine().split(Pattern.quote("|"));
                    if (userdata[0].equals(cpf) && userdata[1].equals(senha)) {
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
