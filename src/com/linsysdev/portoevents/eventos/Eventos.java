package com.linsysdev.portoevents.eventos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
    private List<String> participantes;

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
                "^[\\p{L}0-9\\s.,'’\"“”?!:;()-]+$")) {
            this.nome = nome;
            return true;
        } else {
            System.out.println("Insira um nome válido para o evento.");
            return false;
        }
    }

    public String getLogradouro() {
        return logradouro;
    }

    public boolean setLogradouro(String logradouro) {

        String logradouroCapitalized = Arrays.stream(logradouro.split("\\s"))
                .map(palavra -> Character.toTitleCase(palavra.charAt(0)) + palavra.substring(1))
                .collect(Collectors.joining(" "));

        if (logradouroCapitalized.matches(
                "^(Rua|Avenida|Travessa|Alameda|Praça|Estrada|Rodovia)\s+[A-Za-zÀ-ÖØ-öø-ÿ\s'-]+$")) {
            this.logradouro = logradouro;
            return true;
        } else {
            System.out.println(
                    "ERRO: Insira um logradouro válido, começando com uma das possibilidades abaixo:\nRua|Avenida|Travessa|Alameda|Praça|Estrada|Rodovia");
            System.out.println("EXEMPLO: Avenida das Nações Unidas, 1650");
            return false;
        }

    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public boolean setDataHora(LocalDateTime dataHora) {
        LocalDateTime dataMinima = LocalDateTime.now().plusHours(24);
        if (dataHora.isBefore(dataMinima)) {
            System.out.println("ERRO: O evento precisa ser marcado com antecedência mínima de 24h.");
            return false;
        }

        try {
            this.dataHora = dataHora;
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
            return false;
        }

        return true;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public boolean setDuracao(Integer duracao) {
        if (duracao < 30) {
            System.out.println("ERRO: A duração mínima do evento deve ser igual ou superior à 30 minutos.");
            return false;
        } else if (duracao > 360) {
            System.out.println(
                    "ERRO: No momento, realizamos eventos com duração de até 6 horas (360 minutos).\n Por favor, insira uma duração menor.");
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
            System.out.println("ERRO: Insira uma descrição para o evento.");
            return false;
        }
        if (descricao.length() > 1500) {
            System.out.println(
                    "ERRO: A descrição inserida para o evento é muito grande. Por favor, insira uma descrição com menos de 1500 caracteres.");
            return false;
        }
        this.descricao = descricao;
        return true;
    }

    public Integer getNumero() {
        return numero;
    }

    public boolean setNumero(Integer numero) {
        if (numero > 99999) {
            System.out.println("ERRO: Digite um número de endereço válido.");
            return false;
        }
        this.numero = numero;
        return true;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        if (complemento.isBlank()) {
            System.out.printf("INFO: Complemento não informado; preenchendo em sistema como \"N/A\".");
            this.complemento = "N/A";
        } else {
            this.complemento = complemento;
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
            System.out.println("ERRO: Digite um bairro válido.");
            return false;
        }

    }

    public String getCidade() {
        return cidade;
    }

    public boolean setCidade(String cidade) {

        if (cidade.matches(
                "^[A-Za-zÀ-ÖØ-öø-ÿ\\s'-]+$")) {
            this.cidade = cidade;
            return true;
        } else {
            System.out.println("ERRO: Digite uma cidade válida.");
            return false;
        }
    }

    public String getUf() {
        return uf;
    }

    public boolean setUf(String uf) {

        for (UF c : UF.values()) {
            if (uf.equals(c)) {
                this.uf = uf;
                return true;
            }
        }
        System.out.println("ERRO: Digite um UF de estado válido.");
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
            System.out.println("ERRO: Digite um CEP válido (sem pontuação).");
            return false;
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean setCategoria(String category) {

        if (categoria.toUpperCase().equals("ANIVERSÁRIO")) {
            category = "ANIVERSARIO";
        }

        for (Categoria c : Categoria.values()) {
            if (categoria.toUpperCase().equals(c)) {
                this.categoria = category;
                return true;
            }
        }
        System.out.println("ERRO: Insira uma categória válida dentre as categorias abaixo: ");
        return false;

    }

    public Eventos() {
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public void criarEvento() {

    }

}
