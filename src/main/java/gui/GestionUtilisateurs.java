package gui;
 
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Module.Utilisateur;
import dao.DBDAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
 
public class GestionUtilisateurs extends JFrame implements ActionListener{
 
    private JPanel contentPane;
    private JButton btnAjouter, btnModifier, btnSupprimer, btnSupprimerMdp, btnRetour;
	private JTable tableUtilisateurs;
    private DefaultTableModel modelUtilisateurs;
    
 
    public GestionUtilisateurs() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 410);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
 
        this.btnAjouter = new JButton("Ajouter");
        btnAjouter.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        btnAjouter.setBounds(70, 258, 140, 30);
        contentPane.add(btnAjouter);
        btnAjouter.addActionListener(this);
 
        this.btnModifier = new JButton("Modifier");
        btnModifier.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        btnModifier.setBounds(282, 258, 140, 30);
        contentPane.add(btnModifier);
        btnModifier.addActionListener(this);
 
        this.btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        btnSupprimer.setBounds(483, 258, 154, 30);
        contentPane.add(btnSupprimer);
        btnSupprimer.addActionListener(this);
 
        this.btnSupprimerMdp = new JButton("Supprimer Mot de passe");
        btnSupprimerMdp.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        btnSupprimerMdp.setBounds(117, 317, 305, 30); // Positionn茅 au centre sur la deuxi猫me ligne
        contentPane.add(btnSupprimerMdp);
        btnSupprimerMdp.addActionListener(this);
 
        this.btnRetour = new JButton("Retour");
        btnRetour.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        btnRetour.setBounds(454, 317, 140, 30); // Positionn茅 au centre juste en bas
        contentPane.add(btnRetour);
        btnRetour.addActionListener(this);
 
        JLabel lblListeDesProfils = new JLabel("Liste des profils");
        lblListeDesProfils.setHorizontalAlignment(SwingConstants.CENTER);
        lblListeDesProfils.setForeground(Color.WHITE);
        lblListeDesProfils.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
        lblListeDesProfils.setBounds(165, 56, 347, 30);
        contentPane.add(lblListeDesProfils);
 
        //table d'utilisateurs
        
        modelUtilisateurs = new DefaultTableModel();
		modelUtilisateurs.addColumn("ID");
		modelUtilisateurs.addColumn("username");
		modelUtilisateurs.addColumn("email");
		modelUtilisateurs.addColumn("role");
		
		tableUtilisateurs = new JTable(modelUtilisateurs);
		JScrollPane scrollPane = new JScrollPane(tableUtilisateurs);
        scrollPane.setBounds(29, 95, 631, 139);
        contentPane.add(scrollPane);
 
        
        
        JLabel lblNewLabel = new JLabel("Gestion des utilisateurs");
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
        
		//récupération des données depuis la BDD mysql
		insertionDonnees();
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	GestionUtilisateurs frame = new GestionUtilisateurs();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
	public void insertionDonnees() {
		DBDAO dbdao = new DBDAO();
		List<Utilisateur> utilisateurs = dbdao.listeUtilisateurs();
		for(Utilisateur utilisateur : utilisateurs) {
			modelUtilisateurs.addRow(new Object[] {utilisateur.getIduser(), utilisateur.getUsername(), utilisateur.getEmail(), utilisateur.getUserrole()});
		}
	}
 
    @Override
 
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==btnAjouter) {
        	AjouterUtilisateur frame = new AjouterUtilisateur();
            frame.setVisible(true);
            dispose();
        } else if(ae.getSource()==btnModifier) {
        	ModifierUtilisateur frame = new ModifierUtilisateur();
            frame.setVisible(true);
            dispose();
        } else if(ae.getSource()==btnSupprimer) {
        	SupprimerUtilisateur frame = new SupprimerUtilisateur();
            frame.setVisible(true);
            dispose();
        } else if(ae.getSource()==btnSupprimerMdp) {
        	SupprimerMotDePasse frame = new SupprimerMotDePasse();
            frame.setVisible(true);
            dispose();
        } else if(ae.getSource()==btnRetour) {
            MenuPrincipal frame = new MenuPrincipal();
            frame.setVisible(true);
            dispose();
        }
    }
 
}
