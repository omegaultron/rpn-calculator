import java.util.EmptyStackException;
import java.util.Stack;

public class RPNEvaluator {
    String rpnExpr;

    public String getRpnExpr() {
        return rpnExpr;
    }

    public void setRpnExpr(String rpnExpr) {
        this.rpnExpr = rpnExpr;
    }

    public Double evaluateExpression() throws Exception {
        Stack<Double> stack = new Stack<>();
        for (String token : this.rpnExpr.split(" ")){
            try {
                if (token.equals("*")) {
                    double secondOperand = stack.pop();
                    double firstOperand = stack.pop();
                    stack.push(firstOperand * secondOperand);
                } else if (token.equals("/")) {
                    double secondOperand = stack.pop();
                    double firstOperand = stack.pop();
                    stack.push(firstOperand / secondOperand);
                } else if (token.equals("-")) {
                    double secondOperand = stack.pop();
                    double firstOperand = stack.pop();
                    stack.push(firstOperand - secondOperand);
                } else if (token.equals("+")) {
                    double secondOperand = stack.pop();
                    double firstOperand = stack.pop();
                    stack.push(firstOperand + secondOperand);
                } else if (token.equals("^")) {
                    double secondOperand = stack.pop();
                    double firstOperand = stack.pop();
                    stack.push(Math.pow(firstOperand, secondOperand));
                } else if(token.equals("%")) {
                    stack.push(stack.pop() / 100);
                } else if(token.equals("!")) {
                    stack.push(calculateFactorial(stack.pop()));
                }else {
                    try {
                        stack.push(Double.parseDouble(token+""));
                    } catch (NumberFormatException e) {
                        throw new Exception("Invalid expression character.");
                    }
                }
            } catch (EmptyStackException ese) {
                throw new Exception("Invalid RPN expression.");
            }
        }
        if (stack.size() > 1) {
            throw new Exception("Invalid RPN expression.");
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
