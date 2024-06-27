package com.craftinginterpreters.lox;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AstPrinterTests {
  private final AstPrinter astPrinter = new AstPrinter();

  @Test
  public void visitLogicalExprTest() {
    String expected = "(or nil 1)";

    Expr logicalExpr = new Expr.Logical(
        new Expr.Literal(null),
        new Token(TokenType.OR, "or", null, 1),
        new Expr.Literal(1)
    );

    assertEquals(expected, astPrinter.print(logicalExpr));
  }

  @Test
  public void visitVariableExprTest() {
    String expected = "a";

    Expr variableExpr = new Expr.Variable(
        new Token(TokenType.IDENTIFIER, "a", null, 1)
    );

    assertEquals(expected, astPrinter.print(variableExpr));
  }

  @Test
  public void visitAssignExprTest() {
    String expected = "(def a 1)";

    Expr assignExpr = new Expr.Assign(
        new Token(TokenType.IDENTIFIER, "a", null, 1),
        new Expr.Literal(1)
    );

    assertEquals(expected, astPrinter.print(assignExpr));
  }
}