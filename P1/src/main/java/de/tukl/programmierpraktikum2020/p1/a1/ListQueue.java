package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.UnaryOperator;

public class ListQueue<E> implements PriorityQueue<E>{
    LinkedList<E> list = new LinkedList<>();
    Comparator<E> comparator;
    public ListQueue(Comparator<E> comparator) {
        this.comparator = comparator;
    }


    @Override
    public void insert(E elem) {
        list.add(elem);
        list.sort(comparator.reversed()); // Absteigend sortiert

    }

    @Override
    public void merge(PriorityQueue<E> otherQueue) {
        for (PriorityQueue<E> otherList = otherQueue; !otherList.isEmpty(); ) {
            this.insert(((ListQueue<E>) otherList).list.removeFirst()); // entfernt jedes Mal das erste Element unserer
                                                                        // Warteschlange und fügt sie sortiert in der Liste ein.

        }
    }

    @Override
    public E deleteMax() {
       E element = this.max(); // speichert das Maximum
       list.remove(this.max()); // entfernt das Maximum
       return element; //gibt dieses gespeicherte Maximum zurück
    }

    @Override
    public E max() {
        if (this.isEmpty()){return null;}
        else {
                return list.getFirst();
        }
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean update(E elem, E updatedElem) {
        if (list.contains(elem)){
            list.remove(elem);
            this.insert(updatedElem);
            return true;
        }
        return false;
    }

    @Override
    public void map(UnaryOperator<E> f) {
        LinkedList<E> speicherList = list;
        list = new LinkedList<>();

        for (E element: speicherList) {
            this.insert(f.apply(element));
        }
    }
}
