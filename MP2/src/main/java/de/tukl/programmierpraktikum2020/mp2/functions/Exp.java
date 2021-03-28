package de.tukl.programmierpraktikum2020.mp2.functions;

public class Exp implements Function {
    Function funktion;

    public Exp(Function funktion) {
        this.funktion = funktion;
    }

    @Override
    public String toString() {
        return ("exp(" + funktion.toString() + ")");
    }

    @Override
    public double apply(double x) {
        return Math.exp(funktion.apply(x));
    }

    @Override
    public Function derive() {
        return new Mult(funktion.derive(), new Exp(funktion));
    }

}
