import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * ITSEARVIOINTI TÃ„HÃ„N:
 *  Tehtävä oli haastava, koska luentojen jälkeen jäi minulle vähän muodottomaksi, että miten se tehdään. Katsoin esimerkkejä luennoilta ja tein
 *  tämän niiden pohjalta. Ei ole siistiä, sisältää vissiin jotain tarpeettomiakin osia, mutta loppuen lopuksi sain toimimaan.
 *  Yritys erehdys oli taas läsnä.
 *  Siitä olin kyllä ylpeä, että ymmärsin mitä tulee tehdä ja miksi. Toteuttaminen vaan oli haastavaa.
 */

/**
 * TÃ¤ydennÃ¤ _ainakin_ TODO-kohtiin, muuallekin voit joutua tÃ¤ydentÃ¤mÃ¤Ã¤n.
 */

/** rengas
 * @param <E> alkiotyyppi
 */

public class TRAI_23_X7_ksuurnie<E> implements TRAI_23_X7<E> {
    // ^^^^^ oma tunnus tÃ¤hÃ¤n

    int n = 0;
    // alkioiden mÃ¤Ã¤rÃ¤ renkaassa
    // TODO
    private ListNode<E> first;
    private ListNode<E> last;
    private ListNode<E> latest;
    private ListNode<E> iLatest;
    public final ListNode<E> EOL = null;


    //Konstruktori - eli pitäisi luoda uuden toteutuksen tästä luokasta
    public TRAI_23_X7_ksuurnie() {
        // TODO
        first = EOL;
        last = EOL;
        n = 0;
    }

    public class ListNode<E>{
        public ListNode<E> next;
        public ListNode<E> previous;
        public E element;
        public final ListNode EOL = null;

        protected ListNode(E x){
            next = EOL;
            element = x;
        }
        public ListNode<E> getNext(){
            return next;
        }
        public E getElement(){
            return element;
        }

    } //Loppuu tähän

    /**
     * Alkioiden mÃ¤Ã¤rÃ¤ renkaassa.
     *
     * @return Alkioiden mÃ¤Ã¤rÃ¤n.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * LisÃ¤Ã¤ renkaaseen alkion.
     *
     * @param x LisÃ¤ttÃ¤vÃ¤ alkio
     * @return palauttaa aina tosi (aina mahtuu)
     */
    @Override
    public boolean add(E x) {
        // TODO
        ListNode<E> nd = new ListNode<E>(x);

        //jos ensimmäinen lisättävä alkio
        if(size() < 1){
           first = nd;
           last = nd;
           latest = first;
        }
        //lisätään viittaus renkaan "loppuun" ja päivitetään rengasrakenne
        else {
            nd.previous = last;
            last.next = nd;
            last = nd;
            nd.next = first;
        }
        //kasvatetaan renkaan kokoa
        n++;
        return true;
    }



    /**
     * Palauttaa seuraavan alkion renkaasta.
     * Alussa palauttaa jonkin alkion.
     * Toistuvasti kutsuttaessa palauttaa kaikkia alkioita vuorotellen.
     *
     * @return Yhden renkaan alkion.
     * @throws NoSuchElementException jollei renkaassa ole alkoita
     */
    @Override
    public E next() {
        if (n == 0)
            throw new NoSuchElementException("Rengas on tyhjÃ¤, next() ei voi kutsua!");
        // TODO
        else if (n == 1) {
            return first.getElement();
        }
        //jos alkioita enemmän kuin yksi, etsitään seuraava node ja haetaan sen elementti
        else {
            //arvo talteen ja siirrytään seuraavaan
            E now = latest.getElement();
            latest = latest.getNext();
            return now; //palautetaan arvo
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new iter();
    }

    private class iter implements Iterator<E> {
        // TODO

        // luodaan käyttöön uusi listnode
        private ListNode<E> iLatest;

        //tehdään konstruktori iteraattorille
        public iter() {
            iLatest = first;
        }

        @Override
        public boolean hasNext() {
            return n > 0;
        }

        @Override
        public E next() {
            // TODO
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            E iNow = iLatest.getElement();
            iLatest = iLatest.getNext();
            return iNow;

        }
    }
}
