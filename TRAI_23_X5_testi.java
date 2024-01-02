// TRAI_23_X5_testi.java SJ

import java.util.*;

public class TRAI_23_X5_testi {


    static TRAI_23_X5 testattava = new TRAI_23_X5_ksuurnie(); /* <-- Oma tunnus tÃ¤hÃ¤n */
    static int print = 4;

    public static void main(String[] args) {

        int N = 5;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        int seed = N;
        if (args.length > 0)
            seed = Integer.parseInt(args[1]);

        if (args.length > 0)
            print = Integer.parseInt(args[2]);

        int virhe = 0;
        Random r = new Random(seed);

        virhe += testaa(r, 1, 1, 1, false, 2);    // satunnainen syÃ¶te
        virhe += testaa(r, 2, 2, 2, false, 2);    // satunnainen syÃ¶te
        virhe += testaa(r, 3, 3, 3, false, 2);    // satunnainen syÃ¶te

        virhe += testaa(r, N, N, N, false, 2);    // satunnainen syÃ¶te
        virhe += testaa(r, N, N, N, false, 1);    // satunnainen syÃ¶te
        virhe += testaa(r, N, N + 1, N + 2, false, 2);    // satunnainen syÃ¶te
        virhe += testaa(r, N + 2, N + 1, N, false, 2);    // satunnainen syÃ¶te
        virhe += testaa(r, N, N * 2, N * 3, false, 2);    // satunnainen syÃ¶te
        virhe += testaa(r, N * 3, N * 2, N, false, 2);    // satunnainen syÃ¶te
        virhe += testaa(r, N, N, N, false, 2);    // satunnainen syÃ¶te
        virhe += testaa(r, N, N, N, false, 4);    // satunnainen syÃ¶te
        virhe += testaa(r, N, N, N, false, 10);    // satunnainen syÃ¶te
        virhe += testaa(r, N, N, N, false, 100);    // satunnainen syÃ¶te
        virhe += testaa(r, N, N, N, true, 2);        // kaikkiin samat arvot
        virhe += testaa(r, N, N, 0, true, 2);    // kahteen samat arvot ja kolmas tyhjÃ¤
        virhe += testaa(r, N, 0, N, true, 2);    // kahteen samat arvot ja kolmas tyhjÃ¤
        virhe += testaa(r, 0, N, N, true, 2);    // kahteen samat arvot ja kolmas tyhjÃ¤
        virhe += testaa(r, N, N, 0, false, 1);    // kahteen jotain ja kolmas tyhjÃ¤
        virhe += testaa(r, N, 0, N, false, 1);    // kahteen jotain ja kolmas tyhjÃ¤
        virhe += testaa(r, 0, N, N, false, 1);    // kahteen jotain ja kolmas tyhjÃ¤
        virhe += testaa(r, 0, 0, N, true, 2);    // kaksi tyhjÃ¤Ã¤, kolmanteen jotain
        virhe += testaa(r, 0, 0, 0, true, 2);    // kolme tyhjÃ¤Ã¤

        virhe += testaaMjono(r, N, N, N, 1);    // satunnainen merkkijonosyÃ¶te
        virhe += testaaMjono(r, N*10, N*10, N*10, 2);    // satunnainen merkkijonosyÃ¶te

        // ajetaan vielÃ¤ vaihtuvilla testeillÃ¤
        r.setSeed(System.currentTimeMillis());
        virhe += testaa(r, N, N, N, false, 2);    // satunnainen syÃ¶te
        virhe += testaa(r, N, N, N, false, 3);    // satunnainen syÃ¶te
        virhe += testaa(r, N, N, N, false, 4);    // satunnainen syÃ¶te

        // jokunen isompi testi vielÃ¤
        long start = System.currentTimeMillis();
        virhe += testaa(r, 100, 100, 100, false, 2);
        virhe += testaa(r, 1000, 1000, 1000, false, 2);
        virhe += testaa(r, 10000, 10000, 10000, false, 2);
        if (System.currentTimeMillis() < start + 5 * 1000) // max 5 sekuntia
            virhe += testaa(r, 100000, 100000, 100000, false, 2);
        if (System.currentTimeMillis() < start + 5 * 1000) // max 5 sekuntia
            virhe += testaa(r, 1000000, 1000000, 1000000, false, 2);

        if (virhe == 0)
            System.out.println("\n Kaikki testit ok");
        else
            System.out.println("\nYhteensÃ¤ " + virhe + " testiÃ¤ epÃ¤onnistui.");


    } // main()

    /**
     * Testaa metodia halutun kokoisilla syÃ¶tteillÃ¤.
     * Joukkojen koot voivat olla pienempiÃ¤ jos/kun satunnaislukugeneraattori
     * antaa samoja lukuja uudestaan.
     *
     * @param r     satunnaislukugeneraattori
     * @param n1    S1 koko
     * @param n2    S2 koko
     * @param n3    S3 koko
     * @param samat laitetaanko joukkoisin samoja lukuja vai eri lukuja
     * @param k     kerroin N:lle satunnaisluvun maksimia varten
     * @return 0 jos tulos oli oikea, muuten 1
     */
    static int testaa(Random r, int n1, int n2, int n3, boolean samat, int k) {
        Set<Integer> S1 = new HashSet<>();
        Set<Integer> S2 = new HashSet<>();
        Set<Integer> S3 = new HashSet<>();

        if (samat) {    // samat arvot kaikkiin
            int N = Math.max(Math.max(n1, n2), n3);
            for (int i = 0; i < N; i++) {
                int x = r.nextInt(N * k);
                if (i < n1) S1.add(x);
                if (i < n2) S2.add(x);
                if (i < n3) S3.add(x);
            }

        } else {    // kullekin eri arvoja
            for (int i = 0; i < n1; i++)
                S1.add(r.nextInt(n1 * k));
            for (int i = 0; i < n2; i++)
                S2.add(r.nextInt(n2 * k));
            for (int i = 0; i < n3; i++)
                S3.add(r.nextInt(n3 * k));
        }

        return testaa2(S1, S2, S3);
    }

