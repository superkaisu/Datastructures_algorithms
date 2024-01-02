import java.util.Map;
import java.util.Set;

public interface TRAI_23_X6 {

    /**
     * Laskee tiedon siitÃ¤ mitkÃ¤ joukot leikkaavat keskenÃ¤Ã¤n (siis millÃ¤ on yhteisiÃ¤ alkioista).
     * Tulos palautetaan kuvauksena siten, ettÃ¤ jos syÃ¶tteen joukolla Ji on yhteisiÃ¤ alkioita
     * jonkun muun syÃ¶tteen joukon Jj kanssa, niin kuvauksen avaimeen Ji liittyvÃ¤ssÃ¤ arvossa (joukossa) on
     * viittaus joukkoon Jj (ja pÃ¤invastoin). Kuvaus sisÃ¤ltÃ¤Ã¤ avaimenaan kunkin joukon JJ sisÃ¤ltÃ¤mÃ¤n
     * joukon Ji ja kunkin avaimen arvona on joukko niistÃ¤ joukoista Jj joilla on vÃ¤hintÃ¤Ã¤n yksi yhteinen alkio
     * joukon Ji kanssa.
     * @param JJ syÃ¶te (joukkojen joukko)
     * @param <E> joukkojen alkiotyyppi
     * @return kuvaus joukkojen leikkaavuuksista
     */
    public <E> Map<Set<E>, Set<Set<E>>> leikkaavatJoukot(Set<Set<E>> JJ);
}