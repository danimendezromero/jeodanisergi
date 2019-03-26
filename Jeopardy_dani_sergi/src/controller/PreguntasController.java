package controller;

import model.Respuesta;
import model.Categoria;
import model.Jugador;
import model.Pregunta;
import view.PreguntasVista;
import utilities.Juego;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PreguntasController implements ActionListener {
    private PreguntasVista view;
    private Jugador j1;
    private Jugador j2;
    private Jugador jugador;
    private HashMap<String, TreeMap> categories = new HashMap<String, TreeMap>();
    private RespuestasVistaController respuestaController;
    private Juego juego;

    public PreguntasVista getView() {
        return view;
    }

    public PreguntasController(Jugador j1, Jugador j2) {
        this.j1 = j1;
        this.j2 = j2;
        this.view = new PreguntasVista(j1, j2);
        juego = new Juego(j1, j2, this);

        for (int i = 0; i < view.getCasillas().length; i++) {
            for (int j = 0; j < view.getCasillas()[i].length; j++) {
                this.view.getCasillas()[i][j].addActionListener(this);
            }
        }

        loadData();
        setDataToButtons();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < view.getCasillas().length; i++) {
            for (int j = 0; j < view.getCasillas()[i].length; j++) {
                if (view.getCasillas()[i][j] == e.getSource()) {
                    int posX = i;
                    int posY = j;
                    view.getCasillas()[i][j].setEnabled(false);
                    view.getCasillas()[i][j].setBackground(Color.BLACK);
                    view.setEnabled(false);
                    respuestaController = new RespuestasVistaController(categories, view.getCasillas(), posX, posY, this);
                }
            }
        }

        respuestaController.detectarPregunta();
        jugador = juego.jugadorActual(juego.getTurno());
        juego.siguienteTurnoo();
    }

    private void loadData() {
        BufferedReader br = null;
        String[] datos = null;
        Path j1 = FileSystems.getDefault().getPath("./categories.txt");
        Path j2 = FileSystems.getDefault().getPath("namecategories.txt");

        try {
            br = Files.newBufferedReader(j1, StandardCharsets.UTF_8);
            String linea;
            ArrayList<Pregunta> aux = new ArrayList<>();
            ArrayList<Respuesta> auxA;
            ArrayList<Pregunta> auxQ = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                auxA = new ArrayList<>();

                datos = linea.split(":");
                for (int i = 3; i < datos.length; i++) {
                    Respuesta r = new Respuesta();
                    if (datos[i].contains("$")) {
                        r.setRespuestaCorrecta(true);
                        datos[i] = datos[i].replace("$", "");
                        r.setTexto(datos[i]);
                    } else {
                        r.setRespuestaCorrecta(false);
                        r.setTexto(datos[i]);
                    }
                    auxA.add(r);
                }

                Pregunta q = new Pregunta();
                q.setTexto(datos[2]);
                q.setresupestas(auxA);
                q.setCategoria(datos[0]);
                q.setPuntos(Integer.parseInt(datos[1]));
                auxQ.add(q);
            }

            br = Files.newBufferedReader(j2, StandardCharsets.UTF_8);
            while ((linea = br.readLine()) != null) {
                datos = linea.split(":");
                for (int i = 0; i < datos.length; i++) {
                    Categoria c = new Categoria();
                    c.setNombre(datos[i]);
                    for (Pregunta item : auxQ) {
                        if (item.getCategoria().equals(datos[i])) {
                            aux.add(item);
                        }
                    }
                    c.setPreguntas(aux);
                    TreeMap preguntas = new TreeMap<Integer, Pregunta>();
                    for (int j = 0; j < c.getPreguntas().size(); j++) {
                        preguntas.put(c.getPreguntas().get(j).getPuntos(), c.getPreguntas().get(j));
                    }

                    categories.put(c.getNombre(), preguntas);
                    aux.clear();
                }
            }


        } catch (IOException e) {
            System.out.println("No se ha podido leer el fichero");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar BufferedReader");
            }
        }


    }

    public void setDataToButtons() {
        ArrayList nameCategories = new ArrayList<String>();
        ArrayList price = new ArrayList<Integer>();
        for (Map.Entry<String, TreeMap> elem : categories.entrySet()) {
            String key = elem.getKey();
            nameCategories.add(key);
            TreeMap<Integer, Pregunta> value = elem.getValue();
            for (Map.Entry<Integer, Pregunta> child : value.entrySet()) {
                int childKey = child.getKey();
                price.add(childKey);
            }
        }


        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < view.getCasillas()[i].length; j++) {
                view.getCasillas()[i][j].setText((String) nameCategories.get(j));
            }
        }

        for (int i = 0; i < view.getCasillas().length; i++) {
            for (int j = 1; j < view.getCasillas()[i].length; j++) {
                if (view.getCasillas()[j][i].isEnabled()) {
                    view.getCasillas()[j][i].setText(String.valueOf(price.get(j - 1)));
                }

            }
        }
    }

    public void setDataToButtonsDouble() {
        if (juego.doubleRound()) {
            int count = 0;
            Iterator it;
            it = categories.entrySet().iterator();
            ArrayList doublePrice = new ArrayList<TreeMap>();
            while (it.hasNext()) {
                HashMap.Entry e = (HashMap.Entry) it.next();
                Iterator secondMap = ((TreeMap) e.getValue()).entrySet().iterator();
                TreeMap tm = new TreeMap();
                while (secondMap.hasNext()) {
                    Map.Entry e2 = (Map.Entry) secondMap.next();
                    Pregunta qt = (Pregunta) e2.getValue();
                    qt.setPuntos(qt.getPuntos() * 2);
                    tm.put((Integer) e2.getKey() * 2, qt);
                }
                doublePrice.add(tm);
            }

            it = categories.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry e = (HashMap.Entry) it.next();
                e.setValue(doublePrice.get(count));
                count++;
            }
            JOptionPane.showMessageDialog(null, "Empieza la JeoPardy DoubleRound");
            setDataToButtons();
        }
    }

    public void printPositivePts() {
        if (j1.getNombre().equals(jugador.getNombre())) {
            jugador.setPuntos(respuestaController.getPregunta().getPuntos());
            view.getJugador1Puntos().setText(String.valueOf(jugador.getPuntos()) + " PTS");
        } else {
            jugador.setPuntos(respuestaController.getPregunta().getPuntos());
            view.getJugador2Puntos().setText(String.valueOf(jugador.getPuntos()) + " PTS");
        }
    }

    public void printNegativePts() {
        if (j1.getNombre().equals(jugador.getNombre())) {
            jugador.setNegativePuntos(respuestaController.getPregunta().getPuntos());
            view.getJugador1Puntos().setText(String.valueOf(jugador.getPuntos()) + " PTS");
        } else {
            jugador.setNegativePuntos(respuestaController.getPregunta().getPuntos());
            view.getJugador2Puntos().setText(String.valueOf(jugador.getPuntos() + " PTS"));
        }
    }

    public void printTurns() {
        if (j1.getNombre().equals(jugador.getNombre())) {
            view.getJugador2Tablero().setBackground(Color.GREEN);
            view.getJugador1Tablero().setBackground(Color.WHITE);
        } else {
            view.getJugador2Tablero().setBackground(Color.WHITE);
            view.getJugador1Tablero().setBackground(Color.GREEN);
        }
    }

    public void endOfGame() {
        if (juego.endOfGame()) {
            if (j1.getPuntos() == j2.getPuntos()) {
                view.dispose();
                JOptionPane.showMessageDialog(null, "Hay un empate! Empieza la Final Round");
                new RondaFinalController(j1, j2,0);
            } else {
                view.dispose();
                JOptionPane.showMessageDialog(null, "Partida Finalizada!");
                new VistaFinalController(j1, j2);
            }

        }
    }
}
