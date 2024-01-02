import java.util.Set;

public interface TRAI_23_X5 {

    /**
     * Joukkojen kaksi kolmesta -leikkaus.
     * Luo uuden joukon johon algoritmi laittaa ne syÃ¶tejoukkojen alkiot jotka
     * kuuluvat tasan kahteen kolmesta syÃ¶tejoukosta.
     * Ei muuta syÃ¶tejoukkojensa sisÃ¤ltÃ¶Ã¤ (vaan luo uuden tulosjoukon)
     * Jos mikÃ¤Ã¤n alkio ei tÃ¤ytÃ¤ ehtoa, palautetaan tyhjÃ¤ joukko.
     * @param S1    syÃ¶tejoukko (ei muuteta)
     * @param S2    syÃ¶tejoukko (ei muuteta)
     * @param S3    syÃ¶tejoukko (ei muuteta)
     * @param <E>   alkiotyyppi
     * @return  kaksi-kolmesta tulosjoukko
     */
    public <E> Set<E> kaksiKolmestaLeikkaus(Set<E> S1, Set<E> S2, Set<E> S3);

}