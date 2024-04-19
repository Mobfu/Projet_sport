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

public class Liste_utilisateurs extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnRetour, btnAppliquer;
	private JTable tableUtilisateurs;
    private DefaultTableModel modelUtilisateurs;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Liste_utilisateurs frame = new Liste_utilisateurs();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Liste_utilisateurs() {
		
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
		
		//JButtons
		
		this.btnAppliquer = new JButton("appliquer un filtre");
		btnAppliquer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAppliquer.setBounds(356, 312, 236, 30);
		contentPane.add(btnAppliquer);
		btnAppliquer.addActionListener(this);
		
		this.btnRetour = new JButton("retour");
		btnRetour.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnRetour.setBounds(113, 312, 140, 30);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Liste des profils");
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
		
		//récupération des données depuis la BDD mysql
		insertionDonnees();

	}
	
	public void insertionDonnees() {
		DBDAO dbdao = new DBDAO();
		List<Utilisateur> utilisateurs = dbdao.listeUtilisateurs();
		for(Utilisateur utilisateur : utilisateurs) {
			modelUtilisateurs.addRow(new Object[] {utilisateur.getIduser(), utilisateur.getUsername(), utilisateur.getEmail(), utilisateur.getUserrole()});
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btnRetour) {
			Menu_principal frame = new Menu_principal();
			frame.setVisible(true);
			dispose();
		}
		else if(ae.getSource()==btnAppliquer) {
			Filtrer_profils frame = new Filtrer_profils();
			frame.setVisible(true);
			dispose();
		}
		
	}
}
