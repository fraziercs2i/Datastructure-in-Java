package de.tukl.programmierpraktikum2020.mp2.functions;

public class Tan implements Function {
    Function fTan;

    public Tan(Function fTan) {
        this.fTan = fTan;
    }

    @Override
    public String toString() {
        return "tan(" + fTan.toString() + ')';
    }

    @Override
    public double apply(double x) {
        return Math.tan(fTan.apply(x));
    }

    @Override
    public Function derive() {
        // (tan(f))' = f' / (cos(f))^2
        return new Div(fTan.derive(), new Pow(new Cos(fTan), new Const(2.0)));
        //  return (new Div(new Sin(fTan), new Cos(fTan))).derive();  zweiter Alternativ
    }
}
