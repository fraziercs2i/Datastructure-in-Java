package de.tukl.programmierpraktikum2020.mp1;

import java.util.Comparator;

public class BibleAnalyzer {
    public static void countWords(Map<String, Integer> counts) {

        for (String word: Util.getBibleWords()) { //Wir prüfen, ob das Wort schon existiert oder nicht.
        Integer haufigkeit = counts.get(word);
            if (haufigkeit == null) {
                counts.put(word, 1);} // Falls nicht, dann wird zunächst seine Haufigskeit 1.
            else {

                counts.put(word, haufigkeit +1); //Wir erhöhen dessen Haufigskeit um 1 jedes Mal, wenn das Wort vorkommt.

            }

        }

    }

    public static void main(String[] args) {
        // 4-1)
            Map<String,Integer> myObj = new TreeMap<>(Comparator.<String>naturalOrder() );
            countWords(myObj);

        // 4-2)
        // Die Anzahl der Einträge in der TreeMap ist gleich die Anzahl von Wörtern.
            String[] arrayString = new String[myObj.size()];
            myObj.keys(arrayString);        //
         // 4-3)
            sort(arrayString, myObj);
         // 4-4)
        for (String word: arrayString) {

            System.out.println(myObj.get(word) + " " +word);
        }
    }

    public static void sort(String[] words, Map<String, Integer> counts) {
        //Wir haben die Sortierungsmethode Selection-Sort ausgewählt.
        // Quelle : http://www.java-programmieren.com/selectionsort-java.php
        int i = 0;
        while (i < words.length-1) {
            for (int j = i+1; j < words.length ; j++){
                if (counts.get(words[i]) > counts.get(words[j])){
                    String ts = words[i];
                    words[i] = words[j];
                    words[j] = ts;
                }
            }
            i++;
        }
    }
}
