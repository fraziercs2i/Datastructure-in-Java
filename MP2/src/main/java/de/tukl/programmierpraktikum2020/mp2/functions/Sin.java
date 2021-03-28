package de.tukl.programmierpraktikum2020.mp2.functions;

public class Sin implements Function {
    Function fSinus;

    public Sin(Function fSinus) {
        this.fSinus = fSinus;
    }

    @Override
    public String toString() {
        return ("sin(" + fSinus.toString() + ")");
    }

    @Override
    public double apply(double x) {
        return Math.sin(fSinus.apply(x));
    }

    @Override
    public Function derive() {
        Cos function = new Cos(fSinus);
        return new Mult(fSinus.derive(), function);
    }
}
