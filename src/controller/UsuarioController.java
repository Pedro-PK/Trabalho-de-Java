package controller;

import model.Usuario;
import util.Logger;
import util.Repositorio;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private List<Usuario> usuarios;
    private Repositorio<Usuario> repositorio;
    private int proximoId;

    public UsuarioController() {
        repositorio = new Repositorio<>("dados/usuarios.dat");
        usuarios = repositorio.carregar();
        proximoId = 1;
        for (Usuario u : usuarios) {
            if (u.getId() >= proximoId) proximoId = u.getId() + 1;
        }
    }

    public boolean cadastrar(String nome, String email, String telefone, String cpf) {
        if (buscarPorCpf(cpf) != null) {
            System.out.println("Ja existe um usuario com esse CPF.");
            return false;
        }
        usuarios.add(new Usuario(proximoId++, nome, email, telefone, cpf));
        repositorio.salvar(usuarios);
        Logger.registrar("INFO", "Usuario cadastrado: " + nome);
        return true;
    }

    public Usuario buscarPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    public Usuario buscarPorCpf(String cpf) {
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) return u;
        }
        return null;
    }

    public List<Usuario> buscarPorNome(String nome) {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getNome().toLowerCase().contains(nome.toLowerCase())) resultado.add(u);
        }
        return resultado;
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    public boolean atualizar(int id, String nome, String email, String telefone) {
        Usuario u = buscarPorId(id);
        if (u == null) { System.out.println("Usuario nao encontrado."); return false; }
        u.setNome(nome);
        u.setEmail(email);
        u.setTelefone(telefone);
        repositorio.salvar(usuarios);
        Logger.registrar("INFO", "Usuario atualizado: ID " + id);
        return true;
    }

    public boolean remover(int id) {
        Usuario u = buscarPorId(id);
        if (u == null) { System.out.println("Usuario nao encontrado."); return false; }
        usuarios.remove(u);
        repositorio.salvar(usuarios);
        Logger.registrar("INFO", "Usuario removido: " + u.getNome());
        return true;
    }

    public boolean podeEmprestar(int idUsuario, int emprestimosAtivos) {
        Usuario u = buscarPorId(idUsuario);
        return u != null && u.isAtivo() && emprestimosAtivos < u.getLimiteEmprestimos();
    }
}
