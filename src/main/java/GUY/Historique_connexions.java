package GUY;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ProjetSport.DBDAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Historique_connexions extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnRetour, btnAppliquer;
	private JTable table; // 将 JTable 作为成员变量
    private JScrollPane scrollPane;
    private JComboBox<Integer> roleComboBox;
	DBDAO dbdao = new DBDAO();
	DefaultTableModel tableModel = dbdao.getTempLoginTableModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Historique_connexions frame = new Historique_connexions();
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
	public Historique_connexions() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		//recuperation de l image depuis le fichier
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		
		//JButtons
		
		this.btnAppliquer = new JButton("appliquer");
		btnAppliquer.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnAppliquer.setBounds(520, 74, 140, 30);
		contentPane.add(btnAppliquer);
		btnAppliquer.addActionListener(this);
		
		this.btnRetour = new JButton("retour");
		btnRetour.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		btnRetour.setBounds(282, 312, 140, 30);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(this);
		
		
		JLabel lblListeDesProfils = new JLabel("filtre de recherche :");
		lblListeDesProfils.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeDesProfils.setForeground(Color.WHITE);
		lblListeDesProfils.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		lblListeDesProfils.setBounds(29, 74, 300, 30);
		contentPane.add(lblListeDesProfils);
		
		if (tableModel != null) {
		    this.table = new JTable(tableModel);
		    this.scrollPane = new JScrollPane(table);
		    scrollPane.setBounds(29, 115, 631, 173);
		    contentPane.add(scrollPane);
		}
		
		this.roleComboBox = new JComboBox<>(new Integer[]{0, 1, 2, 3});
		roleComboBox.setBounds(308, 75, 201, 30); // 可以调整位置和大小
        contentPane.add(roleComboBox);
		
		JLabel lblNewLabel = new JLabel("connexions récentes au site web :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(107, 11, 483, 37);
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
			Integer selectedRole = (Integer)roleComboBox.getSelectedItem();
            // 调用 DBDAO 类中的新方法来获取过滤后的 DefaultTableModel
            DefaultTableModel filteredModel = dbdao.getTempLoginByRole(selectedRole);
            // 更新 JTable 的模型
            this.table.setModel(filteredModel);
		}
		
	}
}