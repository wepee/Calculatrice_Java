package calcutils;

import java.util.ArrayList;
import java.util.List;

public class CalcUtils {

    private List<Float> numbers = new ArrayList<>();
    private List<Operator> operators = new ArrayList<>();
    private boolean operationIsNeeded = false;

    public CalcUtils() {
    }

    public static void main(String[] args) {
        //tests
        CalcUtils cal = new CalcUtils();
        cal.add(2);
        cal.add(Operator.PLUS);
        cal.add(3);
        cal.add(Operator.DIVIDE);
        cal.add(0);
        cal.calculate();
    }


    /**
     * Add a number in the calc Stack
     * @param number to add in the calc Stack
     */
    public void add(float number) {
        try {
            if (operationIsNeeded)
                throw new Exception("2 nombres de suite");
            else {
                numbers.add(number);
                operationIsNeeded = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Add a string of a number in the calc Stack
     * @param number to add in the calc Stack
     */
    public void add(String number) {
        this.add(Float.parseFloat(number));
    }

    public void add(Operator operator) {
        try {
            if (!operationIsNeeded)
                throw new Exception("2 operations de suite");
            else {
                operators.add(operator);
                operationIsNeeded = false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @return String which contain the result of the Stack
     */
    public String calculate() {

        try {
            if (!operationIsNeeded)
                throw new Exception("You need to specify one more number");

            float result = numbers.get(0);

            for (int i = 1; i < numbers.size(); i++) {
                switch (operators.get(i - 1)) {
                    case PLUS -> {
                        result = result + numbers.get(i);
                        break;
                    }
                    case MINUS -> {
                        result = result - numbers.get(i);
                        break;
                    }
                    case MULTIPLY -> {
                        result = result * numbers.get(i);
                        break;
                    }
                    case DIVIDE -> {
                        if (numbers.get(i) == 0)
                            throw new Exception("YOU CANNOT DIVIDE BY 0");
                        result = result / numbers.get(i);
                        break;
                    }
                    case MODULO -> {
                        if (numbers.get(i) == 0)
                            throw new Exception("YOU CANNOT DIVIDE BY 0");
                        result = result % numbers.get(i);
                        break;
                    }
                }
            }


            System.out.println("Le resultat est " + result);
            return String.valueOf(result);

        } catch (Exception e) {
            System.out.println(e.toString());
            return e.toString();
        }
    }

    /**
     * Calculate one number operation
     * @param number param of the operation
     * @param spe operation special (only one param)
     * @return result of operation
     */
    public static String specialCalcul(double number, Special spe) {


        switch (spe) {
            case COS -> {
                number = Math.cos(number);
                break;
            }
            case SIN -> {
                number = Math.sin(number);
                break;
            }
            case TAN -> {
                number = Math.tan(number);
                break;
            }
            case FACT -> {

                int fact = 1;

                for (int i = 1; i <= number; i++)
                    fact *= i;

                number = fact;

                break;

            }
            case SQRT -> {
                number = Math.sqrt(number);
                break;
            }
        }
        return String.valueOf(number);
    }

    /**
     * @param number String of the param number
     * @param spe operation special (only one param)
     * @return result of operation
     */
    public String specialCalcul(String number, Special spe) {
        return specialCalcul(Float.parseFloat(number), spe);
    }
}