package no.oslomet.cs.algdat.Oblig3;

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
    }

    @org.junit.jupiter.api.Test
    void fjernAlle() {
    }

    @org.junit.jupiter.api.Test
    void antall() {
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
    }


    @org.junit.jupiter.api.Test
    void omvendtString() {
    }

    @org.junit.jupiter.api.Test
    void høyreGren() {
    }

    @org.junit.jupiter.api.Test
    void lengstGren() {
    }

    @org.junit.jupiter.api.Test
    void grener() {
    }

    @org.junit.jupiter.api.Test
    void bladnodeverdier() {
    }

    @org.junit.jupiter.api.Test
    void postString() {
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
}