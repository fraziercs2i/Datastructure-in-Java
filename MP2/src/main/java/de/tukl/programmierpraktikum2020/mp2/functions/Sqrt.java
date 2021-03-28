package de.tukl.programmierpraktikum2020.mp2.functions;

public class Sqrt implements Function{
    Function function;

    public Sqrt(Function function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "sqrt(" + function.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.sqrt(function.apply(x));
    }

    @Override
    public Function derive() {
        // (sqrt(f))' = f' / (2* sqrt(f))
        return new Div(function.derive(),new Mult(new Const(2),new Sqrt(function)));
    }

}
