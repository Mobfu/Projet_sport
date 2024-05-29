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
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SendEmailNotification extends JFrame implements ActionListener {
    private DBDAO dbdao;
    private JPanel contentPane;
    private JTextField emailField;
    private JTextField subjectField;
    private JTextField messageField;
    private JButton btnSend, btnCancel;

    public SendEmailNotification() {
        dbdao = new DBDAO();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 410);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();

        JLabel lblInstruction = new JLabel("Envoyer une notification par email");
        lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
        lblInstruction.setForeground(Color.WHITE);
        lblInstruction.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        lblInstruction.setBounds(83, 20, 543, 37);
        contentPane.add(lblInstruction);

        JLabel lblEmail = new JLabel("Adresse email :");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setBounds(93, 80, 190, 30);
        contentPane.add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(293, 80, 263, 30);
        contentPane.add(emailField);
        emailField.setColumns(10);

        JLabel lblSubject = new JLabel("Objet :");
        lblSubject.setForeground(Color.WHITE);
        lblSubject.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        lblSubject.setHorizontalAlignment(SwingConstants.LEFT);
        lblSubject.setBounds(93, 130, 190, 30);
        contentPane.add(lblSubject);

        subjectField = new JTextField();
        subjectField.setBounds(293, 130, 263, 30);
        contentPane.add(subjectField);
        subjectField.setColumns(10);

        JLabel lblMessage = new JLabel("Message :");
        lblMessage.setForeground(Color.WHITE);
        lblMessage.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        lblMessage.setHorizontalAlignment(SwingConstants.LEFT);
        lblMessage.setBounds(93, 180, 190, 30);
        contentPane.add(lblMessage);

        messageField = new JTextField();
        messageField.setBounds(293, 180, 263, 100);
        contentPane.add(messageField);
        messageField.setColumns(10);

        btnSend = new JButton("Envoyer");
        btnSend.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        btnSend.setBounds(428, 323, 140, 30);
        contentPane.add(btnSend);
        btnSend.addActionListener(this);

        btnCancel = new JButton("Annuler");
        btnCancel.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        btnCancel.setBounds(148, 323, 140, 30);
        contentPane.add(btnCancel);
        btnCancel.addActionListener(this);

        JLabel imageLabel = new JLabel("");
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setIcon(new ImageIcon(background));
        contentPane.add(imageLabel);
        imageLabel.setBounds(0, 0, 686, 373);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SendEmailNotification frame = new SendEmailNotification();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSend) {
            String email = emailField.getText();
            String subject = subjectField.getText();
            String message = messageField.getText();

            try {
                if (dbdao.verifyEmailExists(email)) {
                    dbdao.sendEmailNotification(email, subject, message);
                    JOptionPane.showMessageDialog(null, "Notification envoyée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Email introuvable", "Erreur", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnCancel) {
            dispose();
        }
    }
}
