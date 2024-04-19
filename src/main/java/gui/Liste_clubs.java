package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Liste_clubs extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnRetour, btnAppliquer;
	private JTable tableClubs;
    private DefaultTableModel modelClubs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Liste_clubs frame = new Liste_clubs();
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
	public Liste_clubs() {
		
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
		modelClubs.addColumn("deprtement");
		modelClubs.addColumn("region");
		modelClubs.addColumn("statut_geo");
		modelClubs.addColumn("code_fede");
		modelClubs.addColumn("nom_federation");
		modelClubs.addColumn("nbr_clubs");
		modelClubs.addColumn("nbr_epa");
		modelClubs.addColumn("total_epa_clubs");
		tableClubs = new JTable(modelClubs);
		JScrollPane scrollPane = new JScrollPane(tableClubs);
		contentPane.add(scrollPane);
		scrollPane.setBounds(0, 56, 985, 234);
		contentPane.add(scrollPane);
		
		//JButtons
		
		this.btnAppliquer = new JButton("appliquer un filtre");
		btnAppliquer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAppliquer.setBounds(619, 312, 238, 30);
		contentPane.add(btnAppliquer);
		btnAppliquer.addActionListener(this);
		
		this.btnRetour = new JButton("retour");
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
		insertionDonnees();
	}
	
	public void insertionDonnees() {
		DBDAO dbdao = new DBDAO();
		List<Club> clubs = dbdao.listeClubs();
		for(Club club : clubs) {
			modelClubs.addRow(new Object[] {club.getIdclub(), club.getCode_commune(), club.getDeprtement(), club.getCode_fede(), club.getNbr_clubs(), club.getNbr_epa(), club.getTotal_epa_clubs(), club.getNom_commune(), club.getRegion(), club.getStatut_geo(), club.getNom_federation(), club.getCode_qpv(), club.getNom_qpv()});
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
			Filtrer_clubs frame = new Filtrer_clubs();
			frame.setVisible(true);
			dispose();
		}
		
	}
}
