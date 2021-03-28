package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

public class FibonacciHeap<E> implements PriorityQueue<E> {
    Comparator<E> comparator;

    private List<E> listofpriorities = new ArrayList<>();
    private FibonacciHeapNode<E> max;
    private int numberofNodes;


    public FibonacciHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void insert(E elem) {
        helper(new FibonacciHeapNode<>(elem));
        listofpriorities.add(elem);
    }

    //Insert a new Nodeelem in the heap
    public void helper(FibonacciHeapNode<E> Nodeelem) {

        //wir prüfen, ob der max nicht leer ist
        if (max != null) {

            //wir fügen das Element im Rechtteil des Heaps
            Nodeelem.Nodeleft = max;
            Nodeelem.Noderight = max.Noderight;
            max.Noderight = Nodeelem;

            //wir prüfen, ob der Rechteils von Nodeelem nicht null ist
            if (Nodeelem.Noderight == null) {
            } else {
                Nodeelem.Noderight.Nodeleft = Nodeelem;
            }
            if (Nodeelem.Noderight == null) {
                Nodeelem.Noderight = max;
                max.Nodeleft = Nodeelem;
            }
            //Wir prüfen,ob das Element größer als der Max ist
            if (comparator.compare(Nodeelem.priority, max.priority) <= 0) {
            } else {
                max = Nodeelem;
            }
        } else {
            max = Nodeelem;
        }

        numberofNodes++;
    }

    @Override
    public void merge(PriorityQueue<E> otherQueue) {
        while (!otherQueue.isEmpty()) {
            this.insert(otherQueue.deleteMax());
        }
    }

    @Override
    public E deleteMax() {
        return deleteHelper();
    }

    //Lösche das Maximum im Heap
    public E deleteHelper() {
        FibonacciHeapNode<E> currentMax = this.max;
        if (currentMax != null) {
            listofpriorities.remove(this.max.priority);
            int kinderAnzahl = currentMax.grad;
            FibonacciHeapNode<E> maxkinder = currentMax.Nodekind;
            FibonacciHeapNode<E> rechttmp;

            //solange es existiert kinder vom Max
            while (kinderAnzahl > 0) {
                rechttmp = maxkinder.Noderight;

                // lösche das Maximum von Kinder in der Kinder List
                maxkinder.Nodeleft.Noderight = maxkinder.Noderight;
                maxkinder.Noderight.Nodeleft = maxkinder.Nodeleft;

                // füge maxkinder in der  root list von heap hinzu
                maxkinder.Nodeleft = this.max;
                maxkinder.Noderight = this.max.Noderight;
                this.max.Noderight = maxkinder;
                maxkinder.Noderight.Nodeleft = maxkinder;

                // Die Eltern werden dann gelöscht
                maxkinder.Nodeeltern = null;
                maxkinder = rechttmp;
                //die Anzahl der Kinder wird dekrementiert
                kinderAnzahl--;

            }

            // der current Max wird dann von der root Liste entfernt
            currentMax.Nodeleft.Noderight = currentMax.Noderight;
            currentMax.Noderight.Nodeleft = currentMax.Nodeleft;

            if (currentMax == currentMax.Noderight) {
                this.max = null;

            } else {
                this.max = currentMax.Noderight;
                gradupdate();
            }
            numberofNodes--;
            return currentMax.priority;
        }
        return null;
    }

