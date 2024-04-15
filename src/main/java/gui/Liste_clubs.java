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

public class Liste_clubs extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnRetour, btnAppliquer;
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
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		//recuperation de l image depuis le fichier
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		
		//JButtons
		
		this.btnAppliquer = new JButton("appliquer un filtre");
		btnAppliquer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAppliquer.setBounds(364, 312, 238, 30);
		contentPane.add(btnAppliquer);
		btnAppliquer.addActionListener(this);
		
		this.btnRetour = new JButton("retour");
		btnRetour.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnRetour.setBounds(105, 312, 140, 30);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 66, 631, 215);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Liste des clubs de sport");
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
