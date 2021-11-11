package by.epam.andrewpertsev.parser_arithmetic;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class SyntaxAnalyzer {
    public static void main(String[] args) throws ParserException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser syntaxAnalyzer = new Parser();

        for (; ; ) {
            try {
                System.out.print("Введите выражение для вычисления\n-> ");
                String str = reader.readLine();
                if (str.equals(""))
                    break;
                double result = syntaxAnalyzer.evaluate(str);

                DecimalFormatSymbols s = new DecimalFormatSymbols();
                s.setDecimalSeparator('.');
                DecimalFormat f = new DecimalFormat("#,###.00", s);


                System.out.printf("%s = %s%n", str, f.format(result));

                System.out.println(" resu" + result);

            } catch (ParserException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}

