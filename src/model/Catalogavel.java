package model;

public interface Catalogavel {

    String getCodigo();
    String getTitulo();
    String getCategoria();
    boolean isDisponivel();

    default String resumo() {
        return "[" + getCodigo() + "] " + getTitulo() + " (" + getCategoria() + ") - Disponivel: " + (isDisponivel() ? "Sim" : "Nao");
    }
}
