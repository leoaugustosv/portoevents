package com.linsysdev.portoevents.eventos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Eventos {

    private String nome;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String categoria;
    private LocalDateTime dataHora;
    private Integer duracao;
    private String descricao;
    private String participantes;

    public enum Categoria {
        ANIVERSARIO,
        CASAMENTO,
        FORMATURA,
        BALADA,
        ESPORTIVO,
        PALESTRA,
        BENEFICENTE
    }

    public enum UF {
        AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO
    }

    public String getNome() {
        return nome;
    }

    public boolean setNome(String nome) {
        if (nome.matches(
                "^[\\p{L}0-9\\s.,'’\"“”?!:;()-]+$") && nome.length() < 300) {
            this.nome = nome;
            return true;
        } else {
            System.out.println("\nERRO: Insira um nome válido para o evento.");
            return false;
        }
    }

    public String getLogradouro() {
        return logradouro;
    }

    public boolean setLogradouro(String logradouro) {

        if (logradouro.isBlank()) {
            System.out.println(
                    "\nERRO: Insira um logradouro, começando com uma das possibilidades abaixo:\nRua|Avenida|Travessa|Alameda|Praça|Estrada|Rodovia");
            return false;
        }

        String logradouroCapitalized = Arrays.stream(logradouro.split("\\s"))
                .map(palavra -> Character.toTitleCase(palavra.charAt(0)) + palavra.substring(1))
                .collect(Collectors.joining(" "));

        if (logradouroCapitalized.matches(
                "^(Rua|Avenida|Travessa|Alameda|Praça|Estrada|Rodovia)\s+[A-Za-zÀ-ÖØ-öø-ÿ\s'-]+$")
                && logradouroCapitalized.length() < 300) {
            this.logradouro = logradouroCapitalized;
            return true;
        } else {
            System.out.println(
                    "\nERRO: Insira um logradouro válido, começando com uma das possibilidades abaixo:\nRua|Avenida|Travessa|Alameda|Praça|Estrada|Rodovia");
            return false;
        }

    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public boolean setDataHora(LocalDateTime dataHora) {
        LocalDateTime dataMinima = LocalDateTime.now().plusHours(24);
        if (dataHora.isBefore(dataMinima)) {
            System.out.println("\nERRO: O evento precisa ser marcado com antecedência mínima de 24h.");
            return false;
        }

        try {
            this.dataHora = dataHora;
        } catch (Exception e) {
            System.out.println("\nERRO: " + e);
            return false;
        }

        return true;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public boolean setDuracao(Integer duracao) {
        if (duracao < 30) {
            System.out.println("\nERRO: A duração mínima do evento deve ser igual ou superior à 30 minutos.");
            return false;
        } else if (duracao > 360) {
            System.out.println(
                    "\nERRO: No momento, realizamos eventos com duração de até 6 horas (360 minutos).\n Por favor, insira uma duração menor.");
            return false;
        }
        this.duracao = duracao;
        return true;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean setDescricao(String descricao) {

        if (descricao.isBlank()) {
            System.out.println("\nERRO: Insira uma descrição para o evento.");
            return false;
        }
        if (descricao.length() > 1500) {
            System.out.println(
                    "\nERRO: A descrição inserida para o evento é muito grande. Por favor, insira uma descrição com menos de 1500 caracteres.");
            return false;
        }

        final String regex = "\\|";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(descricao);

        if (matcher.find()) {
            System.out.println("\nERRO: Caractere inválido \"\\|\" inserido. Remova o caractere e tente novamente.");
            return false;
        } else {
            this.descricao = descricao;
            return true;
        }
    }

    public Integer getNumero() {
        return numero;
    }

    public boolean setNumero(Integer numero) {
        if (numero > 99999) {
            System.out.println("\nERRO: Digite um número de endereço válido.");
            return false;
        }
        this.numero = numero;
        return true;
    }

    public String getComplemento() {
        return complemento;
    }

    public boolean setComplemento(String complemento) {
        if (complemento.isBlank()) {
            System.out.printf("\nINFO: Preenchendo complemento em sistema como \"N/A\" (não aplicável).\n");
            this.complemento = "N/A";
            return true;
        }

        if (complemento.length() > 1500) {
            System.out.println(
                    "\nERRO: A descrição inserida para o evento é muito grande. Por favor, insira uma descrição com menos de 1500 caracteres.");
            return false;
        }

        final String regex = "\\|";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(complemento);

        if (matcher.find()) {
            System.out.println("Caractere inválido \"|\" inserido. Remova o caractere e tente novamente.");
            return false;
        } else {
            this.complemento = complemento;
            return true;
        }

    }

    public String getBairro() {
        return bairro;
    }

    public boolean setBairro(String bairro) {

        if (bairro.matches(
                "^[A-Za-zÀ-ÖØ-öø-ÿ\\s'-]+$")) {
            this.bairro = bairro;
            return true;
        } else {
            System.out.println("\nERRO: Digite um bairro válido.");
            return false;
        }

    }

    public String getCidade() {
        return cidade;
    }

    public boolean setCidade(String cidade) {

        if (cidade.matches(
                "^[A-Za-zÀ-ÖØ-öø-ÿ\s]+$")) {
            this.cidade = cidade;
            return true;
        } else {
            System.out.println("\nERRO: Digite uma cidade válida.");
            return false;
        }
    }

    public String getUf() {
        return uf;
    }

    public boolean setUf(String uf) {

        for (UF c : UF.values()) {
            if (uf.toUpperCase().equals(c.name())) {
                this.uf = uf.toUpperCase();
                return true;
            }
        }
        System.out.println("\nERRO: Digite um UF de estado válido.");
        return false;

    }

    public String getCep() {
        return cep;
    }

    public boolean setCep(String cep) {

        if (cep.matches(
                "^\\d{8}$")) {
            this.cep = cep;
            return true;
        } else {
            System.out.println("\nERRO: Digite um CEP válido (sem pontuação).");
            return false;
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean setCategoria(String category) {

        if (category.toUpperCase().equals("ANIVERSÁRIO")) {
            category = "ANIVERSARIO";
        }

        for (Categoria c : Categoria.values()) {
            if (category.toUpperCase().equals(c.name())) {
                this.categoria = category;
                return true;
            }
        }
        System.out.println("\nERRO: Insira uma categória válida dentre as categorias abaixo: ");
        return false;

    }

    public Eventos() {
    }

    public Eventos(String currentUser) {
        this.participantes = "," + currentUser;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    public boolean criarEvento(Scanner sc) {
        boolean validacao = validarCamposEvento(sc);

        if (validacao == false) {
            System.out.println("\nEVENTO NÃO CADASTRADO.\nVoltando para a tela inicial...");
            return false;
        } else {
            armazenarEvento();
            System.out.println("\n>> EVENTO CADASTRADO COM SUCESSO!\nAperte enter para continuar!");
            sc.nextLine();
            return true;
        }
    }

    private boolean validarCamposEvento(Scanner sc) {

        boolean nomeValid = false;
        boolean logradouroValid = false;
        boolean numeroValid = false;
        boolean complementoValid = false;
        boolean bairroValid = false;
        boolean cidadeValid = false;
        boolean ufValid = false;
        boolean cepValid = false;
        boolean categoriaValid = false;
        boolean dataHoraValid = false;
        boolean duracaoValid = false;
        boolean descricaoValid = false;
        char tentarNovamente = 'S';

        while ((!nomeValid || !logradouroValid || !numeroValid || !complementoValid || !bairroValid || !cidadeValid
                || !ufValid || !cepValid || !categoriaValid || !dataHoraValid || !duracaoValid || !descricaoValid)
                && tentarNovamente == 'S') {
            System.out.println("Insira as informações pedidas abaixo para cadastrar um evento:\n");

            do {
                System.out.printf("Nome do evento --> ");
                nomeValid = this.setNome(sc.nextLine());
            } while (!nomeValid);

            do {
                System.out.printf("\nLougradouro:\n\n(EXEMPLO: Avenida das Nações Unidas)\n--> ");
                logradouroValid = this.setLogradouro(sc.nextLine());
            } while (!logradouroValid);

            do {
                try {
                    System.out.printf("\nNúmero --> ");
                    numeroValid = this.setNumero(Integer.parseInt(sc.nextLine()));

                } catch (Exception e) {
                    System.out.println("\nERRO: Digite um número válido.");
                }
            } while (!numeroValid);

            do {
                System.out.printf("\nComplemento (aperte Enter caso não haja complemento) --> ");
                complementoValid = this.setComplemento(sc.nextLine());
            } while (!complementoValid);

            do {
                System.out.printf("\nBairro --> ");
                bairroValid = this.setBairro(sc.nextLine());
            } while (!bairroValid);

            do {
                System.out.printf("\nCidade --> ");
                cidadeValid = this.setCidade(sc.nextLine());
            } while (!cidadeValid);

            do {
                System.out.printf("\nUF (ex: SP) --> ");
                ufValid = this.setUf(sc.nextLine());
            } while (!ufValid);

            do {
                System.out.printf("\nCEP (sem pontuação) --> ");
                cepValid = this.setCep(sc.nextLine());
            } while (!cepValid);

            do {
                System.out.println("\nInforme qual a categoria do evento: ");
                System.out.println("ANIVERSÁRIO, CASAMENTO, FORMATURA, BALADA, ESPORTIVO, PALESTRA, BENEFICENTE");
                System.out.printf("--> ");
                categoriaValid = this.setCategoria(sc.nextLine());
            } while (!categoriaValid);

            do {
                System.out.println("\nInforme a data e hora do evento:\n(ex: 23/05/2024 19:00)");
                System.out.printf("--> ");
                String dataHoraInserida = sc.nextLine();
                try {
                    LocalDateTime dataHoraConvertida = LocalDateTime.parse(dataHoraInserida,
                            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

                    dataHoraValid = this.setDataHora(dataHoraConvertida);
                } catch (Exception e) {
                    System.out.println(
                            "\nERRO: Formato digitado inválido. Digite um formato válido e tente novamente.");
                }

            } while (!dataHoraValid);

            do {

                try {
                    System.out.printf("Duração (em minutos): --> ");
                    duracaoValid = this.setDuracao(Integer.parseInt(sc.nextLine()));

                } catch (Exception e) {
                    System.out.println("\nERRO: Digite um número válido.");
                }

            } while (!duracaoValid);

            do {
                System.out.printf("Descrição: --> ");
                descricaoValid = this.setDescricao(sc.nextLine());
            } while (!descricaoValid);

            if ((!nomeValid || !logradouroValid || !numeroValid || !complementoValid || !bairroValid || !cidadeValid
                    || !ufValid || !cepValid || !categoriaValid || !dataHoraValid || !duracaoValid || !descricaoValid)
                    && tentarNovamente == 'S') {
                do {
                    System.out.println("Deseja tentar realizar o cadastro deste evento novamente? (S/N)");
                    tentarNovamente = sc.nextLine().toUpperCase().charAt(0);
                } while (tentarNovamente != 'N' && tentarNovamente != 'S');

                if (tentarNovamente == 'N') {
                    return false;
                } else {
                    System.out.println("\nTENTANDO NOVAMENTE...\n====================================");
                }
            }

        }

        if (nomeValid && logradouroValid && numeroValid && complementoValid && bairroValid && cidadeValid
                && ufValid && cepValid && categoriaValid && dataHoraValid && duracaoValid && descricaoValid) {
            if (horarioOcupado()) {
                System.out.println("Aperte enter para continuar.");
                sc.nextLine();
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    protected static File createEventsData() {

        File dir = new File("data");
        dir.mkdirs();
        File eventsfile = new File(dir, "events.data");
        try {
            eventsfile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventsfile;

    }

    public void armazenarEvento() {
        try {

            FileWriter fw = new FileWriter(createEventsData(), true);
            fw.write(this.getNome() + "|" + this.getLogradouro() + "|" + getNumero() + "|" + getComplemento()
                    + "|" + this.getBairro() + "|" + this.getCidade() + "|" + this.getUf() + "|" + this.getCep()
                    + "|" + this.getCategoria() + "|" + this.getDataHora() + "|" + this.getDuracao() + "|"
                    + this.getDescricao() + "|" + this.getParticipantes() + System.getProperty("line.separator"));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean horarioOcupado() {
        try {

            Scanner fsc = new Scanner(createEventsData());

            while (fsc.hasNextLine()) {
                String[] eventdata = fsc.nextLine().split(Pattern.quote("|"));

                LocalDateTime horarioInicial = LocalDateTime.parse(eventdata[9]);
                Integer duracaoEvento = Integer.parseInt(eventdata[10]);
                LocalDateTime horarioFinal = horarioInicial.plusMinutes(duracaoEvento);

                LocalDateTime horarioInicialSolicitado = getDataHora();
                LocalDateTime horarioFinalSolicitado = getDataHora().plusMinutes(getDuracao());

                if (!(horarioInicialSolicitado.isAfter(horarioInicial)
                        || horarioInicialSolicitado.isEqual(horarioInicial))
                        &&
                        !(horarioFinalSolicitado.isBefore(horarioFinal)
                                || horarioFinalSolicitado.isEqual(horarioFinal))) {
                    System.out.println();

                    System.out.println(
                            ">> ERRO: HORÁRIO PARA EVENTO INDISPONÍVEL.");
                    System.out.println(
                            "O horário do evento que você tentou cadastrar está em conflito com o seguinte evento:\n");
                    System.out.println("==> NOME: " + eventdata[0]);
                    System.out.println("==> DATA E HORA: " + horarioInicial + " até "
                            + horarioFinal.getHour() + ":" + horarioFinal.getMinute());
                    System.out.println("\nPor favor, escolha uma outra data e hora, e tente novamente.");
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

}