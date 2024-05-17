package com.linsysdev.portoevents.eventos;

import java.time.LocalDateTime;
import java.util.List;

public class Eventos {

    private String nome;
    private String endereco;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public Eventos(String nome, String endereco, Categoria categoria, LocalDateTime dataHora, Integer duracao,
            String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.dataHora = dataHora;
        this.duracao = duracao;
        this.descricao = descricao;
    }

    public Eventos(String nome, String endereco, Categoria categoria, LocalDateTime dataHora, Integer duracao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.dataHora = dataHora;
        this.duracao = duracao;
        this.descricao = "Esse evento não possui descrição.";
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

}
