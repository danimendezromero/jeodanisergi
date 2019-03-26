package controller;

import model.Respuesta;
import model.Jugador;
import model.Pregunta;
import view.RondaFinalVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class RondaFinalController implements ActionListener {

    private RondaFinalVista vista;
    private Jugador p1, p2;
    private int controlTurn;
    private ArrayList preguntas = new ArrayList<Pregunta>();
    private Jugador jugador;
    private final int END_FINAL = 10;

    public RondaFinalController(Jugador p1, Jugador p2, int controlTurn) {
        if (controlTurn == END_FINAL) {
            JOptionPane.showMessageDialog(null, "Es un empate!");
        } else {
            this.vista = new RondaFinalVista();
            this.controlTurn = controlTurn;
            this.p1 = p1;
            this.p2 = p2;
            this.jugador = finalJugadorTurn(controlTurn);
            this.vista.getRes1().addActionListener(this);
            this.vista.getRes2().addActionListener(this);
            this.vista.getRes3().addActionListener(this);
            vista.getJugadorActual().setText("TURNO DE " + jugador.getNombre());
            loadFinalData();
            setPreguntas();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vista.getJugadorActual().setText("TURNO DE " + jugador.getNombre());
        Pregunta actualPregunta = (Pregunta) preguntas.get(controlTurn);

        if (e.getSource() == this.vista.getRes1()) {
            for (Respuesta cAns : actualPregunta.getRespuesta()) {
                if (vista.getRes1().getText().equals(cAns.getTexto())) {
                    if (cAns.esRespuestaCorrecta()) {
                        controlTurn++;
                        jugador = finalJugadorTurn(controlTurn);
                        JOptionPane.showMessageDialog(null, "Respuesta Correcta!");
                        vista.dispose();
                        new RondaFinalController(p1, p2, controlTurn);
                    } else {
                        jugador.setPuntos(-1);
                        JOptionPane.showMessageDialog(null, "Respuesta Incorrecta!");
                        new VistaFinalController(p1, p2);
                        vista.dispose();
                    }
                }
            }
        }

        if (e.getSource() == this.vista.getRes2()) {
            for (Respuesta cAns : actualPregunta.getRespuesta()) {
                if (vista.getRes2().getText().equals(cAns.getTexto())) {
                    if (cAns.esRespuestaCorrecta()) {
                        controlTurn++;
                        jugador = finalJugadorTurn(controlTurn);
                        JOptionPane.showMessageDialog(null, "Respuesta Correcta!");
                        vista.dispose();
                        new RondaFinalController(p1, p2, controlTurn);
                    } else {
                        jugador.setPuntos(-1);
                        JOptionPane.showMessageDialog(null, "Respuesta Incorrecta!");
                        new VistaFinalController(p1, p2);
                        vista.dispose();
                    }

                }
            }
        }

        if (e.getSource() == this.vista.getRes3()) {
            for (Respuesta cAns : actualPregunta.getRespuesta()) {
                if (vista.getRes3().getText().equals(cAns.getTexto())) {
                    if (cAns.esRespuestaCorrecta()) {
                        controlTurn++;
                        jugador = finalJugadorTurn(controlTurn);
                        JOptionPane.showMessageDialog(null, "Respuesta Correcta!");
                        vista.dispose();
                        new RondaFinalController(p1, p2, controlTurn);
                    } else {
                        jugador.setPuntos(-1);
                        JOptionPane.showMessageDialog(null, "Respuesta Incorrecta!");
                        new VistaFinalController(p1, p2);
                        vista.dispose();
                    }

                }
            }
        }
    }

    private Jugador finalJugadorTurn(int controlTurn) {
        if (controlTurn % 2 == 0) {
            return p1;
        } else {
            return p2;
        }
    }

    private void loadFinalData() {
        BufferedReader br = null;
        Path p1 = FileSystems.getDefault().getPath("finalround");

        try {
            br = Files.newBufferedReader(p1, StandardCharsets.UTF_8);
            String linea;
            String[] datos;
            while ((linea = br.readLine()) != null) {
                ArrayList respuestas = new ArrayList<>();

                datos = linea.split(":");
                for (int i = 1; i < datos.length; i++) {
                    Respuesta a = new Respuesta();
                    if (datos[i].contains("$")) {
                        a.setRespuestaCorrecta(true);
                        datos[i] = datos[i].replace("$", "");
                        a.setTexto(datos[i]);
                    } else {
                        a.setRespuestaCorrecta(false);
                        a.setTexto(datos[i]);
                    }
                    respuestas.add(a);
                }

                Pregunta q = new Pregunta();
                q.setTexto(datos[0]);
                q.setresupestas(respuestas);

                preguntas.add(q);
            }

        } catch (IOException e) {
            System.out.println("No se ha podido leer el fichero");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el BufferedReader");
            }
        }
    }

    public void setPreguntas() {
        Pregunta actualPregunta = (Pregunta) preguntas.get(controlTurn);
        vista.getPreguntaTexto().setText(actualPregunta.getTexto());
        vista.getRes1().setText(actualPregunta.getRespuesta().get(0).getTexto());
        vista.getRes2().setText(actualPregunta.getRespuesta().get(1).getTexto());
        vista.getRes3().setText(actualPregunta.getRespuesta().get(2).getTexto());
    }

}
