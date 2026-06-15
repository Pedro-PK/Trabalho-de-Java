package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Status { ATIVO, DEVOLVIDO, ATRASADO }

    private int id;
    private int idUsuario;
    private int idLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private Status status;
    private double multa;

    private static final int PRAZO_DIAS = 14;
    private static final double MULTA_POR_DIA = 0.50;

    public Emprestimo(int id, int idUsuario, int idLivro) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = LocalDate.now().plusDays(PRAZO_DIAS);
        this.status = Status.ATIVO;
        this.multa = 0;
    }

    public void registrarDevolucao() {
        this.dataDevolucao = LocalDate.now();
        if (dataDevolucao.isAfter(dataPrevistaDevolucao)) {
            long atraso = dataDevolucao.toEpochDay() - dataPrevistaDevolucao.toEpochDay();
            this.multa = atraso * MULTA_POR_DIA;
        }
        this.status = Status.DEVOLVIDO;
    }

    public boolean estaAtrasado() {
        return status == Status.ATIVO && LocalDate.now().isAfter(dataPrevistaDevolucao);
    }

    public void atualizarStatus() {
        if (estaAtrasado()) status = Status.ATRASADO;
    }

    private String fmt(LocalDate d) {
        return d == null ? "---" : d.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int id) { this.idUsuario = id; }

    public int getIdLivro() { return idLivro; }
    public void setIdLivro(int id) { this.idLivro = id; }

    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public double getMulta() { return multa; }

    @Override
    public String toString() {
        return "Emprestimo #" + id
                + " | Usuario ID: " + idUsuario
                + " | Livro ID: " + idLivro
                + " | Retirada: " + fmt(dataEmprestimo)
                + " | Prazo: " + fmt(dataPrevistaDevolucao)
                + " | Devolucao: " + fmt(dataDevolucao)
                + " | Status: " + status
                + (multa > 0 ? " | Multa: R$ " + String.format("%.2f", multa) : "");
    }
}
