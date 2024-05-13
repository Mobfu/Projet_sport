package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DBDAO;
import Module.Utilisateur;

import javax.swing.JScrollPane;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JTable;

public class ResultatRechercheUtilisateur extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnRetour;
	private JTable tableUtilisateurs;
    private DefaultTableModel modelUtilisateurs;
    
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resultat_recherche_utilisateur frame = new Resultat_recherche_utilisateur(List<Utilisateur> resultats);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ResultatRechercheUtilisateur(List<Utilisateur> resultats) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		//recuperation de l image depuis le fichier
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		
		//partie insertion des données dans la JTable
		
		modelUtilisateurs = new DefaultTableModel();
		modelUtilisateurs.addColumn("ID");
		modelUtilisateurs.addColumn("username");
		modelUtilisateurs.addColumn("email");
		modelUtilisateurs.addColumn("role");
		
		tableUtilisateurs = new JTable(modelUtilisateurs);
		JScrollPane scrollPane = new JScrollPane(tableUtilisateurs);
		contentPane.add(scrollPane);
		scrollPane.setBounds(10, 56, 666, 234);
		contentPane.add(scrollPane);
		
		this.btnRetour = new JButton("Retour \u00E0 la liste des utilisateurs");
		btnRetour.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnRetour.setBounds(152, 316, 426, 30);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("R\u00E9sultat de la recherche");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(152, 8, 387, 37);
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
		
		//récupération des données depuis la BDD mysql
		//insertionDonnees();
		for(Utilisateur utilisateur : resultats) {
			modelUtilisateurs.addRow(new Object[] {utilisateur.getIduser(), utilisateur.getUsername(), utilisateur.getEmail(), utilisateur.getUserrole()});
		}

	}
	
	/*public void insertionDonnees() {
		DBDAO dbdao = new DBDAO();
		String username, userrole;
		List<Utilisateur> resultats = dbdao.ResultatRecherche(userrole, username);
		for(Utilisateur utilisateur : resultats) {
			modelUtilisateurs.addRow(new Object[] {utilisateur.getIduser(), utilisateur.getUsername(), utilisateur.getEmail(), utilisateur.getUserrole()});
		}
	}*/
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btnRetour) {
			ListeUtilisateurs frame = new ListeUtilisateurs();
			frame.setVisible(true);
			dispose();		
		}
	}
}
