package de.tukl.programmierpraktikum2020.mp2.functions;

public class Cos implements Function {
    Function fCos;

    public Cos(Function fCosin) {
        this.fCos = fCosin;
    }

    @Override
    public String toString() {
        return ("cos(" + fCos.toString() + ")");
    }

    @Override
    public double apply(double x) {
        return Math.cos(fCos.apply(x));
    }

    @Override
    public Function derive() {
        return new Mult(fCos.derive(), new Mult(new Const(-1), new Sin(fCos)));
    }
}
