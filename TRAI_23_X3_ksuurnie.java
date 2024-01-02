import java.util.LinkedList;
import java.util.ListIterator;

public class TRAI_23_X3_ksuurnie implements TRAI_23_X3 {
    /**
     * Maltoin miettiä ogelman rauhassa ja ottaa selvää LinkedList:sta, ListIteratorista,
     * compareTo:sta ja siitä miten objekteja vertaillaan sen avulla.
     * Kirjoitin koodin suunnitelman mukaan ja tein vain pienen pieniä muutoksia.
     * Kokonaisaika tietokoneen ääressä oli jotakuinkin n. 10min.
     * Arvioisin suorituksen menneen nappiin, kun koodi toimi ensi yrittämällä täydellisesti.
     * Algoritmin aikavaativuus on lineaarinen O(|L|), sillä olen käyttänyt LinkedListin iteraattoria
     * listan läpikäyntiin ja iteraattorin
     * .remove() metodia sekä uuteen listaan lisäykseen vakioaikaista LinkedListin operaatiota
     * .add().
     */


    /**
     * SiirrÃ¤ suuremmat toiseen listaan.
     * Poistaa listasta kaikki sellaiset alkiot jotka seuraavat alkiota raja.
     * (Ovat "suurempia", eli ne alkiot a joille a.compareTo(raja) > 0. Poistetut alkiot
     * siirretÃ¤Ã¤n uuteen listaan joka palautetaan.
     * Lista L sÃ¤ilyy muuten jÃ¤rjestyksessÃ¤.
     * @param L syÃ¶telista
     * @param raja alkio jota suuremmat siirretÃ¤Ã¤n
     * @return siirretyt alkiot listana
     */
    @Override
    public <E extends Comparable<? super E>>  LinkedList<E> siirraSuuremmat(LinkedList<E> L, E raja) {

        LinkedList<E> U = new LinkedList<>();

        ListIterator<E> it = L.listIterator();

        while(it.hasNext()) {
            E x = it.next();

            if((x.compareTo(raja)) > 0){
                U.add(x);
                it.remove();
            }
        }

        return U;
    }

}