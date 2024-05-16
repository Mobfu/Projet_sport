package gui;
import java.awt.EventQueue;
 
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Module.Utilisateur;
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
import java.util.List;
import java.awt.Window;
import java.awt.event.*;
import javax.swing.*;
 
public class AjouterUtilisateur extends JFrame implements ActionListener{
 
	private DBDAO dbdao ;
	private JPanel contentPane;
	private JTextField textField_1;
	private JButton btnAnnuler, btnAjouter;
	private JTextField textField;
	private JTextField textField_2;
	
	public AjouterUtilisateur() {
		dbdao = new DBDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//recuperation de l image depuis le fichier
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(305, 95, 263, 30);
		contentPane.add(textField_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(305, 152, 263, 30);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_1.setBounds(179, 151, 76, 30);
		contentPane.add(lblNewLabel_1_1);
		
		//JLButtons
		
		this.btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler.setBounds(148, 323, 140, 30);
		contentPane.add(btnAnnuler);
		btnAnnuler.addActionListener(this);
		
		this.btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAjouter.setBounds(428, 323, 140, 30);
		contentPane.add(btnAjouter);
		btnAjouter.addActionListener(this);
		
		//JTextfields
		
		textField_1 = new JTextField();
		textField_1.setBounds(305, 213, 263, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		//JLabels
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(102, 212, 158, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Nom d'utilisateur :");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setLabelFor(this);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(69, 94, 199, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Ajouter un utilisateur");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(165, 8, 347, 37);
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
		setLocationRelativeTo(null);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterUtilisateur frame = new AjouterUtilisateur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	@Override
	public void actionPerformed(ActionEvent ae) {
	    if(ae.getSource()==btnAjouter) {
	        String name = textField_2.getText();
	        String email = textField.getText();
	        String password = textField_1.getText();
	        if(dbdao.addUser(name, email, password)){
	            JOptionPane.showMessageDialog(null, "Utilisateur ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
	            GestionUtilisateurs frame = new GestionUtilisateurs();
	            frame.setVisible(true);
	            dispose();
	        } else {
	        	if(dbdao.verifNomUnique(name)) {
	        		JOptionPane.showMessageDialog(null, "Nom d'utilisateur déjà utilisé", "Erreur", JOptionPane.ERROR_MESSAGE);
	        	}else if(dbdao.verifMailUnique(email)){
	        		JOptionPane.showMessageDialog(null, "Email déjà utilisé", "Erreur", JOptionPane.ERROR_MESSAGE);
	        	}else {
	        		JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
	        	}
	        }
	    } else if(ae.getSource()==btnAnnuler) {
	    	GestionUtilisateurs frame = new GestionUtilisateurs();
	        frame.setVisible(true);            
	        dispose();
	    }
	}
}
