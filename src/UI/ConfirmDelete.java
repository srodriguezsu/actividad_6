package UI;

import FriendPKG.Friend;

import javax.swing.*;
import java.awt.event.*;

public class ConfirmDelete extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel msg;

    public ConfirmDelete(Friend amigo) {
        setContentPane(contentPane);
        setSize(600,200);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        msg.setText("¿Seguro que deseas eliminar a "+ amigo.getName() + " definitivamente?");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                amigo.remove();
                dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }




}
