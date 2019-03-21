package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/* La vista resupestasView se basa en dos paneles, el panel de la pregunta, cuyo layout es un BorderLayout definido en la posición NORTH, y las respuestas son un GridLayout de una columna.
    */
public class RespuestaVista extends JFrame {
    private JPanel resupestas, pregunta;
    private JLabel preguntaTexto;

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

    private JButton res1, res2, res3;

    public RespuestaVista() {
        this.setTitle("Rsponder:");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        resupestas = new JPanel(new GridLayout(3, 1));
        pregunta = new JPanel(new BorderLayout());
        preguntaTexto = new JLabel();
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

        resupestas.setPreferredSize(new Dimension(900,100));
        preguntaTexto.setPreferredSize(new Dimension(720, 75));
        preguntaTexto.setHorizontalAlignment(SwingConstants.CENTER);

        resupestas.add(res1);
        resupestas.add(res2);
        resupestas.add(res3);

        pregunta.add(preguntaTexto, BorderLayout.NORTH);
        pregunta.add(resupestas, BorderLayout.CENTER);

        pane.add(pregunta);

    }
}
