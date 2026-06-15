package view;

import controller.LivroController;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class LivroView {

    private LivroController controller;
    private Scanner scanner;

    public LivroView(LivroController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Livros ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por titulo");
            System.out.println("4. Atualizar");
            System.out.println("5. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt();

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: listar(controller.listarTodos()); break;
                case 3: buscarTitulo(); break;
                case 4: atualizar(); break;
                case 5: remover(); break;
                case 0: break;
                default: System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.print("Codigo: "); String codigo = scanner.nextLine();
        System.out.print("Titulo: "); String titulo = scanner.nextLine();
        System.out.print("Autor: "); String autor = scanner.nextLine();
        System.out.print("Categoria: "); String categoria = scanner.nextLine();
        System.out.print("Ano: "); int ano = lerInt();
        System.out.print("Quantidade: "); int qtd = lerInt();

        if (controller.cadastrar(codigo, titulo, autor, categoria, ano, qtd)) {
            System.out.println("Livro cadastrado!");
        }
    }

    private void buscarTitulo() {
        System.out.print("Titulo (ou parte): ");
        String titulo = scanner.nextLine();
        listar(controller.buscarPorTitulo(titulo));
    }

    private void atualizar() {
        System.out.print("ID do livro: "); int id = lerInt();
        Livro l = controller.buscarPorId(id);
        if (l == null) { System.out.println("Nao encontrado."); return; }
        System.out.println("Editando: " + l.getTitulo());

        System.out.print("Novo titulo [" + l.getTitulo() + "]: ");
        String titulo = scanner.nextLine(); if (titulo.isEmpty()) titulo = l.getTitulo();

        System.out.print("Novo autor [" + l.getAutor() + "]: ");
        String autor = scanner.nextLine(); if (autor.isEmpty()) autor = l.getAutor();

        System.out.print("Nova categoria [" + l.getCategoria() + "]: ");
        String cat = scanner.nextLine(); if (cat.isEmpty()) cat = l.getCategoria();

        System.out.print("Novo ano [" + l.getAnoPublicacao() + "]: ");
        String anoStr = scanner.nextLine();
        int ano = anoStr.isEmpty() ? l.getAnoPublicacao() : Integer.parseInt(anoStr);

        System.out.print("Nova quantidade total [" + l.getQuantidadeTotal() + "]: ");
        String qtdStr = scanner.nextLine();
        int qtd = qtdStr.isEmpty() ? l.getQuantidadeTotal() : Integer.parseInt(qtdStr);

        if (controller.atualizar(id, titulo, autor, cat, ano, qtd)) System.out.println("Atualizado!");
    }

    private void remover() {
        System.out.print("ID do livro: "); int id = lerInt();
        Livro l = controller.buscarPorId(id);
        if (l == null) { System.out.println("Nao encontrado."); return; }
        System.out.print("Remover '" + l.getTitulo() + "'? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            if (controller.remover(id)) System.out.println("Removido!");
        }
    }

    private void listar(List<Livro> livros) {
        if (livros.isEmpty()) { System.out.println("Nenhum livro encontrado."); return; }
        System.out.println();
        for (Livro l : livros) System.out.println(l);
    }

    private int lerInt() {
        while (true) {
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Digite um numero: "); }
        }
    }
}
