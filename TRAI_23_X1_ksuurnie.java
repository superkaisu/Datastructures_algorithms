public class TRAI_23_X1_ksuurnie implements TRAI_23_X1 {
    //                    ^^^^^ OMA TUNNUS TÃ„HÃ„N
    // NimeÃ¤ tiedosto TRAI_23_X1_tunnus.java

    /**
     *  Lähdin ratkaisemaan ongelmaa suunnittelemalla algoritmia huolellisesti.
     *  Sain oikein hyvän rungon aikaiseksi, mutta hassahdin loopeissa käyttämään taulukon pituutta
     *  ottamatta sitä ensin aliohjelmassa muttujaan. Toinen virheeni oli, että epähuomiossa sisemmän
     *  loopin kirjasin alkamaan j = 1. Kun huomasin, ettei tulos ollut toivotun lainen, laitoin tietokoneen
     *  kiinni ja pohdin mitkä asiat vaikuttivat siihen, ettei kaikki testit mene läpi.
     *  Algoritmiani ajatellessa keksin, että sisempi for-loop täytyy alkaa j = i +1, jotta jokainen kierros
     *  Alkaa oikeasta kohdasta. Kun avasin taas koneen, ajattelin myös samalla kokeilla laittaa taulukon pituuden
     *  muuttujaan, koska pohdin, että sillä voisi olla merkitystä.
     *  Opin siis:
     *  - Laita tarvittavat asiat muuttujiin
     *  - Pohdi, minkä suhteen asioiden toivon muuttuvan
     *  Tein mielestäni hyvää työtä.
     **/

    /**
     * Ovatko uniikkeja. Jos taulukon kaikki alkiot ovat eri lukuja (tai taulukossa on vain 0 tai 1
     * alkiota), metodi palauttaa true. Jos taas jokin (tai jotkin) luku esiintyy kahdesti tai
     * useammin, metodi palauttaa false. Voit olettaa taulukossa olevan vain kelvollisia
     * kokonaislukuja (ei siis null:eja).
     *
     * @param A SyÃ¶tetaulukko.
     * @return true jos kaikki alkiot ovat eri lukuja, muuten false
     */
    @Override
    public boolean ovatkoUniikkeja(Integer[] A) {
        boolean uniikki = true;
        int pituus = A.length;

        for (int i = 0; i < pituus; i++)
        {
            if (A.length <= 1)
            {
                break;
            }

            for (int j = i + 1; j < pituus; j++)
            {
                if (A[i].equals(A[j]))
                {
                    uniikki = false;
                    break;
                }

            }

        }

        return uniikki;
    }
}