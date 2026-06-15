package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repositorio<T extends Serializable> {

    private String arquivo;

    public Repositorio(String arquivo) {
        this.arquivo = arquivo;
    }

    public void salvar(List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            Logger.registrar("ERRO", "Falha ao salvar " + arquivo + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> carregar() {
        File f = new File(arquivo);
        if (!f.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Logger.registrar("ERRO", "Falha ao carregar " + arquivo + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
