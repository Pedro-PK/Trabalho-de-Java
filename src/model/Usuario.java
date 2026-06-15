package model;

public class Usuario extends Pessoa {

    private static final long serialVersionUID = 1L;

    private String cpf;
    private int limiteEmprestimos;
    private boolean ativo;

    public Usuario(int id, String nome, String email, String telefone, String cpf) {
        super(id, nome, email, telefone);
        this.cpf = cpf;
        this.limiteEmprestimos = 3;
        this.ativo = true;
    }

    @Override
    public String getTipo() {
        return "Usuario";
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | CPF: " + cpf + " | Ativo: " + (ativo ? "Sim" : "Nao");
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public int getLimiteEmprestimos() { return limiteEmprestimos; }
    public void setLimiteEmprestimos(int limite) { this.limiteEmprestimos = limite; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
