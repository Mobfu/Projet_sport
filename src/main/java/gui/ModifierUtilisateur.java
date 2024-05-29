package gui;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
 
import dao.DBDAO;
 
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
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
public class ModifierUtilisateur extends JFrame implements ActionListener{
	private DBDAO dbdao ;
	private JPanel contentPane;
    private JTextField usernameField;
    private JComboBox<String> roleComboBox;
    private JButton btnModifier;
    private JButton btnAnnuler;
    private JTextField nameField;
	public ModifierUtilisateur() {
		dbdao = new DBDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//recuperation de l image depuis le fichier
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler.setBounds(109, 304, 140, 30);
		contentPane.add(btnAnnuler);
		btnAnnuler.addActionListener(this);
		btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnModifier.setBounds(447, 304, 140, 30);
		contentPane.add(btnModifier);
		btnModifier.addActionListener(this);
		roleComboBox = new JComboBox<>();
		roleComboBox.setBounds(302, 177, 245, 28);
		roleComboBox.addItem("Admin");
        roleComboBox.addItem("Sportif");
        roleComboBox.addItem("Membre Ministère Sport");
        roleComboBox.addItem("Elu");
		contentPane.add(roleComboBox);
		nameField = new JTextField();
		nameField.setBounds(302, 116, 245, 28);
		contentPane.add(nameField);
		nameField.setColumns(10);
		JLabel lblRole = new JLabel("Rôle :");
		lblRole.setHorizontalAlignment(SwingConstants.LEFT);
		lblRole.setForeground(Color.WHITE);
		lblRole.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblRole.setBackground(UIManager.getColor("Button.background"));
		lblRole.setBounds(217, 175, 63, 30);
		contentPane.add(lblRole);
		JLabel lblNom = new JLabel("Nom d'utilisateur :");
		lblNom.setHorizontalAlignment(SwingConstants.LEFT);
		lblNom.setForeground(Color.WHITE);
		lblNom.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNom.setBackground(UIManager.getColor("Button.background"));
		lblNom.setBounds(94, 112, 199, 30);
		contentPane.add(lblNom);
 
		JLabel lblNewLabel = new JLabel("Modifier un utilisateur");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(149, 8, 417, 37);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//insertion de l image dans un JLabel, attention dernier JLabel declare pour qu il reste en fond
		JLabel imageLabel = new JLabel("");
		imageLabel.setToolTipText("");
		imageLabel.setLabelFor(this);
		contentPane.add(imageLabel, Integer.valueOf(0));
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setIcon(new ImageIcon(background));
		contentPane.add(imageLabel);
		imageLabel.setBounds(0, 0, 686, 373);
		JButton button = new JButton("New button");
		button.setBounds(414, 304, 89, 23);
		contentPane.add(button);
		setLocationRelativeTo(null);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierUtilisateur frame = new ModifierUtilisateur();
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
                return 0; // Valeur par défault, à ajuster selon vos besoins
        }
    }

	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnModifier) {
            String username = nameField.getText();
            String selectedRole = (String) roleComboBox.getSelectedItem();
            int roleValue = getRoleValue(selectedRole);
            if (dbdao.updateRoleUser(username, roleValue)) {
                JOptionPane.showMessageDialog(this, "Utilisateur " + username + " modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                String type_action="modification d'un utilisateur";
                dbdao.AjoutAction(type_action, PageConnexion.session);
                GestionUtilisateurs frame = new GestionUtilisateurs();
                frame.setVisible(true);
                dispose();
            }
            else
            	JOptionPane.showMessageDialog(this, "Une erreur a été rencontrée ", "Echec", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == btnAnnuler) {
                // Retourner à la page de gestion des utilisateurs
        		GestionUtilisateurs frame = new GestionUtilisateurs();
                frame.setVisible(true);
                dispose();
            }
    }
 
}
