package de.tukl.programmierpraktikum2020.mp1;

public class ListMap<K, V> implements Map<K, V> {

    private static class MyClass<K, V> {
        MyClass<K, V> rest;
        K fst;
        V snd;

        MyClass(K fst, V snd, MyClass<K, V> rest) {
            this.fst = fst;
            this.snd = snd;
            this.rest = rest;
        }
    }

    private MyClass<K, V> top = null;

    @Override
    public V get(K key) {
        V value = null;
        for (MyClass<K, V> currListElement = top; currListElement != null; currListElement = currListElement.rest) {
            if (currListElement.fst.equals(key)) {
                value = currListElement.snd;
                break;
            }
        }
        return value;

    }

    @Override
    public void put(K key, V value) {

        MyClass<K, V> currListElement = top;
        while (currListElement != null){
            if (currListElement.fst.equals(key)){  //Wir weisen einfach dem Schlüssel einen neuen Wert zu, wenn dieser bereits existiert.
                currListElement.snd = value;
                return;
            }
        currListElement = currListElement.rest;
        }
       top = new MyClass<>(key, value, top);
    }

    @Override
    public void remove(K key) {

        MyClass<K, V> currListElement = top;
        // Wenn die Liste leer ist, dann können wir keinen Eintrag löschen.
        if (currListElement != null) {
            if (currListElement.fst.equals(key)) {
                top = currListElement.rest;
            }
            else {

                while (currListElement.rest != null) {
                    if (currListElement.rest.fst.equals(key)) {
                        if (currListElement.rest.rest == null) { //Wir prüfen, ob die Restliste von der Restliste leer ist.
                            currListElement.rest = null;break;
                        } else {
                            currListElement.rest = currListElement.rest.rest;return;
                        }

                    }
                    currListElement = currListElement.rest;
                }
            }
        }
    }

    @Override
    public int size() {
        int n = 0;
        for (MyClass<K, V> cur = top; cur != null; cur = cur.rest) n++;
        return n;
    }

    @Override
    public void keys(K[] array) {

        if (array == null || array.length < this.size()) {
            throw new IllegalArgumentException();
        }
       else {
           int j = 0;
           for (MyClass<K,V> curList = top; curList!= null; curList = curList.rest){
               array[j] = curList.fst;
               j++;
           }
        }
    }
}
