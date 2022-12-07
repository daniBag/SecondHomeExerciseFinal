import java.util.Scanner;

public class Exercise5 {
    public static void printBoard (char[] toPrint){
        final int ROW_LENGTH = 3;
        for (int i = 0; i < toPrint.length;){
            for (int j = 0; j < ROW_LENGTH; j++, i++){
                System.out.print(toPrint[i]);
            }
            System.out.println();
        }
    }
    public static boolean isAvailable (char[] gameBoard, int requestedPosition){
        boolean available = false;
        if (gameBoard[requestedPosition] == '\u0000'){
            available = true;
        }
        return available;
    }
    public static int getPositionFromUser (char[] gameBoard){
        Scanner scanner = new Scanner(System.in);
        int requestedPosition;
        System.out.println("Please enter your choice of position on the board (a number between 1-9): ");
        requestedPosition = scanner.nextInt();
        boolean acceptable = false;
        do {
            if (requestedPosition >= 1 && requestedPosition <= 9) {
                requestedPosition--;
                if (isAvailable(gameBoard, requestedPosition)){
                    acceptable = true;
                }else{
                    System.out.println("The position you chose is already taken, please pick an empty one: ");
                    requestedPosition = scanner.nextInt();
                }
            }else{
                System.out.println("ERROR! Please make sure to enter a number between 1-9: ");
                requestedPosition = scanner.nextInt();
            }
        }while (!acceptable);
        return requestedPosition;
    }
    public static char checkRows (char[] gameBoard, char firstPlayer, char secondPlayer) {
        final char NOT_FOUND = '-';
        final int POSITION_FIX = 1;
        char victory = NOT_FOUND;
        for (int i = 0; i < 7; i += 3) {
            victory = cellsCompare(gameBoard, i, POSITION_FIX, firstPlayer, secondPlayer, NOT_FOUND);
            if (victory == firstPlayer || victory == secondPlayer){
                break;
            }
        }
        return victory;
    }
    public static char checkColumns (char[] gameBoard, char firstPlayer, char secondPlayer) {
        final char NOT_FOUND = '-';
        final int POSITION_FIX = 3;
        char victory = NOT_FOUND;
        for (int i = 0; i < 3; i++) {
            victory = cellsCompare(gameBoard, i, POSITION_FIX, firstPlayer, secondPlayer, NOT_FOUND);
            if (victory == firstPlayer || victory == secondPlayer){
                break;
            }
        }
        return victory;
    }
    public static char checkDiagonals (char[] gameBoard, char firstPlayer, char secondPlayer) {
        final char NOT_FOUND = '-';
        int i = 0;
        final int POSITION_FIX = (4 - i);
        char victory = NOT_FOUND;
        for (i = 0; i < 3; i += 2) {
            victory = cellsCompare(gameBoard, i, POSITION_FIX, firstPlayer, secondPlayer, NOT_FOUND);
            if (victory == firstPlayer || victory == secondPlayer){
                break;
            }
        }
        return victory;
    }
    public static char cellsCompare (char[] gameBoard, int i, int positionFix, char firstPlayer, char secondPlayer, char notFound){
        char result = notFound;
        if (gameBoard[i] == firstPlayer) {
            if (gameBoard[i + positionFix] == firstPlayer) {
                if (gameBoard[i + (2 * positionFix)] == firstPlayer) {
                    result = firstPlayer;
                }
            }
        } else if (gameBoard[i] == secondPlayer) {
            if (gameBoard[i + positionFix] == secondPlayer) {
                if (gameBoard[i + (2 * positionFix)] == secondPlayer) {
                    result = secondPlayer;
                }
            }
        }
        return result;
    }

        public static char checkWinner (char[] gameBoard) {
            final char FIRST_PLAYER = 'X';
            final char SECOND_PLAYER = 'O';
            final char NOT_FOUND = '-';
            char result = checkDiagonals(gameBoard, FIRST_PLAYER, SECOND_PLAYER);
            if (result == NOT_FOUND) {
                result = checkRows(gameBoard, FIRST_PLAYER, SECOND_PLAYER);
                if (result == NOT_FOUND) {
                    result = checkColumns(gameBoard, FIRST_PLAYER, SECOND_PLAYER);
                }
            }
            return result;
        }
        public static boolean placeSymbolOnBoard (char[] gameBoard, int position, char playerSymbol) {
            boolean gameOver = false;
            final char FIRST_PLAYER = 'X';
            final char SECOND_PLAYER = 'O';
            char checkForWin;
            gameBoard[position] = playerSymbol;
            printBoard(gameBoard);
            checkForWin = checkWinner(gameBoard);
            if (checkForWin == FIRST_PLAYER) {
                System.out.println("The winner is player 1!");
                gameOver = true;
            } else if (checkForWin == SECOND_PLAYER) {
                System.out.println("The winner is player 2!");
                gameOver = true;
            }
            return gameOver;
        }

        public static void main (String[]args) {
            final int BOARD_SIZE = 9;
            final char FIRST_PLAYER = 'X';
            final char SECOND_PLAYER = 'O';
            char[] gameBoard = new char[BOARD_SIZE];
            boolean gameOver;
            int currentPosition;
            int movesCounter = 0;
            do {
                currentPosition = getPositionFromUser(gameBoard);
                if (movesCounter % 2 == 0){
                    gameOver = placeSymbolOnBoard(gameBoard, currentPosition, FIRST_PLAYER);
                }else{
                    gameOver = placeSymbolOnBoard(gameBoard, currentPosition, SECOND_PLAYER);
                }
                movesCounter++;
                if (movesCounter == 9 && !gameOver){
                    gameOver = true;
                    System.out.println("Draw!");
                }
            }while (!gameOver);
        }
}
