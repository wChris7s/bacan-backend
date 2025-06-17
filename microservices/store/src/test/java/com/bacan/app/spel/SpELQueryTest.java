package com.bacan.app.spel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpELQueryTest {
  @Test
  void nullCategoryId() {
    Collection<Long> categoryIds = null;

    ExpressionParser parser = new SpelExpressionParser();
    StandardEvaluationContext context = new StandardEvaluationContext();
    context.setVariable("categoryIds", categoryIds);

    Expression exp = parser.parseExpression("#categoryIds == null");
    Boolean isNull = exp.getValue(context, Boolean.class);

    Assertions.assertEquals(true, isNull);
  }

  @Test
  void emptyCategoryId() {
    Collection<Long> categoryIds = new ArrayList<>();

    ExpressionParser parser = new SpelExpressionParser();
    StandardEvaluationContext context = new StandardEvaluationContext();
    context.setVariable("categoryIds", categoryIds);

    Expression exp = parser.parseExpression("#categoryIds.isEmpty()");
    Boolean isEmpty = exp.getValue(context, Boolean.class);

    Assertions.assertEquals(true, isEmpty);
  }

  @Test
  void nullOrEmptyCategoryId() {
    Collection<Long> categoryIds = new ArrayList<>();

    ExpressionParser parser = new SpelExpressionParser();
    StandardEvaluationContext context = new StandardEvaluationContext();
    context.setVariable("categoryIds", categoryIds);

    Expression exp = parser.parseExpression("{#categoryIds == null || #categoryIds.isEmpty()}");
    Boolean isEmpty = exp.getValue(context, Boolean.class);

    Assertions.assertEquals(true, isEmpty);
  }

  @Test
  void notEmptyCategoryId() {
    Collection<Long> categoryIds = List.of(1L, 2L, 3L);

    ExpressionParser parser = new SpelExpressionParser();
    StandardEvaluationContext context = new StandardEvaluationContext();
    context.setVariable("categoryIds", categoryIds);

    Expression exp = parser.parseExpression("#categoryIds.isEmpty()");
    Boolean isEmpty = exp.getValue(context, Boolean.class);

    exp = parser.parseExpression("#categoryIds.size()");
    Integer size = exp.getValue(context, Integer.class);

    Assertions.assertEquals(false, isEmpty);
    Assertions.assertEquals(3, size);
  }
}
