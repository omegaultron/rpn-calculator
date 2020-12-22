import java.util.Scanner;

public class RPNCalculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter space separated RPN expression : ");
        String enteredExpression = scanner.nextLine();
        scanner.close();

        RPNEvaluator rpnEvaluator = new RPNEvaluator(enteredExpression);
        double exprResult = rpnEvaluator.evaluateExpression();
        System.out.println("RPN expression output : " + exprResult);

    }
}