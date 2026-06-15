package view;

import controller.EmprestimoController;
import controller.LivroController;
import controller.UsuarioController;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class EmprestimoView {

    private EmprestimoController controller;
    private UsuarioController usuarioController;
    private LivroController livroController;
    private Scanner scanner;

    public EmprestimoView(EmprestimoController controller, UsuarioController uc, LivroController lc, Scanner scanner) {
        this.controller = controller;
        this.usuarioController = uc;
        this.livroController = lc;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Emprestimos ---");
            System.out.println("1. Realizar emprestimo");
            System.out.println("2. Registrar devolucao");
            System.out.println("3. Listar todos");
            System.out.println("4. Listar ativos");
            System.out.println("5. Listar atrasados");
            System.out.println("6. Emprestimos de um usuario");
            System.out.println("7. Cancelar emprestimo");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt();

            switch (opcao) {
                case 1: realizarEmprestimo(); break;
                case 2: registrarDevolucao(); break;
                case 3: listar(controller.listarTodos()); break;
                case 4: listar(controller.listarAtivos()); break;
                case 5: listar(controller.listarAtrasados()); break;
                case 6: porUsuario(); break;
                case 7: cancelar(); break;
                case 0: break;
                default: System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private void realizarEmprestimo() {
        System.out.print("ID do usuario: "); int idU = lerInt();
        Usuario u = usuarioController.buscarPorId(idU);
        if (u == null) { System.out.println("Usuario nao encontrado."); return; }
        System.out.println("Usuario: " + u.getNome());

        System.out.print("ID do livro: "); int idL = lerInt();
        Livro l = livroController.buscarPorId(idL);
        if (l == null) { System.out.println("Livro nao encontrado."); return; }
        System.out.println("Livro: " + l.getTitulo() + " | Disponiveis: " + l.getQuantidadeDisponivel());

        if (controller.realizarEmprestimo(idU, idL)) {
            System.out.println("Emprestimo realizado! Prazo de devolucao: 14 dias.");
        }
    }

    private void registrarDevolucao() {
        System.out.print("ID do emprestimo: "); int id = lerInt();
        Emprestimo e = controller.buscarPorId(id);
        if (e == null) { System.out.println("Nao encontrado."); return; }
        System.out.println(e);
        System.out.print("Confirmar devolucao? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            if (controller.registrarDevolucao(id)) {
                System.out.println("Devolucao registrada!");
                Emprestimo atualizado = controller.buscarPorId(id);
                if (atualizado != null && atualizado.getMulta() > 0) {
                    System.out.printf("Multa por atraso: R$ %.2f%n", atualizado.getMulta());
                }
            }
        }
    }

    private void porUsuario() {
        System.out.print("ID do usuario: "); int id = lerInt();
        Usuario u = usuarioController.buscarPorId(id);
        if (u == null) { System.out.println("Usuario nao encontrado."); return; }
        System.out.println("Emprestimos de " + u.getNome() + ":");
        listar(controller.listarPorUsuario(id));
    }

    private void cancelar() {
        System.out.print("ID do emprestimo: "); int id = lerInt();
        Emprestimo e = controller.buscarPorId(id);
        if (e == null) { System.out.println("Nao encontrado."); return; }
        System.out.println(e);
        System.out.print("Confirmar cancelamento? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            if (controller.cancelar(id)) System.out.println("Cancelado!");
        }
    }

    private void listar(List<Emprestimo> lista) {
        if (lista.isEmpty()) { System.out.println("Nenhum emprestimo encontrado."); return; }
        System.out.println();
        for (Emprestimo e : lista) System.out.println(e);
    }

    private int lerInt() {
        while (true) {
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Digite um numero: "); }
        }
    }
}
