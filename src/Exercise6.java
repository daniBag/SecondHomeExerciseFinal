import java.util.Scanner;

public class Exercise6 {
    public static boolean acceptedValueOfParameter(String check) {
        boolean acceptedValue = true;
        String digitsValidation = "0123456789";
        char currentChar;
        if (check.length() > 0){
            for (int i = 0; i < check.length(); i++) {
                currentChar = check.charAt(i);
                if (!digitsValidation.contains(currentChar + "")) {
                    acceptedValue = false;
                    break;
                }
            }
        }
        return acceptedValue;
    }
    //ax^2-bx+c=0
    public static boolean inputValidation (String userInput){
         boolean valid = false;
         String mandatoryEnding = "=0";
         String secondDegree = "x^2";
         String firstDegree = "x";
         String arithmeticActions = "+-";
         String tempString;
         String tempCheck;
         if (userInput.endsWith(mandatoryEnding)){
             if (userInput.contains(secondDegree)){
                 tempCheck = userInput.substring(0, userInput.indexOf(secondDegree));
                 if (acceptedValueOfParameter(tempCheck) || tempCheck.equals("-")){
                     tempCheck = userInput.substring(userInput.indexOf(secondDegree) + 3, userInput.indexOf(secondDegree) + 4);
                     if (arithmeticActions.contains(tempCheck)){
                         tempString = userInput.substring(userInput.indexOf(secondDegree) + 4);
                         if (tempString.contains(firstDegree)){
                             tempCheck = tempString.substring(0, tempString.indexOf(firstDegree));
                             if (acceptedValueOfParameter(tempCheck)){
                                 tempCheck = tempString.substring(tempString.indexOf(firstDegree) + 1, tempString.indexOf(firstDegree) + 2);
                                 if (arithmeticActions.contains(tempCheck)){
                                     tempCheck = tempString.substring(tempString.indexOf(firstDegree) + 2, tempString.indexOf(mandatoryEnding));
                                     if (acceptedValueOfParameter(tempCheck)){
                                         valid = true;
                                     }
                                 }
                             }
                         }
                     }
                 }
             }
         }
         return valid;
    }
    public static int extractParameterA (String userInput){
        int aParameter;
        String temp = userInput.substring(0, userInput.indexOf("x"));
        aParameter = numericValueOf(temp);
        return aParameter;
    }
    public static int extractParameterB (String userInput){
        int bParameter;
        String temp = userInput.substring(userInput.indexOf("^") + 3);
        temp = temp.substring(0, temp.indexOf("x"));
        bParameter = numericValueOf(temp);
        return bParameter;
    }
    public static int extractParameterC (String userInput){
        int cParameter;
        String temp = userInput.substring(userInput.indexOf("^") + 3);
        temp = temp.substring(temp.indexOf("x") + 2, temp.indexOf("="));
        cParameter = numericValueOf(temp);
        return cParameter;
    }
    public static int numericValueOf(String string){
        int numericValue;
        if (string.equals("-")){
            numericValue = -1;
        }else if (string.equals("")){
            numericValue = 1;
        }else{
            numericValue = Integer.valueOf(string);
        }
        return numericValue;
    }
    public static void quadraticEquation (int aParameter, int bParameter, int cParameter){
        double delta = Math.pow(bParameter, 2)-(4 * aParameter * cParameter);
        if (delta > 0) {
            double squareRoot = Math.sqrt(delta);
            double xFirst = (((-1) * bParameter) + squareRoot) / (2 * aParameter);
            double xSecond = (((-1) * bParameter) - squareRoot) / (2 * aParameter);
            System.out.println("There are 2 available solutions: " + xFirst + " and " + xSecond);
        } else if (delta == 0) {
            System.out.println("There is only one available solution: " + (((-1) * bParameter) / (2 * aParameter)));
        } else {
            System.out.println("Unfortunately, there are no available solutions.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a phrase in a form of quadratic equation (ax^2+bx+c=0): ");
        String possibleEquation = scanner.nextLine();
        if (inputValidation(possibleEquation)){
            int aParameter = extractParameterA(possibleEquation);
            int bParameter = extractParameterB(possibleEquation);
            int cParameter = extractParameterC(possibleEquation);
            quadraticEquation(aParameter, bParameter, cParameter);
        }else{
            System.out.println("We're sorry, but the phrase you've inserted was not a quadratic equation.");
        }
    }
}


