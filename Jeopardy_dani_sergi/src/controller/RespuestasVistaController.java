package controller;

import model.Respuesta;
import model.Jugador;
import model.Pregunta;
import utilities.Juego;
import view.RespuestaVista;
import view.PreguntasVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

public class RespuestasVistaController implements ActionListener {
    private ArrayList<Respuesta> respuestas;
    private Pregunta pregunta;
    private PreguntasController preguntasController;
    private RespuestaVista vista;
    private int posX, posY;
    private JButton[][] celdas;
    private HashMap<String, TreeMap<Integer, Pregunta>> categorias;

    public Pregunta getPregunta() {
        return pregunta;
    }

    public RespuestasVistaController(HashMap categorias, JButton[][] celdas, int posX, int posY, PreguntasController preguntasController) {
        this.vista = new RespuestaVista();
        this.preguntasController = preguntasController;
        this.categorias = categorias;
        this.celdas = celdas;
        this.posX = posX;
        this.posY = posY;
        respuestas = new ArrayList<>();
        pregunta = new Pregunta();
        vista.setVisible(true);
        vista.getRes1().addActionListener(this);
        vista.getRes2().addActionListener(this);
        vista.getRes3().addActionListener(this);
        detectarPregunta();
    }

    public void detectarPregunta() {
        for (Map.Entry<String, TreeMap<Integer, Pregunta>> elem : categorias.entrySet()) {
            String key = elem.getKey();
            TreeMap<Integer, Pregunta> value = elem.getValue();
            if (celdas[0][posY].getText().equals(key)) {
                for (Map.Entry<Integer, Pregunta> child : value.entrySet()) {
                    int childKey = child.getKey();
                    Pregunta childValue = child.getValue();

                    if (childKey == Integer.valueOf(celdas[posX][posY].getText())) {
                        vista.getPreguntaTexto().setText(childValue.getTexto());
                        vista.getRes1().setText(childValue.getRespuesta().get(0).getTexto());
                        vista.getRes2().setText(childValue.getRespuesta().get(1).getTexto());
                        vista.getRes3().setText(childValue.getRespuesta().get(2).getTexto());

                        respuestas = childValue.getRespuesta();
                        pregunta = childValue;
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getRes1()) {
            for (Respuesta answer : respuestas) {
                if (answer.getTexto().equals(vista.getRes1().getText())) {
                    if (answer.esRespuestaCorrecta()) {
                        vista.getRes1().setBackground(Color.GREEN);
                        vista.getRes2().setEnabled(false);
                        vista.getRes3().setEnabled(false);
                        preguntasController.printPositivePts();
                    } else {
                        vista.getRes1().setBackground(Color.RED);
                        for (Respuesta correctAnswer : respuestas) {
                            if (correctAnswer.esRespuestaCorrecta()) {
                                if (correctAnswer.getTexto().equals(vista.getRes2().getText())) {
                                    vista.getRes2().setBackground(Color.GREEN);
                                    vista.getRes3().setBackground(Color.RED);
                                } else if (correctAnswer.getTexto().equals(vista.getRes3().getText())) {
                                    vista.getRes2().setBackground(Color.RED);
                                    vista.getRes3().setBackground(Color.GREEN);
                                }
                            }
                        }
                        preguntasController.printNegativePts();
                    }
                }
            }
            preguntasController.printTurns();

        } else if (e.getSource() == vista.getRes2()) {
            for (Respuesta answer : respuestas) {
                if (answer.getTexto().equals(vista.getRes2().getText())) {
                    if (answer.esRespuestaCorrecta()) {
                        vista.getRes2().setBackground(Color.GREEN);
                        vista.getRes1().setEnabled(false);
                        vista.getRes3().setEnabled(false);
                        preguntasController.printPositivePts();
                    } else {
                        vista.getRes2().setBackground(Color.RED);
                        for (Respuesta correctAnswer : respuestas) {
                            if (correctAnswer.esRespuestaCorrecta()) {
                                if (correctAnswer.getTexto().equals(vista.getRes1().getText())) {
                                    vista.getRes1().setBackground(Color.GREEN);
                                    vista.getRes3().setBackground(Color.RED);
                                } else if (correctAnswer.getTexto().equals(vista.getRes3().getText())) {
                                    vista.getRes1().setBackground(Color.RED);
                                    vista.getRes3().setBackground(Color.GREEN);
                                }
                            }
                        }
                        preguntasController.printNegativePts();
                    }
                }
            }

            preguntasController.printTurns();

        } else if (e.getSource() == vista.getRes3()) {
            for (Respuesta answer : respuestas) {
                if (answer.getTexto().equals(vista.getRes3().getText())) {
                    if (answer.esRespuestaCorrecta()) {
                        vista.getRes3().setBackground(Color.GREEN);
                        vista.getRes1().setEnabled(false);
                        vista.getRes2().setEnabled(false);
                        preguntasController.printPositivePts();
                    } else {
                        vista.getRes3().setBackground(Color.RED);
                        for (Respuesta correctAnswer : respuestas) {
                            if (correctAnswer.esRespuestaCorrecta()) {
                                if (correctAnswer.getTexto().equals(vista.getRes1().getText())) {
                                    vista.getRes1().setBackground(Color.GREEN);
                                    vista.getRes2().setBackground(Color.RED);
                                } else if (correctAnswer.getTexto().equals(vista.getRes2().getText())) {
                                    vista.getRes1().setBackground(Color.RED);
                                    vista.getRes2().setBackground(Color.GREEN);
                                }
                            }
                        }
                        preguntasController.printNegativePts();
                    }
                }
            }
            preguntasController.printTurns();
        }

        new Timer().schedule(new TimerTask() {
            public void run() {
                preguntasController.getView().setEnabled(true);
                vista.dispose();
            }
        }, 2000);
        preguntasController.setDataToButtonsDouble();
        preguntasController.endOfGame();
    }
}