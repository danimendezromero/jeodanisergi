/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Jugador;

import javax.swing.*;
import java.awt.*;



public class PreguntasVista extends JFrame {
    private JButton[][] casillas;
    private JPanel jugador1Tablero;
    private JPanel jugador2Tablero;
    private JSplitPane jugadoresInfo;
    private JLabel jugador1, jugador2, jugador1Puntos, jugador2Puntos;
    private Jugador j1, j2;
    public static final Color DarkGreen = new Color(0,153,0);
    public static final Color DarkBlue = new Color(0,0,204);
    public static final Color LightBlue = new Color(51,153,255);
    public JButton[][] getCasillas() {
        return casillas;
    }

    public JPanel getJugador1Tablero() {
        return jugador1Tablero;
    }

    public JPanel getJugador2Tablero() {
        return jugador2Tablero;
    }

    public JLabel getJugador1Puntos() {
        return jugador1Puntos;
    }

    public JLabel getJugador2Puntos() {
        return jugador2Puntos;
    }

    public PreguntasVista(Jugador j1, Jugador j2) {
        this.j1 = j1;
        this.j2 = j2;
        this.setTitle("Tablero");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new BorderLayout(8, 8));
        jugador1 = new JLabel(j1.getNombre());
        jugador2 = new JLabel(j2.getNombre());
        jugador1Puntos = new JLabel(String.valueOf(j1.getPuntos()) + " Puntos");
        jugador2Puntos = new JLabel(String.valueOf(j2.getPuntos()) + " Puntos");

        jugador1.setHorizontalAlignment(SwingConstants.CENTER);
        jugador2.setHorizontalAlignment(SwingConstants.CENTER);
        jugador1Puntos.setHorizontalAlignment(SwingConstants.CENTER);
        jugador2Puntos.setHorizontalAlignment(SwingConstants.CENTER);

        jugador1.setFont(new Font("Roboto", Font.BOLD, 25));
        jugador1.setForeground(Color.BLACK);
        jugador2.setFont(new Font("Roboto", Font.BOLD, 25));
        jugador2.setForeground(Color.BLACK);
        jugador1Puntos.setFont(new Font("Roboto", Font.BOLD, 13));
        jugador1Puntos.setForeground(Color.BLACK);
        jugador2Puntos.setFont(new Font("Roboto", Font.BOLD, 13));
        jugador2Puntos.setForeground(Color.BLACK);

        jugador1Tablero = new JPanel();
        jugador2Tablero = new JPanel();

        jugador1Tablero.setLayout(new BorderLayout());
        jugador2Tablero.setLayout(new BorderLayout());

        jugador1Tablero.setBackground(DarkGreen);
        jugador2Tablero.setBackground(Color.WHITE);

        jugador1Tablero.add(jugador1);
        jugador2Tablero.add(jugador2);
        jugador1Tablero.add(jugador1Puntos, BorderLayout.SOUTH);
        jugador2Tablero.add(jugador2Puntos, BorderLayout.SOUTH);

        jugadoresInfo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jugador1Tablero, jugador2Tablero);
        jugadoresInfo.setResizeWeight(.5d);
        jugadoresInfo.setEnabled(false);

        JPanel casillasPanel = new JPanel(new GridLayout(6, 6));
        casillas = new JButton[6][6];

        pane.setSize(new Dimension(600, 480));

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j] = new JButton();
                casillas[i][j].setPreferredSize(new Dimension(200,100));

                if (i == 0 && j < 6) {
                    casillas[i][j].setEnabled(false);
                    casillas[i][j].setBackground(DarkBlue);
                    casillas[i][j].setFont(new Font("Roboto", Font.BOLD, 25));
                    casillas[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 5, 2, Color.BLACK));
                } else {
                    casillas[i][j].setBackground(LightBlue);
                    casillas[i][j].setFont(new Font("Roboto", Font.BOLD, 25));
                    casillas[i][j].setForeground(Color.WHITE);
                    casillas[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                    casillas[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                casillasPanel.add(casillas[i][j]);
            }
        }

        pane.add(casillasPanel, BorderLayout.CENTER);
        pane.add(jugadoresInfo, BorderLayout.SOUTH);
    }

}
