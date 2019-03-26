package controller;

import model.Jugador;
import view.VistaFinalVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VistaFinalController implements ActionListener {
    private VistaFinalVista vista;
    private Jugador p1, p2;

    public VistaFinalController(Jugador p1, Jugador p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.vista = new VistaFinalVista(p1, p2);
        this.vista.getbotonCerrar().addActionListener(this);
        setWinner();
    }

    public void setWinner() {
        Path path = Paths.get("ActualWinner.txt");

        if (p1.getPuntos() > p2.getPuntos()) {
            vista.getJugadorganador().setText(p1.getNombre());

            try {
                Files.write(path, vista.getJugadorganador().getText().getBytes());
            } catch (IOException e) {
                System.out.println("No se ha podido escribir el fichero");
            }
        } else {
            vista.getJugadorganador().setText(p2.getNombre());

            try {
                Files.write(path, vista.getJugadorganador().getText().getBytes());
            } catch (IOException e) {
                System.out.println("No se ha podido escribir el fichero");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getbotonCerrar()) {
            vista.dispose();
        }
    }
}
