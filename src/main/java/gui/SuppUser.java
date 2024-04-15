 package gui;
 
import java.awt.EventQueue;
import ProjetSport.DBDAO;
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
 
public class SuppUser extends JFrame implements ActionListener{
	private DBDAO dbdao ;
	private JPanel contentPane;
	private JButton btnAnnuler, btnAjouter;
	private JTextField textField_2;
	private JLabel lblAfinDeSupprimer;
	
	public SuppUser() {
		dbdao = new DBDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//recuperation de l image depuis le fichier
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		
		lblAfinDeSupprimer = new JLabel("Afin de supprimer un utilisateur seul son username suffit");
		lblAfinDeSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAfinDeSupprimer.setForeground(Color.WHITE);
		lblAfinDeSupprimer.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblAfinDeSupprimer.setBounds(83, 127, 543, 37);
		contentPane.add(lblAfinDeSupprimer);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(293, 175, 263, 30);
		contentPane.add(textField_2);
		
		//JLButtons
		
		this.btnAnnuler = new JButton("annuler");
		btnAnnuler.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler.setBounds(148, 323, 140, 30);
		contentPane.add(btnAnnuler);
		btnAnnuler.addActionListener(this);
		
		this.btnAjouter = new JButton("supprimer");
		btnAjouter.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAjouter.setBounds(428, 323, 140, 30);
		contentPane.add(btnAjouter);
		btnAjouter.addActionListener(this);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setLabelFor(this);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(148, 174, 135, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Supprimer un utilisateur");
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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuppUser frame = new SuppUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btnAjouter) {
			String name = textField_2.getText();
			 dbdao.deleteUserByUsername(name);
			JOptionPane.showMessageDialog(null, "Utilisateur supprim¨¦ avec succ¨¨s !", "Succ¨¨s", JOptionPane.INFORMATION_MESSAGE);
			Gestion_utilisateurs frame = new Gestion_utilisateurs();
			frame.setVisible(true);
			dispose();
		}else if(ae.getSource()==btnAnnuler) {
			Gestion_utilisateurs frame = new Gestion_utilisateurs();
			frame.setVisible(true);			
			dispose();
		}
		
	}
}
 
