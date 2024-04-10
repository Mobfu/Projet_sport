package GUY;

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

public class Menu_principal extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_principal frame = new Menu_principal();
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
	public Menu_principal() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		//recuperation de l image depuis le fichier
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("/fond.jpg")).getImage();
		
		JButton btnAnnuler_1_2 = new JButton("gestion de clubs");
		btnAnnuler_1_2.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler_1_2.setBounds(394, 232, 251, 30);
		contentPane.add(btnAnnuler_1_2);
		
		JButton btnAnnuler_1_1_1 = new JButton("afficher les clubs de sport");
		btnAnnuler_1_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler_1_1_1.setBounds(10, 232, 355, 30);
		contentPane.add(btnAnnuler_1_1_1);
		
		JButton btnAnnuler_1_1 = new JButton("afficher une liste d'utilisateurs");
		btnAnnuler_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler_1_1.setBounds(10, 125, 355, 30);
		contentPane.add(btnAnnuler_1_1);
		
		JButton btnAnnuler_1 = new JButton("gérer les utilisateurs");
		btnAnnuler_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler_1.setBounds(394, 125, 251, 30);
		contentPane.add(btnAnnuler_1);
		
		//JLabels contenant les textes et les textfields
		
		JButton btnAnnuler = new JButton("déconnexion");
		btnAnnuler.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler.setBounds(261, 312, 163, 30);
		contentPane.add(btnAnnuler);
		
		JLabel lblNewLabel = new JLabel("Menu principal");
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

}
