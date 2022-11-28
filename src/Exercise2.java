public class Exercise2 {
    public static boolean containsNumbersOnly (String toCheck) {
        boolean numbersOnly = true;
        String numericalCompareTool = "0123456789";
        String check;
        for (int i = 0; i < toCheck.length(); i++) {
            check = toCheck.substring(i, (i+1));
            if (!numericalCompareTool.contains(check)) {
                numbersOnly = false;
                break;
            }
        }
        return numbersOnly;
    }

    public static String phoneNumberNormalizing (String phoneNumber){
        final int SHORT_FORM_LENGTH = 10;
        final int LONG_PREFIX_FORM_LENGTH = 12;
        String firstPrefixForm = "05";
        String secondPrefixForm = "9725";
        char allowedDivider = '-';
        char tempCharToCompare;
        String resultOfNormalizing = "";
        String comparingTempString;
        String modifiedPhoneNumber;
        if (phoneNumber.length() <= LONG_PREFIX_FORM_LENGTH && phoneNumber.length() >= SHORT_FORM_LENGTH) {
            if (phoneNumber.length() == LONG_PREFIX_FORM_LENGTH) {
                comparingTempString = phoneNumber.substring(0, 4);
                if (secondPrefixForm.equals(comparingTempString)) {
                    comparingTempString = phoneNumber.substring(4, 12);
                    if (containsNumbersOnly(comparingTempString)) {
                        modifiedPhoneNumber = phoneNumber.replace("972", "0");
                        resultOfNormalizing = (modifiedPhoneNumber.substring(0, 3) + allowedDivider + modifiedPhoneNumber.substring(3, 10));
                    }
                }
            } else {
                comparingTempString = phoneNumber.substring(0, 2);
                if (firstPrefixForm.equals(comparingTempString)) {
                    if (phoneNumber.length() == SHORT_FORM_LENGTH) {
                        comparingTempString = phoneNumber.substring(2, 10);
                        if (containsNumbersOnly(comparingTempString)) {
                            resultOfNormalizing = (phoneNumber.substring(0, 3) + allowedDivider + phoneNumber.substring(3, 10));
                        }
                    } else {
                        tempCharToCompare = phoneNumber.charAt(3);
                        if (allowedDivider == tempCharToCompare) {
                            comparingTempString = (phoneNumber.charAt(2) + phoneNumber.substring(4, 11));
                            if (containsNumbersOnly(comparingTempString)) {
                                resultOfNormalizing = phoneNumber;
                            }
                        }
                    }
                }
            }
        }
        return resultOfNormalizing;
    }
}

