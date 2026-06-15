package model;

import java.io.Serializable;

public class Livro implements Catalogavel, Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private int anoPublicacao;
    private int quantidadeTotal;
    private int quantidadeDisponivel;

    public Livro(int id, String codigo, String titulo, String autor, String categoria, int anoPublicacao, int quantidade) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeTotal = quantidade;
        this.quantidadeDisponivel = quantidade;
    }

    @Override
    public String getCodigo() { return codigo; }

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public String getCategoria() { return categoria; }

    @Override
    public boolean isDisponivel() {
        return quantidadeDisponivel > 0;
    }

    public boolean retirarExemplar() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
            return true;
        }
        return false;
    }

    public void devolverExemplar() {
        if (quantidadeDisponivel < quantidadeTotal) {
            quantidadeDisponivel++;
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int ano) { this.anoPublicacao = ano; }

    public int getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(int qtd) { this.quantidadeTotal = qtd; }

    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(int qtd) { this.quantidadeDisponivel = qtd; }

    @Override
    public String toString() {
        return resumo() + " | Autor: " + autor + " | Ano: " + anoPublicacao
                + " | Estoque: " + quantidadeDisponivel + "/" + quantidadeTotal;
    }
}
