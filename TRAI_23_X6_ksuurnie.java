import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TRAI_23_X6_ksuurnie implements TRAI_23_X6 {

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     *
     * aikavaativuuden parametrit: n = syÃ¶tejoukkojen mÃ¤Ã¤rÃ¤, m = syÃ¶tejoukkojen _yhteinen_ alkiomÃ¤Ã¤rÃ¤.
     *
     * Tämä tehtävä vaati minulta suht paljon pohjatyötä. Piti etsiä miten kuvaus toimii, kun yritin ensin lisätä jokaista leikkausta
     * yksittäin kuvauksen avaimen arvoksi, eikä se kai onnistu? Vähän tarkemmin kun luki tehtävänantoa, tajusin, että täytyy laittaa
     * joukkojen joukko avaimelle arvoksi. Vähän vaikea tehtävä ja varmaan olisi olemassa tehokkaampi algoritmikin tälle?
     * En saanut tätä pelkästään suunnittelemalla toimimaan, vaan käytin lopuksi yritys erehdystä, että sain loput virheet
     * korjattua..
     * Arvioisin aikavaativuuden omassani olevan n * (n-1 * m), eli todella hidas pahimmassa tapauksessa.
     */

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
    @Override
    public <E> Map<Set<E>, Set<Set<E>>> leikkaavatJoukot(Set<Set<E>> JJ) {
        Map<Set<E>, Set<Set<E>>> tulos = new HashMap<>();

        //Käydään kaikki joukot joukkojen joukossa läpi
        for (Set<E> J : JJ){

            //Lista niille joukoille, jotka leikkaavat keskenään
            Set<Set<E>> leikkaavat = new HashSet<>();

            //Jokaista joukkoa kohti tarkastellaan mitkä joukot on toisensa leikkauksia
            for(Set<E> K : JJ){

                //Ei verrata joukkoa itseensä, vaan muihin joukkoihin
                if(J != K){

                    //Otetaan kopio alkuperäisestä joukosta ja tehdään siihen leikkaus K:n kanssa/K:sta. Ei muuteta syötettä.
                    Set<E> leikkaus = new HashSet<>(J);
                    leikkaus.retainAll(K);

                    //Jos leikkaavat, niin lisätään listaan se joukko, joka on vertailun kohteena.
                    if(!leikkaus.isEmpty()){
                        leikkaavat.add(K);
                    }
                }
            }

            //Lisätään leikkaavien joukkojen lista kuvaukseen
            tulos.put(J, leikkaavat);
        }

        return tulos;
    }

}