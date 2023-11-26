package UI;

import FriendPKG.Friend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorAmigo extends JFrame{
    private JTextField inNombre;
    private JComboBox paísComboBox;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JTextField inNumero;
    private JPanel mainPanel;

    private void retornar(){
        // Destruye esta ventana
        setVisible(false);
        dispose();

        // Re abre homepage
        HomePage mainWindow = new HomePage();
        mainWindow.setVisible(true);
    }

    public EditorAmigo(Friend amigo){
        // Set up the page
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600,600);
        setTitle("Amigo");

        inNombre.setText(amigo.getName());
        inNumero.setText(String.valueOf(amigo.getPhoneNumber()));

        // btn para Actualizar datos
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    // Si el usuario entra un número correcto

                    String nombre = inNombre.getText();
                    Long numero = Long.valueOf(inNumero.getText());
                    amigo.changeName(nombre);
                    amigo.changePhoneNumber(numero);

                    retornar();

                } catch (NumberFormatException exception){
                    DatosMalos alertWindow = new DatosMalos();
                    alertWindow.setVisible(true);
                }

            }
        });
        // Eliminar amigo
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmDelete emergente = new ConfirmDelete(amigo);
                emergente.setVisible(true);
                retornar();
            }
        });
    }
}
