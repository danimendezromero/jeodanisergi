package model;

import java.util.ArrayList;

public class Categoria {
    private String nombreCat;
    private ArrayList<Pregunta> preguntas;

    public String getNombre() {
        return nombreCat;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setNombre(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
