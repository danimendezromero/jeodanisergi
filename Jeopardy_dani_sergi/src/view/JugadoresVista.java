package view;

import javax.swing.*;
import java.awt.*;

/* La vista PlayersView se basa en una GridBagLayout donde definimos dos cajas de texto y dos labels para que los usuarios puedan introducir su nombre. Contiene dos botones para empezar la partida o
cancelar
 */
public class JugadoresVista extends JFrame {

    private JLabel jugador1,jugador2;
    private JTextField nombre1,nombre2;
    private JButton empezar,finalizar;

    public JTextField getNombre1() {
        return nombre1;
    }

    public JTextField getNombre2() {
        return nombre2;
    }

    public JButton getEmpezar() {
        return empezar;
    }

    public JButton getFinalizar() {
        return finalizar;
    }

    public JugadoresVista (){
        this.setTitle("Inicio del juego");
        this.setResizable(false);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addComponentsToPane(Container contentPane) {

        jugador1 = new JLabel("Jugador 1: ");
        jugador2 = new JLabel("Jugador 2: ");
        nombre1 = new JTextField(20);
        nombre2 = new JTextField(20);
        empezar = new JButton("Empezar");
        finalizar = new JButton("Salir");

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(5, 5, 5, 5);

        gc.gridx = 0;
        gc.gridy = 0;
        contentPane.add(jugador1, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        contentPane.add(nombre1, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        contentPane.add(jugador2, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        contentPane.add(nombre2, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        contentPane.add(empezar, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        contentPane.add(finalizar, gc);

    }


}
