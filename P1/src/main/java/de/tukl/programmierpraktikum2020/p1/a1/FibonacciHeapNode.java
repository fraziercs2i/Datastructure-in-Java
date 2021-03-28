package de.tukl.programmierpraktikum2020.p1.a1;

public class FibonacciHeapNode<E> {

        FibonacciHeapNode<E> Nodeleft, Noderight, Nodekind, Nodeeltern;
        boolean markiert = false;
        E priority;
        int grad;

        public FibonacciHeapNode(E priority) {
            this.grad = 0;
            this.priority = priority;
            this.Nodeleft = this;
            this.Noderight = this;
            this.Nodeeltern = null;
        }


}
