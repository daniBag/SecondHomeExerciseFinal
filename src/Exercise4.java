public class Exercise4 {
    public static int checkForAscendingThenDescendingAndReturnsPeak (float[] arrayToCheck){
        final int INVALID = -1;
        int peakIndex = INVALID;
        boolean passedPeak = false;
        for (int i = 0; i < arrayToCheck.length - 1; i++){
            if (passedPeak){
                if (arrayToCheck[i] <= arrayToCheck[i + 1]){
                    peakIndex = INVALID;
                    break;
                }
            }else{
                if (arrayToCheck[i] == arrayToCheck[i +1]){
                    peakIndex = INVALID;
                    break;
                } else if (arrayToCheck[i] > arrayToCheck[i + 1]) {
                    peakIndex = i;
                    passedPeak = true;
                }
            }
        }
        return peakIndex;
    }
}
