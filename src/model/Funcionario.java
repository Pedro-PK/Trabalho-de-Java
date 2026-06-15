package model;

public class Funcionario extends Pessoa {

    private static final long serialVersionUID = 1L;

    private String matricula;
    private String cargo;
    private double salario;

    public Funcionario(int id, String nome, String email, String telefone, String matricula, String cargo, double salario) {
        super(id, nome, email, telefone);
        this.matricula = matricula;
        this.cargo = cargo;
        this.salario = salario;
    }

    @Override
    public String getTipo() {
        return "Funcionario";
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Matricula: " + matricula + " | Cargo: " + cargo;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
