public class Plosca {

    private int[][] mreza;

    private static final int VRSTICE = 6;
    private static final int STOLPCI = 7;

    public Plosca() {
        mreza = new int[VRSTICE][STOLPCI]; // prazna polja, povsod ničle
    }

    // VSTAVLJANJE ŽETONA
    public int dodajZeton(int stolpec, int igralec) {

        // če stolpec ne obstaja
        if (stolpec < 0 || stolpec >= STOLPCI) {
            return -1;
        }
        // od spodnje vrstice 
        for (int i = VRSTICE - 1; i >= 0; i--) {
            if (mreza[i][stolpec] == 0) {
                mreza[i][stolpec] = igralec; // spremenimo polje. številka igralca
                return i; // vrne vrstico, kjer je padel žeton
            }
        }

        return -1; // stolpec je poln, neveljavna poteza
    }

    // PREVERIMO POLNOST
    public boolean jePolna() {
        // preverimo samo zgornjo vrstico
        for (int j = 0; j < STOLPCI; j++) {
            if (mreza[0][j] == 0) {
                return false;
            }
        }
        return true; // plošča je polna
    }

    // PREVERJANJE ZMAGE
    public boolean preveriZmago(int vrstica, int stolpec, int igralec) {

        return preveri(vrstica, stolpec, igralec, 1, 0)   // navpično
            || preveri(vrstica, stolpec, igralec, 0, 1)   // vodoravno
            || preveri(vrstica, stolpec, igralec, 1, 1)   // diagonalno \
            || preveri(vrstica, stolpec, igralec, 1, -1);                  // diagonalno /
    }

    // metoda za štetje v smeri. 
    private boolean preveri(int v, int s, int igralec, int premik_navpicno, int premik_vodoravno) {

        int count = 1; // stevec koliko v vrsto

        count += stej(v, s, igralec, premik_navpicno, premik_vodoravno);
        count += stej(v, s, igralec, -premik_navpicno, -premik_vodoravno);

        return count >= 4;
    }

    private int stej(int v, int s, int igralec, int premik_navpicno, int premik_vodoravno) {

        int count = 0; // stevec sosednjih zetonov

        v += premik_navpicno; // premik po vrsticah
        s += premik_vodoravno; // premik po stolpcih

        // stejemo dokler smo se v polju in imamo žetone iste barve
        while (v >= 0 && v < VRSTICE && s >= 0 && s < STOLPCI
                && mreza[v][s] == igralec) {
            count++;
            v += premik_navpicno;
            s += premik_vodoravno;
        }

        return count;
    }

    public int[][] getMreza() {
        return mreza;
    }

    public int getPolje(int vrstica, int stolpec) {
        return mreza[vrstica][stolpec];
    }
    
    
    // cilj, do kam bo padal žeton
    public int getVrstica(int stolpec) {
        if (stolpec < 0 || stolpec >= STOLPCI) return -1;
        for (int i = VRSTICE - 1; i >= 0; i--) {
            if (mreza[i][stolpec] == 0) return i;
        }
        return -1;
    }
}