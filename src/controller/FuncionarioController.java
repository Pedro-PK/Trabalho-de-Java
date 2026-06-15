package controller;

import model.Funcionario;
import util.Logger;
import util.Repositorio;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    private List<Funcionario> funcionarios;
    private Repositorio<Funcionario> repositorio;
    private int proximoId;

    public FuncionarioController() {
        repositorio = new Repositorio<>("dados/funcionarios.dat");
        funcionarios = repositorio.carregar();
        proximoId = 1;
        for (Funcionario f : funcionarios) {
            if (f.getId() >= proximoId) proximoId = f.getId() + 1;
        }
    }

    public boolean cadastrar(String nome, String email, String telefone, String matricula, String cargo, double salario) {
        if (buscarPorMatricula(matricula) != null) {
            System.out.println("Ja existe um funcionario com essa matricula.");
            return false;
        }
        funcionarios.add(new Funcionario(proximoId++, nome, email, telefone, matricula, cargo, salario));
        repositorio.salvar(funcionarios);
        Logger.registrar("INFO", "Funcionario cadastrado: " + nome);
        return true;
    }

    public Funcionario buscarPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) return f;
        }
        return null;
    }

    public Funcionario buscarPorMatricula(String matricula) {
        for (Funcionario f : funcionarios) {
            if (f.getMatricula().equalsIgnoreCase(matricula)) return f;
        }
        return null;
    }

    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios);
    }

    public boolean atualizar(int id, String nome, String email, String telefone, String cargo, double salario) {
        Funcionario f = buscarPorId(id);
        if (f == null) { System.out.println("Funcionario nao encontrado."); return false; }
        f.setNome(nome);
        f.setEmail(email);
        f.setTelefone(telefone);
        f.setCargo(cargo);
        f.setSalario(salario);
        repositorio.salvar(funcionarios);
        Logger.registrar("INFO", "Funcionario atualizado: ID " + id);
        return true;
    }

    public boolean remover(int id) {
        Funcionario f = buscarPorId(id);
        if (f == null) { System.out.println("Funcionario nao encontrado."); return false; }
        funcionarios.remove(f);
        repositorio.salvar(funcionarios);
        Logger.registrar("INFO", "Funcionario removido: " + f.getNome());
        return true;
    }
}
