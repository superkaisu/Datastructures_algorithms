import java.util.*;

/**
 * Testausluokka TRA I tehtÃ¤vÃ¤Ã¤n X2.
 */
public class TRAI_23_X2_testi {

    static Random rnd = new Random();

    static TRAI_23_X2 testattava = new TRAI_23_X2_ksuurnie(); /* <-- Oma tunnus tÃ¤hÃ¤n */


    public static void main(String[] args) {

        // taulukoiden koko
        int N = 6;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);

        // tulostusten mÃ¤Ã¤rÃ¤
        int print = 3;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);

        rnd.setSeed(siemen);

        boolean ok = true;

        // satunnaisia
        ok &= testaaTulosX2(N, N, 0, 0, N, N, false,  print);
        ok &= testaaTulosX2(N, N, 0, 0, N * 2, N * 5, false, print);

        // toisessa pienempi alkio, mutta silti limittÃ¤in
        ok &= testaaTulosX2(N, N, 0, 2, N , N+2, true, print);
        ok &= testaaTulosX2(N, N, 2, 0, N+2 , N, true, print);

        // toisen suurin on toisen pienin
        ok &= testaaTulosX2(N, N, 0, N, N , N*2, true, print);
        ok &= testaaTulosX2(N, N, N, 0, N*2 , N, true, print);

        // toisen suurin on pienempi kuin toisen pienin
        ok &= testaaTulosX2(N, N, 0, N+1, N , N*2, true, print);
        ok &= testaaTulosX2(N, N, N+1, 0, N*2 , N, true, print);

        // lyhyitÃ¤ listoja
        ok &= testaaTulosX2(1, 1, 0, 0, N, N, false, print);
        ok &= testaaTulosX2(1, 2, 0, 0, 1, 1, false, print);
        ok &= testaaTulosX2(2, 1, 0, 0, 3, 2, false, print);
        ok &= testaaTulosX2(2, 0, 0, 0, N, N, false, print);
        ok &= testaaTulosX2(0, 1, 0, 0, N, N, false, print);
        ok &= testaaTulosX2(1, 1, 0, 0, N, N, false, print);
        ok &= testaaTulosX2(0, 0, 0, 0, N, N, false, print);

        // suurempia lukuja
        ok &= testaaTulosX2(N, N, 1000, 1000, 1000+N, 1000+N, false,  print);
        ok &= testaaTulosX2(N, N, 1000, 1000, 1000 + N * 2, 1000 + N * 3, false, print);


        if (ok)
            System.out.println("Alun testit antoivat kaikki oikean tuloksen.");

        // asetetaan "satunnainen" satunnaislukusiemen
        rnd.setSeed(System.currentTimeMillis());

        // testataan monta satunnaista syÃ¶tettÃ¤
        int nTest = 1000;
        int k = 0;
        int virheet = 0;
        for (k = 0; k < nTest; k++) {
            if (! testaaTulosX2(N, N, 0, 0, N, N, rnd.nextBoolean(),  0))
                virheet++;
            if (virheet > 30)
                break;
        }
        if (virheet > 0)
            ok = false;
        System.out.println("\n" + k + " testistÃ¤ " + (k-virheet) + " oikein.");

        if (ok)
            System.out.println("\nKaikki tehdyt testit antoivat oikean tuloksen.\nMuista myÃ¶s itsearviointi ja aikavaativuus.");
        else
            System.out.println("\nJoissain testeissÃ¤ virheitÃ¤.");


    }

    /**
     * Testaa yhdistettÃ¤ eri kokoisille taulukoille.
     *
     * @param n1            1. taulukon alkioiden mÃ¤Ã¤rÃ¤
     * @param n2            2. taulukon alkioiden mÃ¤Ã¤rÃ¤
     * @param min1          pienin luku taulukossa 1
     * @param min2          pienin luku taulukossa 2
     * @param max1          suurin luku taulukossa 1
     * @param max2          suurin luku taulukossa 2
     * @param varmistaRajat varmistetaanko min ja max kuhunkin taulukkoon
     * @param print
     * @return
     */
    static boolean testaaTulosX2(int n1, int n2, int min1, int min2,
                                 int max1, int max2, boolean varmistaRajat, int print) {

        // generoidaan syÃ¶tteet
        ArrayList<Integer> A = satunnainenKasvavaLista(n1, min1, max1, varmistaRajat);
        ArrayList<Integer> B = satunnainenKasvavaLista(n2, min2, max2, varmistaRajat);

        // kopiot syÃ¶tteistÃ¤ talteen
        ArrayList<Integer> cA = new ArrayList<>(A);
        ArrayList<Integer> cB = new ArrayList<>(B);

        // tulostetaan syÃ¶tteet
        if (print > 0) System.out.println("\nTESTI n1="+n1 + " n2="+n2 + " min1="+min1 +
                " min2="+min2 + " max1="+max1 + " max2="+max2);
        if ((n1 < 20 && n2 < 20 && print > 2) || print > 5) {
            System.out.println("A[" + n1 + "]: " + A);
            System.out.println("B[" + n2 + "]: " + B);
        }

        // kutsutaan testattavaa metodia
        ArrayList<Integer> tul = testattava.yhdisteKasvavista(A, B);

        // tulostetaan tulos
        if ((tul.size() < 20 && print > 1) || print > 5)
            System.out.println("Yhdiste: " + tul);

        boolean ok = true;

        ok &= onkoJarjestyksessa(tul, print);
        ok &= onkoUniikitAlkiot(tul, print);

        boolean sisaltoSailynyt = A.equals(cA) && B.equals(cB);
        if (! sisaltoSailynyt && print > 0)
            System.out.println("Virhe: metodi muuttaa listan sisÃ¤ltÃ¶Ã¤!");

        // verrataan tulosta verrokkiyhdisteeseen
        ok &= vertaa(tul, yhdisteJoukoilla(A, B), print);
        // System.out.println("Vertailu: " + ok);

        return ok && sisaltoSailynyt;

    }


    /**
     * Generoi satunnaisen n kokoisen kasvavassa jÃ¤rjestyksessÃ¤ olevan listan
     *
     * @param n             alkioiden mÃ¤Ã¤rÃ¤
     * @param min           pienin mahdollinen alkio
     * @param max           suurin mahdollinen alkio
     * @param varmistaRajat jos tosi, niin min ja max ovat aina mukana (paitsi jos n<2)
     * @return uusi taulukko.
     */
    static ArrayList<Integer> satunnainenKasvavaLista(int n, int min, int max, boolean varmistaRajat) {
        ArrayList<Integer> tulos = new ArrayList<>(n);
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
                tulos.add(max);
                k++;
            }
        }
        for (int i = k; i < n; i++) {
            tulos.add(rnd.nextInt(max - min + 1) + min);
        }
        Collections.sort(tulos);
        return tulos;
    }


    /**
     * Tarkastaa onko annettu kokonaislukulista kasvavassa jÃ¤rjestyksessÃ¤.
     *
     * @param A     tarkastettava lista
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return tosi, jos on kasvava jÃ¤rjestys, muuten epÃ¤tosi
     */
    static public boolean onkoJarjestyksessa(ArrayList<Integer> A, int print) {
        for (int i = 0; i < A.size() - 1; i++)
            if (A.get(i).compareTo(A.get(i + 1)) > 0) {
                if (print > 1)
                    System.out.println("VÃ¤Ã¤rÃ¤ jÃ¤rjestys: A[" + i + "]=" + A.get(i) +
                            "A[" + (i + 1) + "]=" + A.get(i + 1));
                return false;
            }
        if (print > 1)
            System.out.println("Kasvavassa jÃ¤rjestyksessÃ¤ ok");
        return true;
    }

    /**
     * Tarkastaa ettÃ¤ kasvavassa listassa alkiot ovat uniikkeja.
     * Tarkemmin: onko perÃ¤kkÃ¤isissÃ¤ indekseissÃ¤ samoja alkioita.
     *
     * @param A     tarkastettava lista
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return tosi, jos on ei ole tupla-alkioita, muuten epÃ¤tosi
     */
    static public boolean onkoKasvavassaUniikitAlkiot(ArrayList<Integer> A, int print) {
        for (int i = 0; i < A.size() - 1; i++)
            if (A.get(i).equals(A.get(i + 1))) {
                if (print > 1)
                    System.out.println("Sama alkio kahdesti perÃ¤kkÃ¤in: A[" + i + "]=" + A.get(i) +
                            "A[" + (i + 1) + "]=" + A.get(i + 1));
                return false;
            }
        return true;
    }

    /**
     * Tarkastaa ettÃ¤ ovatko listassa olevat alkiot uniikkeja.
     *
     * @param A     tarkastettava lista
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return tosi, jos on ei ole tupla-alkioita, muuten epÃ¤tosi
     */
    static public boolean onkoUniikitAlkiot(ArrayList<Integer> A, int print) {
        HashSet<Integer> HS = new HashSet<>(A.size()*2);
        for (Integer x : A) {
            if (!HS.add(x)) {
                System.out.println("Alkio " + x + " esiintyy useasti.");
                return false;
            }
        }
        if (print > 1)
            System.out.println("Uniikit alkiot tuloksessa ok");
        return true;
    }



    /**
     * Vertaa kahden listan sisÃ¤ltÃ¶Ã¤.
     *
     * @param A     verrattava
     * @param V     verrokki
     * @param print tulostuksen mÃ¤Ã¤rÃ¤
     * @return tosi jos samat alkiot samassa jÃ¤rjestyksessÃ¤, muuten epÃ¤tosi
     */
    static public boolean vertaa(ArrayList<Integer> A, ArrayList<Integer> V, int print) {

        if (print > 1 && A.size() <= 20 && V.size() <= 20) {
            System.out.println("Vertailu");
            System.out.println("A=" + A);
            System.out.println("V=" + V);
        }


        if (A.size() != V.size()) {
            System.out.println("Eri kokoiset: T:" + A.size() + "  V:" + V.size());
            if (A.size() > V.size())
                System.out.println("Tuloslistassa enemmÃ¤n alkioita, onko joku useamman kerran vai onko uusia alkioita?");
            else
                System.out.println("Tuloslistassa vÃ¤hemmÃ¤n alkioita, joku puuttuu?");
            return false;
        }

        if (! A.containsAll(V) || !V.containsAll(A)) {
            System.out.println("SisÃ¤llÃ¶t eivÃ¤t tÃ¤smÃ¤Ã¤");
            return false;
        }
/*
        for (int i = 0; i < A.size(); i++) {
            if (!A.get(i).equals(V.get(i))) {
                System.out.println("VÃ¤Ã¤rÃ¤ alkio: " + "A[" + i + "]=" + A.get(i) +
                            "V[" + i + "]=" + V.get(i));
                return false;
            }
        }

 */

        if (print > 1)
            System.out.println("Vertailu ok");

        return true;
    }


    /**
     * Kasvavien listojen yhdiste.
     * Palauttaa sellaiset alkiot jotka lÃ¶ytyvÃ¤t molemmista syÃ¶telistoista.
     * Kukin alkio tulee tuloslistaan vain kerran.
     * Tuloslista tulee myÃ¶s kasvavaan jÃ¤rjestykseen.
     * <p>
     * TÃ„MÃ„ ANTAA SIIS SAMAN TULOKSEN KUIN MITÃ„ TEHTÃ„VÃ„SSÃ„ X2 PITÃ„Ã„ TEHDÃ„.
     * MUTTA TÃ„MÃ„ EI KELPAA VAUSTAUKSEKSI KOSKA TEHTÃ„VÃ„NANNOSSA KIELLETÃ„Ã„N
     * JOUKKOJEN KÃ„YTTÃ„MINEN. EIKÃ„ TÃ„MÃ„ OLE EDES HYVÃ„ MALLI KOSKA TÃ„MÃ„ EI OLE TEHOKKAIN
     * TAPA TEHDÃ„ (EI HYÃ–DYNNÃ„ SYÃ–TELISTOJEN VALMISTA JÃ„RJESTYSTÃ„).
     * TÃ¤mÃ¤ on tÃ¤ssÃ¤ vain jotta tÃ¤llÃ¤ voidaan testata kunkin oman ratkaisun tuloksen oikeellisuutta.
     *
     * @param A ensimmÃ¤inen syÃ¶telista
     * @param B toinen syÃ¶telista
     * @return yhdistelista
     */
    static public ArrayList<Integer> yhdisteJoukoilla(ArrayList<Integer> A, ArrayList<Integer> B) {
        TreeSet<Integer> ts = new TreeSet<Integer>(A);
        ts.addAll(B);
        return new ArrayList<Integer>(ts);
    }


}
