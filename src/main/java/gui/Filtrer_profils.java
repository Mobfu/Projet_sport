package gui;
 
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.Window;
import java.awt.event.*;
import javax.swing.*;
 
public class Filtrer_profils extends JFrame implements ActionListener{
 
	private JPanel contentPane;
	private JTextField textField_1;
	private JButton btnAnnuler, btnAppliquer;
	public Filtrer_profils() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//recuperation de l image depuis le fichier
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();

		String[] choix = {"administrateur", "élu", "acteurs du monde sportif"};
		JComboBox<String> comboBox = new JComboBox<>(choix);
		comboBox.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(279, 110, 335, 30);

 
		contentPane.add(comboBox);
		//JLButtons
		this.btnAnnuler = new JButton("annuler");
		btnAnnuler.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler.setBounds(126, 323, 140, 30);
		contentPane.add(btnAnnuler);
		btnAnnuler.addActionListener(this);
		this.btnAppliquer = new JButton("appliquer filtre");
		btnAppliquer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAppliquer.setBounds(381, 323, 222, 30);
		contentPane.add(btnAppliquer);
		btnAppliquer.addActionListener(this);
		//JTextfields
		textField_1 = new JTextField();
		textField_1.setBounds(279, 198, 335, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		//JLabels
		JLabel lblNewLabel_2 = new JLabel("nom d'utilisateur :");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(55, 199, 222, 27);
		contentPane.add(lblNewLabel_2);
		JLabel lblNewLabel_1 = new JLabel("rôle :");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setLabelFor(this);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(55, 112, 176, 27);
		contentPane.add(lblNewLabel_1);
		JLabel lblNewLabel = new JLabel("Filtrer les profils utilisateurs");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(149, 8, 417, 37);
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
		JLabel lblNewLabel_2_2 = new JLabel("rôle ");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_2_2.setBounds(198, 237, 79, 27);
		contentPane.add(lblNewLabel_2_2);
		setLocationRelativeTo(null);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Filtrer_profils frame = new Filtrer_profils();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btnAppliquer) {
			Liste_utilisateurs frame = new Liste_utilisateurs ();
			frame.setVisible(true);
			dispose();
		}else if(ae.getSource()==btnAnnuler) {
			Liste_utilisateurs frame = new Liste_utilisateurs ();
			frame.setVisible(true);
			dispose();
		}
	}
}
