package controller;

import model.Jugador;
import view.JugadoresVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JugadoresVistaController implements ActionListener, KeyListener {
    private JugadoresVista vista;

    public JugadoresVistaController() {
        this.vista = new JugadoresVista();
        this.vista.getEmpezar().setEnabled(false);
        this.vista.getEmpezar().addActionListener(this);
        this.vista.getFinalizar().addActionListener(this);
        this.vista.getNombre1().addKeyListener(this);
        this.vista.getNombre2().addKeyListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Jugador p1 = new Jugador(vista.getNombre1().getText());
        Jugador p2 = new Jugador(vista.getNombre2().getText());

        if (e.getSource() == this.vista.getEmpezar()) {
            if (!p1.getNombre().equals(p2.getNombre())) {
                new PreguntasController(p1, p2);
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Los nombres de los jugadores no pueden ser iguales");
            }
        } else {
            vista.dispose();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (this.vista.getNombre1().getText().length() > 0 && this.vista.getNombre2().getText().length() > 0) {
            this.vista.getEmpezar().setEnabled(true);
        } else {
            this.vista.getEmpezar().setEnabled(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.vista.getNombre1().getText().length() > 0 && this.vista.getNombre2().getText().length() > 0) {
            this.vista.getEmpezar().setEnabled(true);
        } else {
            this.vista.getEmpezar().setEnabled(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.vista.getNombre1().getText().length() > 0 && this.vista.getNombre2().getText().length() > 0) {
            this.vista.getEmpezar().setEnabled(true);
        } else {
            this.vista.getEmpezar().setEnabled(false);
        }
    }
}
