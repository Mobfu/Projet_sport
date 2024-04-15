 package gui;
 
import javax.swing.*;
 
import ProjetSport.DBDAO;
 
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
 
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;
import java.awt.event.*;
import javax.swing.*;
 
public class ModifUser extends JFrame implements ActionListener {
 
	private DBDAO dbdao ;
	private JPanel contentPane;
    private JTextField usernameField;
    private JComboBox<String> roleComboBox;
    private JButton btnModifier;
    private JButton btnRetour;
    public ModifUser() {
    	dbdao = new DBDAO();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 226, 217);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 2, 10, 10));
 
        JLabel lblUsername = new JLabel("Nom d'utilisateur:");
        contentPane.add(lblUsername);
 
        usernameField = new JTextField();
        contentPane.add(usernameField);
        usernameField.setColumns(10);
 
        JLabel lblRole = new JLabel("Rôle:");
        contentPane.add(lblRole);
 
        roleComboBox = new JComboBox<>();
        roleComboBox.addItem("Admin");
        roleComboBox.addItem("Sportif");
        roleComboBox.addItem("Membre Ministère Sport");
        roleComboBox.addItem("Elu");
        contentPane.add(roleComboBox);
 
        btnModifier = new JButton("Modifier");
        btnModifier.addActionListener(this);
        contentPane.add(btnModifier);
        btnRetour = new JButton("Retour");
        btnRetour.addActionListener(this);
        contentPane.add(btnRetour);
 
 
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    } 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ModifUser frame = new ModifUser();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private int getRoleValue(String roleName) {
        switch (roleName) {
            case "Admin":
                return 0;
            case "Sportif":
                return 1;
            case "Membre Ministère Sport":
                return 2;
            case "Elu":
                return 3;
            default:
                return 0; // Valeur par défaut, à ajuster selon vos besoins
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnModifier) {
            String username = usernameField.getText();
            String selectedRole = (String) roleComboBox.getSelectedItem();
            int roleValue = getRoleValue(selectedRole);
            if (dbdao.updateRoleUser(username, roleValue)) {
                JOptionPane.showMessageDialog(this, "Utilisateur " + username + " modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            	JOptionPane.showMessageDialog(this, "Une erreur a été rencontré ", "Echec", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == btnRetour) {
                // Retourner à la page de gestion des utilisateurs
            	Gestion_utilisateurs frame = new Gestion_utilisateurs();
                frame.setVisible(true);
                dispose();
            }
    }
 
 
 
  
}