    //diese Methode verwaltet das Grad, falls es gleichheit von Grad in der root Liste existiert
    public void gradupdate() {

        int Gradanzahl = numberofNodes;

        List<FibonacciHeapNode<E>> gradlist =
                new ArrayList<>(Gradanzahl);

        // Wir initisalisieren unsere Gradanzahl
        for (int i = 0; i < Gradanzahl; i++) {
            gradlist.add(null);
        }

        // wir nehmen die Anzahl von root Nodes
        int AnzahlderWurzeln = 0;
        FibonacciHeapNode<E> fib = max;


        if (fib != null) {
            AnzahlderWurzeln++;
            fib = fib.Noderight;

            while (fib != max) {
                AnzahlderWurzeln++;
                fib = fib.Noderight;
            }
        }

        // Für jeder Node in der  root list
        while (AnzahlderWurzeln > 0) {

            int Grad = fib.grad;
            FibonacciHeapNode<E> nächster = fib.Noderight;

            // wir vergleichen die Grad und verschmelzen
            while (true) {
                FibonacciHeapNode<E> fib2 = gradlist.get(Grad);
                if (fib2 == null) {
                    break;
                }

                //wir prüfen die größere Priorität und wir tauschen
                if (comparator.compare(fib.priority, fib2.priority) < 0) {
                    FibonacciHeapNode<E> tmp = fib2;
                    fib2 = fib;
                    fib = tmp;
                }

                //fib2 wird Kind vom fib
                setzekinder(fib2, fib);

                //wir setzen den Grad auf null wegen der Verschmelzung
                gradlist.set(Grad, null);
                Grad++;
            }

            //wir speicher das neue Element in der Grad List
            gradlist.set(Grad, fib);

            // wir fahren fort
            fib = nächster;
            AnzahlderWurzeln--;
        }


        //wir löschen das Maximum
        max = null;

        // wir fassen die Grad von Gradlist zusammen
        for (int i = 0; i < Gradanzahl; i++) {
            FibonacciHeapNode<E> fib3 = gradlist.get(i);
            if (fib3 == null) {
                continue;
            }

            //solange der Max ist nicht null
            if (max != null) {

                // wir löschen den Node von rootlist
                fib3.Nodeleft.Noderight = fib3.Noderight;
                fib3.Noderight.Nodeleft = fib3.Nodeleft;

                // wir fügen in der root List erneut
                fib3.Nodeleft = max;
                fib3.Noderight = max.Noderight;
                max.Noderight = fib3;
                fib3.Noderight.Nodeleft = fib3;

                // wir prüfen, ob dieses das Maximum ist
                if (comparator.compare(fib3.priority, max.priority) > 0) {
                    max = fib3;
                }
            } else {
                max = fib3;
            }
        }
    }

    //Makes fib3 the child of node fib
    public void setzekinder(FibonacciHeapNode<E> fib3, FibonacciHeapNode<E> fib) {
        // löscht fib3 in der root List
        fib3.Nodeleft.Noderight = fib3.Noderight;
        fib3.Noderight.Nodeleft = fib3.Nodeleft;

        // setze fib3 als Kind von fib
        fib3.Nodeeltern = fib;

        if (fib.Nodekind == null) {
            fib.Nodekind = fib3;
            fib3.Noderight = fib3;
            fib3.Nodeleft = fib3;
        } else {
            fib3.Nodeleft = fib.Nodekind;
            fib3.Noderight = fib.Nodekind.Noderight;
            fib.Nodekind.Noderight = fib3;
            fib3.Noderight.Nodeleft = fib3;
        }

        // erhöhe das Grad auf 1
        fib.grad++;

        fib3.markiert = false;
    }

    @Override
    public E max() {
        if (max != null) return max.priority;
        return null;
    }

    @Override
    public boolean isEmpty() {
        return max == null;
    }

    @Override
    public boolean update(E elem, E updatedElem) {
        if (listofpriorities.contains(elem)) {
            List<E> oldPriorityList = listofpriorities;
            listofpriorities = new ArrayList<>();
            max = null;
            for (E priority : oldPriorityList) {
                if (comparator.compare(priority, elem) == 0) {
                    this.insert(updatedElem);
                } else this.insert(priority);
            }
            return true;
        }
        return false;
    }

    @Override
    public void map(UnaryOperator<E> f) {
        listofpriorities.replaceAll(f);
        List<E> oldPriorityList = listofpriorities;
        listofpriorities = new ArrayList<>();
        max = null;
        for (E elem : oldPriorityList) this.insert(elem);
    }
}
