
public interface TRAI_23_X1 {

    /**
     * Ovatko uniikkeja.
     * Jos taulukon kaikki alkiot ovat eri lukuja (tai taulukossa on
     * vain 0 tai 1 alkiota), metodi
     * palauttaa true. Jos taas jokin (tai jotkin) luku esiintyy
     * kahdesti tai useammin, metodi palauttaa false.
     * Voit olettaa taulukossa olevan vain kelvollisia kokonaislukuja
     * (ei siis null:eja).
     *
     * @param A SyÃ¶tetaulukko.
     * @return true jos kaikki alkiot ovat eri lukuja, muuten false
     */
    boolean ovatkoUniikkeja(Integer[] A);

}
