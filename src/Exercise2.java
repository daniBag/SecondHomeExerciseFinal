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
        String prefixFirstForm = "05";
        String prefixSecondForm = "9725";
        char allowedDivider = '-';
        char tempCharToCompare;
        String resultOfNormalizing = "";
        String tempStringToCompareAndModify;
        String modifiedPhoneNumber;
        if (phoneNumber.length() <= LONG_PREFIX_FORM_LENGTH && phoneNumber.length() >= SHORT_FORM_LENGTH) {
            if (phoneNumber.length() == LONG_PREFIX_FORM_LENGTH) {
                tempStringToCompareAndModify = phoneNumber.substring(0, 4);
                if (prefixSecondForm.equals(tempStringToCompareAndModify)) {
                    tempStringToCompareAndModify = phoneNumber.substring(4, 12);
                    if (containsNumbersOnly(tempStringToCompareAndModify)) {
                        modifiedPhoneNumber = phoneNumber.replace("972", "0");
                        resultOfNormalizing = (modifiedPhoneNumber.substring(0, 3) + allowedDivider + modifiedPhoneNumber.substring(3, 10));
                    }
                }
            } else {
                tempStringToCompareAndModify = phoneNumber.substring(0, 2);
                if (prefixFirstForm.equals(tempStringToCompareAndModify)) {
                    if (phoneNumber.length() == SHORT_FORM_LENGTH) {
                        tempStringToCompareAndModify = phoneNumber.substring(2, 10);
                        if (containsNumbersOnly(tempStringToCompareAndModify)) {
                            resultOfNormalizing = (phoneNumber.substring(0, 3) + allowedDivider + phoneNumber.substring(3, 10));
                        }
                    } else {
                        tempCharToCompare = phoneNumber.charAt(3);
                        if (allowedDivider == tempCharToCompare) {
                            tempStringToCompareAndModify = (phoneNumber.charAt(2) + phoneNumber.substring(4, 11));
                            if (containsNumbersOnly(tempStringToCompareAndModify)) {
                                resultOfNormalizing = phoneNumber;
                            }
                        }
                    }
                }
            }
        }
        return resultOfNormalizing;
    }

    public static void main(String[] args) {
        String check = "972546369855";
        System.out.println(phoneNumberNormalizing(check));
    }
}

