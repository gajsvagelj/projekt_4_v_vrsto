import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PloscaPanel extends JPanel {

    
    private static final int VRSTICE = 6;
    private static final int STOLPCI = 7;

    private int getCelica() {
        return Math.min(getWidth() / STOLPCI, getHeight() / VRSTICE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics = (Graphics2D) g;

        // lunkje
        for (int v = 0; v < VRSTICE; v++) {
            for (int s = 0; s < STOLPCI; s++) {
                int x = s * getCelica();
                int y = v * getCelica();

                int polje = igra.getPlosca().getPolje(v, s);

                if (polje == 1) {
                    graphics.setColor(igra.getIgralec1().getBarva());
                } else if (polje == 2) {
                    graphics.setColor(igra.getIgralec2().getBarva());
                } else {
                    graphics.setColor(new Color(18, 18, 28)); // prazno
                }

                graphics.fillOval(x + 5, y + 5, getCelica() - 10, getCelica() - 10);
            }
        }
        
        // stolpci
        graphics.setColor(new Color(30, 60, 120).brighter());
        graphics.setStroke(new BasicStroke(2.0f));

        for (int s = 0; s < STOLPCI + 1; s++) {
            int x = s * getCelica();
            graphics.drawLine(x, 0, x, VRSTICE * getCelica());
        }
        
        // dno
        graphics.drawLine(0, VRSTICE * getCelica(), STOLPCI * getCelica(), VRSTICE * getCelica());
    }
    
    
    
    private Igra igra;
    private JLabel statusLabel;
    
    public PloscaPanel(Igra igra, JLabel statusLabel) {
        super();
        this.igra = igra;
        this.statusLabel = statusLabel;
        setBackground(new Color(30, 60, 120));

        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int stolpec = e.getX()  / getCelica();
                if (stolpec < 0 || stolpec >= STOLPCI) return;
                PloscaPanel.this.igra.odigrajPotezo(stolpec);
                posodobiStatus();
                repaint();
            }
        });
    }
    
    private void posodobiStatus() {
        if (igra.isKonecIgre()) {
            if (igra.isNeodloceno()) {
                statusLabel.setText("Izenačeno!");
            } else {
                statusLabel.setText("Zmagal je: " + igra.getZmagovalec().getIme());
            }
        } else {
            statusLabel.setText("Na vrsti: " + igra.getTrenutniIgralec().getIme());
        }
    }
    
    public void novaIgra() {
        igra = new Igra(igra.getIgralec1(), igra.getIgralec2());
        posodobiStatus();
        repaint();
    }
}
