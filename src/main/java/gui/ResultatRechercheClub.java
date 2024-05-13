package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultatRechercheClub extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JButton btnRetour;

    public ResultatRechercheClub(List<String> results) {
        setTitle("Résultats de la recherche");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 410);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Titre en haut
        JLabel lblNewLabel = new JLabel("Résultats de la recherche");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblNewLabel, BorderLayout.NORTH);

        // Zone de texte au centre
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Affichage des résultats dans la JTextArea
        for (String result : results) {
            textArea.append(result + "\n");
        }

        // Bouton en bas
        btnRetour = new JButton("Retour \u00E0 la liste des clubs");
        btnRetour.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        getContentPane().add(btnRetour, BorderLayout.SOUTH);
        btnRetour.addActionListener(this);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<String> results = List.of("Résultat 1", "Résultat 2", "Résultat 3");
            new ResultatRechercheClub(results);
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnRetour) {
            ListeClubs frame = new ListeClubs();
            frame.setVisible(true);
            dispose();
        }
    }
}
