package controller;

import model.Emprestimo;
import model.Livro;
import model.Usuario;
import util.Logger;
import util.Repositorio;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoController {

    private List<Emprestimo> emprestimos;
    private Repositorio<Emprestimo> repositorio;
    private UsuarioController usuarioController;
    private LivroController livroController;
    private int proximoId;

    public EmprestimoController(UsuarioController usuarioController, LivroController livroController) {
        repositorio = new Repositorio<>("dados/emprestimos.dat");
        emprestimos = repositorio.carregar();
        this.usuarioController = usuarioController;
        this.livroController = livroController;
        proximoId = 1;
        for (Emprestimo e : emprestimos) {
            if (e.getId() >= proximoId) proximoId = e.getId() + 1;
        }
    }

    public boolean realizarEmprestimo(int idUsuario, int idLivro) {
        Usuario usuario = usuarioController.buscarPorId(idUsuario);
        if (usuario == null) { System.out.println("Usuario nao encontrado."); return false; }

        Livro livro = livroController.buscarPorId(idLivro);
        if (livro == null) { System.out.println("Livro nao encontrado."); return false; }

        if (!livro.isDisponivel()) { System.out.println("Nao ha exemplares disponiveis."); return false; }

        int ativos = contarAtivos(idUsuario);
        if (!usuarioController.podeEmprestar(idUsuario, ativos)) {
            System.out.println("Usuario atingiu o limite de emprestimos.");
            return false;
        }

        emprestimos.add(new Emprestimo(proximoId++, idUsuario, idLivro));
        livroController.retirarExemplar(idLivro);
        repositorio.salvar(emprestimos);
        Logger.registrar("INFO", "Emprestimo realizado: " + usuario.getNome() + " -> " + livro.getTitulo());
        return true;
    }

    public boolean registrarDevolucao(int idEmprestimo) {
        Emprestimo emp = buscarPorId(idEmprestimo);
        if (emp == null) { System.out.println("Emprestimo nao encontrado."); return false; }
        if (emp.getStatus() == Emprestimo.Status.DEVOLVIDO) {
            System.out.println("Esse emprestimo ja foi devolvido.");
            return false;
        }
        emp.registrarDevolucao();
        livroController.devolverExemplar(emp.getIdLivro());
        repositorio.salvar(emprestimos);
        Logger.registrar("INFO", "Devolucao registrada: emprestimo #" + idEmprestimo);
        return true;
    }

    public Emprestimo buscarPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public List<Emprestimo> listarTodos() {
        for (Emprestimo e : emprestimos) e.atualizarStatus();
        return new ArrayList<>(emprestimos);
    }

    public List<Emprestimo> listarAtivos() {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            e.atualizarStatus();
            if (e.getStatus() != Emprestimo.Status.DEVOLVIDO) resultado.add(e);
        }
        return resultado;
    }

    public List<Emprestimo> listarAtrasados() {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            e.atualizarStatus();
            if (e.estaAtrasado()) resultado.add(e);
        }
        return resultado;
    }

    public List<Emprestimo> listarPorUsuario(int idUsuario) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getIdUsuario() == idUsuario) { e.atualizarStatus(); resultado.add(e); }
        }
        return resultado;
    }

    public boolean cancelar(int id) {
        Emprestimo emp = buscarPorId(id);
        if (emp == null) { System.out.println("Emprestimo nao encontrado."); return false; }
        if (emp.getStatus() == Emprestimo.Status.DEVOLVIDO) {
            System.out.println("Nao e possivel cancelar um emprestimo ja devolvido.");
            return false;
        }
        livroController.devolverExemplar(emp.getIdLivro());
        emprestimos.remove(emp);
        repositorio.salvar(emprestimos);
        Logger.registrar("INFO", "Emprestimo cancelado: #" + id);
        return true;
    }

    private int contarAtivos(int idUsuario) {
        int count = 0;
        for (Emprestimo e : emprestimos) {
            if (e.getIdUsuario() == idUsuario && e.getStatus() != Emprestimo.Status.DEVOLVIDO) count++;
        }
        return count;
    }
}
