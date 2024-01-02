import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

public interface TRAI_23_X4 {

    /**
     * Etsii ja palauttaa binÃ¤Ã¤ripuun sisÃ¤jÃ¤rjestyksessÃ¤ viimeisen solmun.
     * @param P Tarkasteltava puu.
     * @return Viimeinen solmu tai null jos puu P on tyhjÃ¤.
     * @param <E> puun solmujen alkioiden tyyppi (ei tarvita)
     **/
    public <E> BTreeNode<E> sisaViimeinen(BTree<E> P);

    /**
     * Palauttaa binÃ¤Ã¤ripuun solmun s edeltÃ¤jÃ¤solmun sisÃ¤jÃ¤rjestyksessÃ¤.
     * @param s binÃ¤Ã¤ripuun solmu.
     * @return edeltÃ¤jÃ¤solmu tai null jollei edeltÃ¤jÃ¤Ã¤ ole.
     * @param <E> puun solmujen alkioiden tyyppi (ei tarvita)
     */
    public <E> BTreeNode<E> sisaEdellinen(BTreeNode<E> s);

}