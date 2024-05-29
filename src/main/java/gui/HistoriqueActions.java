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
import javax.swing.table.DefaultTableModel;
 
import dao.DBDAO;
import Module.Action;
import Module.Utilisateur;
 
import javax.swing.JScrollPane;
 
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JComboBox;
 
public class HistoriqueActions extends JFrame implements ActionListener{
 
	private JPanel contentPane;
	private JButton btnRetour, btnAppliquer;
	private JTable tableActions;
    private DefaultTableModel modelActions;
    private JTextField textFieldNom;
    private JComboBox<String> combobox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoriqueActions frame = new HistoriqueActions();
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
	public HistoriqueActions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
 
		
		//recuperation de l image depuis le fichier
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		//partie insertion des données dans la JTable
		this.modelActions = new DefaultTableModel();
		modelActions.addColumn("ID");
		modelActions.addColumn("type d'action");
		modelActions.addColumn("nom de l'utilisateur");
		modelActions.addColumn("heure");
		modelActions.addColumn("adresse ip");
		String[] action = {"", "connexion","deconnexion", "ajout d'un utilisateur","modification d'un utilisateur", "supression d'un utilisateur"};
		this.combobox = new JComboBox<>(action);
		combobox.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		combobox.setMaximumRowCount(5);
		combobox.setBounds(314, 270, 286, 26);
		contentPane.add(combobox);
		JLabel lblNewLabel_1_2 = new JLabel("Appliquer un filtre de recherche ");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_2.setBounds(195, 189, 329, 30);
		contentPane.add(lblNewLabel_1_2);
		JLabel lblNewLabel_1_1 = new JLabel("Action effectu\u00E9e :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_1.setBounds(90, 268, 189, 30);
		contentPane.add(lblNewLabel_1_1);
		JLabel lblNewLabel_1 = new JLabel("Nom d'utilisateur :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblNewLabel_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1.setBounds(80, 227, 199, 30);
		contentPane.add(lblNewLabel_1);
		textFieldNom = new JTextField();
		textFieldNom.setBounds(314, 230, 285, 26);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		tableActions = new JTable(modelActions);
		JScrollPane scrollPane = new JScrollPane(tableActions);
		contentPane.add(scrollPane);
		scrollPane.setBounds(10, 56, 666, 122);
		contentPane.add(scrollPane);
		//JButtons
		this.btnAppliquer = new JButton("Appliquer filtre");
		btnAppliquer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAppliquer.setBounds(356, 312, 236, 30);
		contentPane.add(btnAppliquer);
		btnAppliquer.addActionListener(this);
		this.btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnRetour.setBounds(113, 312, 140, 30);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(this);
		JLabel lblNewLabel = new JLabel("historique des actions des administrateurs");
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
		//récupération des données depuis la BDD mysql
		insertionDonnees("", "");
	}
	public void insertionDonnees(String type_action, String nom_utilisateur) {
		DBDAO dbdao = new DBDAO();
		if (type_action.equals("")&&nom_utilisateur.equals("")) {
			List<Action> actions = dbdao.listeActions();
			for(Action action : actions) {
				modelActions.addRow(new Object[] {action.getIdAction(), action.getType_action(), action.getNom_utilisateur(), action.getTemps(), action.getAdresse_ip()});
			}
		}else {
			List<Action> actions=dbdao.ResultatRechercheAction(type_action, nom_utilisateur);
			for(Action action : actions) {
				modelActions.addRow(new Object[] {action.getIdAction(), action.getType_action(), action.getNom_utilisateur(), action.getTemps(), action.getAdresse_ip()});
			}
		}
	}
	/*public void insertionDonnees() {
		DBDAO dbdao = new DBDAO();
		List <Action> actions = dbdao.listeActions();
		for (Action action : actions) {
			modelActions.addRow(new Object[] {action.getIdAction(), action.getType_action(), action.getNom_utilisateur(), action.getTemps(), action.getAdresse_ip()});
		}
	}*/
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btnRetour) {
			MenuPrincipal frame = new MenuPrincipal();
			frame.setVisible(true);
			dispose();
		}
		else if(ae.getSource()==btnAppliquer) {
			String nom_utilisateur=textFieldNom.getText();
			String type_action=(String) combobox.getSelectedItem();
			modelActions.setRowCount(0);
			insertionDonnees(type_action, nom_utilisateur);
		}
	}
}
