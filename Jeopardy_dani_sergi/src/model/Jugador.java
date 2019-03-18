package model;

public class Jugador {
    private String nombre;
    private int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void setNegativePuntos (int puntos) {
        if (this.puntos - puntos < 0) {
            this.puntos = 0;
        } else {
            this.puntos -= puntos;
        }
    }
}
