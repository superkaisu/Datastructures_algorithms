import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Testausluokka TRA I 2023 tehtÃ¤vÃ¤Ã¤n X1.
 */
public class TRAI_23_X1_testi {

    static Random rnd = new Random();

    static TRAI_23_X1 testattava = new TRAI_23_X1_ksuurnie(); /* <-- Oma tunnus tÃ¤hÃ¤n */

    public static void main(String[] args) {

        // taulukoiden koko
        int N = 11;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);

        // tulostusten mÃ¤Ã¤rÃ¤
        int print = 7;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);

        rnd.setSeed(siemen);

        boolean ok = true;

        // testataan erilaisilla syÃ¶tteillÃ¤
        ok &= testaaTulosX1(0, 0, true, print);
        ok &= testaaTulosX1(1, 0, true, print);
        ok &= testaaTulosX1(2, 0, true, print);
        ok &= testaaTulosX1(2, 0, false, print);
        ok &= testaaTulosX1(3, 0, true, print);
        ok &= testaaTulosX1(3, 0, false, print);
        ok &= testaaTulosX1(4, 0, true, print);
        ok &= testaaTulosX1(N, 0, true, print);
        ok &= testaaTulosX1(N, 0, false, print);
        ok &= testaaTulosX1(N/2, 0, false, print);
        ok &= testaaTulosX1(N*2, 0, true, print);
        ok &= testaaTulosX1(5, 300, false, print);    // toimiihan myÃ¶s isommilla luvuilla?
        ok &= testaaTulosX1(5, 300, true, print);

        if (ok)
            System.out.println("Alun testit antoivat kaikki oikean tuloksen.");

        // asetetaan "satunnainen" satunnaislukusiemen
        rnd.setSeed(System.currentTimeMillis());

        // testataan monta satunnaista syÃ¶tettÃ¤
        int nTest = 1000;
        int k = 0;
        int virheet = 0;
        for (k = 0; k < nTest; k++) {
            if (! testaaTulosX1(rnd.nextInt(nTest)+2, rnd.nextInt(nTest), rnd.nextBoolean(), 0))
                virheet++;
            if (virheet > 30)
                break;
        }
        if (virheet > 0)
            ok = false;
        System.out.println("\n" + k + " testistÃ¤ " + (k-virheet) + " oikein.");

        if (ok)
            System.out.println("\nKaikki tehdyt testit antoivat oikean tuloksen.\nMuista myÃ¶s itsearviointi.");
        else
            System.out.println("\nJoissain testeissÃ¤ virheitÃ¤.");


    }


    /**
     * Testaa tehtÃ¤vÃ¤Ã¤ satunnaisella syÃ¶tteellÃ¤.
     * @param n syÃ¶tteen koko
     * @param min pienin kÃ¤ytettÃ¤vÃ¤ kokonaisluku
     * @param uniikkeja laitetaanko syÃ¶tteeseen uniikkeja vai ei
     * @param print miten paljon tulostetaan 0: ei mitÃ¤Ã¤n, 1 jotain, 2-: enemmÃ¤n
     * @return true jos testauksen tulos oli odotettu, muuten false
     */
    static boolean testaaTulosX1(int n, int min, boolean uniikkeja, int print) {

        // syÃ¶tteen generointi, syÃ¶tteestÃ¤ kopio talteen ja mahdollinen tulostus
        Integer[] A = satunnainenTaulukko(n, min, !uniikkeja);
        Integer[] cA = Arrays.copyOf(A, A.length);

        if (print > 0) System.out.println("\nTesti n="+n + " min="+min + " uniikkeja=" + uniikkeja);
        if (n < 20 && print > 2 || print > 5) System.out.println("A[" + n + "]: " + Arrays.toString(A));

        // kutsutaan varsinaista testattavaa metodia
        boolean tulos = testattava.ovatkoUniikkeja(A);

        // tulostetaan tulos ja verrataan odotettuun
        if (print > 1) System.out.println("Tulos: " + tulos);
        if (print > 0 && tulos != uniikkeja)
            System.out.println("VÃ¤Ã¤rÃ¤ tulos: " + tulos + " vaikka piti olla: " + uniikkeja);

        boolean sisaltoSailynyt = Arrays.equals(A, cA);
        if (! sisaltoSailynyt && print > 0)
            System.out.println("Virhe: metodi muuttaa taulukon sisÃ¤ltÃ¶Ã¤!");

        if (print > 1 && (tulos == uniikkeja) && sisaltoSailynyt)
            System.out.println("Testi ok");

        return (tulos == uniikkeja) && sisaltoSailynyt;

    }



    /**
     * Generoi satunnaisen n kokoisen taulukon.
     * @param n alkioiden mÃ¤Ã¤rÃ¤
     * @param min pienin mahdollinen alkio
     * @param duplikaatteja laitetaanko taulukkoon joku arvo kahdesti vai ei?
     * @return uusi taulukko.
     */
    static Integer[] satunnainenTaulukko(int n, int min, boolean duplikaatteja) {
        if (n < 0 || (n < 2 && duplikaatteja))
            throw new RuntimeException("Ei voi tehdÃ¤ vaadittua taulukkoa");
        boolean onkoJoDuplikaatti = false;
        Integer[] A = new Integer[n];
        if (min > Integer.MAX_VALUE - 4*n)
            min = Integer.MAX_VALUE - 4*n;
        HashSet<Integer> hs = new HashSet<>();  // apujoukko jolla duplikaatit tunnistetaan
        int i = 0;
        while (i < n) {
            Integer x = min + rnd.nextInt(2 + n*3);
            if (hs.add(x))
                A[i++] = x; // uusi alkio, lisÃ¤ys onnistui
            else if (duplikaatteja /* && !onkoJoDuplikaatti */ ) { // poista kommenttimerkit jos vain yksi duplikaattipari sallitaan
                A[i++] = x; // lisÃ¤tÃ¤Ã¤n ensimmÃ¤inen duplikaatti jos siihen oli lupa
                onkoJoDuplikaatti = true;
            } // muuten ei lisÃ¤tÃ¤
        }
        if (duplikaatteja && !onkoJoDuplikaatti)    // jollei tullut duplikaattia, niin lisÃ¤tÃ¤Ã¤n
            teeDuplikaatti(A);
        return A;
    }

    /**
     * Korvaa taulukon satunnaisen alkion toisella siten, ettÃ¤ taulukkoon tulee
     * duplikaatti.
     * @param A syÃ¶tetaulukko
     */
    static void teeDuplikaatti(Integer[] A) {
        if (A == null || A.length < 2)
            throw new RuntimeException("Ei voi tehdÃ¤ duplikaattia alle 2 alkioiseen taulukkoon!");
        int n = A.length;
        int i1 = rnd.nextInt(n);
        int i2 = i1;
        while (i1 == i2)
            i2 = rnd.nextInt(n);
        A[i1] = A[i2];

    }

}

