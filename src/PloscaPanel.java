import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
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
        
        // animacija žetonov
        if (animacijaAktivna) {
            int x = animacijaStolpec * getCelica();
            int y = (int)(animacijaTrenutnaVrstica * getCelica());
            graphics.setColor(animacijaBarva);
            graphics.fillOval(x + 5, y + 5, getCelica() - 10, getCelica() - 10);
        }
    }
    
    
    
    private Igra igra;
    private JLabel statusLabel;
    
    // atributi za animacijo
    private boolean animacijaAktivna = false;
    private int animacijaStolpec;
    private int animacijaVrstica;
    private double animacijaTrenutnaVrstica;
    private Color animacijaBarva;
    private Timer animacijaTimer;
    
    public PloscaPanel(Igra igra, JLabel statusLabel) {
        super();
        this.igra = igra;
        this.statusLabel = statusLabel;
        setBackground(new Color(30, 60, 120));

        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	if (animacijaAktivna) return;
                
                // klik izven plošče
                int stolpec = e.getX() / getCelica();
                if (stolpec < 0 || stolpec >= STOLPCI) return;
                
                // poln stolpec
                int vrstica = igra.getCiljnaVrstica(stolpec);
                if (vrstica == -1) return;
                
                // konec igre
                if (igra.isKonecIgre()) return;

                zacniAnimacijo(stolpec, vrstica);
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
    
    private void zacniAnimacijo(int stolpec, int vrstica) {
        animacijaStolpec = stolpec;
        animacijaVrstica = vrstica;
        animacijaBarva = igra.getTrenutniIgralec().getBarva();
        animacijaTrenutnaVrstica = 0;
        animacijaAktivna = true;

        animacijaTimer = new Timer(30, e -> {
            animacijaTrenutnaVrstica += 0.5;
            if (animacijaTrenutnaVrstica >= animacijaVrstica) {
                animacijaTrenutnaVrstica = animacijaVrstica;
                animacijaAktivna = false;
                animacijaTimer.stop();
                PloscaPanel.this.igra.odigrajPotezo(animacijaStolpec);
                posodobiStatus();
            }
            repaint();
        });
        animacijaTimer.start();
    }
}