    static int testaaMjono(Random r, int n1, int n2, int n3, int len) {
        Set<String> S1 = new HashSet<>();
        Set<String> S2 = new HashSet<>();
        Set<String> S3 = new HashSet<>();

        for (int i = 0; i < n1; i++)
            S1.add(randomString(r, len));

        for (int i = 0; i < n2; i++)
            S2.add(randomString(r, len));

        for (int i = 0; i < n3; i++)
            S3.add(randomString(r, len));

        return testaa2(S1, S2, S3);
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



    static <E> int testaa2(Set<E> S1, Set<E> S2, Set<E> S3) {
        int virhe = 0;
        int n1 = S1.size();
        int n2 = S2.size();
        int n3 = S3.size();

        // kopiot talteen tulostamista ja vertailua varten
        TreeSet<E> TS1 = new TreeSet<>(S1);
        TreeSet<E> TS2 = new TreeSet<>(S2);
        TreeSet<E> TS3 = new TreeSet<>(S3);

        if (n1 + n2 + n3 < 30) {
            System.out.println("---------------------------------------------\nSyÃ¶tejoukot:");
            System.out.println("S1:          " + TS1);
            System.out.println("S2:          " + TS2);
            System.out.println("S3:          " + TS3);
        } else {
            System.out.println("\nJoukkojen alkiomÃ¤Ã¤rÃ¤t: " + n1 + " " + n2 + " " + n3);
        }

        Set<E> vrt = kahdessaKolmestaKuvauksella(S1, S2, S3);
        Set<E> tulos = testattava.kaksiKolmestaLeikkaus(S1, S2, S3);

        if (vrt.size() + tulos.size() < 30) {
            System.out.println("kaksiKolmesta: " + (new TreeSet<>(tulos)));    // jos haluat lajiteltuna tuloksen
            System.out.println("verrokki     : " + (new TreeSet<>(vrt)));        // jos haluat lajiteltuna tuloksen
        } else
            System.out.println("Tuloksen alkiomÃ¤Ã¤rÃ¤t tulos: " + tulos.size() + ", vrt: " + vrt.size());

        if (!S1.equals(TS1) || !S2.equals(TS2) || !S3.equals(TS3)) {
            System.out.println("Muuttaa syÃ¶tejoukkoa vaikka ei saisi!");
            virhe = 1;
        }

        if (vrt.equals(tulos)) {
            System.out.println("Tuloksen sisÃ¤ltÃ¶ tÃ¤smÃ¤Ã¤ verrokkiin, hienoa.");
        } else {
            System.out.println("Tuloksen sisÃ¤ltÃ¶ eroaa verrokista.");
            virhe = 1;
        }

        return virhe;
    }



    /**
     * kahdessa kolmesta -yhdiste kÃ¤yttÃ¤en kuvausta.
     * TÃ„MÃ„ SIIS TEKEE SAMAN KUIN X5, MUTTA X5:SSA ON NYT KIELLETTY KUVAUKSEN KÃ„YTTÃ–
     * JOTEN TÃ„STÃ„ EI KANNATA OTTAA MALLIA. EIKÃ„ TÃ„MÃ„ MUUTENKAAN OLE OHJELMOINTITEKNISESTI
     * TYYLIKÃ„S RATKAISU. ENNEMMINKIN YRITETTY TEHDÃ„ MAHDOLLISIMMAN EPÃ„SELVÃ„...
     * Luo ja palauttaa uuden joukon jossa on ne alkiot jotka ovat
     * tasan kahdessa kolmesta syÃ¶tejoukosta.
     * Jos mikÃ¤Ã¤n alkio ei tÃ¤ytÃ¤ ehtoa, palautetaan tyhjÃ¤ joukko.
     * Ei muuta syÃ¶tejoukkoja.
     *
     * @param S1  syÃ¶tejoukko
     * @param S2  syÃ¶tejoukko
     * @param S3  syÃ¶tejoukko
     * @param <E> alkiotyyppi
     * @return tulosjoukko
     */
    public static <E> Set<E> kahdessaKolmestaKuvauksella(Set<E> S1, Set<E> S2, Set<E> S3) {
        HashMap<E, Integer> HM = new HashMap<>();
        for (Set<E> S : new LinkedList<Set<E>>() {{ add(S1); add(S2);add(S3); }} )
            for (E x : S) HM.compute(x, (k, v) -> v == null ? 1 : v + 1);
        HM.entrySet().removeIf(e -> e.getValue() != 2);
        return HM.keySet();
    }


} // class TRAI_23_X5_testi
