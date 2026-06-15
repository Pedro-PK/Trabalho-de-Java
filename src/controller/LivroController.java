package controller;

import model.Livro;
import util.Logger;
import util.Repositorio;

import java.util.ArrayList;
import java.util.List;

public class LivroController {

    private List<Livro> livros;
    private Repositorio<Livro> repositorio;
    private int proximoId;

    public LivroController() {
        repositorio = new Repositorio<>("dados/livros.dat");
        livros = repositorio.carregar();
        proximoId = 1;
        for (Livro l : livros) {
            if (l.getId() >= proximoId) proximoId = l.getId() + 1;
        }
    }

    public boolean cadastrar(String codigo, String titulo, String autor, String categoria, int ano, int quantidade) {
        if (buscarPorCodigo(codigo) != null) {
            System.out.println("Ja existe um livro com esse codigo.");
            return false;
        }
        livros.add(new Livro(proximoId++, codigo, titulo, autor, categoria, ano, quantidade));
        repositorio.salvar(livros);
        Logger.registrar("INFO", "Livro cadastrado: " + titulo);
        return true;
    }

    public Livro buscarPorId(int id) {
        for (Livro l : livros) {
            if (l.getId() == id) return l;
        }
        return null;
    }

    public Livro buscarPorCodigo(String codigo) {
        for (Livro l : livros) {
            if (l.getCodigo().equalsIgnoreCase(codigo)) return l;
        }
        return null;
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros) {
            if (l.getTitulo().toLowerCase().contains(titulo.toLowerCase())) resultado.add(l);
        }
        return resultado;
    }

    public List<Livro> listarTodos() {
        return new ArrayList<>(livros);
    }

    public List<Livro> listarDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro l : livros) {
            if (l.isDisponivel()) disponiveis.add(l);
        }
        return disponiveis;
    }

    public boolean atualizar(int id, String titulo, String autor, String categoria, int ano, int quantidade) {
        Livro l = buscarPorId(id);
        if (l == null) { System.out.println("Livro nao encontrado."); return false; }
        l.setTitulo(titulo);
        l.setAutor(autor);
        l.setCategoria(categoria);
        l.setAnoPublicacao(ano);
        l.setQuantidadeTotal(quantidade);
        repositorio.salvar(livros);
        Logger.registrar("INFO", "Livro atualizado: ID " + id);
        return true;
    }

    public boolean remover(int id) {
        Livro l = buscarPorId(id);
        if (l == null) { System.out.println("Livro nao encontrado."); return false; }
        livros.remove(l);
        repositorio.salvar(livros);
        Logger.registrar("INFO", "Livro removido: " + l.getTitulo());
        return true;
    }

    public boolean retirarExemplar(int id) {
        Livro l = buscarPorId(id);
        if (l == null) return false;
        boolean ok = l.retirarExemplar();
        if (ok) repositorio.salvar(livros);
        return ok;
    }

    public void devolverExemplar(int id) {
        Livro l = buscarPorId(id);
        if (l != null) { l.devolverExemplar(); repositorio.salvar(livros); }
    }
}
