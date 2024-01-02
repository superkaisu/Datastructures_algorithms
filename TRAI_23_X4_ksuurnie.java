import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

public class TRAI_23_X4_ksuurnie implements TRAI_23_X4 {

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     *  Tein huolellisen suunnitelman algoritmilleni luennon esimerkin mukaisesti.
     *  Molemmat osat algoritmistani toimivat, sisäjärjestyksessä viimeisen solmun löytämistä täytyi
     *  vähän tarkentaa.
     *  Algoritmin aikavaativuus on sisä viim. vastaava O(log n), koska
     *  puusta käydään vai osa solmuista läpi.
     *  edellisen hakeva algoritmi on taas aikavaativuudeltaan O(|puun alkiot|), eli O(n), koska
     *  algoritmi käy kaikki alkiot läpi.
     *  Koko läpikäynti on siis puun alkioiden lkm. eli vastaa O(n).
     */

    /**
     * Etsii ja palauttaa binÃ¤Ã¤ripuun sisÃ¤jÃ¤rjestyksessÃ¤ viimeisen solmun.
     * @param P Tarkasteltava puu.
     * @return Viimeinen solmu tai null jos puu P on tyhjÃ¤.
     * @param <E> puun solmujen alkioiden tyyppi (ei tarvita)
     **/
    @Override
    public <E> BTreeNode<E> sisaViimeinen(BTree<E> P) {
        // TODO
        // Etsi puun juuri
        // navigoi kaikkein oikeanpuoleisimpaan lehtisolmuun
        // Muista tarkistaa, onko mikään solmu null, aloita myös juuren tarkistamisella
        // - null, jos puu on tyhjä.
        // toimiiko while (solmu !=null) navigoi oikealle.

        if (P == null){
            return null;
        }

        BTreeNode<E> n = P.getRoot();
        BTreeNode<E> s = P.getRoot();

        while (n != null){
            n = n.getRightChild();

            if (n != null){
            s = n;
            }
        }

        return s;
    }

    /**
     * Palauttaa binÃ¤Ã¤ripuun solmun s edeltÃ¤jÃ¤solmun sisÃ¤jÃ¤rjestyksessÃ¤.
     * @param s binÃ¤Ã¤ripuun solmu.
     * @return edeltÃ¤jÃ¤solmu tai null jollei edeltÃ¤jÃ¤Ã¤ ole.
     * @param <E> puun solmujen alkioiden tyyppi (ei tarvita)
     */
    @Override
    public <E> BTreeNode<E> sisaEdellinen(BTreeNode<E> s) {
        // TODO

        BTreeNode<E> n;
        BTreeNode<E> p;
        BTreeNode<E> d = s;

        if(d.getLeftChild() != null){
            n = d.getLeftChild();
            while(n.getRightChild() != null){
                n = n.getRightChild();
            }
           return n;
       }
        else {
            n = d;
            p = d.getParent();

            while(p != null){
                if(n == p.getRightChild()){
                    return p;
                }
                else {
                    n = p;
                    p = p.getParent();
                }
            }
            return null;
        }
    }
}