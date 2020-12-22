import java.util.EmptyStackException;
import java.util.Stack;

public class RPNEvaluator {
    String rpnExpr;

    public RPNEvaluator(String rpnExpr) {
        this.rpnExpr = rpnExpr;
    }

    public Double evaluateExpression() {
        Stack<Double> stack = new Stack<>();
        double firstOperand, secondOperand;
        for (String token : this.rpnExpr.split(" ")){
            try {
                switch (token) {
                    case "*":
                        secondOperand = stack.pop();
                        firstOperand = stack.pop();
                        stack.push(firstOperand * secondOperand);
                        break;
                    case "/":
                        secondOperand = stack.pop();
                        firstOperand = stack.pop();
                        stack.push(firstOperand / secondOperand);
                        break;
                    case "-":
                        secondOperand = stack.pop();
                        firstOperand = stack.pop();
                        stack.push(firstOperand - secondOperand);
                        break;
                    case "+":
                        secondOperand = stack.pop();
                        firstOperand = stack.pop();
                        stack.push(firstOperand + secondOperand);
                        break;
                    case "^":
                        secondOperand = stack.pop();
                        firstOperand = stack.pop();
                        stack.push(Math.pow(firstOperand, secondOperand));
                        break;
                    case "%":
                        stack.push(stack.pop() / 100);
                        break;
                    case "!":
                        stack.push(calculateFactorial(stack.pop()));
                        break;
                    default:
                        try {
                            stack.push(Double.parseDouble(token));
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Invalid Expression Character");
                        }
                }
            } catch (EmptyStackException ese) {
                throw new RuntimeException("Invalid Expression");
            }
        }
        if (stack.size() > 1) {
            throw new RuntimeException("Invalid Expression");
        }
        return stack.pop();
    }

    public double calculateFactorial(double n) {
        double fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
}
