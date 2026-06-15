import controller.EmprestimoController;
import controller.FuncionarioController;
import controller.LivroController;
import controller.UsuarioController;
import view.EmprestimoView;
import view.FuncionarioView;
import view.LivroView;
import view.UsuarioView;
import util.Logger;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new File("dados").mkdirs();
        Logger.registrar("Sistema iniciado");

        Scanner scanner = new Scanner(System.in);

        LivroController livroCtrl = new LivroController();
        UsuarioController usuarioCtrl = new UsuarioController();
        FuncionarioController funcionarioCtrl = new FuncionarioController();
        EmprestimoController emprestimoCtrl = new EmprestimoController(usuarioCtrl, livroCtrl);

        LivroView livroView = new LivroView(livroCtrl, scanner);
        UsuarioView usuarioView = new UsuarioView(usuarioCtrl, scanner);
        FuncionarioView funcionarioView = new FuncionarioView(funcionarioCtrl, scanner);
        EmprestimoView emprestimoView = new EmprestimoView(emprestimoCtrl, usuarioCtrl, livroCtrl, scanner);

        System.out.println("=== Sistema de Biblioteca ===");

        int opcao;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("1. Livros");
            System.out.println("2. Usuarios");
            System.out.println("3. Emprestimos");
            System.out.println("4. Funcionarios");
            System.out.println("0. Sair");
            System.out.print("Opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1: livroView.exibirMenu(); break;
                case 2: usuarioView.exibirMenu(); break;
                case 3: emprestimoView.exibirMenu(); break;
                case 4: funcionarioView.exibirMenu(); break;
                case 0: System.out.println("Ate logo!"); Logger.registrar("Sistema encerrado"); break;
                default: System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
