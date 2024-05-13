 package gui;
 import java.awt.EventQueue;
import dao.DBDAO;
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
 
public class SupprimerMotDePasse extends JFrame implements ActionListener{
 
	private DBDAO dbdao ;
	private JPanel contentPane;
	private JButton btnAnnuler, btnAjouter;
	private JTextField textField_2;
	private JLabel lblAfinDeSupprimer;
	
	public SupprimerMotDePasse() {
		dbdao = new DBDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//recuperation de l image depuis le fichier
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		
		lblAfinDeSupprimer = new JLabel("Afin de supprimer un mot de passe le nom de l'utilisateur suffit");
		lblAfinDeSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAfinDeSupprimer.setForeground(Color.WHITE);
		lblAfinDeSupprimer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblAfinDeSupprimer.setBounds(10, 127, 666, 37);
		contentPane.add(lblAfinDeSupprimer);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(293, 175, 263, 30);
		contentPane.add(textField_2);
		
		//JLButtons
		
		this.btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler.setBounds(148, 323, 140, 30);
		contentPane.add(btnAnnuler);
		btnAnnuler.addActionListener(this);
		
		this.btnAjouter = new JButton("Supprimer");
		btnAjouter.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAjouter.setBounds(428, 323, 140, 30);
		contentPane.add(btnAjouter);
		btnAjouter.addActionListener(this);
		
		JLabel lblNewLabel_1 = new JLabel("Nom de l'utilisateur :");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setLabelFor(this);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(67, 174, 216, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Supprimer  le mot de passe d'un utilisateur");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 8, 666, 37);
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
					SupprimerMotDePasse frame = new SupprimerMotDePasse();
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
			dbdao.deleteUserPassword(name);
			JOptionPane.showMessageDialog(null, "Mot de passe de l'utilisateur supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
			MenuPrincipal frame=new MenuPrincipal();
			frame.setVisible(true);
			dispose();
		}else if(ae.getSource()==btnAnnuler) {
			GestionUtilisateurs frame = new GestionUtilisateurs();
			frame.setVisible(true);			
			dispose();
		}
		
	}
}
