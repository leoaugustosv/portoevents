/*
* Desenvolvido pelos alunos:
* Elton Lopes de Meneses
* Guilherme Cabral Mendes Mariano
* Leonardo Augusto Silveira
* Data da última revisão: 16/06/2024
*/


package com.linsysdev.portoevents.eventos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
        // VALIDACAO VIA REGEX
        if (nome.matches(
                "^[\\p{L}0-9\\s.,'’\"“”?!:;()-]+$") && nome.length() < 300) {
            this.nome = nome;
            return true;
        } else {
            System.out.println("\n>>> ERRO: Insira um nome válido para o evento.");
            System.out.println("---------------------------");
            System.out.println();
            return false;
        }
    }

    public String getLogradouro() {
        return logradouro;
    }

    public boolean setLogradouro(String logradouro) {

        // VALIDACAO DE CAMPO VAZIO
        if (logradouro.isBlank()) {
            System.out.println(
                    "\n>>> ERRO: Insira um logradouro que comece com um dos prefixos abaixo:");
                    System.out.println("Rua | Avenida | Travessa | Alameda | Praça | Estrada | Rodovia");
                    System.out.println("---------------------------");
            System.out.println();
            return false;
        }

        // CAPITALIZAR LOGRADOURO
        String logradouroCapitalized = Arrays.stream(logradouro.split("\\s"))
                .map(palavra -> Character.toTitleCase(palavra.charAt(0)) + palavra.substring(1))
                .collect(Collectors.joining(" "));

        // VALIDACAO VIA REGEX
        if (logradouroCapitalized.matches(
                "^(Rua|Avenida|Travessa|Alameda|Praça|Estrada|Rodovia)\s+[A-Za-zÀ-ÖØ-öø-ÿ\s'-]+$")
                && logradouroCapitalized.length() < 300) {
            this.logradouro = logradouroCapitalized;
            return true;
        } else {
            System.out.println(
                    "\n>>> ERRO: Insira um logradouro VÁLIDO, que comece com um dos prefixos abaixo:");
                    System.out.println("Rua | Avenida | Travessa | Alameda | Praça | Estrada | Rodovia");
                    System.out.println("---------------------------");
            return false;
        }

    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public boolean setDataHora(LocalDateTime dataHora) {

        // VALIDACAO DE ANTECEDENCIA DE EVENTO (MIN 24 HRS)
        LocalDateTime dataMinima = LocalDateTime.now().plusHours(24);
        if (dataHora.isBefore(dataMinima)) {
            System.out.println("\n>>> ERRO: O evento precisa ser marcado com antecedência mínima de 24h.");
            System.out.println("---------------------------");
            return false;
        }

        try {
            this.dataHora = dataHora;
        } catch (Exception e) {
            System.out.println("\n>>> ERRO: " + e);
            return false;
        }

        return true;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public boolean setDuracao(Integer duracao) {
        // VALIDACAO DE DURACAO MIN-MAX DE EVENTO
        if (duracao < 30) {
            System.out.println("\n>>> ERRO: A duração mínima do evento deve ser igual ou superior à 30 minutos.");
            System.out.println("---------------------------");
            return false;
        } else if (duracao > 360) {
            System.out.println(
                    "\n>>> ERRO: No momento, realizamos eventos com duração de até 6 horas (360 minutos).\n Por favor, insira uma duração menor.");
                    System.out.println("---------------------------");
            return false;
        }
        this.duracao = duracao;
        return true;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean setDescricao(String descricao) {

        // VALIDACAO DE CAMPO VAZIO
        if (descricao.isBlank()) {
            System.out.println("\n>>> ERRO: Insira uma descrição para o evento.");
            System.out.println("---------------------------");
            return false;
        }

        // VALIDACAO DE DESCRICAO MUITO GRANDE
        if (descricao.length() > 1500) {
            System.out.println(
                    "\n>>> ERRO: A descrição inserida para o evento é muito grande. Por favor, insira uma descrição com menos de 1500 caracteres.");
                    System.out.println("---------------------------");
            return false;
        }

        // VALIDACAO DE CARACTERE ILEGAL
        final String regex = "\\|";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(descricao);

        if (matcher.find()) {
            System.out.println("\n>>> ERRO: Caractere inválido \"\\|\" inserido. Remova o caractere e tente novamente.");
            System.out.println("---------------------------");
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
        // VALIDACAO DE NUMERO MAX
        if (numero > 99999) {
            System.out.println("\n>>> ERRO: Digite um número de endereço válido.");
            System.out.println("---------------------------");
            return false;
        }
        this.numero = numero;
        return true;
    }

    public String getComplemento() {
        return complemento;
    }

    public boolean setComplemento(String complemento) {
        // VALIDACAO DE CAMPO VAZIO
        if (complemento.isBlank()) {
            System.out.printf("\nINFO: Preenchendo complemento em sistema como \"N/A\" (não aplicável).\n");
            this.complemento = "N/A";
            return true;
        }

        // VALIDACAO DE COMPLEMENTO MUITO GRANDE
        if (complemento.length() > 1500) {
            System.out.println(
                    "\n>>> ERRO: A descrição inserida para o evento é muito grande. Por favor, insira uma descrição com menos de 1500 caracteres.");
                    System.out.println("---------------------------");
            return false;
        }

        // VALIDACAO DE CARACTERE ILEGAL
        final String regex = "\\|";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(complemento);

        if (matcher.find()) {
            System.out.println("Caractere inválido \"|\" inserido. Remova o caractere e tente novamente.");
            System.out.println("---------------------------");
            System.out.println();
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

        // VALIDACAO VIA REGEX
        if (bairro.matches(
                "^[A-Za-zÀ-ÖØ-öø-ÿ\\s'-]+$")) {
            this.bairro = bairro;
            return true;
        } else {
            System.out.println("\n>>> ERRO: Digite um bairro válido.");
            System.out.println("---------------------------");
            return false;
        }

    }

    public String getCidade() {
        return cidade;
    }

    public boolean setCidade(String cidade) {

        // VALIDACAO VIA REGEX
        if (cidade.matches(
                "^[A-Za-zÀ-ÖØ-öø-ÿ\s]+$")) {
            this.cidade = cidade;
            return true;
        } else {
            System.out.println("\n>>> ERRO: Digite uma cidade válida.");
            System.out.println("---------------------------");
            return false;
        }
    }

    public String getUf() {
        return uf;
    }

    public boolean setUf(String uf) {

        // VALIDACAO DE UFS DELIMITADOS
        for (UF c : UF.values()) {
            if (uf.toUpperCase().equals(c.name())) {
                this.uf = uf.toUpperCase();
                return true;
            }
        }
        System.out.println("\n>>> ERRO: Digite um UF de estado válido.");
        System.out.println("---------------------------");
        return false;

    }

    public String getCep() {
        return cep;
    }

    public boolean setCep(String cep) {

        // VALIDACAO VIA REGEX
        if (cep.matches(
                "^\\d{8}$")) {
            this.cep = cep;
            return true;
        } else {
            System.out.println("\n>>> ERRO: Digite um CEP válido (sem pontuação).");
            System.out.println("---------------------------");
            return false;
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean setCategoria(String category) {

        // VALIDACAO DE CATEGORIAS DELIMITADAS

        if (category.toUpperCase().equals("ANIVERSÁRIO")) {
            category = "ANIVERSARIO";
        }

        for (Categoria c : Categoria.values()) {
            if (category.toUpperCase().equals(c.name())) {
                this.categoria = category;
                return true;
            }
        }
        System.out.println("\n>>> ERRO: Insira uma categória válida dentre as categorias informadas.");
        System.out.println("---------------------------");
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

        // METODO DE CRIACAO DE EVENTOS CHAMADO PELA CLASSE MAIN

        boolean validacao = validarCamposEvento(sc);

        if (validacao == false) {
            System.out.println("\n>>> INFO: EVENTO NÃO CADASTRADO.\nVoltando para a tela inicial...");
            return false;
        } else {
            armazenarEvento();
            System.out.println("\n>>> INFO: EVENTO CADASTRADO COM SUCESSO!\nAperte enter para continuar!");
            sc.nextLine();
            return true;
        }
    }

    private boolean validarCamposEvento(Scanner sc) {

        // METODO DE VALIDACAO DE TODOS OS CAMPOS INSERIDOS PELO USER

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
        char confirmInput = 'N';
        char tentarNovamente = 'S';

        while ((!nomeValid || !logradouroValid || !numeroValid || !complementoValid || !bairroValid || !cidadeValid
                || !ufValid || !cepValid || !categoriaValid || !dataHoraValid || !duracaoValid || !descricaoValid)
                && tentarNovamente == 'S') {
            
            do {
            try{
                System.out.println(System.lineSeparator().repeat(50));
            System.out.println(">>> CONFIRMAÇÃO: Tem certeza que deseja criar um novo evento? (S/N)");
            confirmInput = sc.nextLine().toUpperCase().charAt(0);
            } catch (Exception e) {
            confirmInput = Character.MIN_VALUE;
            }
        } while (confirmInput != 'N' && confirmInput != 'S');
            
            if (confirmInput == 'N') {
                break;
            }

            System.out.println(System.lineSeparator().repeat(50));
            System.out.println(">>> INFO: Insira as informações pedidas abaixo para cadastrar um evento:");
            System.out.println();
            System.out.println();

            // NOME DO EVENTO
        
            do {
            
            System.out.printf("===> Nome do evento:\n\n(EXEMPLO: Aniversário de 15 anos da Ana)\n--> ");
            nomeValid = this.setNome(sc.nextLine());
            } while (!nomeValid);
                
            // LOGRADOURO
            do {

                System.out.println("\n\n===> Logradouro: ");
                System.out.println();
                System.out.println("(EXEMPLO: Avenida das Nações Unidas | Rodovia Raposo Tavares)");
                System.out.printf("--> ");
                logradouroValid = this.setLogradouro(sc.nextLine());
            } while (!logradouroValid);


            // NÚMERO
            do {
                try {
                    System.out.printf("\n\n===> Número:\n--> ");
                    numeroValid = this.setNumero(Integer.parseInt(sc.nextLine()));

                } catch (Exception e) {
                    System.out.println("\n>>> ERRO: Digite um número válido.");
                    System.out.println("---------------------------");
                }
            } while (!numeroValid);

            // COMPLEMENTO
            do {
                System.out.printf("\n\n===> Complemento:\n\n(aperte Enter caso não haja complemento)\n--> ");
                complementoValid = this.setComplemento(sc.nextLine());
            } while (!complementoValid);

            // BAIRRO
            do {
                System.out.printf("\n\n===> Bairro:\n--> ");
                bairroValid = this.setBairro(sc.nextLine());
            } while (!bairroValid);

            // CIDADE
            do {
                System.out.printf("\n\n===> Cidade:\n--> ");
                cidadeValid = this.setCidade(sc.nextLine());
            } while (!cidadeValid);

            // UF
            do {
                System.out.printf("\n\n===> UF:\n\n(ex: SP)\n--> ");
                ufValid = this.setUf(sc.nextLine());
            } while (!ufValid);

            // CEP
            do {
                System.out.printf("\n\n===> CEP (sem pontuação):\n\n(ex: 01313020)\n--> ");
                cepValid = this.setCep(sc.nextLine());
            } while (!cepValid);

            // CATEGORIA
            do {
                System.out.println("\n\n===> Categoria do evento: ");
                System.out.println();
                System.out.println("Selecione uma: ANIVERSÁRIO, CASAMENTO, FORMATURA, BALADA, ESPORTIVO, PALESTRA, BENEFICENTE");
                System.out.println();
                System.out.printf("--> ");
                categoriaValid = this.setCategoria(sc.nextLine().toUpperCase());
            } while (!categoriaValid);

            // DATA E HORA DO EVENTO
            do {
                LocalDateTime horaSugerida = LocalDateTime.now().plusHours(25);
                System.out.println("\n\n===> Data e hora do evento:\n");
                System.out.println("(ex: "+horaSugerida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))+"):\n");
                System.out.printf("--> ");
                String dataHoraInserida = sc.nextLine();
                try {
                    LocalDateTime dataHoraConvertida = LocalDateTime.parse(dataHoraInserida,
                            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

                    dataHoraValid = this.setDataHora(dataHoraConvertida);
                } catch (Exception e) {
                    System.out.println(
                            "\n>>> ERRO: Formato digitado inválido. Digite um formato válido e tente novamente.");
                }

            } while (!dataHoraValid);


            // DURACAO EM MIN
            do {

                try {
                    System.out.printf("\n\n===> Duração (em minutos):\n --> ");
                    duracaoValid = this.setDuracao(Integer.parseInt(sc.nextLine()));

                } catch (Exception e) {
                    System.out.println("\n>>> ERRO: Digite um número válido.");
                    System.out.println("---------------------------");
                }

            } while (!duracaoValid);

            // DESCRICAO
            do {
                System.out.printf("\n\n===> Descrição:\n\n(máx. 300 caracteres)\n--> ");
                descricaoValid = this.setDescricao(sc.nextLine());
            } while (!descricaoValid);


            // APRESENTAR OPCAO DE TENTAR NOVAMENTE CASO UMA DAS VALIDACOES FALHE
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

        // VALIDACAO FINAL DOS CAMPOS
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

    // METODO PARA CRIAR ARQUIVO EVENTS.DATA CASO NAO EXISTA, OU USAR JA EXISTENTE
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

    // METODO PARA ARMAZENAR DADOS INSERIDOS NO ARQUIVO
    private void armazenarEvento() {
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

    // VALIDACAO DE HORARIO DO EVENTO
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
                            ">> >>> ERRO: HORÁRIO PARA EVENTO INDISPONÍVEL.");
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