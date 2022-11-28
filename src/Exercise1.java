import java.util.Scanner;

public class Exercise1 {
    public static void printAboveAverageValues(){
        Scanner scanner = new Scanner(System.in);
        float[] numbersForAverage = new float[10];
        float sum = 0;
        for (int i = 0; i < numbersForAverage.length; i++){
            System.out.println("Please enter your number: ");
            numbersForAverage[i] = scanner.nextFloat();
            sum += numbersForAverage[i];
        }
        float average = (sum / numbersForAverage.length);
        System.out.println("These numbers are bigger then the average: ");
        for (int i = 0; i < numbersForAverage.length; i++){
            if (numbersForAverage[i] > average){
                System.out.println(numbersForAverage[i]);
            }
        }
    }

    public static void main(String[] args) {
        printAboveAverageValues();
    }
}
