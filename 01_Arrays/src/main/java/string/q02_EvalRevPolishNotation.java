package string;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem:
 *  Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Links:
 *  https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
 *  https://www.programcreek.com/2012/12/leetcode-evaluate-reverse-polish-notation/
 */
public class q02_EvalRevPolishNotation {
    public static void main(String[] args) throws IOException {
        q02_EvalRevPolishNotation rpn = new q02_EvalRevPolishNotation();
        String[] tokens = new String[] { "2", "1", "+", "4", "*", "2", "/"};
        System.out.println(rpn.evalRPN(tokens));
    }

    public String evalRPN(String[] tokens) {
        int returnValue = 0;
        String operators = "+-*/";
        Deque<String> stack = new LinkedList<>();

        for(String str : tokens) {
            //push to stack if it is a number
            if(!operators.contains(str)) {
                stack.push(str);
            }
            else {
                //pop numbers from stack if it is an operator
                int number2 = Integer.valueOf(stack.pop());
                int number1 = Integer.valueOf(stack.pop());
                switch (str) {
                    case "+":
                        stack.push(String.valueOf(number1 + number2));
                        break;
                    case "-":
                        stack.push(String.valueOf(number1 - number2));
                        break;
                    case "*":
                        stack.push(String.valueOf(number1 * number2));
                        break;
                    case "/":
                        stack.push(String.valueOf(number1 / number2));
                        break;
                }
            }
        }
        return stack.pop();
    }


}
