import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class OknoIgre extends JFrame {
	
	private JLabel statusLabel;
	private PloscaPanel plosca;

	public OknoIgre() {
	    super("Štiri v vrsto");

	    Igralec p1 = new Igralec("Rdeči", new Color(220, 50, 50));
	    Igralec p2 = new Igralec("Rumeni", new Color(230, 200, 0));
	    Igra igra = new Igra(p1, p2);

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(700, 600);
	    
	    setLocationRelativeTo(null);

	    // severni panel
        statusLabel = new JLabel("Na vrsti: " + igra.getTrenutniIgralec().getIme());
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(statusLabel, BorderLayout.NORTH);
        
        // gumb za novo igro
        JButton novaIgra = new JButton("Nova igra");
        novaIgra.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(novaIgra, BorderLayout.SOUTH);
        

        plosca = new PloscaPanel(igra, statusLabel);
        add(plosca);
        
        novaIgra.addActionListener(e -> {
            dispose(); // zapre trenutno okno
            new OknoIgre(); // ustvari novo
        });
        
        setVisible(true);
	}

    public static void main(String[] args) {
        new OknoIgre();
    }
}

