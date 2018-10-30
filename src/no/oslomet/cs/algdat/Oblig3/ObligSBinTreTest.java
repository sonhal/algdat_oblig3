package no.oslomet.cs.algdat.Oblig3;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ObligSBinTreTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
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
    void iterator() {
    }
}