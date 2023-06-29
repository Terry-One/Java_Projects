/**
 * Lab 4: Generics <br />
 * The {@code GenericStack} class
 */
import java.util.*;
import java.util.regex.*;
public class GenericStack<T> {

    /**
     * Query the top element
     * @return          {@code T} the top element
     */

    private ArrayList<T> stack = new ArrayList<T> ();
    private int top = 0;
    public T peek() {
        if (this.top==-1) {
            throw new NoSuchElementException("Empty stack!");
        }
        else {
            return stack.get(top-1);
        }
    }

    /**
     * Add a new element as top element
     * @param value     {@code T} the new element
     */
    public void push(T value) {
        stack.add (top++, value);
    }

    /**
     * Remove the top element
     * @return          {@code T} the removed element
     */
    public T pop() {
        return stack.remove (--top);
    }

    /**
     * Query the size of the stack
     * @return          {@code int} size of the element
     */
    public int size() {
        return top;
    }

    /**
     * Check if the stack is empty of not
     * @return          {@code boolean} {@code true} for empty; {@code false} for not
     */
    public boolean isEmpty() {
        return this.top==-1;
    }

    /**
     * Calculate a postfix expression
     * @param exp       {@code String} the postfix expression
     * @return          {@code Double} the value of the expression
     */
    public static Double calcPostfixExpression(String exp) {
        GenericStack<String> stack = new GenericStack<String> ();
        int j=0;
        String[] split = exp.split(" ");
        for (String i : split)
        {
            Pattern p = Pattern.compile("[0-9]");
            Matcher m = p.matcher(i);
            if (m.matches())
            {
                stack.push(i);
            }
            else if (i.equals("+"))
            {  
                Double temp;
                temp = Double.parseDouble(stack.peek());
                stack.pop();
                temp = Double.parseDouble(stack.peek()) + temp;
                stack.pop();
                stack.push(String.valueOf(temp));
            }
            else if (i.equals("-"))
            {  
                Double temp;
                temp = Double.parseDouble(stack.peek());
                stack.pop();
                temp = Double.parseDouble(stack.peek()) - temp;
                stack.pop();
                stack.push(String.valueOf(temp));
            }
            else if (i.equals("*"))
            {  
                Double temp;
                temp = Double.parseDouble(stack.peek());
                stack.pop();
                temp = Double.parseDouble(stack.peek()) * temp;
                stack.pop();
                stack.push(String.valueOf(temp));
            }
            else if (i.equals("/"))
            {  
                Double temp;
                temp = Double.parseDouble(stack.peek());
                stack.pop();
                temp = Double.parseDouble(stack.peek()) / temp;
                stack.pop();
                stack.push(String.valueOf(temp));
            }
            else if (i.equals("^"))
            {  
                Double temp;
                temp = Double.parseDouble(stack.peek());
                stack.pop();
                temp = Math.pow(Double.parseDouble(stack.peek()),temp);
                stack.pop();
                stack.push(String.valueOf(temp));
            }
        }
        return Double.parseDouble(stack.peek());
    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        String[] expressions = {
                "4 1 +",                    // 1: = 5
                "2 6 -",                    // 2: = -4
                "3 3 *",                    // 3: = 9
                "1 4 /",                    // 4: = 0.25
                "2 3 ^",                    // 5: = 8
                "2 1 + 4 * 8 - 3 ^ 6 -",    // 6: 58
        }; // String[] expressions = { ... };
        for (String s: expressions)
            System.out.println(s + " = " + calcPostfixExpression(s));
    }
}
