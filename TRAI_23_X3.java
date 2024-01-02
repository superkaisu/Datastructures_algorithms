import java.util.LinkedList;

public interface TRAI_23_X3 {

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
    public <E extends Comparable<? super E>> LinkedList<E> siirraSuuremmat(LinkedList<E> L, E raja);

}