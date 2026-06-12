public class Igra {

    private Plosca plosca;

    private Igralec igralec1;
    private Igralec igralec2;

    private Igralec trenutniIgralec;

    private boolean konecIgre;
    private boolean neodloceno;

    private Igralec zmagovalec;

    public Igra(Igralec igralec1, Igralec igralec2) {
        this.plosca = new Plosca();

        this.igralec1 = igralec1;
        this.igralec2 = igralec2;

        this.trenutniIgralec = igralec1;

        this.konecIgre = false;
        this.neodloceno = false;

        this.zmagovalec = null; // Na začetku igre zmagovalca še ni
    }
    
    public Igralec getIgralec1() { return igralec1; }
    public Igralec getIgralec2() { return igralec2; }

    public boolean odigrajPotezo(int stolpec) {

        // če je igra že končana, ne dovolimo več potez
        if (konecIgre) {
            return false;
        }
        
        int idIgralca;

        // določimo ID igralca (1 ali 2) glede na trenutnega igralca
        if (trenutniIgralec == igralec1) {
            idIgralca = 1;
        } else {
            idIgralca = 2;
        }

        // vrstica, v katero je padel žeton.
        int vrstica = plosca.dodajZeton(stolpec, idIgralca);

        // neveljavna poteza
        if (vrstica == -1) {
            return false;
        }

        // če smo prišli do sem, je poteza veljavna. Preverimo zmago
        if (plosca.preveriZmago(vrstica, stolpec, idIgralca)) {
            konecIgre = true; // igra je končana, imamo zmagovalca
            zmagovalec = trenutniIgralec; // shranimo zmagovalca
        }

        // preverimo izenačenje
        else if (plosca.jePolna()) {
            konecIgre = true;
            neodloceno = true;  // razlog za konec igre je izenačenenje
        }

        // menjava igralca
        else {
            zamenjajIgralca();
        }

        return true;
    }

    // menjamo igralca na koncu poteze
    private void zamenjajIgralca() {

        if (trenutniIgralec == igralec1) {
            trenutniIgralec = igralec2;
        } else {
            trenutniIgralec = igralec1;
        }
    }

    public Plosca getPlosca() {
        return plosca;
    }

    public Igralec getTrenutniIgralec() {
        return trenutniIgralec;
    }

    
   // vrne vrstico, v katero bi padel žeton v danem stolpcu
    public int getCiljnaVrstica(int stolpec) {
        return plosca.getVrstica(stolpec);
    }
    
    public boolean isKonecIgre() {
        return konecIgre;
    }

    public boolean isNeodloceno() {
    return neodloceno;
    }

    public Igralec getZmagovalec() {
    return zmagovalec;
    }
}