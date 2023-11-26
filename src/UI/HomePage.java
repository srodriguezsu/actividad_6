package UI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import FriendPKG.Friend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame{
    private JList<Friend> ListaAmigos;
    private JPanel mainPanel;
    private JButton amigoNuevoButton;
    private JScrollPane friendsPanel;
    private JPanel selAmigoPanel;
    private JButton editarButton;
    private JLabel selNombre;
    private JLabel selNumero;
    private JLabel welcome;

    // Esta función formatea un string de numero a numero celular colombiano
    private String formatPhoneNumber(String numero){
        int c = 0;
        StringBuilder out = new StringBuilder("+57");
        for (char i: numero.toCharArray()) {
            if (c % 3 == 0 && c <= 6){
                out.append(" ");
            }
            if (c % 2 == 0 && c > 6){
                out.append(" ");
            }
            c ++;
            out.append(i);
        }
        return out.toString();
    }

    public HomePage(){

        // Set up the home page
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200,600);
        setTitle("Tus amigos");
        selAmigoPanel.setVisible(false);
        selAmigoPanel.setPreferredSize(new Dimension(250,150));


        // Set up amigo nuevo btn
        amigoNuevoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AmigoNuevo newWindow = new AmigoNuevo();
                newWindow.setVisible(true);
                setVisible(false);
                dispose();
            }
        });

        // set up editar btn
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditorAmigo ventanaNueva = new EditorAmigo(ListaAmigos.getSelectedValue());
                ventanaNueva.setVisible(true);
                dispose();

            }
        });

        // Friend class reads the txt to add friends to the jList
        DefaultListModel<Friend> modeloAmigos = new DefaultListModel<>();
        if (Friend.getFriendList().size() == 0){
            welcome.setText("No tienes amigos :(");
        }
        else {
            for (Friend amigo:
                    Friend.getFriendList()) {
                modeloAmigos.addElement(amigo);
            }
        }


        ListaAmigos.setModel(modeloAmigos);
        ListaAmigos.setFixedCellHeight(50);

        // Al hacer click a cada amigo muestra su info
        ListaAmigos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {


                Friend amigoSeleccionado = ListaAmigos.getSelectedValue();

                String numero = formatPhoneNumber(
                        String.valueOf(
                                amigoSeleccionado.getPhoneNumber()
                        )
                );

                selAmigoPanel.setVisible(true);


                selNombre.setText(amigoSeleccionado.getName());
                selNumero.setText(numero);
            }
        });
    }

}
