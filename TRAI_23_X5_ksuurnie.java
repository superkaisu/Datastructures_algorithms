import java.util.HashSet;
import java.util.Set;

public class TRAI_23_X5_ksuurnie implements TRAI_23_X5 {

    /**
     * Tein hyvän suunnitelman piirtäen kuvat tarvitsemistani joukko-operaatioista sekä
     * vaiheista, joita tarvitsin algoritmin koirjoittamiseen.
     * Kirjoitin algoritmin suunnitelmani mukaan ja se meni testeistä läpoi ensi yrittämällä.
     * Aikavaativuuden suhteen:
     * Algoritmissani käytän Javan Set all-operaatioita, joiden aikavaativuus on kertaluokkaa O(n).
     * Jos joku alkuperöisistä joukoista on tyhjä, aikavaativuus on O(n^2), koska jokaisessa ehtolauseessa käydään molemmat
     * joukot läpi kerran.
     * Seuraavassa ehtolauseessa tehdään kaikille joukoille all-operaatioita, eli kertaluokka olisi vähintään O(n^2)..
     * Myös viimeisen ehtolauseen kertaluokka on O(n^2)
     * eli koko algoritmi toimii neliöllisessä ajassa.
     */

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
    @Override
    public <E> Set<E> kaksiKolmestaLeikkaus(Set<E> S1, Set<E> S2, Set<E> S3) {
        Set<E> tulos = new HashSet<>();
        Set<E> iS1 = new HashSet<>(S1);
        Set<E> iS2 = new HashSet<>(S2);
        Set<E> iS3 = new HashSet<>(S3);

        // TODO
        // Jos joku joukoista on tyhjä, kahden muun joukon leikkaus riittää
        if (S1.isEmpty() || S2.isEmpty() || S3.isEmpty()){
            if (S1.isEmpty()){
                iS2.retainAll(S3);
                tulos.addAll(iS2);
            }
            else if (S2.isEmpty()){
                iS3.retainAll(S1);
                tulos.addAll(iS3);
            }
            else if (S3.isEmpty()){
                iS1.retainAll(S2);
                tulos.addAll(iS1);
            }
            // jos 2 tai kaikki joukot on tyhjiä, palautetaan tyhjä tulos
            else {
                return tulos;
            }
        }

        // Jos jokaisessa joukossa on alkioita
        if (!(S1.isEmpty() && S2.isEmpty() && S3.isEmpty())) {

            iS1.retainAll(S2);

            iS2.retainAll(S3);

            iS3.retainAll(S1);

            // Jos joku leikkausten joukko on tyhjä, tehdään kahdesta muusta yhdiste
            if (iS1.isEmpty()){
                //lisätään tyhjään tulos joukkoon saatu leikkaus iS2 ja iS3
                tulos.addAll(iS2);
                tulos.addAll(iS3);

            }
            else if (iS2.isEmpty()){
                //lisätään tyhjään tulos joukkoon saatu leikkaus iS1 ja iS3
                tulos.addAll(iS3);
                tulos.addAll(iS1);

            }
            else if (iS3.isEmpty()) {
                //lisätään tyhjään tulos joukkoon saatu leikkaus iS2 ja iS1
                tulos.addAll(iS1);
                tulos.addAll(iS2);

            }
        }


        // Jos jokaisessa alkuperäisten joukkojen yhdisteistä muodostetuista joukoista on alkioita
        // tehdään joukkojen erotus joiden tulokset sitten yhdistetään
        if(!(iS1.isEmpty() && iS2.isEmpty() && iS3.isEmpty())){

            //tehdään kopiot erotusta varten
            Set<E> dS1 = new HashSet<>(iS1);
            Set<E> dS2 = new HashSet<>(iS2);
            Set<E> dS3 = new HashSet<>(iS3);

            dS1.removeAll(iS2);

            dS2.removeAll(iS3);

            dS3.removeAll(iS1);

            // tehdään unioni
            dS1.addAll(dS2);
            dS1.addAll(dS3);

            tulos.addAll(dS1);

        }
        return tulos;
    }
}
