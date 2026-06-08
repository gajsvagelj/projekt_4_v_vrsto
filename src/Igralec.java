import java.awt.Color;

public class Igralec {

    private String ime;
    private Color barva;

    public Igralec(String ime, Color barva) {
        this.ime = ime;
        this.barva = barva;
    }

    
    public String getIme() {
        return ime;
    }

    public Color getBarva() {
        return barva;
    }
}
