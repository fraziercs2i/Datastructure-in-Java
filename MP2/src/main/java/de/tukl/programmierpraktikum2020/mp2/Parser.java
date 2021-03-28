package de.tukl.programmierpraktikum2020.mp2;

import de.tukl.programmierpraktikum2020.mp2.antlr.FunctionBaseVisitor;
import de.tukl.programmierpraktikum2020.mp2.antlr.FunctionParser;
import de.tukl.programmierpraktikum2020.mp2.functions.*;

public class Parser extends FunctionBaseVisitor<Function> {
    @Override
    public Function visitConstVar(FunctionParser.ConstVarContext ctx) {
        return new Const(Double.parseDouble(ctx.getText()));
    }

    @Override
    public Function visitParExpr(FunctionParser.ParExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Function visitTrigExp(FunctionParser.TrigExpContext ctx) {
        Function function = visit(ctx.expr());
        if (ctx.op.getType() == FunctionParser.COS) return new Cos(function);
        if (ctx.op.getType() == FunctionParser.SIN) return new Sin(function);
        else return new Tan(function);
    }

    @Override
    public Function visitExpExpr(FunctionParser.ExpExprContext ctx) {
        if (ctx.op.getType() == FunctionParser.EXP){
            return new Exp(visit(ctx.expr()));
        }
        else {
            return new Log(visit(ctx.expr()));
        }
    }

    @Override
    public Function visitSgnValExpr(FunctionParser.SgnValExprContext ctx) {
        if (ctx.sgn != null && ctx.sgn.getType() == FunctionParser.MINUS) { //Wir prüfen zunächst, ob es vor dem ctx einen Sign gibt.
            return new Mult(new Const(-1.0), super.visitSgnValExpr(ctx));
        }
        return super.visitSgnValExpr(ctx);
    }

    @Override
    public Function visitAddExpr(FunctionParser.AddExprContext ctx) {
        Function fstFunction = visit(ctx.lexpr);
        Function sndFunction = visit(ctx.rexpr);
        if (ctx.op.getType() == FunctionParser.PLUS){
            return new Plus(fstFunction, sndFunction);
        }
        else {
            return new Plus(fstFunction, new Mult(new Const(-1.0), sndFunction));
        }
    }

    @Override
    public Function visitPowExpr(FunctionParser.PowExprContext ctx) {
        return new Pow(visit(ctx.lexpr), visit(ctx.rexpr));
    }

    @Override
    public Function visitMultExpr(FunctionParser.MultExprContext ctx) {
        if (ctx.op.getType() == FunctionParser.MULT){
            return new Mult(visit(ctx.lexpr), visit(ctx.rexpr));
        }
        else {
            return new Div(visit(ctx.lexpr), visit(ctx.rexpr));
        }
    }

    @Override
    public Function visitIdVar(FunctionParser.IdVarContext ctx) {
        return new X();
    }

    @Override
    public Function visitAbsExpr(FunctionParser.AbsExprContext ctx) {
        return new Abs(visit(ctx.expr()));
    }

    @Override
    public Function visitSqrtExpr(FunctionParser.SqrtExprContext ctx) {
        return new Sqrt(visit(ctx.expr()));
    }
}
