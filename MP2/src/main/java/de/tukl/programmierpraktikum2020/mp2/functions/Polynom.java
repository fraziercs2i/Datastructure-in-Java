package de.tukl.programmierpraktikum2020.mp2.functions;

public class Polynom implements Function {
    private double[] a_i;
    public Polynom(double[] arguments) {
        this.a_i = arguments;
    }

    @Override
    public double apply(double x) {
        double result = 0.0;
        for(int i=0;i<a_i.length;i++) {
            result = result + new Pow(new Mult(new Const(a_i[i]),new X()),new Const(i)).apply(x);
        }
        return result;
    }

    @Override
    public Function derive() {
        if (a_i.length == 0) {
            return new Polynom(new double[0]);
        }
        else {
            double[] new_a_i = new double[a_i.length-1];
            for(int i=0;i<new_a_i.length;i++) {
                new_a_i[i] = a_i[i+1] * i+1;
            }
            return new Polynom(new_a_i);
        }
    }
}
