package view;

import javax.swing.*;
import java.awt.*;

public class RondaFinalVista extends JFrame {

    private JPanel respuestas, pregunta;
    private JLabel preguntaTexto;
    private JButton res1, res2, res3;
    private JLabel jugadorActual;

    public JLabel getPreguntaTexto() {
        return preguntaTexto;
    }

    public JButton getRes1() {
        return res1;
    }

    public JButton getRes2() {
        return res2;
    }

    public JButton getRes3() {
        return res3;
    }

    public JLabel getJugadorActual() {
        return jugadorActual;
    }

    public RondaFinalVista() {
        this.setTitle("Preguntas finales:");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        respuestas = new JPanel(new GridLayout(3, 1));
        pregunta = new JPanel(new BorderLayout());
        preguntaTexto = new JLabel();
        jugadorActual = new JLabel();
        jugadorActual.setFont(new Font("Roboto", Font.BOLD, 14));
        jugadorActual.setForeground(Color.WHITE);
        preguntaTexto.setFont(new Font("Roboto", Font.BOLD, 14));
        preguntaTexto.setForeground(Color.WHITE);
        pregunta.setBackground(Color.BLACK);

        res1 = new JButton();
        res2 = new JButton();
        res3 = new JButton();

        res1.setBackground(Color.WHITE);
        res1.setForeground(Color.BLACK);
        res1.setFont(new Font("Roboto", Font.BOLD, 16));

        res2.setBackground(Color.WHITE);
        res2.setForeground(Color.BLACK);
        res2.setFont(new Font("Roboto", Font.BOLD, 16));

        res3.setBackground(Color.WHITE);
        res3.setForeground(Color.BLACK);
        res3.setFont(new Font("Roboto", Font.BOLD, 16));

        respuestas.setPreferredSize(new Dimension(900, 100));
        preguntaTexto.setPreferredSize(new Dimension(720, 75));
        preguntaTexto.setHorizontalAlignment(SwingConstants.CENTER);

        respuestas.add(res1);
        respuestas.add(res2);
        respuestas.add(res3);

        pregunta.add(preguntaTexto, BorderLayout.NORTH);
        pregunta.add(respuestas, BorderLayout.CENTER);
        pregunta.add(jugadorActual, BorderLayout.SOUTH);

        pane.add(pregunta);

    }

}
