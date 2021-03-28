package de.tukl.programmierpraktikum2020.mp2.functions;

public class Mult implements Function {
    Function fstFunktion, sndFunktion;

    public Mult(Function fstFunktion, Function sndFunktion) {
        this.fstFunktion = fstFunktion;
        this.sndFunktion = sndFunktion;
    }

    @Override
    public String toString() {
        return "(" + fstFunktion.toString() + " * " + sndFunktion.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return fstFunktion.apply(x) * sndFunktion.apply(x);
    }

    @Override
    public Function derive() {
        Mult first = new Mult(fstFunktion.derive(), sndFunktion);
        Mult second = new Mult(fstFunktion, sndFunktion.derive());
        return new Plus(first, second);
    }
}
