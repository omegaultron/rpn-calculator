import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class RPNEvaluatorTest {

    RPNEvaluator rpnEvaluator;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void shouldResultInAdditionOfNumbers() {
        rpnEvaluator = new RPNEvaluator("1 2 +");
        assertEquals("expr = 1 2 +, result = 3.0", 3.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("1 2 3 + +");
        assertEquals("expr = 1 2 3 + +, result = 6.0", 6.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("1 2 + 3 +");
        assertEquals("expr = 1 2 + 3 +, result = 6.0", 6.0, rpnEvaluator.evaluateExpression(), 0);
    }

    @Test
    public void shouldResultInSubtractionOfNumbers() {
        rpnEvaluator = new RPNEvaluator("1 2 -");
        assertEquals("expr = 1 2 -, result = -1.0", -1.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("1 2 3 - -");
        assertEquals("expr = 1 2 3 - -, result = 2.0", 2.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("1 2 - 3 -");
        assertEquals("expr = 1 2 - 3 -, result = -4.0", -4.0, rpnEvaluator.evaluateExpression(), 0);
    }

    @Test
    public void shouldResultInMultiplicationOfNumbers() {
        rpnEvaluator = new RPNEvaluator("1 2 *");
        assertEquals("expr = 1 2 *, result = 2.0", 2.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("1 2 3 * *");
        assertEquals("expr = 1 2 3 * *, result = 6.0", 6.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("1 2 * 3 *");
        assertEquals("expr = 1 2 * 3 *, result = 6.0", 6.0, rpnEvaluator.evaluateExpression(), 0);
    }

    @Test
    public void shouldResultInDivisionOfNumbers() {
        rpnEvaluator = new RPNEvaluator("2 1 /");
        assertEquals("expr = 2 1 /, result = 2.0", 2.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("6 4 2 / /");
        assertEquals("expr = 6 4 2 / /, result = 3.0", 3.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("12 2 / 3 /");
        assertEquals("expr = 12 2 / 3 /, result = 2.0", 2.0, rpnEvaluator.evaluateExpression(), 0);
    }

    @Test
    public void shouldResultInPercentageOperation() {
        rpnEvaluator = new RPNEvaluator("10 %");
        assertEquals("expr = 10 %, result = 0.1", 0.1, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("50 %");
        assertEquals("expr = 50 %, result = 0.5", 0.5, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("5 %");
        assertEquals("expr = 5 %, result = 0.05", 0.05, rpnEvaluator.evaluateExpression(), 0);

    }

    @Test
    public void shouldResultInExponentOperationXPowersY() {
        rpnEvaluator = new RPNEvaluator("1 2 ^");
        assertEquals("expr = 1 2 ^, result = 1.0", 1.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("2 2 2 ^ ^");
        assertEquals("expr = 2 2 2 ^ ^, result = 16.0", 16.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("2 2 ^ 2 ^");
        assertEquals("expr = 2 2 ^ 2 ^, result = 16.0", 16.0, rpnEvaluator.evaluateExpression(), 0);
    }

    @Test
    public void shouldResultInFactorialOfNumber() {
        rpnEvaluator = new RPNEvaluator("3 !");
        assertEquals("expr = 3 !, result = 6.0", 6.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator = new RPNEvaluator("3 ! !");
        assertEquals("expr = 3 ! !, result = 720.0", 720.0, rpnEvaluator.evaluateExpression(), 0);
    }

    @Test
    public void shouldEvaluateToCorrectResult() {
        rpnEvaluator = new RPNEvaluator("5 1 2 + 4 * + 3 -");
        assertEquals("expr = 5 1 2 + 4 * + 3 -, result = 14.0",14.0, rpnEvaluator.evaluateExpression(), 0);
    }

    @Test
    public void shouldThrowExceptionWithInvalidExpressionMessage() {
        rpnEvaluator = new RPNEvaluator("^ 5");

        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Invalid Expression");

        rpnEvaluator.evaluateExpression();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() {
        rpnEvaluator = new RPNEvaluator("");
        rpnEvaluator.evaluateExpression();
    }

    @Test
    public void shouldThrowExceptionWithMessageInvalidExpressionCharacter() {
        rpnEvaluator = new RPNEvaluator("@ $ #");

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid Expression Character");

        rpnEvaluator.evaluateExpression();
    }
}