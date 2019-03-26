package utilities;

import controller.PreguntasController;
import model.Jugador;

public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private int turno;
    private PreguntasController controlladorpreguntas;
    private final int RONDADOBLE = 20;
    private final int FINJUEGO = 30;

    public Juego(Jugador jugador1, Jugador jugador2, PreguntasController controlladorpreguntas) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.controlladorpreguntas = controlladorpreguntas;
        this.turno = 0;
    }

    public void siguienteTurnoo() {
        this.turno++;
    }

    public Jugador jugadorActual(int turno) {
        if (turno % 2 == 0) {
            return jugador1;
        } else {
            return jugador2;
        }
    }

    public boolean doubleRound() {
        return this.turno == RONDADOBLE;
    }

    public boolean endOfGame() {
        return this.turno == FINJUEGO;
    }

    public int getTurno() {
        return turno;
    }
}
