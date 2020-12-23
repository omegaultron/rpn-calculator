import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@FunctionalInterface
interface BinaryOperation {
    double binaryAction(double firstOperand, double secondOperand);
}

@FunctionalInterface
interface UnaryOperation {
    double unaryAction(double operand);
}

public class RPNEvaluator {
    String rpnExpr;

    public static Map<String, BinaryOperation> binaryOperationMap = new HashMap<>();
    public static Map<String, UnaryOperation> unaryOperationMap = new HashMap<>();

    static {
        binaryOperationMap.put("+", (a, b) -> b + a);
        binaryOperationMap.put("-", (a, b) -> b - a);
        binaryOperationMap.put("*", (a, b) -> b * a);
        binaryOperationMap.put("/", (a, b) -> b / a);
        binaryOperationMap.put("^", (a, b) -> Math.pow(b, a));

        unaryOperationMap.put("!", a -> calculateFactorial(a));
        unaryOperationMap.put("%", a -> a / 100);
    }

    public RPNEvaluator(String rpnExpr) {
        this.rpnExpr = rpnExpr;
    }

    public Double evaluateExpression() {
        Stack<Double> stack = new Stack<>();
        for (String token : this.rpnExpr.split(" ")){
            try {
                if (binaryOperationMap.containsKey(token)) {
                    stack.push(binaryOperationMap.get(token).binaryAction(stack.pop(), stack.pop()));
                } else if (unaryOperationMap.containsKey(token)) {
                    stack.push(unaryOperationMap.get(token).unaryAction(stack.pop()));
                } else {
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

    public static double calculateFactorial(double n) {
        double fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
}
