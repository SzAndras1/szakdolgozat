## Software Requirement Specification
### Legfontosabb funkciók
* Hirdetésfeladás
* Keresés filterekkel
* Igénybe venni a hirdetést
* Értesítés új üzenetnél vagy egy igénybevételnél
* Saját valutára átváltani a pénznemeket
* Saját profil megtekintés
* Értékelni a szolgáltatást
* Saját hirdetések módosítása
### Felhasználók jellemzői
Egy fajta felhasználó van, tud hirdetéseket feladni, mások hirdetését igénybe venni. Felhasználó böngészhet a hirdetések között, az ő igényei szerint szűrhet a hirdetésekre. Ha igénybe vesz egy hirdetést, akkor megjelenik egy chat felület, ahol tud a két fél kommunikálni.

### Use case diagram
![alt text](/md_images/usecase.png "funckiók")
#### Prekondíciók
Hirdetés feltöltéséhez és igénybe vételéhez be kell regisztrálni az oldalra.
#### Post kondicíók
Igénybevevő sikeresen feltöltött elegendő pénzt, hogy igénybe tudjon venni egy szolgáltatást. Miután sikeresen igénybe vette a kiválasztott hirdetést, megérkezett a hirdetőnek egy értesítést.
Innen a chat felületen tudják bonyolítani az üzletüket.
#### Alternatív esetek:
Ha nincs elegendő egyenlege a felhasználónak egy szolgáltatás igénybevételéhez, akkor visszajelzés kap.
