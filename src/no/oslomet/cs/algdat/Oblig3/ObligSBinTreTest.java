package no.oslomet.cs.algdat.Oblig3;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ObligSBinTreTest {

    ObligSBinTre<Integer> testTre;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testTre = new ObligSBinTre<>(Comparator.naturalOrder());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }


    //Initialization tests


    @org.junit.jupiter.api.Test
    void ObligSBinTre_create_objects() {
        ObligSBinTre<Integer> obj = new ObligSBinTre<>(Comparator.naturalOrder());
        ObligSBinTre<Integer> obj2 = new ObligSBinTre<>(Comparator.naturalOrder());
        assertNotNull(obj);
        assertNotNull(obj2);
    }


    @org.junit.jupiter.api.Test
    void leggInn() {
        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.antall()); // Utskrift: 10
    }

    @org.junit.jupiter.api.Test
    void inneholder() {
    }

    @org.junit.jupiter.api.Test
    void fjern() {
        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.antall());
        tre.fjern(8);
        tre.fjern(1);
        tre.fjern(10);
        System.out.println(tre.antall());
        System.out.println(tre.toString());
    }

    @org.junit.jupiter.api.Test
    void fjern__only_root() {
        ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        String s;

        tre.leggInn(6);
        tre.fjern(6);

        s = tre.toString();
        System.out.println(s);
    }
    @org.junit.jupiter.api.Test
    void fjernAlle() {
        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.toString());
        System.out.println(tre.antall());
        System.out.println(tre.fjernAlle(4));
        System.out.println(tre.fjernAlle(1));
        System.out.println(tre.antall());
        System.out.println(tre.toString());
    }

    @org.junit.jupiter.api.Test
    void fjernAlle__special_case() {
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());

        int[] b = {1, 4, 1, 3, 1, 2, 1, 1};
        for (int verdi : b) tre.leggInn(verdi);

        if (tre.fjernAlle(1) != 5) {

            System.out.println("Oppgave 5t: Feil i fjernAlle(T)!");
        }

        String s = tre.toString();
        if (!s.equals("[2, 3, 4]")) {

            System.out.println("Oppgave 5u: Feil i fjernAlle(T)!");
        }
    }

    @org.junit.jupiter.api.Test
    void antall () {
    }

    @org.junit.jupiter.api.Test
    void antall1() {
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.antall());
        System.out.println(tre.antall(5));
        System.out.println(tre.antall(4));
        System.out.println(tre.antall(7));
        System.out.println(tre.antall(10));

    }

    @org.junit.jupiter.api.Test
    void tom() {
    }

    @org.junit.jupiter.api.Test
    void nullstill() {
        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        tre.nullstill();
        System.out.println(tre);
    }


    @org.junit.jupiter.api.Test
    void omvendtString() {
    }

    @org.junit.jupiter.api.Test
    void høyreGren() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);
        System.out.println(tre.høyreGren() + " " + tre.lengstGren());
// Utskrift: [I, T, J, R, S] [I, A, B, H, C, F, E, D]
    }

    @org.junit.jupiter.api.Test
    void lengstGren() {
    }

    @org.junit.jupiter.api.Test
    void grener() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);
        String[] s = tre.grener();
        for (String gren : s) System.out.println(gren);
    }

    @org.junit.jupiter.api.Test
    void bladnodeverdier() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);
        System.out.println(tre.bladnodeverdier());
        // Utskrift: [D, G, K, N, Q, S]
    }

    @org.junit.jupiter.api.Test
    void postString() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);
        System.out.println(tre.postString());
        // [D, E, G, F, C, H, B, A, K, N, M, L, Q, P, O, S, R, J, T, I]
    }

    @org.junit.jupiter.api.Test
    void toString2() {
        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        System.out.println(tre);
        // [1, 2, 4, 4, 4, 6, 7, 7, 8, 9, 10]
    }

    @org.junit.jupiter.api.Test
    void iterator() {
    }

    @Test
    void omvendtString1() {
        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.omvendtString());
        // [10, 9, 8, 7, 7, 6, 4, 4, 4, 2, 1]
    }
}