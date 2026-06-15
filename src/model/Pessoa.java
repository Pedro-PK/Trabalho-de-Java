package model;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String email;
    private String telefone;

    public Pessoa(int id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public abstract String getTipo();

    // sobrecarga - sem parâmetro retorna o resumo, com true retorna tudo
    public String getInfo() {
        return "ID: " + id + " | " + nome + " | " + email;
    }

    public String getInfo(boolean completo) {
        if (completo) {
            return getInfo() + " | Tel: " + telefone + " | Tipo: " + getTipo();
        }
        return getInfo();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return getInfo(true);
    }
}
