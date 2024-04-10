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
import javax.swing.JScrollPane;

public class Liste_utilisateurs extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
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
		Image background = new ImageIcon(this.getClass().getResource("/fond.jpg")).getImage();
		
		JButton btnAppliquer = new JButton("appliquer");
		btnAppliquer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAppliquer.setBounds(520, 74, 140, 30);
		contentPane.add(btnAppliquer);
		
		textField = new JTextField();
		textField.setBounds(308, 75, 201, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblListeDesProfils = new JLabel("filtre de recherche :");
		lblListeDesProfils.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeDesProfils.setForeground(Color.WHITE);
		lblListeDesProfils.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		lblListeDesProfils.setBounds(29, 74, 300, 30);
		contentPane.add(lblListeDesProfils);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 115, 631, 173);
		contentPane.add(scrollPane);
		
		//JLabels contenant les textes et les textfields
		
		JButton btnAnnuler = new JButton("retour");
		btnAnnuler.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler.setBounds(282, 312, 140, 30);
		contentPane.add(btnAnnuler);
		
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
	}
}
