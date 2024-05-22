package gui;
 
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
 
public class MenuPrincipal extends JFrame implements ActionListener{
	 
	private JPanel contentPane;
	private JButton btnDeconnexion, btnHistoriqueConnexion, btnAffichageClub, btnAffichageUtilisateurs, btnGestionUtilisateurs;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
 
		//recuperation de l image depuis le fichier
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/gui/gestion (1).jpg")));
		lblNewLabel_3.setBounds(86, 208, 100, 100);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/gui/info (1).png")));
		lblNewLabel_2.setBounds(463, 44, 131, 137);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblGestionsConnexionsEt = new JLabel("Gestion connexions et profils");
		lblGestionsConnexionsEt.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionsConnexionsEt.setForeground(Color.WHITE);
		lblGestionsConnexionsEt.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		lblGestionsConnexionsEt.setBounds(299, 175, 355, 37);
		contentPane.add(lblGestionsConnexionsEt);
		JLabel lblConsulterDesInformations = new JLabel("Consulter des informations");
		lblConsulterDesInformations.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulterDesInformations.setForeground(Color.WHITE);
		lblConsulterDesInformations.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		lblConsulterDesInformations.setBounds(24, 56, 355, 37);
		contentPane.add(lblConsulterDesInformations);
		//JButtons
		this.btnHistoriqueConnexion = new JButton("Historique des connexions");
		btnHistoriqueConnexion.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnHistoriqueConnexion.setBounds(299, 218, 355, 30);
		contentPane.add(btnHistoriqueConnexion);
		btnHistoriqueConnexion.addActionListener(this);
		this.btnAffichageClub = new JButton("Afficher les clubs de sport");
		btnAffichageClub.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAffichageClub.setBounds(24, 134, 355, 30);
		contentPane.add(btnAffichageClub);
		btnAffichageClub.addActionListener(this);
		this.btnAffichageUtilisateurs = new JButton("Afficher une liste d'utilisateurs");
		btnAffichageUtilisateurs.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAffichageUtilisateurs.setBounds(24, 93, 355, 30);
		contentPane.add(btnAffichageUtilisateurs);
		btnAffichageUtilisateurs.addActionListener(this);
		this.btnGestionUtilisateurs = new JButton("Gérer les utilisateurs");
		btnGestionUtilisateurs.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnGestionUtilisateurs.setBounds(299, 259, 355, 30);
		contentPane.add(btnGestionUtilisateurs);
		btnGestionUtilisateurs.addActionListener(this);
		this.btnDeconnexion = new JButton("Déconnexion");
		btnDeconnexion.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnDeconnexion.setBounds(261, 312, 163, 30);
		contentPane.add(btnDeconnexion);
		btnDeconnexion.addActionListener(this);
		//JLabels
		JLabel lblNewLabel = new JLabel("Menu principal");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(165, 8, 347, 37);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
 
	
	//methode permettant d atttribuer des actions aux boutons
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btnDeconnexion) {
			PageConnexion frame = new PageConnexion();
			frame.setVisible(true);
			dispose();
		}else if(ae.getSource()==btnHistoriqueConnexion) {
			HistoriqueConnexions frame = new HistoriqueConnexions();
			frame.setVisible(true);
			dispose();
		}
		else if(ae.getSource()==btnAffichageClub) {
			ListeClubs frame = new ListeClubs();
			frame.setVisible(true);
			dispose();
				}
		else if(ae.getSource()==btnAffichageUtilisateurs) {
			ListeUtilisateurs frame = new ListeUtilisateurs();
			frame.setVisible(true);
			dispose();
		}
		else if(ae.getSource()==btnGestionUtilisateurs) {
			GestionUtilisateurs frame = new GestionUtilisateurs();
			frame.setVisible(true);
			dispose();
		}		
	}
}
