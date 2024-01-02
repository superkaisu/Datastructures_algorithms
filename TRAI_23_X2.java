import java.util.ArrayList;

public interface TRAI_23_X2 {

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
    ArrayList<Integer> yhdisteKasvavista(ArrayList<Integer> L1, ArrayList<Integer> L2);

}
