/**
 * ECE 325 - Fall 2020 <br/>
 * Assignment 3: Exception handling <br />
 * Calculator using BNF
 * <p>
 * @author Terry Wan
 */
import java.util.HashMap;

public class Calculator 
{
    //create a hashmap called var to store the expressions
	public HashMap<String, Integer> var = new HashMap<String, Integer>();

    //simply math according to the operators and variables given
	public int doMath(String operators, int a, int b) {
        int ans = -1;
        if (operators == "\\+")
            ans = a+b;
        else if (operators == "\\*")
            ans = a*b;
        else if (operators == "\\-")
            ans =a-b;
        else if (operators == "\\^")
            ans = (int)Math.pow(a,b);
		return ans;
	}

    //evalueate the expressions
    public int evaluate(String exp) 
    {
        // return for cases of integer, variable, or a variable with a - sign
        if (exp.matches("-?\\d+")) 
        {
            return Integer.parseInt(exp);						
        }
        else if (exp.matches("[a-zA-Z]")) 
        {												
			return var.get(exp);
        }
        
        // Iterate for every operator
        String[] operators = { "\\=", "\\+", "\\-", "\\*", "\\^" };
        for (String op : operators) 
        {
            //split the string into substrings with operators
            //these substrings are the variables
            String[] vars = exp.split(op, 2);

            //only evaluate if there's two vaiables
            if (vars.length == 2) 
            {
                if (op.equals("\\=")) 
                {
					String name = vars[0];
					name = name.substring(name.length()-1, name.length());

					int val = execExpression(vars[1]);
					var.put(name, val);
					return val;
				}

				// evaluate for the result recursively 
				return doMath(op, evaluate(vars[0]), evaluate(vars[1]));
			}
		}

		return -1;
	}

	/**
	 * Execute the expression, and return the correct value
	 * @param exp	{@code String} the expression string
	 * @return		{@code int}    the value of the expression
	 */
	public int execExpression(String exp) {
        //avoid errors with signs
        exp = exp.replace(" ", ""); 
        exp = exp.replace(";", "");
		exp = exp.replace("-", "+-");
        
        // do expression inside the brackets
        while (exp.indexOf("(") != -1)
        {
            int i = exp.indexOf("(");
            int level = 1;
            int j = i+1;

            for (; j < exp.length(); ++j) 
            {
                if (exp.charAt(j) == '(') 
                    ++level;
                else if (exp.charAt(j) == ')') 
                    --level;
                if (level == 0) 
                    break;
			}
			String inner = exp.substring(i+1, j);
			int val = execExpression(inner);

			exp = exp.substring(0, i) + Integer.toString(val) + exp.substring(j+1, exp.length());   
		}
        
		return evaluate(exp);
    }


    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        // Part 1
        String[] inputs = {
            "let x = 1;",                                                                           // 1, returns 1
            "(let x = 1) + x;",                                                                     // 2, returns 2
            "(let a = 2) + 3 * a - 5;",                                                             // 3, returns 3
            "(let x = (let y = (let z = 1))) + x + y + z;",                                         // 4, returns 4
            "1 + (let x = 1) + (let y = 2) + (1 + x) * (1 + y) - (let x = y) - (let y = 1) - x;",   // 5, returns 5
            "1 + (let a = (let b = 1) + b) + a + 1;",                                               // 6, returns 6
            "(let a = (let a = (let a = (let a = 2) + a) + a) + a) - 9;",                           // 7, returns 7
            "(let x = 2) ^ (let y = 3);",                                                           // 8, returns 8
            "(let y = 3) ^ (let x = 2);"                                                            // 9, returns 9
        };
        for (int i = 0; i < inputs.length; i++)
            System.out.println(String.format("%d -- %-90s %d", i+1, inputs[i], calc.execExpression(inputs[i])));

        }
    }

