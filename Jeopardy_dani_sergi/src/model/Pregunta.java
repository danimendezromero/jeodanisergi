package model;

import java.util.ArrayList;


public class Pregunta {
    private String texto;
    private String categoria;
    private int puntos;
    private ArrayList<Respuesta> respuesta;
    public String getText() {
        return texto;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
        public ArrayList<respuesta> getRespuesta() {
        return respuesta;
    }

    public void setAnswers(ArrayList<Respuesta> respuestas) {
        this.respuesta = respuestas;
    }
    @Override
    public String toString() {
        return "Pregunta{" + "texto='" + texto + '\'' + ", puntos=" + puntos + ", respuesta=" + respuesta + '}';
    }
}
