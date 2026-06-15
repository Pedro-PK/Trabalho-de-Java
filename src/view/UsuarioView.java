package view;

import controller.UsuarioController;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class UsuarioView {

    private UsuarioController controller;
    private Scanner scanner;

    public UsuarioView(UsuarioController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Usuarios ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por nome");
            System.out.println("4. Atualizar");
            System.out.println("5. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt();

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: listar(controller.listarTodos()); break;
                case 3: buscarNome(); break;
                case 4: atualizar(); break;
                case 5: remover(); break;
                case 0: break;
                default: System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Telefone: "); String tel = scanner.nextLine();
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        if (controller.cadastrar(nome, email, tel, cpf)) System.out.println("Usuario cadastrado!");
    }

    private void buscarNome() {
        System.out.print("Nome (ou parte): ");
        listar(controller.buscarPorNome(scanner.nextLine()));
    }

    private void atualizar() {
        System.out.print("ID do usuario: "); int id = lerInt();
        Usuario u = controller.buscarPorId(id);
        if (u == null) { System.out.println("Nao encontrado."); return; }

        System.out.print("Novo nome [" + u.getNome() + "]: ");
        String nome = scanner.nextLine(); if (nome.isEmpty()) nome = u.getNome();

        System.out.print("Novo email [" + u.getEmail() + "]: ");
        String email = scanner.nextLine(); if (email.isEmpty()) email = u.getEmail();

        System.out.print("Novo telefone [" + u.getTelefone() + "]: ");
        String tel = scanner.nextLine(); if (tel.isEmpty()) tel = u.getTelefone();

        if (controller.atualizar(id, nome, email, tel)) System.out.println("Atualizado!");
    }

    private void remover() {
        System.out.print("ID do usuario: "); int id = lerInt();
        Usuario u = controller.buscarPorId(id);
        if (u == null) { System.out.println("Nao encontrado."); return; }
        System.out.print("Remover '" + u.getNome() + "'? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            if (controller.remover(id)) System.out.println("Removido!");
        }
    }

    private void listar(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) { System.out.println("Nenhum usuario encontrado."); return; }
        System.out.println();
        for (Usuario u : usuarios) System.out.println(u);
    }

    private int lerInt() {
        while (true) {
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Digite um numero: "); }
        }
    }
}
