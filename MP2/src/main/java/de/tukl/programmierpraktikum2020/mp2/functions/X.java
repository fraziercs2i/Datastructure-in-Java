package de.tukl.programmierpraktikum2020.mp2.functions;

public class X implements Function {
    char x;

    public X() {
        this.x = 'x';
    }

    @Override
    public String toString() {
        return String.valueOf(x);
    }

    @Override
    public double apply(double x) {
        return x;
    }

    @Override
    public Function derive() {
        return new Const(1.0);
    }

}
