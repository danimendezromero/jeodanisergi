package view;

import model.Jugador;

import javax.swing.*;
import java.awt.*;

public class VistaFinalVista extends JFrame {

    private JLabel nombre1, nombre2, j1puntos, j2puntos, ganador, jugadorganador;
    private Jugador j1, j2;
    private JButton botonCerrar;

    public JLabel getJugadorganador() {
        return jugadorganador;
    }

    public JButton getbotonCerrar() {
        return botonCerrar;
    }

    public VistaFinalVista(Jugador j1, Jugador j2) {
        this.j1 = j1;
        this.j2 = j2;
        this.setTitle("Resultados");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addComponentsToPane(Container pane) {
        this.setSize(new Dimension(800, 200));
        nombre1 = new JLabel(j1.getNombre());
        j1puntos = new JLabel(String.valueOf(j1.getPuntos()));
        nombre2 = new JLabel(j2.getNombre());
        j2puntos = new JLabel(String.valueOf(j2.getPuntos()));
        ganador = new JLabel("Ganador de la partida");
        jugadorganador = new JLabel("NOMBRE");
        botonCerrar = new JButton("Cerrar");
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        pane.add(nombre1, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        pane.add(nombre2, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        pane.add(j1puntos, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        pane.add(j2puntos, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        pane.add(ganador, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        pane.add(jugadorganador, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        pane.add(botonCerrar, gc);
    }
}
