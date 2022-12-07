import java.util.Random;
import java.util.Scanner;

public class Exercise7 {
    public static String generateSecret (){
        Random random = new Random();
        final int RANDOM_BOUND = 6;
        final int SECRET_LENGTH = 4;
        String secret = "";
        int currentDigit;
        do{
        currentDigit = random.nextInt(RANDOM_BOUND) + 1;
        if (!secret.contains(currentDigit + "")){
            secret = secret + currentDigit;
        }
        }while (secret.length() < SECRET_LENGTH);
        return secret;
    }
    public static boolean checkGuess (String secret, String guess){
        boolean correct = false;
        if (secret.equals(guess)){
            correct = true;
            System.out.println("YOU ARE THE WORLD CHAMPION!!!!");
        }else{
            int accurateCounter = 0;
            int partlyAccurateCounter = 0;
            boolean correctDigits = false;
            for (int i = 0; i < secret.length(); i++){
                if (secret.contains(guess.charAt(i) + "")){
                    if (secret.indexOf(guess.charAt(i)) == i){
                        correctDigits = true;
                        accurateCounter++;
                    }else{
                        correctDigits = true;
                        partlyAccurateCounter++;
                    }
                }
            }
            if (correctDigits){
                if (accurateCounter > 0 && partlyAccurateCounter > 0){
                    System.out.println("You've guessed " + accurateCounter + " accurate guesses and " + partlyAccurateCounter + " partly accurate guesses.");
                }else if (accurateCounter > 0){
                    System.out.println("You've guessed " + accurateCounter + " accurate guesses");
                }else {
                    System.out.println("You've guessed " + partlyAccurateCounter + " partly accurate guesses.");
                }
            }else{
                System.out.println("No digits from your guess are in the secret.");
            }
        }
        return correct;
    }
    public static String getUsersGuess (){
        Scanner scanner = new Scanner(System.in);
        final int GUESS_LENGTH = 4;
        final int GUESS_MIN_BOUND = 1;
        final int GUESS_MAX_BOUND = 6;
        String usersGuess = "";
        boolean valid = false;
        System.out.println("Please enter your guess: ");
        usersGuess = scanner.nextLine();
        if (usersGuess.length() == GUESS_LENGTH){
            if (inputRangeValidation(usersGuess, GUESS_MIN_BOUND, GUESS_MAX_BOUND)){
                valid = true;
            }
        }
        while (!valid){
            System.out.println("ERROR! Make sure to enter a code of 4 digits in the range of 1-6: ");
            usersGuess = scanner.nextLine();
            if (usersGuess.length() == GUESS_LENGTH){
                if (inputRangeValidation(usersGuess, GUESS_MIN_BOUND, GUESS_MAX_BOUND)){
                    valid = true;
                }
            }

        }

        return usersGuess;
    }
    public static boolean inputRangeValidation (String input, int minBound, int maxBound){
        boolean valid = true;
        int tempDigit = 0;
        for (int i = 0; i < input.length(); i++) {
            tempDigit = Integer.parseInt(String.valueOf(input.charAt(i)));
            if (tempDigit < minBound || tempDigit > maxBound) {
                valid = false;
                break;
            }
        }
        return valid;
    }
    public static boolean checkForDuplicates(String usersGuess){
        boolean found = false;
        String duplicationCheck = "";
        for (int i = 0; i < usersGuess.length(); i++){
            if (duplicationCheck.contains(usersGuess.charAt(i) + "")){
                found = true;
                break;
            }
            duplicationCheck = duplicationCheck + usersGuess.charAt(i);
        }
        return found;
    }
    public static boolean turnManager (String secret, int maxGuesses, boolean amountVisible){
        boolean gameOver;
        boolean penalty;
        final int PENALTY = 2;
        String usersGuess;
        int guessCounter = 0;
        do {
            do {
                usersGuess = getUsersGuess();
                penalty = checkForDuplicates(usersGuess);
                if (penalty){
                    maxGuesses -= PENALTY;
                    System.out.print("Because of a duplication in your guess you're fined by 2 guesses ");
                    if (amountVisible){
                        System.out.println("and your remaining amount of guesses are " + (maxGuesses - guessCounter) + " guesses.");
                    }else{
                        System.out.println();
                    }
                }
                }while (penalty);
            guessCounter++;
            gameOver = checkGuess(secret, usersGuess);
            if (amountVisible && !gameOver){
                System.out.println("You have " + (maxGuesses - guessCounter) + " more guesses.");
            }
        }while(!gameOver && guessCounter < maxGuesses);
        if (!gameOver) {
            System.out.println("GAME OVER! The secret code was: " + secret);
        }
        return gameOver;
    }
    public static int getDifficulty (){
        Scanner scanner = new Scanner(System.in);
        final int MIN_BOUND = 1;
        final int MAX_BOUND = 4;
        int difficulty;
        do {
            System.out.println("Please choose your desired difficulty level: ");
            difficulty = scanner.nextInt();
        }while (difficulty < MIN_BOUND || difficulty > MAX_BOUND);
        return difficulty;
    }
    public static void printRules () {
        System.out.println("Welcome to the Code Guessing Game! Here you get to guess a 4-digit code: ");
        System.out.println("Each digit of the code can range from 1-6, while a digit can't repeat itself. For example, 1345 and 5163 are legitimate codes.");
        System.out.println("On the contrary, 9856 or 1225 aren't.");
        System.out.println("Each turn you'll get to guess the code. Be aware, the same rules that applies on the code- apply on your guesses: ");
        System.out.println("Every time you'll enter a digit twice in the same guess- you'll get fined by 2 guessing chances.");
        System.out.println("You'll get a message showing how many digits you've guessed accurately, or partly accurate.");
        System.out.println("********************************************");
        System.out.println("You can choose from 4 difficulty levels: ");
        System.out.println("1. Easy ride: you get 20 chances to guess the code.");
        System.out.println("2. Moderate: you get 15 chances to guess the code.");
        System.out.println("3. Hard: you get 10 chances to guess the code.");
        System.out.println("4. Surprise Box: you'll get a random amount of chances that'll range between 5-25.");
        System.out.println("   In these mode you won't know how many chances you've got until they're finished.");
        System.out.println("********************************************");
    }

    public static void main(String[] args) {
        Random random = new Random();
        String secret = generateSecret();
        printRules();
        int difficulty = getDifficulty();
        boolean amountVisible = true;
        final int EASY_GUESSES_MAX = 20;
        final int MODERATE_GUESSES_MAX = 15;
        final int HARD_GUESSES_MAX = 10;
        final int EASY = 1;
        final int MODERATE = 2;
        final int HARD = 3;
        final int RANDOM_MAX = 21;
        final int RANDOM_MIN = 5;
        int surpriseBox = random.nextInt(RANDOM_MAX) + RANDOM_MIN;
        if (difficulty == EASY){
            System.out.println("You have " + EASY_GUESSES_MAX + " more guesses.");
            turnManager(secret, EASY_GUESSES_MAX, amountVisible);
        } else if (difficulty == MODERATE) {
            System.out.println("You have " + MODERATE_GUESSES_MAX + " more guesses.");
            turnManager(secret, MODERATE_GUESSES_MAX, amountVisible);
        }else {
            if (difficulty == HARD){
                System.out.println("You have " + HARD_GUESSES_MAX + " more guesses.");
                turnManager(secret, HARD_GUESSES_MAX, amountVisible);
            }else{
                amountVisible = false;
                turnManager(secret, surpriseBox, amountVisible);
            }
        }
    }
}
