package com.craftinginterpreters.lox;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AstPrinterTests {
  private final AstPrinter astPrinter = new AstPrinter();

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