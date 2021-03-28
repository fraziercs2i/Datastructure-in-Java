package de.tukl.programmierpraktikum2020.mp2.functions;

import java.util.concurrent.ExecutionException;

public class Log implements Function {
    Function fLog;

    public Log(Function fLog) {
        this.fLog = fLog;
    }

    @Override
    public String toString() {
        return "log(" + fLog.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.log(fLog.apply(x));
    }

    @Override
    public Function derive() {
        return new Div(fLog.derive(), fLog);
    }
}
