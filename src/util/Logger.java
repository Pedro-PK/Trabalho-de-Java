package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String ARQUIVO_LOG = "dados/sistema.log";

    private Logger() {}

    public static void registrar(String mensagem) {
        String hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_LOG, true))) {
            pw.println("[" + hora + "] " + mensagem);
        } catch (IOException e) {
            System.err.println("Nao foi possivel escrever no log: " + e.getMessage());
        }
    }

    public static void registrar(String tipo, String mensagem) {
        registrar("[" + tipo + "] " + mensagem);
    }
}
