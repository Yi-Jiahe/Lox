package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AstPrinterTests {
  private final AstPrinter astPrinter = new AstPrinter();

  @Test
  public void visitCallExprTest() {
    String expected = "(add 1 2 3)";

    List<Expr> arguments = new ArrayList<>();
    arguments.add(new Expr.Literal(1));
    arguments.add(new Expr.Literal(2));
    arguments.add(new Expr.Literal(3));
    Expr callExpr = new Expr.Call(
      new Expr.Literal("add"),
      new Token(TokenType.RIGHT_PAREN, ")", null, 1),
      arguments
    );

    assertEquals(expected, astPrinter.print(callExpr));
  }

  @Test
  public void visitGetExprTest() {
    String expected = "(get instance name)";

    Expr getExpr = new Expr.Get(
        new Expr.Literal("instance"),
        new Token(TokenType.IDENTIFIER, "name", null, 1)
    );

    assertEquals(expected, astPrinter.print(getExpr));
  }

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
  public void visitSetExprTest() {
    String expected = "(set instance name value)";

    Expr setExpr = new Expr.Set(
        new Expr.Literal("instance"),
        new Token(TokenType.IDENTIFIER, "name", null, 1),
        new Expr.Literal("value")
    );

    assertEquals(expected, astPrinter.print(setExpr));
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