# Basic Reverse Polish Notation (RPN) Calculator
Basic Reverse Polish Notation (RPN) Calculator
The RPN notation is also known as postfix expression. The capabilities of the Calculator are as follows:
1. Supports operations for sum, difference, division, multiplication.
2. Supports floating-point operations.
3. Use '^' for an exponent operator. values entered as "X Y ^" are calculated as X to the power of Y.
4. Use '%' for the percent operation. values entered as "X %" are calculated as X/100.
5. Use '!' for Factorial (unary operation).


After cloning locally, perform below commands in terminal
>
> git clone https://github.com/omegaultron/rpn-calculator.git
> cd rpn-calculator
> gradle build 
> java -jar build/libs/RPNCalculator-1.0-SNAPSHOT.jar

#Sample Input and Output
> Enter space separated RPN expression : 1 2 3 + -
> RPN expression output : -4.0
