public class Exercise3 {
    public static float[] removeDuplicationsFromArray (float[] arrayToRemoveFrom){
        int duplicatesCounter = getAmountOfRepetitions(arrayToRemoveFrom);
        boolean duplicated;
        float[] arrayAfterDuplicatesRemoval = new float[arrayToRemoveFrom.length - duplicatesCounter];
        duplicatesCounter = 0;
        for (int i = 0; i < arrayToRemoveFrom.length; i++){
            duplicated = false;
            for (int j = 0; j < arrayAfterDuplicatesRemoval.length; j++){
                if (arrayToRemoveFrom[i] == arrayAfterDuplicatesRemoval[j]){
                    duplicated = true;
                    duplicatesCounter++;
                    break;
                }
            }
            if (!duplicated){
                arrayAfterDuplicatesRemoval[i - duplicatesCounter] = arrayToRemoveFrom[i];
            }
        }
        return arrayAfterDuplicatesRemoval;
    }
    public static int getAmountOfRepetitions (float[] arrayToCountRepetitions){
        int duplicatesCounter = 0;
        boolean duplicated;
        String duplicatedNumberDuplicationPrevention = "";
        String containingCheck;
        for (int i =0; i < arrayToCountRepetitions.length - 1; i++){
            duplicated = false;
            containingCheck = String.valueOf(arrayToCountRepetitions[i]);
            if (duplicatedNumberDuplicationPrevention.contains(containingCheck)){
                continue;
            }
            for (int j = i + 1; j < arrayToCountRepetitions.length; j++){
                if (arrayToCountRepetitions[i] == arrayToCountRepetitions[j]){
                    duplicatesCounter++;
                    duplicated = true;
                }
            }
            if (duplicated){
                duplicatedNumberDuplicationPrevention = duplicatedNumberDuplicationPrevention + containingCheck;
            }
        }
        return duplicatesCounter;
    }

    public static void main(String[] args) {
        float[] check = {5, 8, 5, 1, 4, 4, 5, 1, 85, 6, 1, 1, 7};
        float[] newArray = removeDuplicationsFromArray(check);
        for (int i = 0; i < newArray.length; i++){
            System.out.println(newArray[i]);
        }
    }
}
