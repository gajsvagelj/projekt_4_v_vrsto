# Projekt za predmet Programiranje 2: Igrica 4 v vrsto

## Delovanje
Igro začne igralec z rdečimi žetoni, zgornja vrstica pove kateri igralec je na vrsti, spodaj je gumb za novo igro, ki zapre in nato ponovno odpre okno.

### Igralec
Vsebuje podatke o specifičnem igralcu.

### Plosca
Upravlja stanje igre s pomočjo dvodimenzionalne tabele, kjer 0 predstavlja prazno polje, 1 prvega igralca in 2 drugega igralca. Poišče najnižje prosto mesto v izbranem stolpcu in vanj zapiše vrednost igralca. Če je stolpec poln, vrne -1. Preveri ali je prišlo do zmage ali izenačenja.

### Igra
Določa, kdo je na potezi, in preprečuje neveljavne poteze. Ob vsakem metu žetona posodobi stanje plošče, preveri, ali je prišlo do zmage ali izenačenja, in po potrebi zamenja trenutnega igralca.

### OknoIgre
Ustvari glavno okno igre, inicializira igralca in igro, vsebuje info vrstico in gumb za novo igro. Vsebuje main.

### PloscaPanel
Riše ploščo z žetoni in animacijo padanja, posluša klike in poteze posreduje igri.

Avtorja: Gaj Švagelj in Franc Križanič
