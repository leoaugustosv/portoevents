package com.linsysdev.portoevents.eventos;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Eventos {

    private String nome;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private UF uf;
    private String cep;
    private Categoria categoria;
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
            return false;
        }

    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Eventos(String nome, String logradouro, Integer numero, String complemento, String bairro, String cidade,
            UF uf, String cep, Categoria categoria, LocalDateTime dataHora, Integer duracao, String descricao,
            List<String> participantes) {
        this.nome = nome;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.categoria = categoria;
        this.dataHora = dataHora;
        this.duracao = duracao;
        this.descricao = descricao;
        this.participantes = participantes;
    }

    public Eventos(String nome, String logradouro, Integer numero, String bairro, String cidade,
            UF uf, String cep, Categoria categoria, LocalDateTime dataHora, Integer duracao,
            List<String> participantes) {
        this.nome = nome;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = "N/A";
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.categoria = categoria;
        this.dataHora = dataHora;
        this.duracao = duracao;
        this.descricao = "Esse evento não possui descrição.";
        this.participantes = participantes;
    }

    public Eventos() {
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void criarEvento() {

    }

}
