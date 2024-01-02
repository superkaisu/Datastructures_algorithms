// TRAI_23_X6_testi.java SJ

import java.util.*;

public class TRAI_23_X6_testi {

    static TRAI_23_X6 testattava = new TRAI_23_X6_ksuurnie(); /* <-- Oma tunnus tÃ¤hÃ¤n */

    static Random rnd = null;

    public static void main(String[] args) {

        int N = 6;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);
        int M = 12;
        if (args.length > 1)
            N = Integer.parseInt(args[1]);
        int K = 4;
        if (args.length > 2)
            K = Integer.parseInt(args[2]);
        int print = 4;
        if (args.length > 3)
            print = Integer.parseInt(args[3]);

        rnd = new Random(N);

        boolean ok = true;

        // testataan ensin pienillÃ¤ joukoilla, sitten isommilla
        ok &= testaaX6(3, 6, 3, false, print);
        ok &= testaaX6(4, 0, 2, false, print);
        ok &= testaaX6(0, 3, 2, false, print);
        ok &= testaaX6(1, 3, 2, false, print);
        ok &= testaaX6(2, 5, 2, false, print);
        ok &= testaaX6(N, M, K, false, print);
        ok &= testaaX6(N, M, K, true, print);

        // tÃ¤hÃ¤n voi lisÃ¤tÃ¤ vielÃ¤ testejÃ¤

        System.out.println("=== laitetaan vielÃ¤ joka kerta vaihtuva satunnaissiemen");
        rnd.setSeed(System.currentTimeMillis());
        System.out.println("\n== testataan monta satunnaista syÃ¶tettÃ¤");
        int nTest = 200;
        int k = 0;
        int virheet = 0;
        for (k = 0; k < nTest; k++) {
            if (!testaaX6(rnd.nextInt(k+1), rnd.nextInt(k+1)*2, 4, rnd.nextBoolean(), 0))
                virheet++;
            if (virheet >= 30) {
                k++;
                break;
            }
        }
        if (virheet > 0)
            ok &= false;
        System.out.println("\n" + k + " testistÃ¤ " + (k - virheet) + " oikein.");



