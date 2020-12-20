import org.junit.Test;

import static org.junit.Assert.*;

public class RPNEvaluatorTest {

    @Test
    public void expressionShouldEvaluatesInCorrectResult() throws Exception {
        RPNEvaluator rpnEvaluator = new RPNEvaluator();

        rpnEvaluator.setRpnExpr("1 2 3 + -");
        assertEquals("expr = 1 2 3 + -, result = -4.0",-4.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator.setRpnExpr("6 2 * 3 /");
        assertEquals("expr = 6 2 * 3 /, result = 4.0",4.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator.setRpnExpr("2 3 ^ 4 5 + +");
        assertEquals("expr = 2 3 ^ 4 5 + +, result = 17.0",17.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator.setRpnExpr("50 % 2 *");
        assertEquals("expr = 50 % 2 *, result = 1.0",1.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator.setRpnExpr("3 ! 4 5 * +");
        assertEquals("expr = 3 ! 4 5 * +, result = 26.0",26.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator.setRpnExpr("12 3 / !");
        assertEquals("expr = 12 3 / !, result = 24.0",24.0, rpnEvaluator.evaluateExpression(), 0);

        rpnEvaluator.setRpnExpr("5 1 2 + 4 * + 3 -");
        assertEquals("expr = 5 1 2 + 4 * + 3 -, result = 14.0",14.0, rpnEvaluator.evaluateExpression(), 0);
    }

    @Test
    public void rpnExpressionShouldResultInException() {
        RPNEvaluator rpnEvaluator = new RPNEvaluator();

        rpnEvaluator.setRpnExpr("^ 5");

        try {
            rpnEvaluator.evaluateExpression();
        } catch (Exception e) {
            assertEquals("Invalid RPN expression.", e.getMessage());
        }
    }
}