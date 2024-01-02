import java.util.*;

/**
 * Testausluokka TRA I tehtÃ¤vÃ¤Ã¤n X3.
 */
public class TRAI_23_X3_testi {

    static Random rnd = new Random();

    static TRAI_23_X3 testattava = new TRAI_23_X3_ksuurnie(); /* <-- Oma tunnus tÃ¤hÃ¤n */


    public static void main(String[] args) {

        // taulukoiden koko
        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);

        // tulostusten mÃ¤Ã¤rÃ¤
        int print = 5;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);

        rnd.setSeed(siemen);

        boolean ok = true;

        // satunnaisia
        ok &= testaaTulosX3(N, 0, N, false, N/2, print);
        ok &= testaaTulosX3(N, 0, N * 2, false, N/2, print);
        ok &= testaaTulosX3(N*2, 0, N * 4, false, N, print);

        // kaikki paitsi pienin siirretÃ¤Ã¤n
        ok &= testaaTulosX3(N, 0, N, true, 0, print);

        // vain suurin siirretÃ¤Ã¤n
        ok &= testaaTulosX3(N, 0, N, true, N-1, print);

        // kaikki siirretÃ¤Ã¤n
        ok &= testaaTulosX3(N, 0, N, true, -1, print);

        // ei yhtÃ¤Ã¤n siirretÃ¤
        ok &= testaaTulosX3(N, 0, N, true, N+1, print);


        ok &= testaaTulosX3(1, 1, 1, false, 0, print);
        ok &= testaaTulosX3(1, 1, 1, false, 1, print);
        ok &= testaaTulosX3(1, 1, 1, false, 2, print);
        ok &= testaaTulosX3(2, 1, 2, true, 0, print);
        ok &= testaaTulosX3(2, 1, 2, true, 1, print);
        ok &= testaaTulosX3(2, 1, 2, true, 2, print);
        ok &= testaaTulosX3(2, 1, 2, true, 3, print);

        System.out.println("\nTestataan merkkijonoilla ");

        ok &= testaaTulosX3mjono(5, 1, print);
        ok &= testaaTulosX3mjono(15, 1, print);
        ok &= testaaTulosX3mjono(15, 2, print);

        if (ok)
            System.out.println("\nAlun testit antoivat kaikki oikean tuloksen.");

        // asetetaan "satunnainen" satunnaislukusiemen
        rnd.setSeed(System.currentTimeMillis());


        // testataan monta satunnaista syÃ¶tettÃ¤
        int nTest = 1000;
        int k = 0;
        int virheet = 0;
        for (k = 0; k < nTest; k++) {
            if (! testaaTulosX3(N, 0, N, rnd.nextBoolean(), rnd.nextInt(N+3), 0))
                virheet++;
            if (virheet > 30)
                break;
        }
        if (virheet > 0)
            ok = false;
        System.out.println("\n" + k + " testistÃ¤ " + (k-virheet) + " oikein.");

        if (ok)
            System.out.println("\nKaikki tehdyt testit antoivat oikean tuloksen.");
        else
            System.out.println("\nJoissain testeissÃ¤ virheitÃ¤.");


    }

    /**
     * Testaa jaottelua annetun kokoisella listalla
     *
     * @param n            listan alkioiden mÃ¤Ã¤rÃ¤
     * @param min          pienin luku listassa
     * @param max          suurin luku listassa
     * @param varmistaRajat varmistetaanko min ja max kuhunkin listaan
     * @param raja          jaotteluraja
     * @param print         tulostuksen mÃ¤Ã¤rÃ¤
     * @return  true jos testi meni oikein, muuten false
     */
    static boolean testaaTulosX3(int n, int min, int max, boolean varmistaRajat, Integer raja, int print) {

        // generoidaan syÃ¶te
        LinkedList<Integer> L = satunnainenLista(n, min, max, varmistaRajat);
        LinkedList<Integer> cL = new LinkedList<>(L); // kopio syÃ¶tteestÃ¤ talteen

        if (print > 0) System.out.println("\nTESTI n="+n + "min="+min + " max="+max + " raja="+raja);
        // tulostetaan syÃ¶tteet
        if (n < 20 && print > 2 || print > 5) {
            System.out.println("L=(" + n + "): " + L + " raja: " + raja);
        }

        // kutsutaan testattavaa metodia
        LinkedList<Integer> tulos = testattava.siirraSuuremmat(L, raja);

        // tulostetaan tulos
        if (print > 0) {
            if (n < 20 && print > 2 || print > 5) {
                System.out.println("JÃ¤ljelle jÃ¤i: " + L);
                System.out.println("Siirrettiin : " + tulos);
            }
            System.out.println("SyÃ¶te " + n + " kpl -> L " + L.size() + " tulos " + tulos.size() +
                    "  yhteensÃ¤ " + (L.size() + tulos.size()) + " kpl" );
        }

        boolean ok = true;

        // tarkastetaan, ettÃ¤ alkioiden yhteismÃ¤Ã¤rÃ¤ sÃ¤ilyi
        if (n != L.size() + tulos.size()) {
            ok = false;
            if (print > 0) System.out.println("Listojen alkiomÃ¤Ã¤rÃ¤t eivÃ¤t tÃ¤smÃ¤Ã¤!");
        }

        // tarkastetaan, ettÃ¤ jaottelu meni oikein
        ok &= eihanOleSuurempia(L, raja, print);
        ok &= ovathanSuurempia(tulos, raja, print);

        ok &= vertaaSisallot(cL, L, tulos);

        ok &= onkoSamaJarjestys(L, cL, print);
        ok &= onkoSamaJarjestys(tulos, cL, print);

        return ok;

    }


    /**
     * Testaa jaottelua merkkijonolla.
     *
     * @param n            listan alkioiden mÃ¤Ã¤rÃ¤
     * @param len          merkkijonojen pituus
     * @param print
     * @return
     */
    static boolean testaaTulosX3mjono(int n, int len, int print) {

        // generoidaan syÃ¶te
        LinkedList<String> L = satunnainenListaS(n, len);
        LinkedList<String> cL = new LinkedList<>(L); // kopio syÃ¶tteestÃ¤ talteen

        String raja = randomString(rnd, len);

        if (print > 0) System.out.println("\nTESTI n="+n + " len="+len + " raja="+raja);
        // tulostetaan syÃ¶tteet
        if (n < 20 && print > 2 || print > 5) {
            System.out.println("L=(" + n + "): " + L + " raja: " + raja);
        }

        // kutsutaan testattavaa metodia
        LinkedList<String> tulos = testattava.siirraSuuremmat(L, raja);

        // tulostetaan tulos
        if (print > 0) {
            if (n < 20 && print > 2 || print > 5) {
                System.out.println("JÃ¤ljelle jÃ¤i: " + L);
                System.out.println("Siirrettiin : " + tulos);
            }
            System.out.println("SyÃ¶te " + n + " kpl -> L " + L.size() + " tulos " + tulos.size() +
                    "  yhteensÃ¤ " + (L.size() + tulos.size()) + " kpl" );
        }

        boolean ok = true;

        // tarkastetaan, ettÃ¤ alkioiden yhteismÃ¤Ã¤rÃ¤ sÃ¤ilyi
        if (n != L.size() + tulos.size()) {
            ok = false;
            if (print > 0) System.out.println("Listojen alkiomÃ¤Ã¤rÃ¤t eivÃ¤t tÃ¤smÃ¤Ã¤!");
        }

        // tarkastetaan, ettÃ¤ jaottelu meni oikein
        ok &= eihanOleSuurempia(L, raja, print);
        ok &= ovathanSuurempia(tulos, raja, print);

        ok &= vertaaSisallot(cL, L, tulos);

        ok &= onkoSamaJarjestys(L, cL, print);
        ok &= onkoSamaJarjestys(tulos, cL, print);

        return ok;

    }



    /**
     * Generoi satunnaisen n kokoisen listan
     *
     * @param n             alkioiden mÃ¤Ã¤rÃ¤
     * @param min           pienin mahdollinen alkio
     * @param max           suurin mahdollinen alkio
     * @param varmistaRajat jos tosi, niin min ja max ovat aina mukana (paitsi jos n<2)
     * @return uusi taulukko.
     */
    static LinkedList<Integer> satunnainenLista(int n, int min, int max, boolean varmistaRajat) {
        LinkedList<Integer> tulos = new LinkedList<>();
        int k = 0;
        if (max < 1)
            max = 1;
        if (n < 2)
            max = min;
        if (varmistaRajat) {
            if (n >= 1) {
                tulos.add(min);
                k++;
            }
            if (n >= 2) {
                tulos.add(rnd.nextInt(2), max);
                k++;
            }
        }
        for (int i = k; i < n; i++) {
            tulos.add(rnd.nextInt(max - min + 1) + min);
        }
        return tulos;
    }

    /**
     * Generoi satunnaisen n kokoisen merkkijonolistan
     *
     * @param n   alkioiden mÃ¤Ã¤rÃ¤
     * @param len merkkijonojen pituus
     * @return uusi lista
     */
    static LinkedList<String> satunnainenListaS(int n, int len) {
        LinkedList<String> tulos = new LinkedList<>();
        int k = 0;
        for (int i = k; i < n; i++) {
            tulos.add(randomString(rnd, len));
        }
        return tulos;
    }

    /**
     * Palauttaa satunnaisen len mittaisen merkkijonon.
     *
     * @param r   satunnaislukugeneraattori
     * @param len merkkijonon pituus
     * @return uusi merkkijono
     */
    public static String randomString(Random r, int len) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(26) + 'a');
        return new String(C);
    }



    /**
     * Tarkastaa ettei listassa ole suurempia alkioita kuin raja.
     *
     * @param <E>   alkiotyyppi
     * @param L     tarkastettava lista
     * @param raja     vertailtava raja-arvo
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return tosi, jos mikÃ¤Ã¤n ei ole suurempia tai yhtÃ¤suuri kuin raja, muuten epÃ¤tosi
     */
    static public <E extends Comparable<? super E>> boolean eihanOleSuurempia(LinkedList<E> L, E raja, int print) {
        for (E a : L) {
            if (a == null) {
                if (print > 1)
                    System.out.println("VIRHE: Listassa L null alkio!");
                return false;
            } else if (a.compareTo(raja) > 0) {
                if (print > 1)
                    System.out.println("VIRHE: Suurempi alkio jÃ¤ljellÃ¤: " + a +
                            " > " + raja);
                return false;
            }
        }
        return true;
    }

    /**
     * Tarkastaa ettÃ¤ listan kaikki alkiot ovat suurempia kuin raja.
     *
     * @param <E>   alkiotyyppi
     * @param L     tarkastettava lista
     * @param raja     vertailtava raja-arvo
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return tosi, jos kaikki ovat suurempia, muuten epÃ¤tosi
     */
    static public <E extends Comparable<? super E>> boolean ovathanSuurempia(LinkedList<E> L, E raja, int print) {
        for (E a : L) {
            if (a == null) {
                if (print > 1)
                    System.out.println("VIRHE: Listassa null alkio!");
                return false;
            } else if (a.compareTo(raja) <= 0) {
                if (print > 1)
                    System.out.println("VIRHE: Pienempi tai yhtÃ¤suuri lÃ¶ytyi: " + a  +
                            " <= " + raja);
                return false;
            }
        }
        return true;
    }

    static public <E extends Comparable<? super E>> boolean onkoSamaJarjestys(LinkedList<E> L, LinkedList<E> vrt, int print) {
        Iterator<E> vrtIt = vrt.iterator();
        E vrtElem = nextOrNull(vrtIt);
        for (E elem : L) {
            while (!elem.equals(vrtElem) && vrtElem != null)
                vrtElem = nextOrNull(vrtIt);

            if (vrtElem == null) {
                if (print > 4)
                    System.out.println("VIRHE: alkio " + elem + " tuloksessa vÃ¤Ã¤rÃ¤ssÃ¤ paikassa tai puuttuu syÃ¶tteestÃ¤");
                return false;
            }

        }
        return true;
    }

    static <E extends Comparable<? super E>> E nextOrNull(Iterator<E> i) {
        return i.hasNext() ? i.next() : null;
    }


    /**
     * Vertaa, ettÃ¤ onhan listojen A ja B yhteissisÃ¤ltÃ¶ sama kuin listan S.
     * @param S Kaikkien alkioiden lista
     * @param A Osa-alkioiden lista
     * @param B Osa-alkioiden lista
     * @param <E> alkiotyyppi
     * @return tosi, jos sisÃ¤llÃ¶t tÃ¤smÃ¤Ã¤vÃ¤t, muuten epÃ¤tosi
     */
    static <E> boolean vertaaSisallot(LinkedList<E> S, LinkedList<E> A, LinkedList<E> B) {
        Map<E, Integer> MS = new HashMap<>();
        Map<E, Integer> MAB = new HashMap<>();
        for (E x : S)
            MS.compute(x, (k, v) -> v == null ? 1 : v+1);
        for (E x : A)
            MAB.compute(x, (k, v) -> v == null ? 1 : v+1);
        for (E x : B)
            MAB.compute(x, (k, v) -> v == null ? 1 : v+1);

        // TODO: voisi tulostaa mahdollisia virheitÃ¤

        return MS.equals(MAB);
    }


}