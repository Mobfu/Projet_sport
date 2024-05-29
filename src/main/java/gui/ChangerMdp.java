package gui;
import dao.DBDAO;
 
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
 
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;
 
public class ChangerMdp extends JFrame implements ActionListener{
 
	private DBDAO dbdao ;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnAnnuler, btnConnexion;
	
	
	public ChangerMdp() {
		dbdao = new DBDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//recuperation de l image depuis le fichier
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		
		//JLButtons
		
		this.btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler.setBounds(148, 323, 140, 30);
		contentPane.add(btnAnnuler);
		btnAnnuler.addActionListener(this);
		
		this.btnConnexion = new JButton("Changer");
		btnConnexion.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnConnexion.setBounds(428, 323, 140, 30);
		contentPane.add(btnConnexion);
		btnConnexion.addActionListener(this);
		
		//JTextfields
		
		textField_1 = new JTextField();
		textField_1.setBounds(359, 228, 170, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(359, 129, 170, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//JLabels
		
		JLabel lblNewLabel_2 = new JLabel("mot de passe :");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(165, 229, 158, 27);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("login :");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setLabelFor(this);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(188, 130, 81, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Changer mot de passe administrateur");
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
					ChangerMdp frame = new ChangerMdp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	@Override
	public void actionPerformed(ActionEvent ae) {
	    if(ae.getSource()==btnConnexion) {
	        String username = textField.getText();
	        String password = textField_1.getText();
	        if(dbdao.updatePassword(username, password)) {
	        	JOptionPane.showMessageDialog(null, "Mot de passe changé !", "Vous allez être rediriger vers la page de connexion" + username,JOptionPane.INFORMATION_MESSAGE);
	        	Page_Connexion frame = new Page_Connexion();
	            frame.setVisible(true);
	            dispose();
	        } else {
	        	JOptionPane.showMessageDialog(null, "Erreur dans la saisie mot de passe incorrect", " Connexion 茅chou茅e.",JOptionPane.INFORMATION_MESSAGE);
	        }
	    } else if(ae.getSource()==btnAnnuler) {
	        dispose();
	    }
	}
 
 
}
