package de.tukl.programmierpraktikum2020.mp2.functions;

public class Plus implements Function {
    Function fstFunktion, sndFunktion;

    public Plus(Function fstFunktion, Function sndFukntion) {
        this.fstFunktion = fstFunktion;
        this.sndFunktion = sndFukntion;
    }

    @Override
    public String toString() {
        return "(" + fstFunktion.toString() + " + " + sndFunktion.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return fstFunktion.apply(x) + sndFunktion.apply(x);
    }

    @Override
    public Function derive() {
        return new Plus(fstFunktion.derive(), sndFunktion.derive());
    }

}
