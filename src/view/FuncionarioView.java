package view;

import controller.FuncionarioController;
import model.Funcionario;

import java.util.List;
import java.util.Scanner;

public class FuncionarioView {

    private FuncionarioController controller;
    private Scanner scanner;

    public FuncionarioView(FuncionarioController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Funcionarios ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por matricula");
            System.out.println("4. Atualizar");
            System.out.println("5. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt();

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: listar(controller.listarTodos()); break;
                case 3: buscarMatricula(); break;
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
        System.out.print("Matricula: "); String mat = scanner.nextLine();
        System.out.print("Cargo: "); String cargo = scanner.nextLine();
        System.out.print("Salario: "); double sal = lerDouble();
        if (controller.cadastrar(nome, email, tel, mat, cargo, sal)) System.out.println("Funcionario cadastrado!");
    }

    private void buscarMatricula() {
        System.out.print("Matricula: ");
        Funcionario f = controller.buscarPorMatricula(scanner.nextLine());
        if (f == null) System.out.println("Nao encontrado.");
        else System.out.println(f);
    }

    private void atualizar() {
        System.out.print("ID do funcionario: "); int id = lerInt();
        Funcionario f = controller.buscarPorId(id);
        if (f == null) { System.out.println("Nao encontrado."); return; }

        System.out.print("Novo nome [" + f.getNome() + "]: ");
        String nome = scanner.nextLine(); if (nome.isEmpty()) nome = f.getNome();

        System.out.print("Novo email [" + f.getEmail() + "]: ");
        String email = scanner.nextLine(); if (email.isEmpty()) email = f.getEmail();

        System.out.print("Novo telefone [" + f.getTelefone() + "]: ");
        String tel = scanner.nextLine(); if (tel.isEmpty()) tel = f.getTelefone();

        System.out.print("Novo cargo [" + f.getCargo() + "]: ");
        String cargo = scanner.nextLine(); if (cargo.isEmpty()) cargo = f.getCargo();

        System.out.print("Novo salario [" + f.getSalario() + "]: ");
        String salStr = scanner.nextLine();
        double sal = salStr.isEmpty() ? f.getSalario() : Double.parseDouble(salStr.replace(",", "."));

        if (controller.atualizar(id, nome, email, tel, cargo, sal)) System.out.println("Atualizado!");
    }

    private void remover() {
        System.out.print("ID do funcionario: "); int id = lerInt();
        Funcionario f = controller.buscarPorId(id);
        if (f == null) { System.out.println("Nao encontrado."); return; }
        System.out.print("Remover '" + f.getNome() + "'? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            if (controller.remover(id)) System.out.println("Removido!");
        }
    }

    private void listar(List<Funcionario> lista) {
        if (lista.isEmpty()) { System.out.println("Nenhum funcionario encontrado."); return; }
        System.out.println();
        for (Funcionario f : lista) System.out.println(f.getInfo(true) + " | Salario: R$ " + String.format("%.2f", f.getSalario()));
    }

    private int lerInt() {
        while (true) {
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Digite um numero: "); }
        }
    }

    private double lerDouble() {
        while (true) {
            try { return Double.parseDouble(scanner.nextLine().trim().replace(",", ".")); }
            catch (NumberFormatException e) { System.out.print("Digite um valor: "); }
        }
    }
}