        if (ok)
            System.out.println("Kaikki testit ok!");
        else
            System.out.println("Joissain testeissÃ¤ virheitÃ¤!");


    } // main()


    static boolean testaaX6(int joukkoja, int alkioita, int kerroin, boolean uniikkiAlkio, int print) {

        boolean ok = true;

        if (print > 0) System.out.println("\n===========\ntestaaX6 " + joukkoja + " " + alkioita + "\n");

        Set<Set<Integer>> SS = new HashSet<>(joukkoja*2);
        Set<Set<Integer>> SSkopio = new HashSet<>(joukkoja*2);


        Map<Set<Integer>, Set<Set<Integer>>> vrt = new HashMap<>(joukkoja*2);

        teeSyote(joukkoja, alkioita, kerroin, uniikkiAlkio, SS, SSkopio, vrt);



        if (print > 2) {
            System.out.println("SyÃ¶tejoukot:");
            tulostaSyote(SS);
        }

        if (print > 2) {
            System.out.println("Odotettu tulos:");
            tulostaTulos(vrt);
        }
        if (print > 1)
            System.out.println("Kutsutaan metodia.");

        Map<Set<Integer>, Set<Set<Integer>>> tulos = testattava.leikkaavatJoukot(SS);

        if (print > 2) {
            System.out.println("leikkaavatJoukot tulos:");
            tulostaTulos(tulos);
        }


        if (! SSkopio.equals(SS)) {
            System.out.println("SyÃ¶tejoukko on muuttunut!");
            ok = false;
        }

        if (vrt.equals(tulos)) {
            if (print > 1)
                System.out.println("Testauksen tulos on sama kuin odotettu tulos!");
        } else {
            ok = false;
            System.out.println("Tulos ei tÃ¤smÃ¤Ã¤ odotettuun verrokkiin!");

            // jotain muita tarkastuksia, lÃ¤hinnÃ¤ tulee tarkempia tulostuksia kuin yllÃ¤olevalla
            // pelkÃ¤llÃ¤ verrokkiin vertailulla
            if (! SSkopio.equals(tulos.keySet())) {
                System.out.println("Tuloskuvauksen avainjoukko ei tÃ¤smÃ¤Ã¤ syÃ¶tejoukkoon!");
            }

            // tarkastetaan, ettei joukko itse ole leikkaavien joukkojensa joukossa
            for (Map.Entry<Set<Integer>, Set<Set<Integer>>> E : tulos.entrySet()) {
                if (E.getValue().contains(E.getKey()))
                    System.out.println("Joukon " + E.getKey() + " leikkaavuuksissa on joukko itse!");
            }

            // kasataan kaikki leikaavuudet yhteen ja tarkastetaan, ettÃ¤ ne ovat alkuperÃ¤isiÃ¤ joukkoja
            Set<Set<Integer>> kaikki = new HashSet<>();
            for (Map.Entry<Set<Integer>, Set<Set<Integer>>> E : tulos.entrySet())
                kaikki.addAll(E.getValue());
            for (Set<Integer> S : kaikki)
                if (! SS.contains(S))
                    System.out.println("Tuloksen joukko " + S + " ei ole alkuperÃ¤isessÃ¤ joukossa!");

        }

        return ok;

    }

    static void tulostaSyote(Set<Set<Integer>> SS) {
        System.out.println("-------");
        for (Set<Integer> S : SS)
            System.out.println(" " + S);
        System.out.println("-------");
    }


    static void tulostaTulos(Map<Set<Integer>, Set<Set<Integer>>> M) {
        System.out.println("-------");
        for (Map.Entry<Set<Integer>, Set<Set<Integer>>> E : M.entrySet()) {
            System.out.print(" " + E.getKey() + " : [ ");
            for (Set<Integer> S : E.getValue())
                System.out.print(S + " ");
            System.out.println("]");
        }
        System.out.println("\n-------");
    }


    /**
     * Generoi syÃ¶tteen ja verrokkituloksen tehtÃ¤vÃ¤Ã¤n X6.
     * @param joukkoja joukkojen mÃ¤Ã¤rÃ¤
     * @param alkioita erilaisten alkioiden mÃ¤Ã¤rÃ¤
     * @param todNak todennÃ¤kÃ¶isyys alkion sijoittumiselle kuhunkin joukkoon, 1=aina, isompi = harvemmin
     * @param uniikkiAlkio sijoitetaanko jokaiseen joukkoon yksi uniikki (negatiivinen) alkio vai ei
     * @param SS joukkojen joukko johon generoitu tulos talletetaan
     * @param SSkopio syvÃ¤kopio joukosta SS
     * @param vrt kuvaus johon verrokkitulos tallennetaan
     */
    static void teeSyote(int joukkoja, int alkioita, int todNak,
                         boolean uniikkiAlkio, Set<Set<Integer>> SS,
                         Set<Set<Integer>> SSkopio, Map<Set<Integer>, Set<Set<Integer>>> vrt) {

        if (todNak < 1)
            todNak = 1;
        ArrayList<Set<Integer>> SSA = new ArrayList<>(joukkoja);
        ArrayList<Set<Set<Integer>>> LA = new ArrayList<>(joukkoja);
        // luodaan syÃ¶tejoukot ja verrokkitulokseen kuvaus
        // kuhunkin syÃ¶tejoukkoon yksi uniikki alkio (-j)
        // koska muuten joukot voivat olla samanlaisia, eikÃ¤ siten niitÃ¤
        // voisi kÃ¤yttÃ¤Ã¤
        for (int j = 0; j < joukkoja; j++) {
            Set<Integer> S = new HashSet<>(alkioita * 2);
            Set<Set<Integer>> LS = new HashSet<>(alkioita * 2);
            if (uniikkiAlkio)
                S.add(-(j+1));
            SSA.add(S);
            LA.add(LS);
        }

        ArrayList<ArrayList<Integer>> kaikkiLeikkaavuudet = new ArrayList<>(alkioita);

        // lisÃ¤tÃ¤Ã¤n syÃ¶tejoukkoihin alkioita
        // ja muistetaan mitkÃ¤ syÃ¶tejoukot saavat minkÃ¤kin alkion
        for (int x = 0; x < alkioita; x++) {
            ArrayList<Integer> alkionSisaltavatJoukot = new ArrayList<>(joukkoja);
            for (int j = 0; j < SSA.size(); j++) {
                Set<Integer> S = SSA.get(j);
                if (rnd.nextInt(todNak) == 0) {
                    S.add(x);
                    alkionSisaltavatJoukot.add(j);
                }
            }
            kaikkiLeikkaavuudet.add(alkionSisaltavatJoukot);
        }

        for (ArrayList<Integer> saavat : kaikkiLeikkaavuudet) {
            // lisÃ¤tÃ¤Ã¤n leikkaavuuksiin ne jotka saivat tÃ¤mÃ¤n alkion
            for (int j : saavat) {
                for (int j2 : saavat)
                    if (! SSA.get(j).equals(SSA.get(j2)))
                        LA.get(j).add(SSA.get(j2));
            }
        }

        // taulukot parametreina saatuihin joukkojen joukkoon ja kuvaukseen
        for (int j = 0; j < joukkoja; j++) {
            vrt.put(SSA.get(j), LA.get(j));
        }
        SS.addAll(SSA);

        // haluttaessa vielÃ¤ syÃ¶tejoukosta SS tehdÃ¤Ã¤n syvÃ¤kopio
        if (SSkopio != null) {
            for (Set<Integer> S : SSA)
                SSkopio.add(new HashSet<>(S));
        }

    }


} // class TRAI_23_X6_testi