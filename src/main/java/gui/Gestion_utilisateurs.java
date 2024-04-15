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
import javax.swing.JScrollPane;

public class Gestion_utilisateurs extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnAjouter, btnModifier, btnSupprimer, btnRetour;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestion_utilisateurs frame = new Gestion_utilisateurs();
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
	public Gestion_utilisateurs() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		//recuperation de l image depuis le fichier
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		
		//JButtons
		
		this.btnAjouter = new JButton("ajouter");
		btnAjouter.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAjouter.setBounds(70, 258, 140, 30);
		contentPane.add(btnAjouter);
		btnAjouter.addActionListener(this);
		
		this.btnModifier = new JButton("modifier");
		btnModifier.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnModifier.setBounds(282, 258, 140, 30);
		contentPane.add(btnModifier);
		btnModifier.addActionListener(this);
		
		this.btnSupprimer = new JButton("supprimer");
		btnSupprimer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnSupprimer.setBounds(483, 258, 140, 30);
		contentPane.add(btnSupprimer);
		btnSupprimer.addActionListener(this);
		
		this.btnRetour = new JButton("retour");
		btnRetour.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnRetour.setBounds(282, 312, 140, 30);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(this);
		
		
		JLabel lblListeDesProfils = new JLabel("liste des profils");
		lblListeDesProfils.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeDesProfils.setForeground(Color.WHITE);
		lblListeDesProfils.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		lblListeDesProfils.setBounds(165, 56, 347, 30);
		contentPane.add(lblListeDesProfils);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 95, 631, 139);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Gestion des utilisateurs");
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

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btnAjouter) {
			System.out.println("fonction pas encore r¨¦alis¨¦e");
		}
		else if(ae.getSource()==btnModifier) {
			System.out.println("fonction pas encore r¨¦alis¨¦e");
		}
		else if(ae.getSource()==btnSupprimer) {
			System.out.println("fonction pas encore r¨¦alis¨¦e");
		}
		else if(ae.getSource()==btnRetour) {
			Menu_principal frame = new Menu_principal();
			frame.setVisible(true);
			dispose();
		}
	}
}