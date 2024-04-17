package gui;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Resultats_recherche_club extends JFrame {
    private JTextArea textArea;

    public Resultats_recherche_club(List<String> results) {
        setTitle("Résultats de la recherche");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
		Image background = new ImageIcon(this.getClass().getResource("fond.jpg")).getImage();
		JLabel backgroundLabel = new JLabel(new ImageIcon(background));
        getContentPane().add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        backgroundLabel.add(scrollPane, BorderLayout.CENTER);

        // Affichage des résultats dans le JTextArea
        for (String result : results) {
            textArea.append(result + "\n");
        }
    }
}
