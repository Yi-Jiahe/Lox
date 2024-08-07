package com.craftinginterpreters.lox;

// TO-DO: implement Stmt.Visitor<String> 
class AstPrinter implements Expr.Visitor<String> {
  String print(Expr expr) {
    return expr.accept(this);
  }

  @Override
  public String visitBinaryExpr(Expr.Binary expr) {
    return parenthesize(expr.operator.lexeme,
                        expr.left, expr.right);
  }

  @Override
  public String visitCallExpr(Expr.Call expr) {
    return parenthesize(print(expr.callee), expr.arguments.toArray(new Expr[0]));
  }

  @Override
  public String visitGetExpr(Expr.Get expr) {
    return "(" + String.join(" ", "get", print(expr.object), expr.name.lexeme) + ")";
  }

  @Override
  public String visitGroupingExpr(Expr.Grouping expr) {
    return parenthesize("group", expr.expression);
  }

  @Override
  public String visitLiteralExpr(Expr.Literal expr) {
    if (expr.value == null) return "nil";
    return expr.value.toString();
  }

  @Override
  public String visitLogicalExpr(Expr.Logical expr) {
    return parenthesize(expr.operator.lexeme,
                        expr.left, expr.right);
  }

  @Override
  public String visitSetExpr(Expr.Set expr) {
    return "(" + String.join(" ", "set", print(expr.object), expr.name.lexeme, print(expr.value)) + ")";

  }

  @Override
  public String visitUnaryExpr(Expr.Unary expr) {
    return parenthesize(expr.operator.lexeme, expr.right);
  }

  @Override
  public String visitVariableExpr(Expr.Variable expr) {
    return expr.name.lexeme;
  }

  @Override
  public String visitAssignExpr(Expr.Assign expr) {
    return parenthesize(String.join(" ", "def", expr.name.lexeme), expr.value);
  }

  private String parenthesize(String name, Expr... exprs) {
    StringBuilder builder = new StringBuilder();

    builder.append("(").append(name);
    for (Expr expr : exprs) {
      builder.append(" ");
      builder.append(expr.accept(this));
    }
    builder.append(")");

    return builder.toString();
  }
}