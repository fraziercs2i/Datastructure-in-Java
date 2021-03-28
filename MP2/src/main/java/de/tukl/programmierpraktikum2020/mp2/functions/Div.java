package de.tukl.programmierpraktikum2020.mp2.functions;

public class Div implements Function {
    Function fstFunktion, sndFunktion;

    public Div(Function fstFunktion, Function sndFunktion) {
        this.fstFunktion = fstFunktion;
        this.sndFunktion = sndFunktion;
    }

    @Override
    public String toString() {
        return "(" + fstFunktion.toString() + " / " + sndFunktion.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return (fstFunktion.apply(x) / sndFunktion.apply(x));
    }

    @Override
    public Function derive() {
        Mult first = new Mult(fstFunktion.derive(), sndFunktion);
        Mult second = new Mult(new Const(-1), new Mult(fstFunktion, sndFunktion.derive()));
        Plus third = new Plus(first, second);
        Mult fourth = new Mult(sndFunktion, sndFunktion);
        return new Div(third, fourth);
    }
}
