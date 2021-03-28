package de.tukl.programmierpraktikum2020.mp2.functions;

public class Abs implements Function {
    Function fAbs;

    public Abs(Function fAbs) {
        this.fAbs = fAbs;
    }

    @Override
    public String toString() {
        return "abs" + fAbs.toString() + ')';
    }

    @Override
    public double apply(double x) {
        return Math.abs(fAbs.apply(x));
    }

    @Override
    public Function derive() {
        return new Abs(fAbs.derive());
    }
}
