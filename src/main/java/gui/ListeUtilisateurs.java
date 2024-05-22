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
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class ListeUtilisateurs extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnRetour, btnAppliquer;
	private JTable tableUtilisateurs;
    private DefaultTableModel modelUtilisateurs;
    private JTextField textFieldNom;
    private JComboBox<String> combobox;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeUtilisateurs frame = new ListeUtilisateurs();
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
	public ListeUtilisateurs() {
		
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
		
		String[] choix = {"", "Administrateur","Sportif", "Membre Ministère Sport","Elu"};
		this.combobox = new JComboBox<>(choix);
		combobox.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		combobox.setMaximumRowCount(5);
		combobox.setBounds(500, 241, 176, 26);
		contentPane.add(combobox);
		
		JLabel lblNewLabel_1_2 = new JLabel("Appliquer un filtre de recherche ");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_2.setBounds(195, 189, 329, 30);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("R\u00F4le :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_1.setBounds(420, 241, 70, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Nom d'utilisateur :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1.setBounds(10, 238, 199, 30);
		contentPane.add(lblNewLabel_1);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(200, 241, 176, 26);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		tableUtilisateurs = new JTable(modelUtilisateurs);
		JScrollPane scrollPane = new JScrollPane(tableUtilisateurs);
		contentPane.add(scrollPane);
		scrollPane.setBounds(10, 56, 666, 122);
		contentPane.add(scrollPane);
		
		//JButtons
		
		this.btnAppliquer = new JButton("Appliquer filtre");
		btnAppliquer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAppliquer.setBounds(356, 312, 236, 30);
		contentPane.add(btnAppliquer);
		btnAppliquer.addActionListener(this);
		
		this.btnRetour = new JButton("Retour");
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
		insertionDonnees("", "");
	}
	
	public void insertionDonnees(String role, String nom) {
		DBDAO dbdao = new DBDAO();
		if (nom.equals("")&&role.equals("")) {
			List<Utilisateur> utilisateurs = dbdao.listeUtilisateurs();
			for(Utilisateur utilisateur : utilisateurs) {
				String a="pas encore défini";
				switch(utilisateur.getUserrole()) {
					case 0 :
						a="administrateur";
						break;
					case 1 :
						a="sportif";
						break;
					case 2 :a="ministère du sport";
						break;
					case 3 : a="élu";
						break;
				}
				modelUtilisateurs.addRow(new Object[] {utilisateur.getIduser(), utilisateur.getUsername(), utilisateur.getEmail(), a});
			}
		}else {
			List<Utilisateur> utilisateurs=dbdao.ResultatRecherche(role, nom);
			for(Utilisateur utilisateur : utilisateurs) {
				String a="pas encore défini";
				switch(utilisateur.getUserrole()) {
					case 0 :
						a="administrateur";
						break;
					case 1 :
						a="sportif";
						break;
					case 2 :a="ministère du sport";
						break;
					case 3 : a="élu";
						break;
				}
				modelUtilisateurs.addRow(new Object[] {utilisateur.getIduser(), utilisateur.getUsername(), utilisateur.getEmail(), a});
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btnRetour) {
			MenuPrincipal frame = new MenuPrincipal();
			frame.setVisible(true);
			dispose();
		}
		else if(ae.getSource()==btnAppliquer) {
			String nom=textFieldNom.getText();
			String role=(String) combobox.getSelectedItem();
			modelUtilisateurs.setRowCount(0);
			insertionDonnees(role, nom);
		}
		
	}
}
