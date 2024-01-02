import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstraktin tietotyypin rengas rajapinta.
 * next() -operaatio tarjoaa aina seuraavia alkioita
 */
public interface TRAI_23_X7<E> extends Iterable<E> {

    /**
     * Alkioiden mÃ¤Ã¤rÃ¤ renkaassa.
     * @return Alkioiden mÃ¤Ã¤rÃ¤n.
     */
    int size();

    /**
     * LisÃ¤Ã¤ renkaaseen alkion.
     * LisÃ¤yspaikalla ei ole vÃ¤liÃ¤.
     * @param x LisÃ¤ttÃ¤vÃ¤ alkio
     * @return palauttaa aina tosi (aina mahtuu)
     */
    boolean add(E x);

    /**
     * Palauttaa seuraavan alkion renkaasta.
     * Alussa palauttaa jonkin alkion.
     * Toistuvasti kutsuttaessa palauttaa kaikkia alkioita vuorotellen.
     * @return Yhden renkaan alkion.
     * @throws NoSuchElementException jollei renkaassa ole alkoita
     */
    E next();


    /**
     * Onko rengas tyhjÃ¤
     * @return true jos rengas on tyhjÃ¤, muuten false.
     */
    default boolean isEmpty() {
        return this.size() == 0;
    }


    /**
     * Palauttaa tiedon voidaanko next():iÃ¤ kutsua.
     * @return tosi jos renkaassa on alkioita, muuten epÃ¤tosi
     **/
    default boolean hasNext() {
        return this.size() > 0;
    }

    /**
     * Palauttaa uuden lÃ¤pikÃ¤ynnin.
     * @return uusi iteraattori jolla voidaan kÃ¤ydÃ¤ lÃ¤pi tÃ¤tÃ¤ rengasta loputtomasti.
     */
    default Iterator<E> iterator() {
        throw new UnsupportedOperationException("iterator() ei ole vielÃ¤ toteutettu!");
    }


    /**
     * Poistaa ja palauttaa renkaasta sen alkion joka edellisellÃ¤ kerralla on next():llÃ¤ palautettu.
     * @return Poistettu alkio.
     * @throws NoSuchElementException jollei renkaassa ole alkoita
     * @throws IllegalStateException jollei next()iÃ¤ ole kutsuttu
     */
    default E remove() {
        throw new UnsupportedOperationException("remove() ei ole vielÃ¤ toteutettu!");
    }

} // interface
