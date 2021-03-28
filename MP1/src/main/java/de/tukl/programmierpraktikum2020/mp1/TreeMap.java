package de.tukl.programmierpraktikum2020.mp1;

import java.util.Comparator;

public class TreeMap< K , V > implements Map< K , V > {

    private static class Baum<K , V> implements Comparator<K> {

        // wir implementieren unsere Klasse Baum zum Aufbau eines Pfads des TreeMap
        Baum<K,V> left;
        Baum<K,V> right;

        K key;
        V value;

        Baum (K key , V value) {
            this.key = key ;
            this.value = value;
            this.left = null;
            this.right = null;
        }



        @Override
        public int compare(K o1, K o2) {
            return compare(o1, o2);
        }
    }



    // Wir setzen ein Objekt Comparator

    Comparator<K> vergleich;

    // wir setzen die Wurzel des Baumes

    Baum<K, V> wurzel;

    // Nach Einfügen put() bzw remove () wird die größe unseres Baumes und 1 erhöhen
    // bzw um 1 verringern

    int Baumsize;

    public TreeMap(Comparator<K> vergleich) {

        this.vergleich = vergleich;
        this.Baumsize = 0;
        this.wurzel = null;

    }


    @Override
    public V get(K key) {
        // Wir prüfen, ob die Wurzel nicht leer ist.

        if (wurzel != null) {

            Baum<K, V> Element = wurzel;

                do {

                    int compareresult = vergleich.compare(key, Element.key);

                    if (compareresult <= 0) if (compareresult == 0)
                        return Element.value;
                    else
                        Element = Element.left;
                    else {
                        Element = Element.right;
                    }
                } while (Element != null);
        }
        return null;
    }

    @Override
    public void put(K key, V value) {

        Baum<K, V> toInsertElement = new Baum<K,V>(key, value);

        if (wurzel == null) {
            wurzel = toInsertElement;
            Baumsize = Baumsize + 1;
            return;
        }

        Baum<K, V> startElement  = wurzel;
        Baum<K, V> speicherBaum = null; //speicher Element für unsere toInsertElement
        boolean speicher   = false; // Mit speicher entscheiden wir welcher pfad, indem wir den key einfügen werden


        for (Baum<K,V> top = wurzel; top != null ; top = startElement ) {
            speicherBaum = startElement;
            int compareResult = vergleich.compare(key, top.key);
            if (compareResult < 0) {
                startElement = startElement.left;

                speicher = true;

            } else {
                if (compareResult > 0) {
                    startElement = startElement.right;

                    speicher = false;

                } else {

                    startElement.value = value;
                    return;

                }
            }

        }

    Baumsize++;
    if(speicher)
    {
        speicherBaum.left = toInsertElement;
    } else
    {
        speicherBaum.right = toInsertElement;
    }
}

    @Override
    public void remove(K key) {
        throw new UnsupportedOperationException();

    }

    @Override
    public int size() {
        return this.Baumsize;
    }

    @Override
    public void keys(K[] array) {

        if (array == null || array.length < size()) {
            throw new IllegalArgumentException();
        } else {
            helper(wurzel, array);
        }
    }
    // wir fügen jeder key in unserem arry mit Hilfe der Funktion helper

    private void helper(Baum<K, V> baum, K[] array) {
        if (baum != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    array[i] = baum.key;
                    break; // break not return
                }
            }
            helper(baum.left, array);
            helper(baum.right, array);
        }
    }
}