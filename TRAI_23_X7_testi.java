import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;

public class TRAI_23_X7_testi {

    public static void main(String[] args) {

        // syÃ¶tekoko
        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        boolean ok = true;

        // testattava rengas
        TRAI_23_X7<Integer> R = new TRAI_23_X7_ksuurnie<>(); // oma tunnus tÃ¤hÃ¤n

        // verrokkijoukot joilla verrataan mitÃ¤ renkaaseen on laitettu ja mitÃ¤
        // sieltÃ¤ saadaan ulos
        HashSet<Integer> renkaaseenLaitetut = new HashSet<>();
        HashSet<Integer> renkaastaSaadut = new HashSet<>();


        // testataan next() tyhjÃ¤stÃ¤
        try {
            Integer x = R.next();
            System.out.println("Saatiin vÃ¤Ã¤rin tyhjÃ¤stÃ¤ : " + x);
            ok = false;
        } catch (Exception e) {
            System.out.println("TyhjÃ¤stÃ¤ saatiin oikein poikkeus : " + e);
        }

        int n = R.size(); // testataan koko
        if (n == 0)
            System.out.println("TyhjÃ¤n koko on oikein.");
        else {
            System.out.println("VÃ¤Ã¤rÃ¤ koko: " + n + " (piti olla 0)");
            ok = false;
        }

        // lisÃ¤tÃ¤Ã¤n yksi 0
        System.out.println("\nadd(0)");
        R.add(0);
        renkaaseenLaitetut.add(0);

        // testataan next, pitÃ¤isi olla pelkkÃ¤ nolla
        System.out.println("N next():");
        for (int i = 0; i < N; i++) {
            Integer x = R.next();
            System.out.print(x + " ");
            if (x != 0) {
                System.out.print("VÃ¤Ã¤rÃ¤ alkio ");
                ok = false;
            }
        }
        System.out.println();

        n = R.size();
        if (n == 1)
            System.out.println("Yksialkioisen koko on oikein.");
        else {
            System.out.println("VÃ¤Ã¤rÃ¤ koko: " + n + " (piti olla 1)");
            ok = false;
        }


        // testaan muutama add
        System.out.println("\nN/2 add():");
        for (int i = 1; i <= N / 2; i++) {
            System.out.print(i + " ");
            R.add(i);
            renkaaseenLaitetut.add(i);
        }
        System.out.println();

        n = R.size();
        if (n == 1 + N / 2)
            System.out.println("Monialkioisen koko on oikein.");
        else {
            System.out.println("VÃ¤Ã¤rÃ¤ koko: " + n + " (piti olla " + (1 + N / 2) + ")");
            ok = false;
        }


        // testataan taas next
        System.out.println("\nN*2 next():");
        for (int i = 0; i < N * 2; i++) {
            Integer x = R.next();
            System.out.print(x + " ");
            if (!renkaaseenLaitetut.contains(x)) {
                System.out.print("VÃ¤Ã¤rÃ¤ alkio");
                ok = false;
            }
            renkaastaSaadut.add(x);
        }
        System.out.println();
        // kaikki alkiot piti tulla kÃ¤sitellyksi
        if (!renkaaseenLaitetut.equals(renkaastaSaadut)) {
            System.out.println("Jotain alkioita jÃ¤i saamatta next():llÃ¤");
            ok = false;
        } else
            System.out.println("next() palautti oikein");

        // vuorotellen next ja add
        System.out.println("\nN next() add():");
        for (int i = 0; i < N; i++) {
            Integer x = R.next() + N;
            while (!renkaaseenLaitetut.add(x))
                x++;
            System.out.print(x + " ");
            R.add(x);
        }
        System.out.println();

        renkaastaSaadut.clear();
        // testataan while hasNext-next
        System.out.println("\nN*4 hasNext() next():");
        int ii = N * 4;
        while (R.hasNext()) {
            Integer x = R.next();
            System.out.print(x + " ");
            if (!renkaaseenLaitetut.contains(x)) {
                System.out.print("VÃ¤Ã¤rÃ¤ alkio");
                ok = false;
            }
            renkaastaSaadut.add(x);
            // hasNext renkaassa on ikuinen, joten joudutaan poistumaan nÃ¤in
            if (ii-- < 0)
                break;
        }
        System.out.println();
        // kaikki alkiot piti tulla jossain vaiheessa
        if (!renkaaseenLaitetut.equals(renkaastaSaadut)) {
            System.out.println("Jotain alkioita jÃ¤i saamatta next():llÃ¤");
            ok = false;
        } else
            System.out.println("next() palautti oikein");

        // testataan lÃ¤pikÃ¤ynnit iteratorilla
        // try-catch ympÃ¤rillÃ¤ jos vaikka sitÃ¤ ei oltu vielÃ¤ toteutettu

        try {
            System.out.println("\nN*4 foreachia");
            renkaastaSaadut.clear();
            int i = N * 4;
            for (Integer x : R) {

                System.out.print(x + " ");
                if (!renkaaseenLaitetut.contains(x)) {
                    System.out.print("VÃ¤Ã¤rÃ¤ alkio");
                    ok = false;
                }
                renkaastaSaadut.add(x);
                // foreach renkaassa on ikuinen, joten joudutaan poistumaan nÃ¤in
                if (i-- < 0)
                    break;
            }
            System.out.println();
            if (!renkaaseenLaitetut.equals(renkaastaSaadut)) {
                System.out.println("Jotain alkioita jÃ¤i saamatta next():llÃ¤");
                ok = false;
            } else
                System.out.println("next() palautti oikein");


        } catch (Exception e) {
            System.out.println("foreachia kokeillessa saatiin poikkeus : " + e);
            ok = false;
        }

        try {
            System.out.println("\nN*4 iteraattorilla ");
            int i = N * 4;
            Iterator<Integer> it = R.iterator();
            while (it.hasNext()) {
                Integer x = it.next();
                System.out.print(x + " ");
                // iterointi renkaassa on ikuinen, joten joudutaan poistumaan nÃ¤in
                if (i-- < 0)
                    break;
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("iteratoria kokeiltaessa saatiin poikkeus : " + e);
            ok = false;
        }

        try {
            System.out.println("\nTestataan kahden iteraattorin kÃ¤yttÃ¶ ");
            n = R.size();
            if (n % 2 != 0) {   // tehdÃ¤Ã¤n alkiomÃ¤Ã¤rÃ¤stÃ¤ parillinen
                R.add(12345);
                renkaaseenLaitetut.add(12345);
                n++;
            }
            renkaastaSaadut.clear();

            Iterator<Integer> i1 = R.iterator();
            Iterator<Integer> i2 = R.iterator();
            // n2 perÃ¤kkÃ¤istÃ¤ i1.next():iÃ¤ pitÃ¤isi antaa kaikki alkiot vaikka i2 etenesi myÃ¶s
            for (int i = 0; i < n; i++) {
                Integer x = i1.next();
                System.out.print(x + " ");
                renkaastaSaadut.add(x);
                i2.next(); // i2 etenee myÃ¶s omaa tahtiaan, jÃ¤tetÃ¤Ã¤n alkio huomioimatta
            }
            System.out.println();

            if (!renkaaseenLaitetut.equals(renkaastaSaadut)) {
                System.out.println("Jotain alkioita jÃ¤i saamatta next():llÃ¤");
                ok = false;
            } else
                System.out.println("kahden iteraattorin next():it palautti oikein");

        } catch (Exception e) {
            System.out.println("kahta iteratoria kokeiltaessa saatiin poikkeus : " + e);
            ok = false;
        }

        if (ok)
            System.out.println("\nX7 alun testit ok.");
        else
            System.out.println("\nX7 alun testeissÃ¤ ongelm(i)a.");

        // testataan next-remove
        // try-catch ympÃ¤rillÃ¤ jos vaikka sitÃ¤ ei oltu vielÃ¤ toteutettu
        try {

            System.out.println("\nN/2 next&remove():");
            for (int i = 0; i < N / 2; i++) {
                Integer x = R.next();
                Integer xr = R.remove();
                System.out.print(" n:" + x);
                System.out.print(" r:" + xr + " | "); // pitÃ¤isi aina tulla sama
                renkaaseenLaitetut.remove(xr);
                if (!x.equals(xr)) {
                    System.out.print("VÃ¤Ã¤rÃ¤ poistettu");
                    ok = false;
                }
            }
            System.out.println();

            // testataan next
            System.out.println("\nN*3 next():");
            renkaastaSaadut.clear();
            for (int i = 0; i < N * 3; i++) {
                Integer x = R.next();
                System.out.print(x + " ");
                if (!renkaaseenLaitetut.contains(x)) {
                    System.out.print("VÃ¤Ã¤rÃ¤ alkio");
                    ok = false;
                }
                renkaastaSaadut.add(x);
            }
            System.out.println();
            if (!renkaaseenLaitetut.equals(renkaastaSaadut)) {
                System.out.println("Jotain alkioita jÃ¤i saamatta next():llÃ¤");
                ok = false;
            } else
                System.out.println("next() palautti oikein");

            // testataan tupla-remove()
            try {
                System.out.println("\ntupla-remove -testi:");
                Iterator<Integer> it = R.iterator();
                Integer x = it.next();
                it.remove();
                System.out.println("EnsimmÃ¤inen remove() tehty");
                it.remove();
                System.out.println("Iteraattorin tupla-remove() ei heittÃ¤nyt poikkeusta vaikka piti!");
                ok = false;
            } catch (IllegalStateException e) {
                System.out.println("Iteratorin tupla-remove heitti poikkeuksen oikein: " + e);
            }




            // testataan Fail-Fast
            try {
                System.out.println("\nFail-fast -testi:");
                Iterator<Integer> it = R.iterator();
                Integer x1 = it.next();
                R.add(4321);
                x1 = it.next();
                System.out.println("Iteraattorin next ei heittÃ¤nyt poikkeusta vaikka piti!");
                ok = false;
            } catch (ConcurrentModificationException e) {
                System.out.println("Iteratorin next heitti poikkeuksen oikein: " + e);
            }


            // poistetaan loput
            System.out.println("\nwhile next()&remove():");
            renkaastaSaadut.clear();
            while (!R.isEmpty()) {
                Integer x = R.next();
                Integer xr = R.remove();
                System.out.print(" n:" + x);
                System.out.print(" r:" + xr + " | "); // pitÃ¤isi aina tulla sama
                renkaaseenLaitetut.remove(xr);
                if (!x.equals(xr)) {
                    System.out.print("VÃ¤Ã¤rÃ¤ poistettu");
                    ok = false;
                }
            }
            System.out.println();

            // testataan next() tyhjÃ¤stÃ¤
            try {
                Integer x = R.next();
                System.out.println("\nSaatiin tyhjÃ¤stÃ¤ : " + x);
                ok = false;
            } catch (Exception e) {
                System.out.println("\nTyhjÃ¤stÃ¤ saatiin oikein poikkeus : " + e);
            }

        } catch (Exception e) {
            System.out.println("remove() kokeillessa saatiin poikkeus : " + e);
            ok = false;
        }


        renkaaseenLaitetut.clear();
        try {
            System.out.println("\nLisÃ¤tÃ¤Ã¤n paljon alkioita:");
            // otetaan ensin nykyinen mahdollinen sisÃ¤ltÃ¶ jos remove() ei ole toteutettu
            if (R.hasNext()) {
                for (int i = 0; i < R.size()+1; i++)
                    renkaaseenLaitetut.add(R.next());
            }

            // lisÃ¤tÃ¤Ã¤n paljon negatiivisia alkioita
            for (int i = -1; i > -1000000; i--) {
                R.add(i);
                renkaaseenLaitetut.add(i);
            }

            // kÃ¤ydÃ¤Ã¤n kaikki lÃ¤pi
            renkaastaSaadut.clear();
            for (int i = 0; i < R.size()+1; i++)
                renkaastaSaadut.add(R.next());

            // vertaillaan saatiinko kaikki alkiot
            if (renkaaseenLaitetut.equals(renkaastaSaadut)) {
                System.out.println("Paljonkin tuntuu toimivan hyvin");
            } else {
                System.out.println("Paljon lisÃ¤ttÃ¤essÃ¤ tuntuu olevan ongelmaa??");
                ok = false;
            }


        } catch (Exception e) {
            System.out.println("paljon laitettaessa kokeillessa saatiin poikkeus : " + e);
            ok = false;
        }




        if (ok)
            System.out.println("\nX7, 31 ja 32 testit ok.");
        else
            System.out.println("\nX7, 31 tai 32 testeissÃ¤ ongelm(i)a.");



    } // main()
} // class