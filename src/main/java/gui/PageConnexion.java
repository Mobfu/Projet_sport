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
import javax.swing.JPasswordField;
public class PageConnexion extends JFrame implements ActionListener{
	private DBDAO dbdao ;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnAnnuler, btnConnexion, icone;
	private JPasswordField textField_1;
	private boolean MDPVisible = false;
	public static String session=null;
	public PageConnexion() {
		dbdao = new DBDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//recuperation de l image depuis le fichier
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		 // Charger et redimensionner l'image
        ImageIcon imageDeBase = new ImageIcon(getClass().getResource("/gui/eye.png"));
        Image oeilimage = imageDeBase.getImage().getScaledInstance(40, 23, Image.SCALE_SMOOTH);
        ImageIcon oeil2 = new ImageIcon(oeilimage);
 
        // Créer le bouton avec l'icône redimensionnée
        this.icone = new JButton(oeil2);
        icone.setBounds(536, 228, 31, 30);
        contentPane.add(icone);
        icone.addActionListener(this);
		textField_1 = new JPasswordField();
		textField_1.setBounds(359, 228, 170, 30);
		contentPane.add(textField_1);
		//JLButtons
		this.btnAnnuler = new JButton("Fermer l'application");
		btnAnnuler.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAnnuler.setBounds(82, 323, 234, 30);
		contentPane.add(btnAnnuler);
		btnAnnuler.addActionListener(this);
		this.btnConnexion = new JButton("Connexion");
		btnConnexion.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnConnexion.setBounds(446, 323, 140, 30);
		contentPane.add(btnConnexion);
		btnConnexion.addActionListener(this);
		textField = new JTextField();
		textField.setBounds(359, 129, 170, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		//JLabels
		JLabel lblNewLabel_2 = new JLabel("Mot de passe :");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(199, 231, 158, 27);
		contentPane.add(lblNewLabel_2);
		JLabel lblNewLabel_1 = new JLabel("Nom d'utilisateur ou adresse mail :");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setLabelFor(this);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 130, 349, 27);
		contentPane.add(lblNewLabel_1);
		JLabel lblNewLabel = new JLabel("Accueil administrateur");
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
					PageConnexion frame = new PageConnexion();
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
	        session = username;
	        System.out.println(session);
	        String type_action="connexion";
	        if((dbdao.checkUserA(username, password)) && (dbdao.checkPassword(username, password))) {
	        	JOptionPane.showMessageDialog(null, "Connexion réussie !", "Bienvenue Mr/Mme " + username,JOptionPane.INFORMATION_MESSAGE);
	            dbdao.AjoutAction(type_action, session);
	        	MenuPrincipal frame=new MenuPrincipal();
	            frame.setVisible(true);
	            dispose();
	        } else {
	        	JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect", " Connexion échouée.",JOptionPane.INFORMATION_MESSAGE);
	        }
	    } else if(ae.getSource()==btnAnnuler) {
	    	dispose();
	    }else if(ae.getSource()==icone) {
	    	 if (MDPVisible) {
	    		 textField_1.setEchoChar('*');
	    		 MDPVisible = false;
	         } else {
	        	 textField_1.setEchoChar((char) 0);
	        	 MDPVisible = true;
	         }
	    }
	}
}