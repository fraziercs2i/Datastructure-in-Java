package de.tukl.programmierpraktikum2020.mp2.functions;

public class Pow implements Function {
    Function g, h;

    public Pow(Function g, Function h) {
        this.g = g;
        this.h = h;
    }

    @Override
    public String toString() {
        return "Pow(" + g.toString() + "," + h.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.pow(g.apply(x), h.apply(x));
    }

    @Override
    public Function derive() {
        // (g(h))' = g'(h) * h' = h * g' * g ^ (h-1)
        Plus first = new Plus(h, new Const(-1));
        Pow second = new Pow(g, first);
        return new Mult(h, new Mult(g.derive(), second));
    }
}
