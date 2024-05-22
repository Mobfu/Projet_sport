package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
import Module.Club;
import Module.Utilisateur;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class ListeClubs extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnRetour, btnAppliquer;
	private JTable tableClubs;
    private DefaultTableModel modelClubs;
    private JLabel lblNewLabel_2;
    private JTextField textFieldLieu;
    private JLabel lblNewLabel_3;
    private JComboBox<String> comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeClubs frame = new ListeClubs();
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
	public ListeClubs() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 999, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		//recuperation de l image depuis le fichier
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		
		//partie insertion des données dans la JTable
		
		modelClubs = new DefaultTableModel();
		modelClubs.addColumn("Idclub");
		modelClubs.addColumn("code_commune");
		modelClubs.addColumn("nom_commune");
		modelClubs.addColumn("code_qpv");
		modelClubs.addColumn("nom_qpv");
		modelClubs.addColumn("departement");
		modelClubs.addColumn("region");
		modelClubs.addColumn("statut_geo");
		modelClubs.addColumn("code_fede");
		modelClubs.addColumn("nom_federation");
		modelClubs.addColumn("nbr_clubs");
		modelClubs.addColumn("nbr_epa");
		modelClubs.addColumn("total_epa_clubs");
		
		
		//récupération du nom des différentes fédérations et suppression des doublons
		
		this.comboBox=new JComboBox<>();
		
		DBDAO dbdao = new DBDAO();
		List<Club> maListe=dbdao.listeClubs();
		List<String> maListe2 = new ArrayList<>();
		for(Club club : maListe) {
			maListe2.add(club.getNom_federation());
		}
		HashSet<String> ensembleSansDoublons = new HashSet<>(maListe2);
		ArrayList<String> listeSansDoublons = new ArrayList<>(ensembleSansDoublons);
		Collections.sort(listeSansDoublons);
		listeSansDoublons.add(0, "");
		for (String valeur : listeSansDoublons) {
			comboBox.addItem(valeur);
		}
			
		comboBox.setBounds(230, 262, 238, 26);
		contentPane.add(comboBox);
		
		lblNewLabel_3 = new JLabel("d\u00E9partement, commune) :");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_3.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_3.setBounds(477, 271, 265, 30);
		contentPane.add(lblNewLabel_3);
		
		textFieldLieu = new JTextField();
		textFieldLieu.setColumns(10);
		textFieldLieu.setBounds(737, 262, 238, 26);
		contentPane.add(textFieldLieu);
		
		lblNewLabel_2 = new JLabel("Lieu (r\u00E9gion,");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2.setBounds(544, 245, 140, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Nom de la f\u00E9d\u00E9ration :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1.setBounds(0, 259, 238, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Appliquer un filtre de recherche ");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_2.setBounds(333, 215, 329, 30);
		contentPane.add(lblNewLabel_1_2);
		tableClubs = new JTable(modelClubs);
		JScrollPane scrollPane = new JScrollPane(tableClubs);
		contentPane.add(scrollPane);
		scrollPane.setBounds(0, 56, 985, 148);
		contentPane.add(scrollPane);
		
		//JButtons
		
		this.btnAppliquer = new JButton("Appliquer filtre");
		btnAppliquer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAppliquer.setBounds(619, 312, 238, 30);
		contentPane.add(btnAppliquer);
		btnAppliquer.addActionListener(this);
		
		this.btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnRetour.setBounds(137, 312, 140, 30);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Liste des clubs de sport");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(326, 8, 347, 37);
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
		imageLabel.setBounds(0, 0, 992, 373);
		setLocationRelativeTo(null);
		
		//récupération des données depuis la BDD mysql
		insertionDonnees("", "");
	}
	
	public void insertionDonnees(String lieu, String nom_federation) {
		DBDAO dbdao = new DBDAO();
		if(lieu.equals("")&&nom_federation.equals("")) {
			List<Club> clubs = dbdao.listeClubs();
			for (Club club : clubs) {
				modelClubs.addRow(new Object[] {club.getIdclub(), club.getCode_commune(), club.getNom_commune(), club.getCode_qpv(), club.getNom_qpv(), club.getDeprtement(), club.getRegion(), club.getStatut_geo(), club.getCode_fede(), club.getNom_federation(), club.getNbr_clubs(), club.getNbr_epa(), club.getTotal_epa_clubs()});
			}
		}else {
			List<Club> clubs=dbdao.ResultatRechercheClub(lieu, nom_federation);
			for (Club club : clubs) {
				modelClubs.addRow(new Object[] {club.getIdclub(), club.getCode_commune(), club.getNom_commune(), club.getCode_qpv(), club.getNom_qpv(), club.getDeprtement(), club.getRegion(), club.getStatut_geo(), club.getCode_fede(), club.getNom_federation(), club.getNbr_clubs(), club.getNbr_epa(), club.getTotal_epa_clubs()});
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
			String nom_federation=(String) comboBox.getSelectedItem();
			String lieu=textFieldLieu.getText();
			modelClubs.setRowCount(0);
			insertionDonnees(lieu, nom_federation);
		}
		
	}
}
