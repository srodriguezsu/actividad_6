package UI;

import FriendPKG.Friend;

import javax.swing.*;
import java.awt.event.*;

public class AmigoNuevo extends JFrame {
    private JPanel mainPanel;
    private JTextField inNombre;
    private JComboBox paísComboBox;
    private JButton crearButton;
    private JButton cancelarButton;
    private JTextField inNumero;
    private void retornar(){
        // Destruye esta ventana
        setVisible(false);
        dispose();

        // Re abre homepage
        HomePage mainWindow = new HomePage();
        mainWindow.setVisible(true);
    }


    public AmigoNuevo(){
        // Set up the page
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(600,600);
        this.setTitle("Amigo");

        // botón de crear
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    // Si el usuario entra un número correcto

                    String nombre = inNombre.getText();
                    Long numero = Long.valueOf(inNumero.getText());
                    Friend amigo = new Friend(nombre, numero);

                    retornar();

                } catch (NumberFormatException exception){
                    DatosMalos alertWindow = new DatosMalos();
                    alertWindow.setVisible(true);
                }

            }
        });
        // Botón de cancelar
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retornar();
            }
        });
    }
}
