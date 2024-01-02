import java.util.ArrayList;

public class TRAI_23_X2_ksuurnie implements TRAI_23_X2 {

    /**
     * Tämä oli kivireen vetämistä minulle. Olin hahmotellut algoritmin juuri oikein,
     * mutta toteutus ei onnistunut toivotulla tavalla.
     * Se johti turhaan työstämiseen ja turhautumiseen, vaikka olisin voimut luottaa siihen, että ajat-
     *  telin asian oikein.
     *  Jouduin turvautumaan ohjausapuun, jossa purin opetusavustajan kanssa sekavaa algoritmiani.
     *  Algoritmini aikavaatimus taitaa olla kuitenkin nyt O(n) (summasäännöllä useammasta eri haarasta O(n)..)
     *  En ole tyytyväinen monisyiseen if-else if-else tilanteeseen, mutta algoritmi toimii.
     *  Paitsi tilanteen, jossa molempien listojen 1. alkio on sama ja toinen lista on pituudeltaan 1, toinen 2.
     *  ---> tämän kirjoitettuani hoksasin, mitä täytyi muuttaa ja tein pienen muutoksen, jonka jälkeen kaikki testit
     *  menevät läpi.
     *  Loppu hyvin, kaikki hyvin - joudun vielä opettelemaan algoritmin hahmottelemisen ja
     *  ensimmäisen kirjoituskerran jälkeen oman ajattelun debuggausta.
     */
    /**
     * Kasvavien listojen yhdiste.
     * Palauttaa uuden listan jossa on sellaiset alkiot jotka lÃ¶ytyvÃ¤t
     * jommastakummasta tai molemmista syÃ¶telistoista.
     * Kukin alkio tulee tuloslistaan vain kerran.
     * Tuloslista tulee myÃ¶s kasvavaan jÃ¤rjestykseen.
     * Jos jompikumpi syÃ¶telistoista on epÃ¤jÃ¤rjestyksessÃ¤, tulosta ei tarvitse tuottaa.
     * @param L1 ensimmÃ¤inen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @param L2 toinen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @return yhdistelista
     */

    @Override
    public ArrayList<Integer> yhdisteKasvavista(ArrayList<Integer> L1, ArrayList<Integer> L2) {

        ArrayList<Integer> yhdiste = new ArrayList<>();
        int i = 0;
        int j = 0;

        //muuttuja, johon säilön tiedon mikä alkio viimeisimpänä yhdisteessä
        //oletetaan, että alkiot kaikki arvoltaan nolla tai suurempia.
        int e = -1;

        while(i < L1.size() && j < L2.size())
        {
            int is1 = L1.get(i);
            int is2 = L2.get(j);
            // Jos L1 käsiteltävä alkio pienempi kuin L2 ensimmäinen alkio
            if (is1 < is2) {
                if(is1 > e) {
                    yhdiste.add(is1);
                    e = is1;
                    i++;
                }
                else {
                    i++;
                }
            }
            // jos L2 käsiteltävä alkio pienempi kuin L1 alkio
            else if (is2 < is1) {
                if(is2 > e) {
                    yhdiste.add(is2);
                    e = is2;
                    j++;
                }
                else {
                    j++;
                }
            }
            // Jos käsiteltävät alkiot ovat samat
            else if (is1 == is2){
                if (is1 > e) {
                    yhdiste.add(is1);
                    e = is1;
                    i++;
                    j++;
                }
                else {
                    i++;
                    j++;
                }
            }

        }

        //Näillä käydään loput alkiot läpi ylemmän while-toiston loputtua
        //Jos L2 on käymättä läpi loppuun
        if (i >= j && j < L2.size()){
            while (j < L2.size()){
                if(L2.get(j) > e){
                    yhdiste.add(L2.get(j));
                    e = L2.get(j);
                    j++;
                }
                else{
                    j++;
                }
            }
        }
        // Jos L1 on käymättä läpi loppuun
        else if (j >= i && i < L1.size()){
            while(i < L1.size()){
                if(L1.get(i) > e) {
                    yhdiste.add(L1.get(i));
                    e = L1.get(i);
                    i++;
                }
                else{
                    i++;
                }
            }
        }
        //Jos jompi kumpi listoista on tyhjä
        else if (L2.isEmpty()){
            while(i < L1.size()){
                if(L1.get(i) > e) {
                    yhdiste.add(L1.get(i));
                    e = L1.get(i);
                    i++;
                }
                else{
                    i++;
                }
            }
        }
        else if (L1.isEmpty()){
            while (j < L2.size()){
                if(L2.get(j) > e){
                    yhdiste.add(L2.get(j));
                    e = L2.get(j);
                    j++;
                }
                else{
                    j++;
                }
            }
        }

        // SUUNNITTELE algoritmi huolella ENNEN toteutuksen aloittamista!

        // HyÃ¶dynnÃ¤ listojen L1 ja L2 jÃ¤rjestystÃ¤ jo paperilla, sitten
        // suunnitelmassa ja lopuksi toimivassa algoritmissa.
        // NÃ¤in tuloslista tulee helpommin jÃ¤rjestykseen ja siitÃ¤ on
        // helpompi jÃ¤ttÃ¤Ã¤ duplikaatit pois.

        return yhdiste;
    }

}